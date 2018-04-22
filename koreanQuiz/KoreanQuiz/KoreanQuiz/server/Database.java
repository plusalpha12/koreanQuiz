package server;
import java.sql.*;

public class Database {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private UserDTO dto;

	public Database() {
		
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
			ps = conn.prepareStatement("insert userdata set NULL, UserName=?, UserID=?, UserPasswd=?, 1");
			ps.setString(1, UserID);
			ps.setString(2, UserPasswd);
			ps.setString(3, UserName);

			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 등록 아이디 유무 체크
	public static boolean IdCheck(String id) {
		boolean idcheck = false;
		ResultSet rs = null;
		ps = null;
		
		System.out.println("db test");

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
		finally {	// 체크 후 뒤처리
			if (rs != null) try { rs.close(); } catch(SQLException e) {}
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
		}
		return idcheck;
	}

	public boolean PwdCheck(String id, String password) {
		boolean logincheck = false;
		String pwd = "";
		ResultSet rs = null;
		ps = null;
		try {
			ps = conn.prepareStatement("select UserPasswd from userdata where UserID = ?");
			ps.setString(1, id);
			
			rs = ps.executeQuery();

			if(rs.next()) {
				pwd = rs.getString("UserPasswd");
				if(pwd.equals(password)) {
					logincheck = true;
				}
				else {
					// 입력한 비밀번호가 다를 시 출력
				}
			}

		}catch (SQLException e) {e.printStackTrace();}

		finally {	// 체크 후 뒤처리
			if (rs != null) try { rs.close(); } catch(SQLException e) {}
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
		}
		return logincheck;
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

	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		conn.close();

		super.finalize();
	}
}
