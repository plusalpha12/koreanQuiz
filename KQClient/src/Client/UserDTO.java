package Client;

public class UserDTO {

	private String UserId = null;
	private String Userpwd = null;
	private String newUserId = null;
	private String newUserpwd = null;
	private String newUsername = null;
	private String newUserbirth = null;
	private boolean logincheck = false;
	private boolean Joincheck = false;
	private int Lcheck = 1;
	private int Jcheck = 1;

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
	
	
	public boolean isLogincheck() {
		return logincheck;
	}
	public void setLogincheck(boolean logincheck, int check) {
		this.logincheck = logincheck;
		this.Lcheck = check;
	}
	public int getLcheck() {
		return Lcheck;
	}
	public boolean isJoincheck() {
		return Joincheck;
	}
	public void setJoincheck(boolean joincheck, int check) {
		Joincheck = joincheck;
		this.Jcheck = check;
	}
	public int getJcheck() {
		return Jcheck;
	}

}
