package serial;

import java.io.Serializable;

public class sendclient implements Serializable {
	
	private String userid = null;
	private String username = null;
	
	public sendclient() {
	}

	public sendclient(String userid, String username) {
		this.userid = userid;
		this.username = username;
	}

	public String getUserid() {
		// TODO Auto-generated method stub
		return userid;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
}
