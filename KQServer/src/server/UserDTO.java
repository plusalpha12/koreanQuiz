package server;

import java.net.Socket;

public class UserDTO { public UserDTO() {}

private String UserId = null;
private String Userpwd = null;
private String UserName = null;
private Socket socket = null;
private int Score = 0;

private String newUserId = null;
private String newUserpwd = null;
private String newUsername = null;

public String getUserId() {	return UserId;}

public void setUserId(String userId) { UserId = userId; }

public String getUserpwd() {return Userpwd;}

public void setUserpwd(String userpwd) { Userpwd = userpwd; }

public String getUserName() {return UserName;}

public void setUserName(String userName) { UserName = userName; }

public Socket getSocket() {return socket;}

public void setSocket(Socket socket) { this.socket = socket; }

public int getScore() {return Score;}

public void setScore(int score) { Score = score; }


public String getNewUserId(){return newUserId;}

public void setNewUserId(String newUserId) { this.newUserId = newUserId; }

public String getNewUserpwd() {	return newUserpwd;}

public void setNewUserpwd(String newUserpwd) { this.newUserpwd = newUserpwd; }

public String getNewUsername() {return newUsername;}

public void setNewUsername(String newUsername) { this.newUsername = newUsername; }
}