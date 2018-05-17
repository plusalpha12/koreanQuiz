package server;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class InitialGameManager {

	private static ArrayList<InitialGameRoom> roomlist = null;
	private ArrayList<Client> userlist = null;
	private Client client = null;
	private AtomicInteger atomic = new AtomicInteger();

	public InitialGameManager() {
		roomlist = new ArrayList<InitialGameRoom>();
		userlist = new ArrayList<Client>();
	}
	public InitialGameRoom createRoom(Client client) {	// 혼자하기
		ArrayList<String> textlist = new ArrayList<String>();
		int roomnum = atomic.incrementAndGet();
		this.client = client;
		
		InitialGameRoom room = new InitialGameRoom(roomnum);
		
		roomlist.add(room);
		room.enterUser(client);
		textlist.add("play");
		room.broadcast(textlist);
		
		System.out.println("게임 룸 : " + roomnum + " 생성");
		
		return room;
	}

	public InitialGameRoom createRoom(ArrayList<Client> c) { // 멀티 매칭시
		ArrayList<String> textlist = new ArrayList<String>();
		int roomnum = atomic.incrementAndGet();
		this.userlist = c;
		int i = 0;
		
		InitialGameRoom room = new InitialGameRoom(roomnum);
		
		roomlist.add(room);
		System.out.println("유저 수 : " + c.size());
		room.enterUser(c);
		
		System.out.println("게임 룸 : " + roomnum + " 생성");

		return room;
	}

	public static void removeRoom(InitialGameRoom room) {
		room.room_close();
		roomlist.remove(room);
	}
	public ArrayList<InitialGameRoom> getRoomlist(){
		return roomlist;
	}
}
