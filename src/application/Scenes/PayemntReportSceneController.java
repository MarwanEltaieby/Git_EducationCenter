package application.Scenes;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import application.Objects.DatabaseConnector;
import application.Objects.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PayemntReportSceneController implements Initializable {

	 @FXML
	    private ComboBox<String> CourseNameComboBox;

	    @FXML
	    private TableColumn<Payment, String> CourseNameColumn;

	    @FXML
	    private DatePicker DatePicker1;

	    @FXML
	    private DatePicker DatePicker2;

	    @FXML
	    private TableColumn<Payment, String> NameColumn;

	    @FXML
	    private TableColumn<Payment, Integer> PaymentColumn;

	    @FXML
	    private TableColumn<Payment, String> PaymentDateColumn;

	    @FXML
	    private TableView<Payment> PaymentTableView;

	    @FXML
	    private TableColumn<Payment, String> PhonenumberColumn;

	    @FXML
	    private TextField PhonenumberTextField;

	    @FXML
	    private Label TotalPaymentLabel;
	    
	    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();
	    
	    private ObservableList<String> CourseNameList = FXCollections.observableArrayList();
	    
	    private Parent root;
	    
	    private Scene scene;
	    
	    private Stage stage;

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				CourseNameList.add("All");
				CourseNameComboBox.setValue("All");
				DatabaseConnector connector = new DatabaseConnector();
				Connection con = connector.getConnection();
				Statement st = con.createStatement();
				String paymentSQL = "SELECT student_phonenumber, student_name, course_name, payment, payment_date "
						+ "FROM payment JOIN student USING(student_phonenumber) WHERE payment_date ='"
						+ LocalDate.now() + "';";
				String sql = "SELECT course_name FROM courses;";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String courseName = rs.getString(1);
					CourseNameList.add(courseName);
				}
				
				rs = st.executeQuery(paymentSQL);
				while(rs.next()) {
					Payment payment = new Payment();
					payment.setPhone(rs.getString(1));
					payment.setName(rs.getString(2));
					payment.setCourseName(rs.getString(3));
					payment.setPayment(rs.getInt(4));
					payment.setPaymentDate(rs.getString(5));
					paymentList.add(payment);
				}
				paymentSQL = "SELECT payment FROM payment WHERE payment_date = '" + LocalDate.now() + "';";
				int sum = 0;
				rs = st.executeQuery(paymentSQL);
				while(rs.next()) {
					int payment = rs.getInt(1);
					sum += payment;
				}
				TotalPaymentLabel.setText("( " + sum + " )");
			}catch(Exception e) {
				e.printStackTrace();
				e.getCause();
			}
			CourseNameComboBox.setItems(CourseNameList);
			PaymentTableView.setItems(paymentList);
			PhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("phone"));
			NameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("name"));
			CourseNameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("courseName"));
			PaymentColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("payment"));
			PaymentDateColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("paymentDate"));
		}
		
		public void getAdminScene(ActionEvent event) {
			try {
				root = FXMLLoader.load(getClass().getResource("AdminScene.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}catch(Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}


		public void search(ActionEvent event) {
			try {
				paymentList.clear();
				DatabaseConnector connector = new DatabaseConnector();
				Connection con = connector.getConnection();
				Statement st = con.createStatement();
				DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String phone;
				String courseName;
				String date1;
				String date2;
				String paymentSQL = null;
				int sum = 0;
				String totalPaymentSQL = null;
				//1st condition (all inputs)
			if(!PhonenumberTextField.getText().isEmpty() && CourseNameComboBox.getValue() != "All" 
					&& !DatePicker1.getValue().format(myFormatter).isEmpty() && !DatePicker2.getValue().format(myFormatter).isEmpty()) {
				
				phone = PhonenumberTextField.getText();
				courseName = CourseNameComboBox.getValue();
				LocalDate date = DatePicker1.getValue();
				date1 = date.format(myFormatter);
				date = DatePicker2.getValue();
				date2 = date.format(myFormatter);
				paymentSQL = "SELECT payment.student_phonenumber, student.student_name, payment.course_name, payment.payment, payment.payment_date "
						+ "FROM payment JOIN student USING(student_phonenumber) WHERE student_phonenumber LIKE '" + phone + "%' AND course_name = '" + courseName 
						+ "'AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";

				totalPaymentSQL = "SELECT payment FROM payment JOIN student USING(student_phonenumber) WHERE student_phonenumber LIKE '" 
				+ phone + "%' AND course_name = '" + courseName + "' AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";		
				
				//2nd condition (no phone)			
			} else if(PhonenumberTextField.getText().isEmpty() && CourseNameComboBox.getValue() != "All" 
					&& !DatePicker1.getValue().format(myFormatter).isEmpty() && !DatePicker2.getValue().format(myFormatter).isEmpty()) {
				
				courseName = CourseNameComboBox.getValue();
				LocalDate date = DatePicker1.getValue();
				date1 = date.format(myFormatter);
				date = DatePicker2.getValue();
				date2 = date.format(myFormatter);
				paymentSQL = "SELECT payment.student_phonenumber, student.student_name, payment.course_name, payment.payment, payment.payment_date "
						+ "FROM payment JOIN student USING(student_phonenumber) WHERE course_name = '" + courseName 
						+ "'AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";
				
				totalPaymentSQL = "SELECT payment FROM payment JOIN student USING(student_phonenumber) WHERE course_name = '" + courseName 
						+ "' AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";	
				
				//3rd condition (no course)
			} else if(!PhonenumberTextField.getText().isEmpty() && CourseNameComboBox.getValue() == "All" 
					&& !DatePicker1.getValue().format(myFormatter).isEmpty() && !DatePicker2.getValue().format(myFormatter).isEmpty()) {
				
				phone = PhonenumberTextField.getText();
				courseName = CourseNameComboBox.getValue();
				LocalDate date = DatePicker1.getValue();
				date1 = date.format(myFormatter);
				date = DatePicker2.getValue();
				date2 = date.format(myFormatter);
				paymentSQL = "SELECT payment.student_phonenumber, student.student_name, payment.course_name, payment.payment, payment.payment_date "
						+ "FROM payment JOIN student USING(student_phonenumber) WHERE student_phonenumber LIKE '" + phone + "%' " 
						+ "AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";		
				
				totalPaymentSQL = "SELECT payment FROM payment JOIN student USING(student_phonenumber) WHERE student_phonenumber LIKE '" 
						+ phone + "%' AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";
				
				//4th condition (no course, nor phone)
			} else if(PhonenumberTextField.getText().isEmpty() && CourseNameComboBox.getValue() == "All" 
					&& !DatePicker1.getValue().format(myFormatter).isEmpty() && !DatePicker2.getValue().format(myFormatter).isEmpty()) {
				
				LocalDate date = DatePicker1.getValue();
				date1 = date.format(myFormatter);
				date = DatePicker2.getValue();
				date2 = date.format(myFormatter);
				paymentSQL = "SELECT payment.student_phonenumber, student.student_name, payment.course_name, payment.payment, payment.payment_date "
						+ "FROM payment JOIN student USING(student_phonenumber) WHERE payment_date BETWEEN '" + date1 + "' AND '" + date2 + "';";		

				totalPaymentSQL = "SELECT payment FROM payment JOIN student USING(student_phonenumber) WHERE payment_date BETWEEN '" 
				+ date1 + "' AND '" + date2 + "';";
				
			}
			ResultSet rs = st.executeQuery(paymentSQL);
			while(rs.next()) {
				Payment payment = new Payment();
				String studentPhone = rs.getString(1);
				String studentName = rs.getString(2);
				String course = rs.getString(3);
				int coursePayment = rs.getInt(4);
				String date = rs.getString(5);
				payment.setPhone(studentPhone);
				payment.setName(studentName);
				payment.setCourseName(course);
				payment.setPayment(coursePayment);
				payment.setPaymentDate(date);
				paymentList.add(payment);
			}
			rs = st.executeQuery(totalPaymentSQL);
			while (rs.next()) {
				int x = rs.getInt(1);
				sum += x;
			}
			TotalPaymentLabel.setText("( " + sum + " )");
			CourseNameComboBox.setItems(CourseNameList);
			PaymentTableView.setItems(paymentList);
			PhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("phone"));
			NameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("name"));
			CourseNameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("courseName"));
			PaymentColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("payment"));
			PaymentDateColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("paymentDate"));
				
			}catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
		
		public void exportPDF(ActionEvent event) {
			try {
				Document document = new Document();
				document.setPageSize(PageSize.A4.rotate());
				document.setMargins(-90f, -90f, 10f, 10f);
				PdfWriter.getInstance(document, new FileOutputStream("PDFs\\Payments.pdf"));
				String totalPayment = "                               Total Payement = " + TotalPaymentLabel.getText() + "\n\n";
				Paragraph para = new Paragraph(totalPayment);
				document.open();
				document.add(para);
				
				//add table
				PdfPTable table = new PdfPTable(5);
				PdfPCell c1 = new PdfPCell(new Phrase("Student Phone"));
				c1.setBackgroundColor(BaseColor.GREEN);
				table.addCell(c1);
		
				c1 = new PdfPCell(new Phrase("Student Name"));
				c1.setBackgroundColor(BaseColor.GREEN);
				table.addCell(c1);
				
				c1 = new PdfPCell(new Phrase("CourseName"));
				c1.setBackgroundColor(BaseColor.GREEN);
				table.addCell(c1);
		
				c1 = new PdfPCell(new Phrase("Payment"));
				c1.setBackgroundColor(BaseColor.GREEN);
				table.addCell(c1);
			
				c1 = new PdfPCell(new Phrase("Payment Date"));
				c1.setBackgroundColor(BaseColor.GREEN);
				table.addCell(c1);
			
				for(Payment selected : paymentList) {
					table.addCell(selected.getPhone());
					table.addCell(selected.getName());
					table.addCell(selected.getCourseName());
					table.addCell(Integer.toString(selected.getPayment()));
					table.addCell(selected.getPaymentDate());
				}
				document.add(table);
				document.close();
			} catch (DocumentException e) {
					e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	    
}
