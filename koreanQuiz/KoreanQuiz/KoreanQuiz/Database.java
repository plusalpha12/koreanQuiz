import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	Connection conn;
	public Database() {
		System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String strUrl = "jdbc:mysql://localhost:3306/jspdb";
		try {
			conn = DriverManager.getConnection(strUrl, secret_Data.getDB_admin(), secret_Data.getDB_passwd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
}
