package Client;

import java.net.Socket;

public class Client {
	
	private int roomnum = 0;
	private String userid = null;
	private String username = null;
	private int Score = 0;
	
	public Client() {
		}
	
	public Client(String userid, String username) {
		this.userid = userid;
		this.username = username;
	}
	
	public String getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
}
