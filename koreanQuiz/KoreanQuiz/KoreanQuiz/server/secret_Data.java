package server;

public class secret_Data{
	
	static private String DB_admin = "root";
	static private String DB_passwd = "remake12tjqj!@";
	
	public static String getDB_admin() {
		return DB_admin;
	}
	public static void setDB_admin(String dB_admin) {
		secret_Data.DB_admin = dB_admin;
	}
	public static String getDB_passwd() {
		return DB_passwd;
	}
	public static void setDB_passwd(String DB_passwd) {
		secret_Data.DB_passwd = DB_passwd;
	}
	
}
