package application.Scenes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import application.Objects.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FirstSceneController {
	
	@FXML
	private Label DatabaseCheckerLabel;

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void getAdminLoginScene(ActionEvent event) {
		
		try {
			root = FXMLLoader.load(getClass().getResource("AdminLoginScreen.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getSecretaryLoginScene(ActionEvent event) {
		
		try {
			root = FXMLLoader.load(getClass().getResource("SecretaryLoginScreen.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void statusDB(ActionEvent event) throws SQLException, ClassNotFoundException {
		DatabaseConnector connector = new DatabaseConnector();
		Connection connectDB = connector.getConnection();
		if(!connectDB.isClosed()) {
			DatabaseCheckerLabel.setText("Connected");
		} else {
			DatabaseCheckerLabel.setText("Not Connected");
		}
		
	}
}
