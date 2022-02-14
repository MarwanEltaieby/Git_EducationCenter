package application.Scenes;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import application.Objects.DatabaseConnector;
import application.Objects.Payment;

public class PaymentSceneController implements Initializable{

	 @FXML
	    private TableColumn<Payment, Integer> CourseIdColumn;

	    @FXML
	    private ComboBox<Integer> CourseIdComboBox;

	    @FXML
	    private TableColumn<Payment, Double> PaymentColumn;

	    @FXML
	    private TableColumn<Payment, String> PaymentDateColumn;

	    @FXML
	    private TableView<Payment> PaymentTableView;

	    @FXML
	    private TextField PaymentTextField;

	    @FXML
	    private TextField PhonenumberTextField;

	    @FXML
	    private TableColumn<Payment, String> StudentNameColumn;

	    @FXML
	    private TableColumn<Payment, String> StudentPhonenumberColumn;
	    
	    private ObservableList<Payment> PaymentList = FXCollections.observableArrayList(); 
	    
	    private ObservableList<Integer> CourseIdList = FXCollections.observableArrayList();
	    
	    private Parent root;
	    
	    private Stage stage;
	    
	    private Scene scene;
	    
	    public void submitPayment(ActionEvent event) {
	    	try {
	    		String phoneNumber = PhonenumberTextField.getText();
	    		int courseID = (int) CourseIdComboBox.getValue();
	    		double payment = Double.parseDouble(PaymentTextField.getText());
	    		String paymentDate = LocalDate.now() + "";
	    		DatabaseConnector connector = new DatabaseConnector();
	    		Connection con = connector.getConnection();
	    		Statement st = con.createStatement();
	    		String sql = "INSERT INTO registered_student_payment VALUE( '" + phoneNumber + "', '" 
	    		+ courseID + "', '" + payment + "', '" + paymentDate + "');";
	    		st.executeUpdate(sql);
	    		root = FXMLLoader.load(getClass().getResource("PaymentScene.fxml"));
	    		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    		scene = new Scene(root);
	    		stage.setScene(scene);
	    		stage.setResizable(false);
	    		stage.show();
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				DatabaseConnector connector = new DatabaseConnector();
				Connection con = connector.getConnection();
				Statement st = con.createStatement();
				String sql = "SELECT course_id FROM courses;";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					int courseID = rs.getInt(1);
					CourseIdList.add(courseID);
				}
				sql = "SELECT student_phonenumber, student_name, course_id, payment, payment_date "
						+ "FROM registered_student_payment JOIN student using(student_phonenumber) ORDER BY payment_date DESC;";
				rs = st.executeQuery(sql);
				while(rs.next()) {
					Payment payment = new Payment();
					payment.setPhoneNumber(rs.getString(1));
					payment.setStudentName(rs.getString(2));
					payment.setCourseID(rs.getInt(3));
					payment.setPayment(rs.getDouble(4));
					payment.setPaymentDate(rs.getString(5));
					PaymentList.add(payment);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			CourseIdComboBox.setItems(CourseIdList);
			PaymentTableView.setItems(PaymentList);
			StudentPhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("phoneNumber"));
			StudentNameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("studentName"));
			CourseIdColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("courseID"));
			PaymentColumn.setCellValueFactory(new PropertyValueFactory<Payment, Double>("payment"));
			PaymentDateColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("paymentDate"));
		}
		
		public void remove(ActionEvent event) {
	    	try {
				DatabaseConnector connector = new DatabaseConnector();
				Connection con = connector.getConnection();
				Statement st = con.createStatement();
				int selectedPayment = PaymentTableView.getSelectionModel().getSelectedIndex();
				Payment selected = PaymentList.get(selectedPayment);
				String sql = "DELETE FROM registered_student_payment WHERE student_phonenumber = '" + selected.getPhoneNumber() + 
						"' AND course_id = '" + selected.getCourseID() + "' AND payment = '" + selected.getPayment() + 
						"' AND payment_date = '" + selected.getPaymentDate() + "';";
				st.executeUpdate(sql);
				PaymentTableView.getItems().remove(selectedPayment);
			}catch(Exception e) {
				e.printStackTrace();
			}
	    }
		
		public void getSecretaryScene(ActionEvent event) {
			try {
				root = FXMLLoader.load(getClass().getResource("SecretaryScene.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}



