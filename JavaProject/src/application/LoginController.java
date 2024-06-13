package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button loginButton;

	private Connection connection;
	private PreparedStatement statement;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@FXML
	private void loginAction(ActionEvent event) throws IOException {
		if (validateFields()) {
			try {
				connection = connectionToDB.connectToDB();

				String sql = "SELECT * FROM students WHERE email=? AND password=?";
				statement = connection.prepareStatement(sql);
				statement.setString(1, emailField.getText());
				statement.setString(2, passwordField.getText());

				ResultSet UpdatededData = statement.executeQuery();

				if (UpdatededData.next()) {
					showSuccessAlert("Login Successful!");
					switchToHomeScene(event);
				} else {
					showErrorAlert("Failed to login. Please try again.");
				}

			} catch (SQLException ex) {
				showErrorAlert("Error logining in. Please try again.");
				Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error logining in user", ex);
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException ex) {
					Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Error closing resources", ex);
				}
			}
		}
	}

	public void switchToHomeScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomeSence.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSignUp(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private boolean validateFields() {
		String email = emailField.getText();
		String password = passwordField.getText();

		if (email.isEmpty() || password.isEmpty()) {
			showErrorAlert("All fields are required.");
			return false;
		}

		if (!isValidEmail(email)) {
			showErrorAlert("Invalid email format.");
			return false;
		}

		return true;
	}

	private boolean isValidEmail(String email) {

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailRegex);
	}

	private void showErrorAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showSuccessAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Success");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}