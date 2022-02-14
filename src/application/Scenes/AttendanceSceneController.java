package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import application.Objects.Attendance;
import application.Objects.DatabaseConnector;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AttendanceSceneController implements Initializable {
	
	@FXML
    private TableColumn<Attendance, CheckBox> AttendanceColumn;

    @FXML
    private TableView<Attendance> AttendanceTableView;

    @FXML
    private TableColumn<Attendance, Integer> CourseIdColumn;

    @FXML
    private TableColumn<Attendance, String> NameColumn;

    @FXML
    private TableColumn<Attendance, String> PhonenumberColumn;

    @FXML
    private TableColumn<Attendance, Integer> SessionNumberColumn;

    @FXML
    private TextField courseIdTextfield;

    @FXML
    private TextField phonenumberTextfield;
    
    private ObservableList<Attendance> AttendanceList = FXCollections.observableArrayList();

    private Parent root;
    
    private Stage stage;
    
    private Scene scene;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT student_phonenumber, student_name, course_id, session_number, is_student_attended FROM registered_student_attendance "
					+ "JOIN student USING(student_phonenumber) WHERE session_date = '" + LocalDate.now() + "';";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String phonenumber = rs.getString(1);
				String name = rs.getString(2);
				int courseID = rs.getInt(3);
				int sessionNumber = rs.getInt(4);
				int isAttendedNumber = rs.getInt(5);
				
				Attendance attendance = new Attendance();
				attendance.setPhoneNumber(phonenumber);
				attendance.setStudentName(name);
				attendance.setCourseID(courseID);
				attendance.setSessionNumber(sessionNumber);
				CheckBox isAttended = new CheckBox();
				if(isAttendedNumber == 1) {
					isAttended.setSelected(true);
					attendance.setIsAttended(isAttended);
				} else {
					isAttended.setSelected(false);
					attendance.setIsAttended(isAttended);
				}
				AttendanceList.add(attendance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AttendanceTableView.setItems(AttendanceList);
		PhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("phoneNumber"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("studentName"));
		CourseIdColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("courseID"));
		SessionNumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("sessionNumber"));
		AttendanceColumn.setCellValueFactory(new PropertyValueFactory<Attendance, CheckBox>("isAttended"));
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
	
	public void checkAttendance(ActionEvent event) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			int selectedID = AttendanceTableView.getSelectionModel().getSelectedIndex();
			Attendance selected = AttendanceList.get(selectedID);
			String sql = "UPDATE registered_student_attendance SET is_student_attended = 1 "
					+ "WHERE student_phonenumber = '" + selected.getPhoneNumber() 
					+ "'AND course_id = '" + selected.getCourseID() + "'AND session_number = '" 
					+ selected.getSessionNumber() + "'AND session_date = '" 
					+ LocalDate.now() + "';";
			st.executeUpdate(sql);
			root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void uncheckAttendance(ActionEvent event) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			int selectedID = AttendanceTableView.getSelectionModel().getSelectedIndex();
			Attendance selected = AttendanceList.get(selectedID);
			String sql = "UPDATE registered_student_attendance SET is_student_attended = 0 "
					+ "WHERE student_phonenumber = '" + selected.getPhoneNumber() 
					+ "'AND course_id = '" + selected.getCourseID() + "'AND session_number = '" 
					+ selected.getSessionNumber() + "'AND session_date = '" 
					+ LocalDate.now() + "';";
			st.executeUpdate(sql);
			root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void filter(ActionEvent event) {
		try{
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String phone = phonenumberTextfield.getText();
			int course = 0;
			if(!courseIdTextfield.getText().equals("")) {
				course = Integer.parseInt(courseIdTextfield.getText());
			}
			String sql = null;
			if(course != 0 && phone != null) {
				AttendanceTableView.getItems().removeAll(AttendanceList);
				sql = "SELECT student_phonenumber, student_name, course_id, session_number, is_student_attended FROM registered_student_attendance "
						+ "JOIN student USING(student_phonenumber) WHERE session_date = '" + LocalDate.now() + "' AND student_phonenumber LIKE '" + phone + "%' AND course_id = " + course + ";";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String phonenumber = rs.getString(1);
					String name = rs.getString(2);
					int courseID = rs.getInt(3);
					int sessionNumber = rs.getInt(4);
					int isAttendedNumber = rs.getInt(5);
					
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(phonenumber);
					attendance.setStudentName(name);
					attendance.setCourseID(courseID);
					attendance.setSessionNumber(sessionNumber);
					CheckBox isAttended = new CheckBox();
					if(isAttendedNumber == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					AttendanceList.add(attendance);
				}
			} else if(course == 0 && phone != null) {
				AttendanceTableView.getItems().removeAll(AttendanceList);
				sql = "SELECT student_phonenumber, student_name, course_id, session_number, is_student_attended FROM registered_student_attendance "
						+ "JOIN student USING(student_phonenumber) WHERE session_date = '" + LocalDate.now() + "' AND student_phonenumber LIKE '" + phone + "%';";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String phonenumber = rs.getString(1);
					String name = rs.getString(2);
					int courseID = rs.getInt(3);
					int sessionNumber = rs.getInt(4);
					int isAttendedNumber = rs.getInt(5);
					
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(phonenumber);
					attendance.setStudentName(name);
					attendance.setCourseID(courseID);
					attendance.setSessionNumber(sessionNumber);
					CheckBox isAttended = new CheckBox();
					if(isAttendedNumber == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					AttendanceList.add(attendance);
				}
			} else if(course != 0 && phone == null) {
				AttendanceTableView.getItems().removeAll(AttendanceList);
				sql = "SELECT student_phonenumber, student_name, course_id, session_number, is_student_attended FROM registered_student_attendance "
						+ "JOIN student USING(student_phonenumber) WHERE session_date = '" + LocalDate.now() + "' AND course_id " + course + ";";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String phonenumber = rs.getString(1);
					String name = rs.getString(2);
					int courseID = rs.getInt(3);
					int sessionNumber = rs.getInt(4);
					int isAttendedNumber = rs.getInt(5);
					
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(phonenumber);
					attendance.setStudentName(name);
					attendance.setCourseID(courseID);
					attendance.setSessionNumber(sessionNumber);
					CheckBox isAttended = new CheckBox();
					if(isAttendedNumber == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					AttendanceList.add(attendance);
				}
			} else {
				AttendanceTableView.getItems().removeAll(AttendanceList);
				sql = "SELECT student_phonenumber, student_name, course_id, session_number, is_student_attended FROM registered_student_attendance "
						+ "JOIN student USING(student_phonenumber) WHERE session_date = '" + LocalDate.now() + ";";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String phonenumber = rs.getString(1);
					String name = rs.getString(2);
					int courseID = rs.getInt(3);
					int sessionNumber = rs.getInt(4);
					int isAttendedNumber = rs.getInt(5);
					
					Attendance attendance = new Attendance();
					attendance.setPhoneNumber(phonenumber);
					attendance.setStudentName(name);
					attendance.setCourseID(courseID);
					attendance.setSessionNumber(sessionNumber);
					CheckBox isAttended = new CheckBox();
					if(isAttendedNumber == 1) {
						isAttended.setSelected(true);
						attendance.setIsAttended(isAttended);
					} else {
						isAttended.setSelected(false);
						attendance.setIsAttended(isAttended);
					}
					AttendanceList.add(attendance);
				}
			}
			st.executeQuery(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		AttendanceTableView.setItems(AttendanceList);
		PhonenumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("phoneNumber"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<Attendance, String>("studentName"));
		CourseIdColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("courseID"));
		SessionNumberColumn.setCellValueFactory(new PropertyValueFactory<Attendance, Integer>("sessionNumber"));
		AttendanceColumn.setCellValueFactory(new PropertyValueFactory<Attendance, CheckBox>("isAttended"));
	}
}
