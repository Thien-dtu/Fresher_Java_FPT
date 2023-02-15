package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String HOSTNAME = "DESKTOP-R564B2C";
	private static final String PORT = "1433";
	private static final String DATABASE = "CANDIDATE";
	private static final String URL = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + "; databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true;";
	private static final String USER = "thien";
	private static final String PW = "thien123456";
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static boolean check = true;

	/**
	 * Gets the connection.
	 * 
	 * Create date : Feb 15, 2023 10:53:32 PM
	 * Author : Create by ThienBD
	 * BirthDay : 2000_03_02
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);

			Connection conn = DriverManager.getConnection(URL, USER, PW);
			if (check) {
				System.out.println("KET NOI THANH CONG");
				check = false;
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("KET NOI THAT BAI");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("KET NOI THAT BAI");
		}
		return null;
	}
}