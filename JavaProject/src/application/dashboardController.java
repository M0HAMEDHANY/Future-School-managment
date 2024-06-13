package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class dashboardController implements Initializable {
	@FXML
	private Label studentsNumber;

	private Stage stage;
	private Scene scene;
	private Parent root;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		getTotalStudents();
	}

	public void switchToHomeScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("HomeSence.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private void getTotalStudents() {
		Connection connection = connectionToDB.connectToDB();
		int count = 0;
		String sql = "SELECT count(id) from students";
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				count = result.getInt("count(id)");
			}
			studentsNumber.setText(String.valueOf(count));

		} catch (SQLException ex) {
			Logger.getLogger(dashboardController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
