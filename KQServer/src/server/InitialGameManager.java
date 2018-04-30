package server;

import java.util.ArrayList;

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
	}
}
