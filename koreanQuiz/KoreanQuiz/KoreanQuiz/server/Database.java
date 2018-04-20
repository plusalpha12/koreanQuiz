package server;
import java.sql.*;

public class Database {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	private UserDTO dto;

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

	public void Join_user(String UserID, String UserPasswd, String UserName) {
		try {
			PreparedStatement ps 
			= conn.prepareStatement("insert userdata set NULL, UserID=?, UserPasswd=?, UserName=?");
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
	public boolean IdCheck(String id) {
		boolean idcheck = false;
		UserDTO user = new UserDTO();

		try {
			ps = conn.prepareStatement("select password from user where userid = ?");

			ps.setString(1, id);
			rs = ps.executeQuery();

			System.out.println("1");

			if(rs.next()) {
				idcheck = true;
			}

		}catch (SQLException e) {e.printStackTrace();}
		finally {	// 체크 후 뒤처리
			if (rs != null) try { rs.close(); } catch(SQLException e) {}
			if (ps != null) try { ps.close(); } catch(SQLException e) {}
			if (conn != null) try { conn.close(); } catch(SQLException e) {}
		}
		return idcheck;
	}

	public boolean PwdCheck(String id, String password) {
		boolean logincheck = false;
		String pwd = new String("");
		try {
			ps = conn.prepareStatement("select password from user where userid = ?");

			ps.setString(1, id);
			rs = ps.executeQuery();

			if(rs.next()) {
				pwd = rs.getString("password");
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
			if (conn != null) try { conn.close(); } catch(SQLException e) {}
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
