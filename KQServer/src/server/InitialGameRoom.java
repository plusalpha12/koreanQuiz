package server;

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
	public static char[] Chosung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138,
			0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
			0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

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
		for (Client client : c) {

			client.enterRoom(this);

			user = new sendclient(client.getUserid(), client.getUsername());
			userlist2.add(user);
		}
		for(Client client : c) {
			Send_userdata(userlist2, client.getSocket());			
		}

		textlist.add("join");
		broadcast(textlist);
		System.out.println("유저 입장 전달");
		textlist.clear();
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
		System.out.println(text);

		if(text.get(0).equals("join")) { // 게임 실행시
			for (Client client : userlist) {
				text.add(client.getUsername() + "님이 입장하셨습니다.");
			}
			for(Client client : userlist) { // 방 내의 모든 유저에게 전송
				Send_msg(text, client);
			}
		}else if(text.get(0).equals("chat")){ // 채팅이 입력되었을 경우
			int i = 0;
			for(Client client : userlist) {
				System.out.println(i + " " + client.getSocket());
				Send_msg(text, client);
				i++;
			}
		}else if (text.get(0).equals("start")){ // 게임이 시작되는 경우
			quiz = Game.getRandomString(2);
			text.add(quiz);
			for (Client client : userlist) {
				Send_msg(text, client);
			}
		}else if (text.get(0).equals("quiz")){ // 정답인경우
			quiz = Game.getRandomString(2);
			text.add(quiz);
			for (Client client : userlist) {
				Send_msg(text, client);
			}
		}
		else if (text.get(0).equals("exit")){ // 유저가 종료했을 경우
			for (Client client : userlist) {
				Send_msg(text, client);
			}
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

	public void game_start() { // 클라이언트가 준비 될 때마다 실행하는걸로

		int i = 0;
		for(Client client : userlist) {
			if(client.getReady()) i++;
		}
		if(i == userlist.size()) {
			textlist.add("start");
			broadcast(textlist);
		}
		textlist.clear();
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

	public void ans_check(char[] ans, ArrayList<String> textlist, Client c) {

		StringBuilder cho = new StringBuilder("");
		for(int i = 0; i < ans.length; i++) {
			int tmp = ((ans[i] - '가')/(28*21));
			if(tmp >= 0) {
				cho.append(Chosung[tmp]);
				System.out.println(tmp);
				System.out.println(quiz);
				System.out.println(cho);
			}
		}
		if(cho.toString().equals(quiz)) {
			if(XmlParser.xmlParsing(textlist.get(2))) {	// 정답일 경우
				for(Client client : userlist) {
					if(client.getComboCount() != 0) {
						if(client == c) {
							continue;
						}else {
							client.setComboCount(true);
						}
					}
				}
				c.setComboCount(false);
				if(c.getComboCount()<5)
					c.setScore(c.getComboCount()*10);
				else
					c.setScore(50);

				System.out.println(c.getUserid() + " : " + c.getScore());
				textlist.set(0, "quiz");
				textlist.add(" - 정답 " + c.getComboCount() + "콤보!");
				textlist.add(c.getUserid());
				textlist.add(String.valueOf(c.getScore()));
				broadcast(textlist);				
			}else
				broadcast(textlist);			
		}else {
			broadcast(textlist);
		}
		textlist.clear();
	}

}