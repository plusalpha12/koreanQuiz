package server;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class InitialGameManager {
	private ArrayList roomlist = new ArrayList();
	private ArrayList<Client> c = new ArrayList<Client>();
	private AtomicInteger atomic = new AtomicInteger();
	private Server_thread st = null;

	public InitialGameRoom createRoom(Server_thread st, ArrayList<Client> c) {
		int roomnum = atomic.incrementAndGet();
		this.st = st;
		this.c = c;
		InitialGameRoom room = new InitialGameRoom(st, roomnum);
		roomlist.add(room);
		room.enterUser(c);
		room.broadcast();
		
		System.out.println("게임 방 생성");
		
		return room;
	}
	
	public void removeRoom(InitialGameRoom room) {
		room.close();
		roomlist.remove(room);
	}
}
