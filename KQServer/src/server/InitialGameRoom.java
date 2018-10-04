package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import serial.sendclient;

public class InitialGameRoom {

	private ObjectOutputStream oos2 = null;
	private ArrayList<Client> userlist = null;
	private ArrayList<sendclient> userlist2 = null;
	private sendclient user = null;
	private int roomnum = 0;
	private ArrayList<String> textlist = new ArrayList<String>();
	private ArrayList<String> answer = new ArrayList<String>();
	private ArrayList<String> chosung2 = new ArrayList<String>();
	private ArrayList<String> chosung = new ArrayList<String>();
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
		textlist.clear();
	}

	public void exitUser(Client client) {
		textlist = new ArrayList<String>();
		client.exitRoom();
		userlist.remove(client);
		System.out.println("유저리스트 : " + userlist);
		textlist.add("exit");
		broadcast(textlist);
		if(userlist.size() < 1) {
			InitialGameManager.removeRoom(this);
		}
		textlist.clear();
	}

	public void room_close() {
		for (Client client : userlist) {
			client.exitRoom();
		}
		userlist.clear();
		userlist = null;
	}

	public void broadcast(ArrayList<String> text) {	

		if(text.get(0).equals("join")) {
			System.out.println("브로드 캐스트" + text);
			for (Client client : userlist) {
				text.add(client.getUsername() + "님께서 입장하셨습니다.");
			}
			for(Client client : userlist) { //
				Send_msg(text, client);
			}
		}else if(text.get(0).equals("chat")){
			System.out.println("브로드 캐스트" + text);
			int i = 0;
			for(Client client : userlist) {
				System.out.println(i + " " + client.getSocket());
				Send_msg(text, client);
				i++;
			}
		}else if (text.get(0).equals("start") || text.get(0).equals("quiz")){
			System.out.println("브로드 캐스트" + text);
			quiz = Game.getRandomString(2);
			text.add(quiz);
			for (Client client : userlist) {
				Send_msg(text, client);
			}
		}
		else if (text.get(0).equals("exit")){ //
			System.out.println("브로드 캐스트" + text);
			for (Client client : userlist) {
				System.out.println(client + "에게 " + text);
				Send_msg(text, client);
			}
		}
	}

	public void Send_userdata(ArrayList<sendclient> c, Socket soc2) {
		try {
			oos2 = new ObjectOutputStream(soc2.getOutputStream());
			oos2.writeObject(c);
			oos2.flush();
			System.out.println("Send_userdata");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void game_start() { //

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
			oos2 = new ObjectOutputStream(sclient.getSocket().getOutputStream());
			oos2.writeObject(textlist);
			oos2.flush();
			System.out.println("send_msg");
		}
		catch (IOException e) {	e.printStackTrace(); }
	}

	public void ans_check(char[] ans, ArrayList<String> textlist, Client c) {
		int tmp;
		StringBuilder cho = new StringBuilder("");
		for(int i = 0; i < ans.length; i++) {
			tmp = ((ans[i] - '가')/(28*21));
			if(tmp >= 0) {
				cho.append(Chosung[tmp]);
			}
		}
		if(cho.toString().equals(quiz)) {
			if(XmlParser.xmlParsing(textlist.get(2))) {	//
				answer.add(textlist.get(2));
				chosung.add(cho.toString().substring(0, 1));
				chosung2.add(cho.toString());
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
				textlist.set(0, "quiz");
				textlist.add(" - 정답" + c.getComboCount() + "콤보!");
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
	public ArrayList<String> getWordList(){
		return answer;
	}
	public ArrayList<String> getChosung(){
		return chosung;
	}
	public ArrayList<String> getChosung2(){
		return chosung2;
	}
}