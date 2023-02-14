package connect_jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
/**
 * Hàm kết nối với database
 * @author DucNH59
 * @date 2023-02-07
 * @return
 */
	public static Connection getConnection() {
		final String USER = "thien";
		final String PASSWORD = "thien123456";
		final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Assignment_Candidate_Managerment;Encrypt=True;TrustServerCertificate=True;user=" + USER
				+ ";password=" + PASSWORD;
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
