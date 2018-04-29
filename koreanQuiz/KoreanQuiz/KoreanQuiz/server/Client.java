package server;

import java.net.Socket;

public class Client {
	
	private int roomnum = 0;
	private InitialGameRoom room = null;
	private String userid = null;
	private String username = null;
	private int Score = 0;
	
	public Client() {
		}
	
	public Client(String userid, String username) {
		this.userid = userid;
		this.username = username;
	}
	
	public void enterRoom(InitialGameRoom room) {
		room.enterUser(this);
		this.room = room;
	}
	
	public void exitRoom(InitialGameRoom room) {
		this.room = null;
	}

}
