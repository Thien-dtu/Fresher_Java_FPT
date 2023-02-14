package main.java.com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	public static Connection getConnect() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbURL = "jdbc:sqlserver://localhost;database=BJVoption2;Encrypt=True;TrustServerCertificate=True;user=sa;password=1234;";
			connection = DriverManager.getConnection(dbURL);
//			if(connection!= null) System.out.println("connect");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
