package application.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private Connection databaseLink;
	
	public Connection getConnection() throws ClassNotFoundException {
		
		String username = "root";
		String password = "12345678";
		String databaseName = "education_center";
		String url = "jdbc:mysql://localhost/" + databaseName;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			e.getCause();
		}
		return databaseLink;
	}
}
