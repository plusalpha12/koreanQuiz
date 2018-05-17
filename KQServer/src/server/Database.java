package server;

import java.sql.SQLException;
import java.util.ArrayList;

public class Database { 
	private static java.sql.Connection conn = null;
	private java.sql.PreparedStatement ps = null;
	private java.sql.ResultSet rs = null;
	private UserDTO dto = null;

	public Database(UserDTO dto) {
		this.dto = dto;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) { e.printStackTrace();
		}
		String strUrl = "jdbc:mysql://localhost:3306/korean_q_server?verifyServerCertificate=false&useSSL=true&autoReconnect=true";
		String db_id = secret_Data.getDB_admin();
		String db_pwd = secret_Data.getDB_passwd();
		try
		{
			conn = java.sql.DriverManager.getConnection(strUrl, db_id, db_pwd);
			System.out.println("db start");
		} catch (SQLException e) {
			System.out.println("test failed");
			e.printStackTrace();
		}
	}

	public void Join_user(String UserID, String UserPasswd, String UserName) {
		ps = null;
		try
		{
			ps = conn.prepareStatement("insert userdata value(NULL, ?, ?, ?, 1)");
			ps.setString(1, UserName);
			ps.setString(2, UserID);
			ps.setString(3, UserPasswd);

			ps.executeUpdate();

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();


			if (ps != null) try { ps.close(); } catch (SQLException localSQLException1) {} } finally { if (ps != null) try { ps.close();
			} catch (SQLException localSQLException2) {}
			}
	}

	public boolean IdCheck(String id) {
		boolean idcheck = false;
		rs = null;
		ps = null;
		try
		{
			ps = conn.prepareStatement("select * from userdata where UserID = ?");
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				idcheck = true;
			}
		} catch (SQLException e) {
			System.out.println("db testfailed");
			e.printStackTrace();


			if (rs != null) try { rs.close(); } catch (SQLException localSQLException1) {}
			if (ps != null) try { ps.close();
			}
			catch (SQLException localSQLException2) {}
		}
		finally
		{
			if (rs != null) try { rs.close(); } catch (SQLException localSQLException3) {}
			if (ps != null) try { ps.close();
			} catch (SQLException localSQLException4) {} }
		return idcheck;
	}

	public boolean nameCheck(String name) { boolean namecheck = false;
	rs = null;
	ps = null;
	try
	{
		ps = conn.prepareStatement("select * from userdata where UserName = ?");
		ps.setString(1, name);

		rs = ps.executeQuery();

		if (rs.next()) {
			namecheck = true;
		}
	} catch (SQLException e) {
		System.out.println("db testfailed");
		e.printStackTrace();


		if (rs != null) try { rs.close(); } catch (SQLException localSQLException1) {}
		if (ps != null) try { ps.close();
		}
		catch (SQLException localSQLException2) {}
	}
	finally
	{
		if (rs != null) try { rs.close(); } catch (SQLException localSQLException3) {}
		if (ps != null) try { ps.close();
		} catch (SQLException localSQLException4) {} }
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

			if (rs.next()) {
				pwd = rs.getString("UserPasswd");
				if (pwd.equals(password)) {
					logincheck = true;
					dto.setUserId(rs.getString("UserID"));
					dto.setUserName(rs.getString("UserName"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();


			if (rs != null) try { rs.close(); } catch (SQLException localSQLException1) {}
			if (ps != null) try { ps.close();
			}
			catch (SQLException localSQLException2) {}
		}
		finally
		{
			if (rs != null) try { rs.close(); } catch (SQLException localSQLException3) {}
			if (ps != null) try { ps.close();
			} catch (SQLException localSQLException4) {} }
		return logincheck;
	}

	public void SelectSen(ArrayList<String> co, ArrayList<String> wr) {
		rs = null;
		ps = null;
		try {
			ps = conn.prepareStatement("select * from sentence");
			rs = ps.executeQuery();
			
			while(rs.next()){
				co.add(rs.getString(1));
				wr.add(rs.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void finalize() throws Throwable
	{
		conn.close();

		super.finalize();
	}
}