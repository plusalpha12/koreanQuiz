package Client;

public class UserDTO {
	
	private String UserId;
	private String Userpwd;
	private String newUserId;
	private String newUserpwd;
	private String newUsername;
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
	
}
