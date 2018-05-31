package server;

public class secret_Data {

	private static String DB_admin = "root";
	private static String DB_passwd = "remake12tjqj!@";

	public static String getDB_admin() {
		return DB_admin;
	}

	public static void setDB_admin(String dB_admin) { DB_admin = dB_admin; }

	public static String getDB_passwd() {
		return DB_passwd;
	}

	public static void setDB_passwd(String DB_passwd) { DB_passwd = DB_passwd; }
}