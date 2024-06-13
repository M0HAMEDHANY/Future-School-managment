package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentSceneController {
	private Stage stage;
	private Scene scene;

	@FXML
	private TableView<Student> tableView;

	public void initialize() {

		ObservableList<Student> students = FXCollections.observableArrayList();

		try {
			Connection connection = connectionToDB.connectToDB();
			String sql = "SELECT * FROM students";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String username = resultSet.getString("username");
				String phoneNumber = resultSet.getString("phone_number");
				String email = resultSet.getString("email");
				String level = resultSet.getString("level");
				students.add(new Student(id, username, phoneNumber, email, level));
			}

			TableColumn<Student, String> idColumn = new TableColumn<>("ID");
			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

			TableColumn<Student, String> userNameColumn = new TableColumn<>("Username");
			userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));

			TableColumn<Student, String> phoneNumberColumn = new TableColumn<>("Phone Number");
			phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

			TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
			emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

			TableColumn<Student, String> levelColumn = new TableColumn<>("Level");
			levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

			tableView.setItems(students);
			tableView.getColumns().addAll(idColumn, userNameColumn, phoneNumberColumn, emailColumn, levelColumn);

			resultSet.close();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void switchToHomeScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("HomeSence.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
