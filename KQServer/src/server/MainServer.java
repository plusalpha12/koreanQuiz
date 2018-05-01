package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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


	public MainServer() {
		igm = new InitialGameManager();
		map = new HashMap<String, Client>();
		wuser = new ArrayList<Client>();

		try {
			server = new ServerSocket(6060);

			System.out.println("1 서버 생성");

			while(true) {
				System.out.println("2 서버 연결 대기");
				client = server.accept();

				Server_thread ST = new Server_thread(client, igm, map, wuser);
				ST.run();
			}
		}catch (SocketException|NullPointerException se) {
			se.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		MainServer main = new MainServer();
	} 
}

class Server_thread extends Thread{

	private Socket soc = null;
	private InitialGameManager igm = null;
	private HashMap<String, Client> map = null;
	private ArrayList<Client> wuser = null;
	private Database db = null;

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	private ArrayList<String> userdata = null;
	private UserDTO dto = null;
	private Client c = null;

	Server_thread(Socket soc, InitialGameManager igm, HashMap<String, Client> map, ArrayList<Client> wuser) {
		this.soc = soc;
		this.igm = igm;
		this.map = map;
		this.wuser = wuser;
		dto = new UserDTO();
		db = new Database(dto);
		try {
			System.out.println("클라이언트 연결");
			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());
		}
		catch (SocketException|NullPointerException se) {
			System.out.println("클라이언트 종료 1");
			try { soc.close();}
			catch (IOException e) {e.printStackTrace();}
		} catch (IOException e) {e.printStackTrace();}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void run() {
		ArrayList<String> textlist = new ArrayList<String>();
		try	{
			while(true) {
				userdata = (ArrayList<String>)ois.readObject();

				if (userdata.size() > 0)
				{
					if (userdata.get(0).equals("userjoin")) {
						userjoin();
					}
					else if (userdata.get(0).equals("userlogin")) {
						userlogin();
					}
					else if (userdata.get(0).equals("initial")) {
						InitialGameRoom room = null;
						wuser.add(c);
						if(wuser.size() > 3 && wuser.size() <= 4 || Timeout()) {
							room = igm.createRoom(this, wuser);
							System.out.println(((Client)wuser.get(0)).getUserid() + ((Client)wuser.get(0)).getUsername());
							System.out.println("유저 입장");
						}
						int i = 0;
						while(i < 5) {
							i++;
							try {
								textlist = (ArrayList<String>)ois.readObject();

								if(textlist.get(0).equals("chat")) {
									room.broadcast(textlist);

								}else if(textlist.get(0).equals("answer")) {

								}else if(textlist.get(0).equals("close")) {
									room.exitUser(c);
									soc.close();
									break;
								}else {

								}
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) { e.printStackTrace();}
						}break;
					}
					else{
						userdata.get(0).equals("sentence");
					}
				}
			}
		}
		catch (ClassNotFoundException e){e.printStackTrace();}
		catch (IOException|NullPointerException e) {
			System.out.println("클라이언트 종료 2");
			try {soc.close();}
			catch (IOException e1) { e1.printStackTrace();}
		}
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
					c = new Client(dto.getUserId(), dto.getUserName());
				} else {
					System.out.println("비밀번호");
					oos.writeObject("password");
					oos.flush();
				}
			}
			else {
				System.out.println("아이디 중복");
				oos.writeObject("id");
				oos.flush();
			}
		} catch (IOException e) { e.printStackTrace();
		}
	}

	public void userjoin() { String newuserid = new String(userdata.get(1).toString());
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
			db.Join_user(newuserid, newuserpwd, newusername);
			oos.writeObject("join");
			oos.flush();
		}
	} catch (IOException e) { e.printStackTrace();
	}
	}

	private boolean Timeout() { for (int i = 10; i > 0; i--) {
		try {
			Thread.sleep(1000);
			System.out.println(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	return true;
	}

	public void Send_userdata(ArrayList<sendclient> c) {
		try {
			oos.writeObject(c);
			oos.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Send_msg(ArrayList<String> textlist) {
		try {
			oos.writeObject(textlist);
			oos.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings({ "unused" })
	public void receive_chat()
	{
		try {
			String textlist = "";
			textlist = (String)ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { e.printStackTrace();
		}
	}
}
