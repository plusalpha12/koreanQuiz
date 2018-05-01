package server;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class InitialGameManager {

	private ArrayList<InitialGameRoom> roomlist = null;
	private ArrayList<Client> c = null;
	private AtomicInteger atomic = new AtomicInteger();
	private Server_thread st = null;

	public InitialGameManager() {
		roomlist = new ArrayList<InitialGameRoom>();
		c = new ArrayList<Client>();
	}

	public InitialGameRoom createRoom(Server_thread st, ArrayList<Client> c) {
		ArrayList<String> textlist = new ArrayList<String>();
		int roomnum = atomic.incrementAndGet();
		this.st = st;
		this.c = c;
		InitialGameRoom room = new InitialGameRoom(st, roomnum);
		roomlist.add(room);
		room.enterUser(c);
		textlist.add("play");
		room.broadcast(textlist);

		System.out.println("게임 룸 생성");

		return room;
	}

	public void removeRoom(InitialGameRoom room) {
		room.close();
		roomlist.remove(room);
	}
}
