package server;

import java.util.ArrayList;

public class InitialGameRoom {

	private ArrayList<Client> userlist = null;
	private int roomnum = 0;
	private Server_thread st = null;

	public InitialGameRoom(Server_thread st, int num) {
		this.st = st;
		roomnum = num;
	}

	public void enterUser(Client c) {
		c.enterRoom(this);
		userlist.add(c);
	}

	public void enterUser(ArrayList<Client> c) {
		int i = 0;
		for (Client client : c) {
			client.enterRoom(this);
		}
	}

	public void exitUser(Client client) {
		client.exitRoom(this);
		userlist.remove(client);
	}

	public void close() {
		for (Client client : userlist) {
			client.exitRoom(this);
		}
		userlist.clear();
		userlist = null;
	}

	public void broadcast() {
		for (Client client : userlist) {
			st.Send_userdata(client);
		}
	}
}