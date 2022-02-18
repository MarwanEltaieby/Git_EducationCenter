package application.Scenes;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.Objects.DatabaseConnector;
import application.Objects.StudentReport;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ReportSceneController implements Initializable {
	
	 @FXML
	 private TableColumn<StudentReport, Character> Attendance10Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance1Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance2Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance3Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance4Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance5Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance6Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance7Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance8Column;

	 @FXML
	 private TableColumn<StudentReport, Character> Attendance9Column;

	 @FXML
	 private TableColumn<StudentReport, String> CollegeColumn;

	 @FXML
	 private ChoiceBox<String> CourseChoiceBox;

	 @FXML
	 private TableColumn<StudentReport, String> CourseColumn;

	 @FXML
	 private TableColumn<StudentReport, String> NameColumn;

	 @FXML
	 private TableColumn<StudentReport, Integer> PaymentColumn;

	 @FXML
	 private TableColumn<StudentReport, String> PhoneColumn;

	 @FXML
	 private TextField PhonenumberTextfield;

	 @FXML
	 private Label TitleLabel;

	 @FXML
	 private TableView<StudentReport> myTableView;
	    
	 private ObservableList<StudentReport> list = FXCollections.observableArrayList();
	    
	 private ObservableList<String> courseList = FXCollections.observableArrayList();
	    
	 private Parent root;
	    
	 private Scene scene;
	    
	 private Stage stage;

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
					String college = rs.getString(3);
					String courseName = rs.getString(4);
					int payment = rs.getInt(5);
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
					StudentReport student = new StudentReport();
					student.setPhoneNumber(phone);
					student.setName(name);
					student.setCollege(college);
					student.setCourseName(courseName);
					student.setPayment(payment);
					if(attendance1 == 1) {
						student.setAttendance1('Y');
					} else {
						student.setAttendance1('N');
					} 
					if(attendance2 == 1) {
						student.setAttendance2('Y');
					} else {
						student.setAttendance2('N');
					}
					if(attendance3 == 1) {
						student.setAttendance3('Y');
					} else {
						student.setAttendance3('N');
					}
					if(attendance4 == 1) {
						student.setAttendance4('Y');
					} else {
						student.setAttendance4('N');
					}
					if(attendance5 == 1) {
						student.setAttendance5('Y');
					} else {
						student.setAttendance5('N');
					}
					if(attendance6 == 1) {
						student.setAttendance6('Y');
					} else {
						student.setAttendance6('N');
					}
					if(attendance7 == 1) {
						student.setAttendance7('Y');
					} else {
						student.setAttendance7('N');
					}
					if(attendance8 == 1) {
						student.setAttendance8('Y');
					} else {
						student.setAttendance8('N');
					}
					if(attendance9 == 1) {
						student.setAttendance9('Y');
					} else {
						student.setAttendance9('N');
					}
					if(attendance10 == 1) {
						student.setAttendance10('Y');
					} else {
						student.setAttendance10('N');
					}
					list.add(student);
				}
			}catch(Exception e) {
				e.printStackTrace();
				e.getCause();
			}
			myTableView.setItems(list);
			PhoneColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("phoneNumber"));
			NameColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("name"));
			CollegeColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("college"));
			CourseColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("courseName"));
			PaymentColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, Integer>("payment"));
			Attendance1Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance1"));
			Attendance2Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance2"));
			Attendance3Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance3"));
			Attendance4Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance4"));
			Attendance5Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance5"));
			Attendance6Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance6"));
			Attendance7Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance7"));
			Attendance8Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance8"));
			Attendance9Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance9"));
			Attendance10Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance10"));
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			courseList.add("All");
			CourseChoiceBox.setValue("All");
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT registered_student.student_phonenumber, student.student_name, student.student_college, "
					+ "registered_student.course_name, registered_student.previous_payment, registered_student.attendance_1, "
					+ "registered_student.attendance_2, registered_student.attendance_3, registered_student.attendance_4, "
					+ "registered_student.attendance_5, registered_student.attendance_6, registered_student.attendance_7, "
					+ "registered_student.attendance_8, registered_student.attendance_9, registered_student.attendance_10 "
					+ "FROM registered_student JOIN student USING(student_phonenumber);";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String phone = rs.getString(1);
				String name = rs.getString(2);
				String college = rs.getString(3);
				String courseName = rs.getString(4);
				int payment = rs.getInt(5);
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
				StudentReport student = new StudentReport();
				student.setPhoneNumber(phone);
				student.setName(name);
				student.setCollege(college);
				student.setCourseName(courseName);
				student.setPayment(payment);
				if(attendance1 == 1) {
					student.setAttendance1('Y');
				} else {
					student.setAttendance1('N');
				} 
				if(attendance2 == 1) {
					student.setAttendance2('Y');
				} else {
					student.setAttendance2('N');
				}
				if(attendance3 == 1) {
					student.setAttendance3('Y');
				} else {
					student.setAttendance3('N');
				}
				if(attendance4 == 1) {
					student.setAttendance4('Y');
				} else {
					student.setAttendance4('N');
				}
				if(attendance5 == 1) {
					student.setAttendance5('Y');
				} else {
					student.setAttendance5('N');
				}
				if(attendance6 == 1) {
					student.setAttendance6('Y');
				} else {
					student.setAttendance6('N');
				}
				if(attendance7 == 1) {
					student.setAttendance7('Y');
				} else {
					student.setAttendance7('N');
				}
				if(attendance8 == 1) {
					student.setAttendance8('Y');
				} else {
					student.setAttendance8('N');
				}
				if(attendance9 == 1) {
					student.setAttendance9('Y');
				} else {
					student.setAttendance9('N');
				}
				if(attendance10 == 1) {
					student.setAttendance10('Y');
				} else {
					student.setAttendance10('N');
				}
				list.add(student);
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
		PhoneColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("phoneNumber"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("name"));
		CollegeColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("college"));
		CourseColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, String>("courseName"));
		PaymentColumn.setCellValueFactory(new PropertyValueFactory<StudentReport, Integer>("payment"));
		Attendance1Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance1"));
		Attendance2Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance2"));
		Attendance3Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance3"));
		Attendance4Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance4"));
		Attendance5Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance5"));
		Attendance6Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance6"));
		Attendance7Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance7"));
		Attendance8Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance8"));
		Attendance9Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance9"));
		Attendance10Column.setCellValueFactory(new PropertyValueFactory<StudentReport, Character>("attendance10"));
	}

}
