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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

	ObservableList<Integer> levels = FXCollections.observableArrayList(1, 2, 3, 4);

	@FXML
	private TextField userNameField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField emailField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField confirmPasswordField;
	@FXML
	private Button signUpButton;
	@FXML
	private ChoiceBox<Integer> levelField;

	@FXML
	public void initialize() {
		levelField.setItems(levels);
	}

	private Connection connection;
	private PreparedStatement statement;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		levelField.setItems(levels);

	}

	@FXML
	private void signupAction(ActionEvent event) throws IOException {

		String phoneNumber = phoneNumberField.getText();
		String email = emailField.getText();
		if (validateFields()) {
			try {
				connection = connectionToDB.connectToDB();

				if (isUserExists(connection, email, phoneNumber)) {
					showErrorAlert("User already exists!");
					return;
				}

				String sql = "INSERT INTO students (username, phone_number, email, level, password) VALUES (?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				statement.setString(1, userNameField.getText());
				statement.setString(2, phoneNumberField.getText());
				statement.setString(3, emailField.getText());
				statement.setInt(4, levelField.getValue());
				statement.setString(5, passwordField.getText());

				int UpdatededData = statement.executeUpdate();

				if (UpdatededData > 0) {
					showSuccessAlert("Sign up Successful!");
					switchToLoginScene(event);
					clearData();
				}

				else {
					showErrorAlert("Failed to sign up. Please try again.");
					
				}

			} catch (SQLException ex) {
				showErrorAlert("Error signing up. Please try again.");
				Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, "Error signing up user", ex);

			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException ex) {
					Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, "Error closing resources", ex);
				}
			}
		}
	}

	private boolean isUserExists(Connection connection, String email, String phoneNumber) throws SQLException {
		String sql = "SELECT * FROM students WHERE email = ? OR phone_number = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, phoneNumber);
		ResultSet resultSet = statement.executeQuery();

		boolean exists = resultSet.next(); // Returns true if user exists; false otherwise

		resultSet.close();
		statement.close();

		return exists;
	}

	public void switchToLoginScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void clearData() {
		userNameField.setText("");
		phoneNumberField.setText("");
		emailField.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
		levelField.getSelectionModel().clearSelection();
	}

	public void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private boolean validateFields() {
		String userName = userNameField.getText();
		String phoneNumber = phoneNumberField.getText();
		String email = emailField.getText();
		String password = passwordField.getText();
		String confirmPassword = confirmPasswordField.getText();
		ObservableList<Integer> level = levelField.getItems(); 

		if (userName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty() || level.isEmpty()) {
			showErrorAlert("All fields are required.");
			return false;
		}

        if (phoneNumber.length() != 11) {
            showErrorAlert("Invalid phone number!");
            return false;
        }

		if (!isValidEmail(email)) {
			showErrorAlert("Invalid email format!");
			return false;
		}

        if (!password.equals(confirmPassword)) {
			showErrorAlert("Passwords do not match!");
			return false;
		}

        if (password.length() < 8) {
            showErrorAlert("Password is weak");
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