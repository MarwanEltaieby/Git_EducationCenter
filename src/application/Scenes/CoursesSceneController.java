package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.Objects.Course;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CoursesSceneController implements Initializable {
	
	@FXML
	private Button BackButton;

	@FXML
	private ChoiceBox<String> InstructorChoiceBox;

	@FXML
	private TableColumn<Course, String> CourseNameColumn;

	@FXML
	private TextField CourseNameTextfield;

	@FXML
	private TableColumn<Course, Integer> CoursePriceColumn;

	@FXML
	private TextField CoursePriceTextField;

	@FXML
	private TableView<Course> CoursesTableView;

	@FXML
	private TableColumn<Course, String> InstructorColumn;

	@FXML
	private Button SubmitButton;

	@FXML
	private Label invalidLabel;

	private ObservableList<Course> list = FXCollections.observableArrayList();
	private ObservableList<String> instructorList = FXCollections.observableArrayList();
	private Parent root;
	private Scene scene;
	private Stage stage;
	
	public void submitCourse(ActionEvent event) throws ClassNotFoundException, SQLException {
		try {
			String courseName = CourseNameTextfield.getText();
			String instructor = InstructorChoiceBox.getValue();
			int coursePrice = Integer.parseInt(CoursePriceTextField.getText());
			new Course(courseName, instructor, coursePrice);
			DatabaseConnector connector = new DatabaseConnector();
			Connection connection = connector.getConnection();
		
			String insertCourse = "INSERT INTO courses VALUE('" + courseName + "', '" + instructor + "', '" + coursePrice + "');";
			
			Statement statement =connection.createStatement();
			statement.executeUpdate(insertCourse);
			invalidLabel.setText("");
			root = FXMLLoader.load(getClass().getResource("CoursesScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			invalidLabel.setText("Error!");
		}
	}
		

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
			String sql = "SELECT * FROM courses;";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString(1);
				String instructor = rs.getString(2);
				int price = rs.getInt(3);
				Course course = new Course(name, instructor, price);
				list.add(course);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CoursesTableView.setItems(list);
		InstructorChoiceBox.setItems(instructorList);
		InstructorColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
		CourseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
		CoursePriceColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("coursePrice"));
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
	
	public void refresh(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("CoursesScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void remove(ActionEvent event) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			int selectedID = CoursesTableView.getSelectionModel().getSelectedIndex();
			Course selected = list.get(selectedID);
			String sql = "DELETE FROM courses WHERE course_name = '" + selected.getCourseName() + "';";
			st.executeUpdate(sql);
			CoursesTableView.getItems().remove(selectedID);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
