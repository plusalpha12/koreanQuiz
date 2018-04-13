package server;
import java.sql.*;

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

		String strUrl = "jdbc:mysql://localhost:3306/Korean_Q_server";
		try {
			conn = DriverManager.getConnection(strUrl, secret_Data.getDB_admin(), secret_Data.getDB_passwd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	public void Join_user(String UserName, String UserID, String UserPasswd) {
		try {
			PreparedStatement ps 
			= conn.prepareStatement("insert userdata set UserName=?, UserID=?, UserPasswd=?, NULL");
			ps.setString(1, UserName);
			ps.setString(2, UserID);
			ps.setString(3, UserPasswd);
			
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Login_user(String UserID, String UserPasswd) {
		
	}
	
	/*public UserModel selectUser(String where) {
		UserModel user = new UserModel();		
		try {
			PreparedStatement ps 
			= conn.prepareStatement("select * from user where userid=?");
			ps.setString(1, where);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			user.setUserid(rs.getString("userid"));  
			user.setPassword(rs.getString("password")); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return user;
	}*/
}
