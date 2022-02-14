package application.Scenes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import application.Objects.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginScreenController {
	
	@FXML
	private TextField usernameTextfield;
	
	@FXML
	private TextField passwordTextfield;
	
	@FXML
	private Label invalidLabel;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void login(ActionEvent event) throws ClassNotFoundException {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connectDb = connector.getConnection();
		
		String verifyLogin = "SELECT count(1) FROM instructor WHERE instructor_username = '"
		+ usernameTextfield.getText() +"' AND instructor_password = '"+ passwordTextfield.getText() + "';";
		
		try {
			Statement statement =connectDb.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			
			while(queryResult.next()) {
				if(queryResult.getInt(1) == 1) {
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
				} else {
					invalidLabel.setText("Invalid username or password!");
				}
			}
			
			
		} catch (Exception e) {
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

}
