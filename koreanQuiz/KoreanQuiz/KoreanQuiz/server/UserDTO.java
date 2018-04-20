package server;

public class UserDTO {

	private String UserId = null;
	private String Userpwd = null;
	private String newUserId = null;
	private String newUserpwd = null;
	private String newUsername = null;
	private String newUserbirth = null;
	private int loginerror = 0;

	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserpwd() {
		return Userpwd;
	}
	public void setUserpwd(String userpwd) {
		Userpwd = userpwd;
	}
	public String getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}
	public String getNewUserpwd() {
		return newUserpwd;
	}
	public void setNewUserpwd(String newUserpwd) {
		this.newUserpwd = newUserpwd;
	}
	public String getNewUsername() {
		return newUsername;
	}
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}
	public String getNewUserbirth() {
		return newUserbirth;
	}
	public void setNewUserbirth(String newUserbirth) {
		this.newUserbirth = newUserbirth;
	}
	public int getLoginerror() {
		return loginerror;
	}
	public void setLoginerror(int loginerror) {
		this.loginerror = loginerror;
	}

}
