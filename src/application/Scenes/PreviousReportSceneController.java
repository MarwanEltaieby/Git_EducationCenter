package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.Objects.Attendance;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PreviousReportSceneController implements Initializable {

    @FXML
    private TableColumn<Attendance, CheckBox> AttendanceColumn;

    @FXML
    private TableColumn<Attendance, Integer> AttendanceCourseIdColumn;

    @FXML
    private TableColumn<Attendance, String> AttendanceDateColumn;

    @FXML
    private TableColumn<Attendance, String> AttendanceNameColumn;

    @FXML
    private TableColumn<Attendance, String> AttendancePhonenumberColumn;

    @FXML
    private TableView<Attendance> AttendanceTableView;

    @FXML
    private ComboBox<Integer> CourseIdComboBox;

    @FXML
    private DatePicker DatePicker1;

    @FXML
    private DatePicker DatePicker2;

    @FXML
    private TableColumn<Payment, Double> PaymentColumn;

    @FXML
    private TableColumn<Payment, Integer> PaymentCourseIdColumn;

    @FXML
    private TableColumn<Payment, String> PaymentDateColumn;

    @FXML
    private TableColumn<Payment, String> PaymentNameColumn;

    @FXML
    private TableColumn<Payment, String> PaymentPhonenumberColumn;

    @FXML
    private TableView<Payment> PaymentTableView;

    @FXML
    private TextField PhonenumberTextField;

    @FXML
    private TableColumn<Attendance, Integer> SessionNumberColumn;

    @FXML
    private Label TotalAttendanceLabel;

    @FXML
    private Label TotalPaymentLabel;
    
    private ObservableList<Attendance> attendanceList = FXCollections.observableArrayList();
    
    private ObservableList<Payment> paymentList = FXCollections.observableArrayList();
    
    private ObservableList<Integer> CourseIdList = FXCollections.observableArrayList();
    
    private Stage stage;
    
    private Parent root;
    
    private Scene scene;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String attendanceSQL = "SELECT student_phonenumber, student_name, course_id, session_number, "
					+ "session_date, is_student_attended FROM registered_student_attendance JOIN student "
					+ "USING(student_phonenumber) WHERE session_date = '" + LocalDate.now() + "';";
			String paymentSQL = "SELECT student_phonenumber, student_name, course_id, payment, payment_date "
					+ "FROM registered_student_payment JOIN student USING(student_phonenumber) WHERE payment_date ='"
					+ LocalDate.now() + "';";
			String sql = "SELECT course_id FROM courses;";
			CourseIdList.add(0);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int courseID = rs.getInt(1);
				CourseIdList.add(courseID);
			}
		
			rs = st.executeQuery(attendanceSQL);
			while(rs.next()) {
				Attendance attendance = new Attendance();
				attendance.setPhoneNumber(rs.getString(1));
				attendance.setStudentName(rs.getString(2));
				attendance.setCourseID(rs.getInt(3));
				attendance.setSessionNumber(rs.getInt(4));
				attendance.setSessionDate(rs.getString(5));
				CheckBox isAttended = new CheckBox();
				if(rs.getInt(6) == 1) {
					isAttended.setSelected(true);
					attendance.setIsAttended(isAttended);
				} else {
					isAttended.setSelected(false);
					attendance.setIsAttended(isAttended);
				}
				attendanceList.add(attendance);
			}
			rs = st.executeQuery(paymentSQL);
			while(rs.next()) {
				Payment payment = new Payment();
				payment.setPhoneNumber(rs.getString(1));
				payment.setStudentName(rs.getString(2));
				payment.setCourseID(rs.getInt(3));
				payment.setPayment(rs.getDouble(4));
				payment.setPaymentDate(rs.getString(5));
				paymentList.add(payment);
			}
			sql = "SELECT COUNT(student_phonenumber) FROM registered_student_attendance WHERE is_student_attended = '1';";
			rs = st.executeQuery(sql);
			String totalAttendance = "";
			while(rs.next()) {
				totalAttendance = rs.getString(1);
			}
			TotalAttendanceLabel.setText("(" + totalAttendance + ")");
			sql = "SELECT payment FROM registered_student_payment WHERE payment_date ='"+ LocalDate.now() + "';";
			rs = st.executeQuery(sql);
			double sum = 0;
			while(rs.next()) {
				double payment = rs.getDouble(1);
				sum += payment;
			}
			TotalPaymentLabel.setText("(" + sum + ")");
		}catch (Exception e) {
			e.printStackTrace();
		}
		AttendanceTableView.setItems(attendanceList);
		AttendancePhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("phoneNumber"));
		AttendanceNameColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("studentName"));
		AttendanceCourseIdColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("courseID"));
		SessionNumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("sessionNumber"));
		AttendanceDateColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("sessionDate"));
		AttendanceColumn.setCellValueFactory(new PropertyValueFactory<Attendance, CheckBox>("isAttended"));
		CourseIdComboBox.setItems(CourseIdList);
		PaymentTableView.setItems(paymentList);
		PaymentPhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("phoneNumber"));
		PaymentNameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("studentName"));
		PaymentCourseIdColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("courseID"));
		PaymentColumn.setCellValueFactory(new PropertyValueFactory<Payment, Double>("payment"));
		PaymentDateColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("paymentDate"));
	}
	
	public void search(ActionEvent event) {
		try {
			String phone = PhonenumberTextField.getText();
			LocalDate date = DatePicker1.getValue();
			DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String date1 = date.format(myFormatter);
			date = DatePicker2.getValue();
			String date2 = date.format(myFormatter);
			int course = CourseIdComboBox.getValue();
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			
			
			//1st condition (all input)
			
			
			
			if(!phone.equals("") && course != 0 && !date1.equals("") && !date2.equals("")) {
				AttendanceTableView.getItems().clear();
				PaymentTableView.getItems().clear();
				String attendanceSqlEnding = "student_phonenumber LIKE '" + phone + "%' AND course_id = '" 
						+ course + "' AND session_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY session_date DESC;";
				String paymentSqlEnding = "student_phonenumber LIKE '" + phone + "%' AND course_id = '" 
						+ course + "' AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY payment_date DESC;";
				String attendanceSQL = "SELECT student_phonenumber, student_name, course_id, session_number, "
						+ "session_date, is_student_attended FROM registered_student_attendance JOIN student "
						+ "USING(student_phonenumber) WHERE " + attendanceSqlEnding;
				String paymentSQL = "SELECT student_phonenumber, student_name, course_id, payment, payment_date "
						+ "FROM registered_student_payment JOIN student USING(student_phonenumber) WHERE " + paymentSqlEnding;
				ResultSet rs = st.executeQuery(attendanceSQL);
				while(rs.next()) {
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(rs.getString(1));
					attendance.setStudentName(rs.getString(2));
					attendance.setCourseID(rs.getInt(3));
					attendance.setSessionNumber(rs.getInt(4));
					attendance.setSessionDate(rs.getString(5));
					CheckBox isAttended = new CheckBox();
					if(rs.getInt(6) == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					attendanceList.add(attendance);
				}
				rs = st.executeQuery(paymentSQL);
				while(rs.next()) {
					Payment payment = new Payment();
					payment.setPhoneNumber(rs.getString(1));
					payment.setStudentName(rs.getString(2));
					payment.setCourseID(rs.getInt(3));
					payment.setPayment(rs.getDouble(4));
					payment.setPaymentDate(rs.getString(5));
					paymentList.add(payment);
				}
				String sql = "SELECT COUNT(student_phonenumber) FROM registered_student_attendance WHERE is_student_attended = '1' AND " + attendanceSqlEnding;
				rs = st.executeQuery(sql);
				String totalAttendance = "";
				while(rs.next()) {
					totalAttendance = rs.getString(1);
				}
				TotalAttendanceLabel.setText("(" + totalAttendance + ")");
				sql = "SELECT payment FROM registered_student_payment WHERE " + paymentSqlEnding;
				rs = st.executeQuery(sql);
				double sum = 0;
				while(rs.next()) {
					double payment = rs.getDouble(1);
					sum += payment;
				}
				TotalPaymentLabel.setText("(" + sum + ")");
				
				
				//2nd condition (no phone)
				
				
				
			} else if(phone.equals("") && course != 0 && !date1.equals("") && !date2.equals("")) {
				AttendanceTableView.getItems().clear();
				PaymentTableView.getItems().clear();
				String attendanceSqlEnding = "course_id = '" + course + "' AND session_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY session_date DESC;";
				String paymentSqlEnding = "course_id = '" + course + "' AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY payment_date DESC;";
				String attendanceSQL = "SELECT student_phonenumber, student_name, course_id, session_number, "
						+ "session_date, is_student_attended FROM registered_student_attendance JOIN student "
						+ "USING(student_phonenumber) WHERE " + attendanceSqlEnding;
				String paymentSQL = "SELECT student_phonenumber, student_name, course_id, payment, payment_date "
						+ "FROM registered_student_payment JOIN student USING(student_phonenumber) WHERE " + paymentSqlEnding;
				ResultSet rs = st.executeQuery(attendanceSQL);
				while(rs.next()) {
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(rs.getString(1));
					attendance.setStudentName(rs.getString(2));
					attendance.setCourseID(rs.getInt(3));
					attendance.setSessionNumber(rs.getInt(4));
					attendance.setSessionDate(rs.getString(5));
					CheckBox isAttended = new CheckBox();
					if(rs.getInt(6) == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					attendanceList.add(attendance);
				}
				rs = st.executeQuery(paymentSQL);
				while(rs.next()) {
					Payment payment = new Payment();
					payment.setPhoneNumber(rs.getString(1));
					payment.setStudentName(rs.getString(2));
					payment.setCourseID(rs.getInt(3));
					payment.setPayment(rs.getDouble(4));
					payment.setPaymentDate(rs.getString(5));
					paymentList.add(payment);
				}
				String sql = "SELECT COUNT(student_phonenumber) FROM registered_student_attendance WHERE is_student_attended = '1' AND " + attendanceSqlEnding;
				rs = st.executeQuery(sql);
				String totalAttendance = "";
				while(rs.next()) {
					totalAttendance = rs.getString(1);
				}
				TotalAttendanceLabel.setText("(" + totalAttendance + ")");
				sql = "SELECT payment FROM registered_student_payment WHERE " + paymentSqlEnding;
				rs = st.executeQuery(sql);
				double sum = 0;
				while(rs.next()) {
					double payment = rs.getDouble(1);
					sum += payment;
				}
				TotalPaymentLabel.setText("(" + sum + ")");
				
				
				//3rd condition (no course)
				
				
				
			} else if(!phone.equals("") && course == 0 && !date1.equals("") && !date2.equals("")) {
				AttendanceTableView.getItems().clear();
				PaymentTableView.getItems().clear();
				String attendanceSqlEnding = "student_phonenumber LIKE '" + phone + "%' AND session_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY session_date DESC;";
				String paymentSqlEnding = "student_phonenumber LIKE '" + phone + "%' AND payment_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY payment_date DESC;";
				String attendanceSQL = "SELECT student_phonenumber, student_name, course_id, session_number, "
						+ "session_date, is_student_attended FROM registered_student_attendance JOIN student "
						+ "USING(student_phonenumber) WHERE " + attendanceSqlEnding;
				String paymentSQL = "SELECT student_phonenumber, student_name, course_id, payment, payment_date "
						+ "FROM registered_student_payment JOIN student USING(student_phonenumber) WHERE " + paymentSqlEnding;
				ResultSet rs = st.executeQuery(attendanceSQL);
				while(rs.next()) {
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(rs.getString(1));
					attendance.setStudentName(rs.getString(2));
					attendance.setCourseID(rs.getInt(3));
					attendance.setSessionNumber(rs.getInt(4));
					attendance.setSessionDate(rs.getString(5));
					CheckBox isAttended = new CheckBox();
					if(rs.getInt(6) == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					attendanceList.add(attendance);
				}
				rs = st.executeQuery(paymentSQL);
				while(rs.next()) {
					Payment payment = new Payment();
					payment.setPhoneNumber(rs.getString(1));
					payment.setStudentName(rs.getString(2));
					payment.setCourseID(rs.getInt(3));
					payment.setPayment(rs.getDouble(4));
					payment.setPaymentDate(rs.getString(5));
					paymentList.add(payment);
				}
				String sql = "SELECT COUNT(student_phonenumber) FROM registered_student_attendance WHERE is_student_attended = '1' AND " + attendanceSqlEnding;
				rs = st.executeQuery(sql);
				String totalAttendance = "";
				while(rs.next()) {
					totalAttendance = rs.getString(1);
				}
				TotalAttendanceLabel.setText("(" + totalAttendance + ")");
				sql = "SELECT payment FROM registered_student_payment WHERE " + paymentSqlEnding;
				rs = st.executeQuery(sql);
				double sum = 0;
				while(rs.next()) {
					double payment = rs.getDouble(1);
					sum += payment;
				}
				TotalPaymentLabel.setText("(" + sum + ")");
				
				
				//4th condition (no phone, no course)
				
				
			} else if(phone.equals("") && course == 0 && !date1.equals("") && !date2.equals("")) {
				AttendanceTableView.getItems().clear();
				PaymentTableView.getItems().clear();
				String attendanceSqlEnding = "session_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY session_date DESC;";
				String paymentSqlEnding = "payment_date BETWEEN '" + date1 + "' AND '" + date2 + "' ORDER BY payment_date DESC;";
				String attendanceSQL = "SELECT student_phonenumber, student_name, course_id, session_number, "
						+ "session_date, is_student_attended FROM registered_student_attendance JOIN student "
						+ "USING(student_phonenumber) WHERE " + attendanceSqlEnding;
				String paymentSQL = "SELECT student_phonenumber, student_name, course_id, payment, payment_date "
						+ "FROM registered_student_payment JOIN student USING(student_phonenumber) WHERE " + paymentSqlEnding;
				ResultSet rs = st.executeQuery(attendanceSQL);
				while(rs.next()) {
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(rs.getString(1));
					attendance.setStudentName(rs.getString(2));
					attendance.setCourseID(rs.getInt(3));
					attendance.setSessionNumber(rs.getInt(4));
					attendance.setSessionDate(rs.getString(5));
					CheckBox isAttended = new CheckBox();
					if(rs.getInt(6) == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					attendanceList.add(attendance);
				}
				rs = st.executeQuery(paymentSQL);
				while(rs.next()) {
					Payment payment = new Payment();
					payment.setPhoneNumber(rs.getString(1));
					payment.setStudentName(rs.getString(2));
					payment.setCourseID(rs.getInt(3));
					payment.setPayment(rs.getDouble(4));
					payment.setPaymentDate(rs.getString(5));
					paymentList.add(payment);
				}
				String sql = "SELECT COUNT(student_phonenumber) FROM registered_student_attendance WHERE is_student_attended = '1' AND " + attendanceSqlEnding;
				rs = st.executeQuery(sql);
				String totalAttendance = "";
				while(rs.next()) {
					totalAttendance = rs.getString(1);
				}
				TotalAttendanceLabel.setText("(" + totalAttendance + ")");
				sql = "SELECT payment FROM registered_student_payment WHERE " + paymentSqlEnding;
				rs = st.executeQuery(sql);
				double sum = 0;
				while(rs.next()) {
					double payment = rs.getDouble(1);
					sum += payment;
				}
				TotalPaymentLabel.setText("(" + sum + ")");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		AttendanceTableView.setItems(attendanceList);
		AttendancePhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("phoneNumber"));
		AttendanceNameColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("studentName"));
		AttendanceCourseIdColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("courseID"));
		SessionNumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("sessionNumber"));
		AttendanceDateColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("sessionDate"));
		AttendanceColumn.setCellValueFactory(new PropertyValueFactory<Attendance, CheckBox>("isAttended"));
		CourseIdComboBox.setItems(CourseIdList);
		PaymentTableView.setItems(paymentList);
		PaymentPhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("phoneNumber"));
		PaymentNameColumn.setCellValueFactory(new PropertyValueFactory<Payment, String>("studentName"));
		PaymentCourseIdColumn.setCellValueFactory(new PropertyValueFactory<Payment, Integer>("courseID"));
		PaymentColumn.setCellValueFactory(new PropertyValueFactory<Payment, Double>("payment"));
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
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

