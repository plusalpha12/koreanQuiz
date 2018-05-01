package server;

import java.util.ArrayList;

import serial.sendclient;

public class InitialGameRoom {

	private ArrayList<Client> userlist = null;
	private ArrayList<sendclient> userlist2 = null;
	private sendclient user = null;
	private int roomnum = 0;
	private Server_thread st = null;

	public InitialGameRoom(Server_thread st, int num) {
		this.st = st;
		roomnum = num;
		userlist = new ArrayList<Client>();
		userlist2 = new ArrayList<sendclient>();
		user = new sendclient();
	}

	public void enterUser(Client c) {
		userlist.add(c);
	}

	public void enterUser(ArrayList<Client> c) {
		int i = 0;
		for (Client client : c) {
			System.out.println(client.getUserid() + "" + client.getUsername());
			client.enterRoom(this);
			user = new sendclient(client.getUserid(), client.getUsername());
			userlist2.add(user);
		}
		System.out.println(c.get(0).getUserid());
		st.Send_userdata(userlist2);
	}

	public void exitUser(Client client) {
		ArrayList<String> textlist = new ArrayList<String>();
		client.exitRoom(this);
		userlist.remove(client);
		textlist.add("exit");
		broadcast(textlist);
	}

	public void close() {
		for (Client client : userlist) {
			client.exitRoom(this);
		}
		userlist.clear();
		userlist = null;
	}

	public void broadcast(ArrayList<String> text) {
		ArrayList<String> textlist = new ArrayList<String>();
		
		if(text.get(0).equals("play")) {
			for (Client client : userlist) {
				textlist.add(client.getUsername() + "¥‘¿Ã ¿‘¿Â«œºÃΩ¿¥œ¥Ÿ.\n");
			}
			st.Send_msg(textlist);
		}else if(text.get(0).equals("chat")){
			st.Send_msg(textlist);
		}else if (text.get(0).equals("exit")){
			for (Client client : userlist) {
				
			}
		}
	}
}