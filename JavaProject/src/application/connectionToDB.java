package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connectionToDB {
	public static Connection connectToDB() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost/mysql";
			String username = "root";
			String password = "";
			connection = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(connectionToDB.class.getName()).log(Level.SEVERE, null, ex);

		}
		return connection;
	}

}
