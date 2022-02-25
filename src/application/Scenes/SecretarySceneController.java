package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.Objects.DatabaseConnector;
import application.Objects.RegisteredStudent;
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
			courseList.add("All");
			CourseChoiceBox.setValue("All");
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
				if(attendance1 == 1) {
					a1.setSelected(true);
				} else {
					a1.setSelected(false);
				}
				a1.setOnAction(e -> checkAttendance1(a1.isSelected()));
				CheckBox a2 = new CheckBox();
				registeredStudent.setAttendance2(a2);
				if(attendance2 == 1) {
					a2.setSelected(true);
				} else {
					a2.setSelected(false);
				}
				a2.setOnAction(e -> checkAttendance2(a2.isSelected()));
				CheckBox a3 = new CheckBox();
				registeredStudent.setAttendance3(a3);
				if(attendance3 == 1) {
					a3.setSelected(true);
				} else {
					a3.setSelected(false);
				}
				a3.setOnAction(e -> checkAttendance3(a3.isSelected()));
				CheckBox a4 = new CheckBox();
				registeredStudent.setAttendance4(a4);
				if(attendance4 == 1) {
					a4.setSelected(true);
				} else {
					a4.setSelected(false);
				}
				a4.setOnAction(e -> checkAttendance4(a4.isSelected()));
				CheckBox a5 = new CheckBox();
				registeredStudent.setAttendance5(a5);
				if(attendance5 == 1) {
					a5.setSelected(true);
				} else {
					a5.setSelected(false);
				}
				a5.setOnAction(e -> checkAttendance5(a5.isSelected()));
				CheckBox a6 = new CheckBox();
				registeredStudent.setAttendance6(a6);
				if(attendance6 == 1) {
					a6.setSelected(true);
				} else {
					a6.setSelected(false);
				}
				a6.setOnAction(e -> checkAttendance6(a6.isSelected()));
				CheckBox a7 = new CheckBox();
				registeredStudent.setAttendance7(a7);
				if(attendance7 == 1) {
					a7.setSelected(true);
				} else {
					a7.setSelected(false);
				}
				a7.setOnAction(e -> checkAttendance7(a7.isSelected()));
				CheckBox a8 = new CheckBox();
				registeredStudent.setAttendance8(a8);
				if(attendance8 == 1) {
					a8.setSelected(true);
				} else {
					a8.setSelected(false);
				}
				a8.setOnAction(e -> checkAttendance8(a8.isSelected()));
				CheckBox a9 = new CheckBox();
				registeredStudent.setAttendance9(a9);
				if(attendance9 == 1) {
					a9.setSelected(true);
				} else {
					a9.setSelected(false);
				}
				a9.setOnAction(e -> checkAttendance9(a9.isSelected()));
				CheckBox a10 = new CheckBox();
				registeredStudent.setAttendance10(a10);
				if(attendance10 == 1) {
					a10.setSelected(true);
				} else {
					a10.setSelected(false);
				}
				a10.setOnAction(e -> checkAttendance10(a10.isSelected()));
				list.add(registeredStudent);
			}
			String courseSQL = "SELECT course_name FROM courses;";
			rs = st.executeQuery(courseSQL);
			while(rs.next()) {
				String courseName = rs.getString(1);
				courseList.add(courseName);
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		CourseChoiceBox.setItems(courseList);
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
	
	public void filter(ActionEvent event) {
		try {
			list.clear();
			String phoneNumber = PhonenumberTextfield.getText();
			String course = CourseChoiceBox.getValue();
			String sql = null;
			if(phoneNumber == null && course == "All") {
				sql = "SELECT registered_student.student_phonenumber, student.student_name, student.student_college, "
						+ "registered_student.course_name, registered_student.previous_payment, registered_student.attendance_1, "
						+ "registered_student.attendance_2, registered_student.attendance_3, registered_student.attendance_4, "
						+ "registered_student.attendance_5, registered_student.attendance_6, registered_student.attendance_7, "
						+ "registered_student.attendance_8, registered_student.attendance_9, registered_student.attendance_10 "
						+ "FROM registered_student JOIN student USING(student_phonenumber);";
			} else if(phoneNumber != null && course == "All") {
				sql = "SELECT registered_student.student_phonenumber, student.student_name, student.student_college, "
						+ "registered_student.course_name, registered_student.previous_payment, registered_student.attendance_1, "
						+ "registered_student.attendance_2, registered_student.attendance_3, registered_student.attendance_4, "
						+ "registered_student.attendance_5, registered_student.attendance_6, registered_student.attendance_7, "
						+ "registered_student.attendance_8, registered_student.attendance_9, registered_student.attendance_10 "
						+ "FROM registered_student JOIN student USING(student_phonenumber) WHERE student_phonenumber LIKE '" + phoneNumber + "%';";
			} else if(phoneNumber != null && course != "All") {
				sql = "SELECT registered_student.student_phonenumber, student.student_name, student.student_college, "
						+ "registered_student.course_name, registered_student.previous_payment, registered_student.attendance_1, "
						+ "registered_student.attendance_2, registered_student.attendance_3, registered_student.attendance_4, "
						+ "registered_student.attendance_5, registered_student.attendance_6, registered_student.attendance_7, "
						+ "registered_student.attendance_8, registered_student.attendance_9, registered_student.attendance_10 "
						+ "FROM registered_student JOIN student USING(student_phonenumber) WHERE student_phonenumber LIKE '" 
						+ phoneNumber + "%' AND course_name = '" + course + "';";
			} else if(phoneNumber == null && course != "All") {
				sql = "SELECT registered_student.student_phonenumber, student.student_name, student.student_college, "
						+ "registered_student.course_name, registered_student.previous_payment, registered_student.attendance_1, "
						+ "registered_student.attendance_2, registered_student.attendance_3, registered_student.attendance_4, "
						+ "registered_student.attendance_5, registered_student.attendance_6, registered_student.attendance_7, "
						+ "registered_student.attendance_8, registered_student.attendance_9, registered_student.attendance_10 "
						+ "FROM registered_student JOIN student USING(student_phonenumber) WHERE course_name = '" + course + "';";
			}
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
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
				if(attendance1 == 1) {
					a1.setSelected(true);
				} else {
					a1.setSelected(false);
				}
				a1.setOnAction(e -> checkAttendance1(a1.isSelected()));
				CheckBox a2 = new CheckBox();
				registeredStudent.setAttendance2(a2);
				if(attendance2 == 1) {
					a2.setSelected(true);
				} else {
					a2.setSelected(false);
				}
				a2.setOnAction(e -> checkAttendance2(a2.isSelected()));
				CheckBox a3 = new CheckBox();
				registeredStudent.setAttendance3(a3);
				if(attendance3 == 1) {
					a3.setSelected(true);
				} else {
					a3.setSelected(false);
				}
				a3.setOnAction(e -> checkAttendance3(a3.isSelected()));
				CheckBox a4 = new CheckBox();
				registeredStudent.setAttendance4(a4);
				if(attendance4 == 1) {
					a4.setSelected(true);
				} else {
					a4.setSelected(false);
				}
				a4.setOnAction(e -> checkAttendance4(a4.isSelected()));
				CheckBox a5 = new CheckBox();
				registeredStudent.setAttendance5(a5);
				if(attendance5 == 1) {
					a5.setSelected(true);
				} else {
					a5.setSelected(false);
				}
				a5.setOnAction(e -> checkAttendance5(a5.isSelected()));
				CheckBox a6 = new CheckBox();
				registeredStudent.setAttendance6(a6);
				if(attendance6 == 1) {
					a6.setSelected(true);
				} else {
					a6.setSelected(false);
				}
				a6.setOnAction(e -> checkAttendance6(a6.isSelected()));
				CheckBox a7 = new CheckBox();
				registeredStudent.setAttendance7(a7);
				if(attendance7 == 1) {
					a7.setSelected(true);
				} else {
					a7.setSelected(false);
				}
				a7.setOnAction(e -> checkAttendance7(a7.isSelected()));
				CheckBox a8 = new CheckBox();
				registeredStudent.setAttendance8(a8);
				if(attendance8 == 1) {
					a8.setSelected(true);
				} else {
					a8.setSelected(false);
				}
				a8.setOnAction(e -> checkAttendance8(a8.isSelected()));
				CheckBox a9 = new CheckBox();
				registeredStudent.setAttendance9(a9);
				if(attendance9 == 1) {
					a9.setSelected(true);
				} else {
					a9.setSelected(false);
				}
				a9.setOnAction(e -> checkAttendance9(a9.isSelected()));
				CheckBox a10 = new CheckBox();
				registeredStudent.setAttendance10(a10);
				if(attendance10 == 1) {
					a10.setSelected(true);
				} else {
					a10.setSelected(false);
				}
				a10.setOnAction(e -> checkAttendance10(a10.isSelected()));
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
	
	public void checkAttendance1(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_1 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance1().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_1 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_1 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance2(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_2 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance2().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_2 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_2 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance3(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_3 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance3().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_3 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_3 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance4(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_4 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance4().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_4 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_4 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance5(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_5 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance5().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_5 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_5 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance6(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_6 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance6().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_6 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_6 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance7(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_7 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance7().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_7 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_7 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance8(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_8 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance8().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_8 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_8 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance9(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_9 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance9().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_9 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_9 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	
	public void checkAttendance10(boolean check) {
		try {	
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			for(RegisteredStudent selected : list) {
				String sql = "SELECT attendance_10 FROM registered_student WHERE student_phonenumber = '" + selected.getPhoneNumber() 
				+ "' AND course_name = '" + selected.getCourseName() + "';";
				ResultSet rs = st.executeQuery(sql);
				int att = 0;
				while(rs.next()) {
					att = rs.getInt(1);
				}
				if(selected.getAttendance10().isSelected()) {		
					if(att == 0) {
						sql = "UPDATE registered_student SET attendance_10 = 1 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				} else {
					if(att == 1) {
						sql = "UPDATE registered_student SET attendance_10 = 0 WHERE student_phonenumber = '" + selected.getPhoneNumber() 
						+ "' AND course_name = '" + selected.getCourseName() + "';";
						st.executeUpdate(sql);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
	public void back(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("FirstScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getReRegisterStudent(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("StudentRe-RegistrationScene.fxml"));
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
