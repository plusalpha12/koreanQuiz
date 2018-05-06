package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import serial.sendclient;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

public class MainServer{
	private static InitialGameManager igm = null;
	private static HashMap<String, Client> map = null;
	private static ArrayList<Client> wuser = null;
	private ServerSocket server = null;
	private Socket client = null;


	public static void main(String[] args) {
		MainServer main = new MainServer();
		main.set_MainServer();
	} 

	public void set_MainServer() {
		igm = new InitialGameManager();
		map = new HashMap<String, Client>();
		wuser = new ArrayList<Client>();

		try {

			Collections.synchronizedMap(map);
			server = new ServerSocket(6060);

			System.out.println("1 서버 생성");

			while(true) {
				System.out.println("2 서버 연결 대기");
				client = server.accept();
				System.out.println(client.getInetAddress() + "에서 접속했습니다.");

				Server_thread ST = new Server_thread(client, igm, map, wuser);
				ST.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Server_thread extends Thread{

	private Socket soc = null;
	private InitialGameManager igm = null;
	private HashMap<String, Client> userlist = null;
	private static ArrayList<Client> wuser = null;
	private static ArrayList<Client> guser = null;
	private Database db = null;

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	private ArrayList<String> userdata = null;
	private UserDTO dto = null;
	private Client c = null;

	Server_thread(Socket soc, InitialGameManager igm, HashMap<String, Client> map, ArrayList<Client> w) {
		this.soc = soc;
		this.igm = igm;
		userlist = map;
		wuser = w;
		dto = new UserDTO();
		db = new Database(dto);
		try {
			System.out.println("클라이언트 연결");
			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());
		}
		catch (SocketException|NullPointerException se) {
			System.out.println("클라이언트 종료 ");
			try { soc.close();}
			catch (IOException e) {e.printStackTrace();}
		} catch (IOException e) {e.printStackTrace();}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void run() {
		ArrayList<String> textlist = new ArrayList<String>();
		String quiz = "";
		InitialGameRoom room = null;
		int i = 0;
		try	{
			while(true) {
				System.out.println("선택 대기");
				userdata = (ArrayList<String>)ois.readObject();

				if (userdata.size() > 0)
				{
					if (userdata.get(0).equals("userjoin")) {userjoin();}

					else if (userdata.get(0).equals("userlogin")) {userlogin();}

					else if (userdata.get(0).equals("initial")) {

						synchronized(wuser) {
							if(wuser.size() < 4) wuser.add(c);	// 이니셜게임 대기열에 입장
						}

						if((wuser.size() >= 2 && wuser.size() <= 4) || Timeout()) {

							synchronized(this) {
								if(igm.getRoomlist().size() < 1) {
									System.out.println("유저 접속");
									room = igm.createRoom(wuser);
									wuser.clear();
								}else if(igm.getRoomlist().size() >= 1 && c.getRoom() == null) {
									System.out.println("유저 접속2");
									room = igm.createRoom(wuser);
									wuser.clear();
								}
							}
						}

						while(true) {
							try {
								System.out.println("명령 대기");
								textlist = (ArrayList<String>)ois.readObject();

								if(textlist.get(0).equals("chat")) { // 채팅일 경우
									System.out.println(textlist);
									c.getRoom().broadcast(textlist);

								}else if(textlist.get(0).equals("answer")) { // 답을 입력받은 경우
									textlist.add("start");
									if(i<10) {
										quiz = Game.getRandomString(2);
									}else {
										quiz = Game.getRandomString(3);
									}
									textlist.add(quiz);
									c.getRoom().broadcast(textlist);

								}else if(textlist.get(0).equals("close")) { // 종료를 누른 경우
									c.getRoom().Send_msg(textlist, c);
									c.getRoom().exitUser(c);
									System.out.println(c.getUserid() + " 게임 종료");
									break;
									
								}else if(textlist.get(0).equals("ready")) { // 준비를 누른 경우
									c.ready_stat(true);
									System.out.println(c.getUserid() + " 게임 준비");
									
									c.getRoom().game_start();
									
								}else if(textlist.get(0).equals("ready_cancle")) { // 준비를 취소할 경우
									c.ready_stat(false);
									System.out.println(c.getUserid() + " 게임 준비 취소");
									
								}else {

								}
							} catch (ClassNotFoundException|NullPointerException e) {e.printStackTrace(); }
							catch (SocketException e) { e.printStackTrace(); break;}
						}
					}
				}
				else{userdata.get(0).equals("sentence");}
			}
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("클라이언트 종료 1");
			try {soc.close();}
			catch (IOException e1) { e1.printStackTrace();}
		}
		catch (IOException|NullPointerException e) {
			System.out.println("클라이언트 종료 2");
			try {soc.close();}
			catch (IOException e1) { e1.printStackTrace();}
		}
	}

	public void userjoin() {
		String newuserid = new String(userdata.get(1).toString());
		String newuserpwd = new String(userdata.get(2).toString());
		String newusername = new String(userdata.get(3).toString());
		try
		{
			if (db.IdCheck(newuserid)) {
				System.out.println("아이디 중복");
				oos.writeObject("dupid");
				oos.flush();
			} else if (db.nameCheck(newusername)) {
				System.out.println("닉네임 중복");
				oos.writeObject("dupname");
				oos.flush();
			} else {
				System.out.println("가입");
				db.Join_user(newuserid, newuserpwd, newusername);
				oos.writeObject("join");
				oos.flush();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}

	public void userlogin() {
		String userid = new String(userdata.get(1).toString());
		String userpwd = new String(userdata.get(2).toString());
		try
		{
			if (db.IdCheck(userid)) {
				if (db.PwdCheck(userid, userpwd)) {
					System.out.println("승인");
					oos.writeObject("login");
					oos.flush();
					c = new Client(dto.getUserId(), dto.getUserName(), soc);
					userlist.put(dto.getUserId(), c);
				} else {
					System.out.println("비밀번호");
					oos.writeObject("password");
					oos.flush();
					oos.close();
					ois.close();
					soc.close();
				}
			}
			else {
				System.out.println("아이디");
				oos.writeObject("id");
				oos.flush();
				oos.close();
				ois.close();
				soc.close();
			}
		} catch (IOException e) { e.printStackTrace();
		}
	}

	private boolean Timeout() {
		for (int i = 5; i > 0; i--) {
			try {
				Thread.sleep(1000);
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@SuppressWarnings({ "unused" })
	public void receive_chat()
	{
		try {
			String textlist = "";
			textlist = (String)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException|NullPointerException e) { e.printStackTrace(); }
	}
}
