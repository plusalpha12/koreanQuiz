package server;

import java.net.Socket;

public class Client{

	private InitialGameRoom room = null;
	private Socket soc = null;
	private int roomnum = 0;
	private String userid = null;
	private String username = null;
	private int Score = 0;
	private int ComboCount = 0;
	private boolean ready = false;

	public Client(Socket soc) {this.soc = soc;}

	public Client(String userid, String username, Socket soc)
	{
		this.userid = userid;
		this.username = username;
		this.soc = soc;
	}

	public void ready_stat(boolean ready) {
		this.ready = ready;
	}

	public boolean getReady() {
		return ready;
	}

	public void enterRoom(InitialGameRoom room) {
		room.enterUser(this);
		this.room = room;
	}

	public void exitRoom() {
		this.room = null;
	}

	public int getComboCount() {
		return ComboCount;
	}

	public void setComboCount(boolean reset) {
		if(reset) 
			ComboCount = 0;
		else 
			ComboCount += 1;
	}

	public int getScore() {
		return Score;
	}

	public void setScore(int score) {
		if(score != 0)
			Score += score;
		else {
			Score = 0;
		}
	}

	public String getUserid() {return userid;}
	public String getUsername() {return username;}
	public Socket getSocket() {return soc;}
	public InitialGameRoom getRoom() {return room;}
}