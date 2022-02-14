package application.Scenes;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminSceneController {
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    public void getCoursesScene(ActionEvent event) {
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
    
    public void getAddSecretaryScene(ActionEvent event) {
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
    
    public void getCreateSessionScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("CreateSessionScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		}
    
    public void getReportScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("ReportScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		}
}
