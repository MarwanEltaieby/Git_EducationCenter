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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentRegistrationSceneController implements Initializable {

	@FXML
    private ChoiceBox<String> CourseNameChoiceBox;

    @FXML
    private TableColumn<Course, String> CourseInstructorColumn;

    @FXML
    private TableColumn<Course, String> CourseNameColumn;

    @FXML
    private TableColumn<Course, Integer> CoursePriceColumn;

    @FXML
    private TableView<Course> CoursesTableView;

    @FXML
    private TextField StudentCollegeTextField;

    @FXML
    private TextField StudentNameTextField;

    @FXML
    private TextField StudentPhonenumberTextField;
    
    @FXML
    private Label invalidLabel;
    
    private ObservableList<Course> list = FXCollections.observableArrayList(); 
    
    private ObservableList<String> courseList = FXCollections.observableArrayList();
    
    private Parent root;
    
    private Stage stage;
    
    private Scene scene;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM courses;";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String name = rs.getString(1);
				String instructor = rs.getString(2);
				int price = rs.getInt(3);
				Course course = new Course(name, instructor, price);
				list.add(course);
			}	
			sql = "SELECT course_name FROM courses;";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String course = rs.getString(1);
				courseList.add(course);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CourseNameChoiceBox.setItems(courseList);
		CoursesTableView.setItems(list);
		CourseInstructorColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
		CourseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
		CoursePriceColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("coursePrice"));	
	}
	
	public void back(ActionEvent event) {
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
	
	public void registerStudent(ActionEvent event) {
		try {
			if(StudentPhonenumberTextField.getText().length() == 11 && isNumeric(StudentPhonenumberTextField.getText())) {
				String name = StudentNameTextField.getText();
				String phoneNumber = StudentPhonenumberTextField.getText();
				String college = StudentCollegeTextField.getText();
				String course = CourseNameChoiceBox.getValue();
			 
				DatabaseConnector connector = new DatabaseConnector();
				Connection connection = connector.getConnection();
				
				String verifySql = "SELECT student_phonenumber FROM student";
				String sql = "INSERT INTO student VALUE( '" + phoneNumber + "', '" + name + "', '" + college + "');";
				String sql2 = "INSERT INTO registered_student(student_phonenumber, course_name) VALUE( '" + phoneNumber + "', '" + course + "');";
				
				boolean isVerified = false;
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(verifySql);
				while(rs.next()) {
					if(rs.getString(1).equals(phoneNumber)) {
						isVerified = true;
						break;
					}
				}
				if(isVerified) {
					st.executeUpdate(sql2);
				} else {
					st.executeUpdate(sql);
					st.executeUpdate(sql2);
				}
				root = FXMLLoader.load(getClass().getResource("SecretaryScene.fxml"));
				stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}else {
				invalidLabel.setText("Invalid PhoneNumber!");
			}
		}catch(Exception e) {
			e.printStackTrace();
			invalidLabel.setText("Error!");
		}
	}
	
	private boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
    
    
}
