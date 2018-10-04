package server;

public class ShareClass {
	private int loginUser, waitUser;
	
	public ShareClass() {
		loginUser = 0;
		waitUser = 0;
	}
	
	public synchronized int login_User(){
		++loginUser;
		return loginUser;
	}
	public synchronized int logout_User(){
		--loginUser;
		return loginUser;
	}
	public synchronized int waitingUser(){
		++waitUser;
		return waitUser;
	}
	public synchronized int endUser(){
		--waitUser;
		return waitUser;
	}
	public int getloginUser() {
		return loginUser;
	}
	public int getwaitUser() {
		return waitUser;
	}
}
