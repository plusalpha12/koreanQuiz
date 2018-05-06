package server;

import java.util.ArrayList;
<<<<<<< HEAD
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
	public InitialGameRoom createRoom(Client client) {
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

	public InitialGameRoom createRoom(ArrayList<Client> c) {
		ArrayList<String> textlist = new ArrayList<String>();
		int roomnum = atomic.incrementAndGet();
		this.userlist = c;
		int i = 0;
		
		InitialGameRoom room = new InitialGameRoom(roomnum);
		
		roomlist.add(room);
		room.enterUser(c);
		textlist.add("play");
		room.broadcast(textlist);
		
		System.out.println("게임 룸 : " + roomnum + " 생성");

		return room;
	}

	public static void removeRoom(InitialGameRoom room) {
		room.room_close();
		roomlist.remove(room);
	}
	public ArrayList<InitialGameRoom> getRoomlist(){
		return roomlist;
=======

public class InitialGameManager {

	private ArrayList<InitialGameRoom> roomlist = new ArrayList<InitialGameRoom>();
	private ArrayList<Client> c = new ArrayList<Client>();
	private java.util.concurrent.atomic.AtomicInteger atomic = new java.util.concurrent.atomic.AtomicInteger();
	private Server_thread st = null;

	public InitialGameManager() {}

	public InitialGameRoom createRoom(Server_thread st, ArrayList<Client> c) {
		int roomnum = atomic.incrementAndGet();
		this.st = st;
		this.c = c;
		InitialGameRoom room = new InitialGameRoom(st, roomnum);
		roomlist.add(room);
		room.enterUser(c);
		room.broadcast();

		System.out.println("게임 룸 생성");

		return room;
	}

	public void removeRoom(InitialGameRoom room) {
		room.close();
		roomlist.remove(room);
>>>>>>> branch 'master' of https://github.com/plusalpha12/koreanQuiz.git
	}
}
