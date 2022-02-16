package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.Objects.DatabaseConnector;
import application.Objects.RegisteredStudent;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SecretarySceneController implements Initializable {
	
	 @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance10Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance1Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance2Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance3Column;
	    
	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance4Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance5Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance6Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance7Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance8Column;

	    @FXML
	    private TableColumn<RegisteredStudent, CheckBox> Attendance9Column;

	    @FXML
	    private ChoiceBox<String> CourseChoiceBox;

	    @FXML
	    private TableColumn<RegisteredStudent, String> NameColumn;

	    @FXML
	    private TableColumn<RegisteredStudent, TextField> PaymentColumn;

	    @FXML
	    private TableColumn<RegisteredStudent, String> PhoneColumn;

	    @FXML
	    private TextField PhonenumberTextfield;

	    @FXML
	    private TableColumn<RegisteredStudent, Integer> PreviousPaymentColumn;

	    @FXML
	    private TableView<RegisteredStudent> myTableView;
	    
	    @FXML
	    private Label TitleLabel;
	    
	    @FXML
	    private TableColumn<RegisteredStudent, String> CourseColumn;
	    
	    private ObservableList<RegisteredStudent> list = FXCollections.observableArrayList();
	    
	    private ObservableList<String> courseList = FXCollections.observableArrayList();
	    
	    private Parent root;
	    
	    private Scene scene;
	    
	    private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT registered_student.student_phonenumber, student.student_name, registered_student.course_name, "
					+ "registered_student.previous_payment, registered_student.attendance_1, "
					+ "registered_student.attendance_2, registered_student.attendance_3, registered_student.attendance_4, "
					+ "registered_student.attendance_5, registered_student.attendance_6, registered_student.attendance_7, "
					+ "registered_student.attendance_8, registered_student.attendance_9, registered_student.attendance_10 "
					+ "FROM registered_student JOIN student WHERE registered_student.student_phonenumber = student.student_phonenumber;";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int i = 0;
				String phone = rs.getString(1);
				String name = rs.getString(2);
				String courseName = rs.getString(3);
				int previousPayment = rs.getInt(4);
				int attendance1 = rs.getInt(5);
				int attendance2 = rs.getInt(6);
				int attendance3 = rs.getInt(7);
				int attendance4 = rs.getInt(8);
				int attendance5 = rs.getInt(9);
				int attendance6 = rs.getInt(10);
				int attendance7 = rs.getInt(11);
				int attendance8 = rs.getInt(12);
				int attendance9 = rs.getInt(13);
				int attendance10 = rs.getInt(14);
				RegisteredStudent registeredStudent = new RegisteredStudent();
				registeredStudent.setPhoneNumber(phone);
				registeredStudent.setName(name);
				registeredStudent.setCourseName(courseName);
				registeredStudent.setPreviousPayment(previousPayment);
				TextField payment = new TextField();
				registeredStudent.setPayment(payment);
				payment.setOnAction(e -> addPayment(Integer.parseInt(payment.getText())));
				CheckBox a1 = new CheckBox();
				registeredStudent.setAttendance1(a1);
				CheckBox a2 = new CheckBox();
				registeredStudent.setAttendance2(a2);
				CheckBox a3 = new CheckBox();
				registeredStudent.setAttendance3(a3);
				CheckBox a4 = new CheckBox();
				registeredStudent.setAttendance4(a4);
				CheckBox a5 = new CheckBox();
				registeredStudent.setAttendance5(a5);
				CheckBox a6 = new CheckBox();
				registeredStudent.setAttendance6(a6);
				CheckBox a7 = new CheckBox();
				registeredStudent.setAttendance7(a7);
				CheckBox a8 = new CheckBox();
				registeredStudent.setAttendance8(a8);
				CheckBox a9 = new CheckBox();
				registeredStudent.setAttendance9(a9);
				CheckBox a10 = new CheckBox();
				registeredStudent.setAttendance10(a10);
				list.add(registeredStudent);
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		myTableView.setItems(list);
		PhoneColumn.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, String>("phoneNumber"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, String>("name"));
		CourseColumn.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, String>("courseName"));
		PreviousPaymentColumn.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, Integer>("previousPayment"));
		PaymentColumn.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, TextField>("payment"));
		Attendance1Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance1"));
		Attendance2Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance2"));
		Attendance3Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance3"));
		Attendance4Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance4"));
		Attendance5Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance5"));
		Attendance6Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance6"));
		Attendance7Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance7"));
		Attendance8Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance8"));
		Attendance9Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance9"));
		Attendance10Column.setCellValueFactory(new PropertyValueFactory<RegisteredStudent, CheckBox>("attendance10"));
	}

	public void getStudentRegistrationScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StudentRegistrationScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void addPayment(int in) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				if(!selected.getPayment().getText().isEmpty()) {
					int payment = Integer.parseInt(selected.getPayment().getText());
					int previousPayment = selected.getPreviousPayment();
					selected.setPreviousPayment(payment + previousPayment);
					int accPayment = selected.getPreviousPayment();
					int index = list.indexOf(selected);
					String sql = "UPDATE registered_student SET previous_payment = '" + accPayment + "' WHERE student_phonenumber = '" 
					+ selected.getPhoneNumber() + "' AND course_name = '" + selected.getCourseName() + "';";
					st.executeUpdate(sql);
					myTableView.getItems().set(index, selected);
					selected.getPayment().clear();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
}
