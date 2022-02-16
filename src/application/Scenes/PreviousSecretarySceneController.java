package application.Scenes;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PreviousSecretarySceneController {
	
	private Parent root;
	private Scene scene;
	private Stage stage;

	
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
	
	public void getStudentRegistrationScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StudentRegistrationScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void getAttendanceScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Attendance.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void getPaymentScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("PaymentScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
