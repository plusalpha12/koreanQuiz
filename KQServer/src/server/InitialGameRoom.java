package server;

<<<<<<< HEAD
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import serial.sendclient;

public class InitialGameRoom {

	private ObjectOutputStream oos = null;
	private ArrayList<Client> userlist = null;
	private ArrayList<sendclient> userlist2 = null;
	private sendclient user = null;
	private int roomnum = 0;
	private ArrayList<String> textlist = new ArrayList<String>();
	private String quiz = "";

	public InitialGameRoom(int num) {
		roomnum = num;
		userlist = new ArrayList<Client>();
		userlist2 = new ArrayList<sendclient>();
		user = new sendclient();
	}

	public void enterUser(Client c) {
		userlist.add(c);
	}

	public void enterUser(ArrayList<Client> c) {
		ArrayList<String> textlist = new ArrayList<String>();
		for (Client client : c) {
			
			client.enterRoom(this);
			
			user = new sendclient(client.getUserid(), client.getUsername());
			userlist2.add(user);
		}
		for(Client client : c) {
			Send_userdata(userlist2, client.getSocket());			
		}
		
		textlist.add("join");
		//quiz = Game.getRandomString(2);
		//textlist.add(quiz);
		System.out.println(textlist);
		broadcast(textlist);
		System.out.println("유저 입장 전달");
	}

	public void exitUser(Client client) {
		ArrayList<String> textlist = new ArrayList<String>();
		client.exitRoom();
		userlist.remove(client);
		textlist.add("exit");
		broadcast(textlist);
		if(userlist.size() < 1) {
			InitialGameManager.removeRoom(this);
		}
	}

	public void room_close() {
		for (Client client : userlist) {
			client.exitRoom();
		}
		userlist.clear();
		userlist = null;
	}

	public void broadcast(ArrayList<String> text) {
		ArrayList<String> textlist = new ArrayList<String>();
		
		System.out.println(textlist);

		if(text.get(0).equals("join")) { // 게임 실행시
			for (Client client : userlist) {
				textlist.add("join");
				textlist.add(client.getUsername() + "님이 입장하셨습니다.");
			}
			for(Client client : userlist) { // 방 내의 모든 유저에게 전송
				Send_msg(textlist, client);
			}
		}
		else if(text.get(0).equals("chat")){ // 채팅이 입력되었을 경우
			int i = 0;
			for(Client client : userlist) {
				System.out.println(i + " " + client.getSocket());
				Send_msg(text, client);
				i++;
			}
		}
		else if (text.get(0).equals("exit")){ // 유저가 종료했을 경우
			for (Client client : userlist) {
				Send_msg(text, client);
			}
		}
		else if (text.get(0).equals("start")){
			text.add(Game.getRandomString(2));
			for (Client client : userlist) {
				Send_msg(text, client);
			}
		}
	}
	
	public void game_start() { // 클라이언트가 준비 될 때마다 실행하는걸로
		ArrayList<String> textlist = new ArrayList<String>();
		
		int i = 0;
		for(Client client : userlist) {
			if(client.getReady()) {
				i++;
			}
		}
		if(i == userlist.size()) {
			textlist.add("start");
			broadcast(textlist);
		}
	}
	
	public void Send_userdata(ArrayList<sendclient> c, Socket soc2) {
		try {
			oos = new ObjectOutputStream(soc2.getOutputStream());
			oos.writeObject(c);
			oos.flush();
			System.out.println("Send_userdata");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Send_msg(ArrayList<String> textlist, Client sclient) {
		try {
			oos = new ObjectOutputStream(sclient.getSocket().getOutputStream());
			oos.writeObject(textlist);
			oos.flush();
			System.out.println("send_msg");
		}
		catch (IOException e) {	e.printStackTrace(); }
	}
	
=======
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
>>>>>>> branch 'master' of https://github.com/plusalpha12/koreanQuiz.git
}