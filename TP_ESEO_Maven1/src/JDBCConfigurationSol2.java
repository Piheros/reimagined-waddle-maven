

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfigurationSol2 {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://127.0.0.1/maven?user=root&password=password";
	static Connection connection = null;
	
	public static Connection getConnection() {
		try {
			System.out.println("Connection : " + DB_CONNECTION);
			
			Class.forName(DB_DRIVER);
			
			if(connection == null) {
				connection = DriverManager.getConnection(DB_CONNECTION);
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException ee) {
			ee.printStackTrace();
		}
		return connection;
	}
}
