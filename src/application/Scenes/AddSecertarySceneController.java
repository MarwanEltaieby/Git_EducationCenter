package application.Scenes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import application.Objects.DatabaseConnector;
import application.Objects.Secretary;
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

public class AddSecertarySceneController implements Initializable {

    @FXML
    private Button BackButton;

    @FXML
    private TableColumn<Secretary, String> InstructorUsernameColumn;

    @FXML
    private TableColumn<Secretary, String> SecretaryUsernameColumn;

    @FXML
    private TableColumn<Secretary, String> SecretaryPasswordColumn;

    @FXML
    private TableView<Secretary> SecretaryTableView;

    @FXML
    private ChoiceBox<String> InstructorUsernameChoiceBox;

    @FXML
    private TextField SecretaryPasswordTextfield;

    @FXML
    private TextField SecretaryUsernameTextfield;

    @FXML
    private Button SubmitButton;

    @FXML
    private Label invalidLabel;
    
    private ObservableList<Secretary> list = FXCollections.observableArrayList();
    private ObservableList<String> intructorList = FXCollections.observableArrayList();
    

    private Parent root;
    private Stage stage;
    private Scene scene;
    
    
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
			root = FXMLLoader.load(getClass().getResource("AddSecretaryScene.fxml"));
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
			int selectedSecretary = SecretaryTableView.getSelectionModel().getSelectedIndex();
			Secretary selected = list.get(selectedSecretary);
			String sql = "DELETE FROM secretary WHERE secretary_username = '" + selected.getUsername() + "';";
			st.executeUpdate(sql);
			SecretaryTableView.getItems().remove(selectedSecretary);
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void submitSecretary(ActionEvent event) {
    	try {
			String instructor = InstructorUsernameChoiceBox.getValue();
			String secretary = SecretaryUsernameTextfield.getText();
			String password = SecretaryPasswordTextfield.getText();
			DatabaseConnector connector = new DatabaseConnector();
			Connection connection = connector.getConnection();
		
			String insertSecretary = "INSERT INTO secretary VALUE('" + secretary + "', '" + instructor + "', '" + password + "');";
			
			Statement statement =connection.createStatement();
			statement.executeUpdate(insertSecretary);
			invalidLabel.setText("");
			root = FXMLLoader.load(getClass().getResource("AddSecretaryScene.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
			invalidLabel.setText("Error!");
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			DatabaseConnector connector = new DatabaseConnector();
			Connection con = connector.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM secretary;";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String instructor = rs.getString(2);
				String secretaryUsername = rs.getString(1);
				String password = rs.getString(3);
				Secretary secretary = new Secretary(instructor, secretaryUsername, password);
				list.add(secretary);
			}	
			
			sql = "SELECT instructor_username FROM instructor;";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String instructorUsername = rs.getString(1);
				intructorList.add(instructorUsername);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SecretaryTableView.setItems(list);
		InstructorUsernameColumn.setCellValueFactory(new PropertyValueFactory<Secretary, String>("instructorUsername"));
		SecretaryUsernameColumn.setCellValueFactory(new PropertyValueFactory<Secretary, String>("username"));
		SecretaryPasswordColumn.setCellValueFactory(new PropertyValueFactory<Secretary, String>("password"));
		
		InstructorUsernameChoiceBox.setItems(intructorList);
	}

}

