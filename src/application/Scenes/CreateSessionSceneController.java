package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import application.Objects.DatabaseConnector;
import application.Objects.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CreateSessionSceneController implements Initializable{

	@FXML
    private ChoiceBox<Integer> CourseIdChoiceBox;

    @FXML
    private TableColumn<Session, Integer> CourseIdColumn;

    @FXML
    private ChoiceBox<String> InstructorChoiceBox;

    @FXML
    private TableColumn<Session, String> InstructorColumn;

    @FXML
    private TableColumn<Session, String> SessionDateColumn;

    @FXML
    private DatePicker SessionDatePicker;

    @FXML
    private ChoiceBox<Integer> SessionNumberChoiceBox;

    @FXML
    private TableColumn<Session, Integer> SessionNumberColumn;

    @FXML
    private TableView<Session> SessionTableView;
    
    private ObservableList<String> instructorList = FXCollections.observableArrayList();
    
    private ObservableList<Integer> courseList = FXCollections.observableArrayList();
    
    private ObservableList<Integer> SessionNumberList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private ObservableList<Session> SessionList = FXCollections.observableArrayList(); 
    
    private Parent root;
    
    private Stage stage;
    
    private Scene scene;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String getInstructors = "SELECT instructor_username FROM instructor;";
			ResultSet rs = st.executeQuery(getInstructors);
			while(rs.next()) {
				String instructor = rs.getString(1);
				instructorList.add(instructor);
			}
			
			String getCourses = "SELECT course_id FROM courses;";
			rs = st.executeQuery(getCourses);
			while(rs.next()) {
				int course = rs.getInt(1);
				courseList.add(course);
			}
			String getSession = "SELECT * FROM session ORDER BY session_date DESC";
			rs = st.executeQuery(getSession);
			while(rs.next()) {
				String instructor = rs.getString(1);
				int courseID = rs.getInt(2);
				int sessionNumber = rs.getInt(3);
				String sessionDate = rs.getString(4);
				Session session = new Session(instructor, courseID, sessionNumber, sessionDate);
				SessionList.add(session);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		InstructorChoiceBox.setItems(instructorList);
		SessionNumberChoiceBox.setItems(SessionNumberList);
		CourseIdChoiceBox.setItems(courseList);
		InstructorColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("instructor"));
		CourseIdColumn.setCellValueFactory(new PropertyValueFactory<Session, Integer>("courseID"));
		SessionNumberColumn.setCellValueFactory(new PropertyValueFactory<Session, Integer>("sessionNumber"));
		SessionDateColumn.setCellValueFactory(new PropertyValueFactory<Session, String>("sessionDate"));
		SessionTableView.setItems(SessionList);
	}
    
	public void submitSession(ActionEvent event) {
		try {
		DatabaseConnector connector = new DatabaseConnector();
		Connection con = connector.getConnection();
		Statement st = con.createStatement();
		String instructor = InstructorChoiceBox.getValue();
		int courseID = CourseIdChoiceBox.getValue();
		int sessionNumber = SessionNumberChoiceBox.getValue();
		DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate sessionDate = SessionDatePicker.getValue();
		String formattedSessionDate = sessionDate.format(myFormatter);
		String sql = "INSERT INTO session VALUE( '" + instructor + "', '" + courseID + "', '" + sessionNumber + "', '" + formattedSessionDate + "');";
		st.executeUpdate(sql);
		sql = "SELECT student_phonenumber FROM registered_student WHERE course_id = " + courseID + " ;";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			String phonenumber = rs.getString(1);
			String sql1 = "INSERT INTO registered_student_attendance VALUE('" + phonenumber + "', '" + courseID + "', '" + sessionNumber + "', '" + formattedSessionDate +"', 0);";
			Statement st1 = con.createStatement();
			st1.executeUpdate(sql1);
		}
		root = FXMLLoader.load(getClass().getResource("CreateSessionScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	catch (IOException e) {
		e.printStackTrace();
		}
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
	
	public void delete(ActionEvent event) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			int selectedIndex = SessionTableView.getSelectionModel().getSelectedIndex();
			Session selected = SessionList.get(selectedIndex);
			String sql = "DELETE FROM session WHERE instructor_username = '" + selected.getInstructor() 
			+ "' AND course_id = '" + selected.getCourseID() + "' AND session_number = '" + selected.getSessionNumber() 
			+ "' AND session_date = '" + selected.getSessionDate() + "';";
			String sql2 = "DELETE FROM registered_student_attendance WHERE course_id = '" + selected.getCourseID() + "' AND session_number = '" + selected.getSessionNumber() 
					+ "' AND session_date = '" + selected.getSessionDate() + "';";
			st.executeLargeUpdate(sql);
			st.executeUpdate(sql2);
			SessionTableView.getItems().remove(selectedIndex);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    
}
