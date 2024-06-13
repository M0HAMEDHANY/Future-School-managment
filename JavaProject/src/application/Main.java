package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Future School Managment");
			//Image image = new Image("school.png");
			//primaryStage.getIcons().add(image);
			Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
			Scene scene = new Scene(root, 850, 500);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
