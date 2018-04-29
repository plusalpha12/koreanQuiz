package server;
import java.sql.*;

public class Database {
	private static Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private UserDTO dto = null;

	public Database(UserDTO dto) {
		
		this.dto = dto;
		
		try { Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {e.printStackTrace();}

		String strUrl = "jdbc:mysql://localhost:3306/korean_q_server?verifyServerCertificate=false&useSSL=true&autoReconnect=true";
		String db_id = secret_Data.getDB_admin();
		String db_pwd = secret_Data.getDB_passwd();
		
		try {
			conn = DriverManager.getConnection(strUrl, db_id, db_pwd);
			System.out.println("db start");
		} catch (SQLException e) {
			System.out.println("test failed");
			e.printStackTrace();
		}			
	}

	public void Join_user(String UserID, String UserPasswd, String UserName) {
		ps = null;
		
		try {
			ps = conn.prepareStatement("insert userdata set NULL, UserName=?, UserID=?, UserPasswd=?, 2");
			ps.setString(1, UserID);
			ps.setString(2, UserPasswd);
			ps.setString(3, UserName);

			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {	// 眉农 饶 第贸府
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
		}
	}

	// 殿废 酒捞叼 蜡公 眉农
	public boolean IdCheck(String id) {
		boolean idcheck = false;
		rs = null;
		ps = null;
	
		try {
			ps = conn.prepareStatement("select * from userdata where UserID = ?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();

			if(rs.next()) {
				idcheck = true;
			}
		}catch (SQLException e) {
			System.out.println("db testfailed");
			e.printStackTrace();
			}
		finally {	// 眉农 饶 第贸府
			if (rs != null) try { rs.close(); } catch(SQLException e) {}
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
		}
		return idcheck;
	}
	public boolean nameCheck(String name) {
		boolean namecheck = false;
		rs = null;
		ps = null;
	
		try {
			ps = conn.prepareStatement("select * from userdata where UserName = ?");
			ps.setString(1, name);
			
			rs = ps.executeQuery();

			if(rs.next()) {
				namecheck = true;
			}
		}catch (SQLException e) {
			System.out.println("db testfailed");
			e.printStackTrace();
			}
		finally {	// 眉农 饶 第贸府
			if (rs != null) try { rs.close(); } catch(SQLException e) {}
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
		}
		return namecheck;
	}

	public boolean PwdCheck(String id, String password) {
		boolean logincheck = false;
		String pwd = "";
		rs = null;
		ps = null;
		try {
			ps = conn.prepareStatement("select * from userdata where UserID = ?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();

			if(rs.next()) {
				pwd = rs.getString("UserPasswd");
				if(pwd.equals(password)) {
					logincheck = true;
					dto.setUserId(rs.getString("UserID"));
					dto.setUserName(rs.getString("UserName"));
				}
			}

		}catch (SQLException e) {e.printStackTrace();}

		finally {	// 眉农 饶 第贸府
			if (rs != null) try { rs.close(); } catch(SQLException e) {}
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
		}
		return logincheck;
	}

	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		conn.close();

		super.finalize();
	}
}
