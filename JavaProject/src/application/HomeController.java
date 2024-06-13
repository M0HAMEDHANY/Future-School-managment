package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void initialize() {

	}

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToStdScene(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("StudentScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	public void switchToDashScene(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		System.out.println("hi");
	}
	
	
	public void switchToSubjectScene(ActionEvent event) throws IOException {

		root = FXMLLoader.load(getClass().getResource("Subject.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		System.out.println("hi");
	}
	
	
	
	
}
