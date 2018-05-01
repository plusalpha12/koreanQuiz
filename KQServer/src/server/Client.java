package server;

import java.io.Serializable;

public class Client{
	
	private transient InitialGameRoom room = null;
	private int roomnum = 0;
	private String userid = null;
	private String username = null;
	private int Score = 0;

	public Client() {}

	public Client(String userid, String username)
	{
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

	public String getUserid() {return userid;}

	public String getUsername() {return username;}
}