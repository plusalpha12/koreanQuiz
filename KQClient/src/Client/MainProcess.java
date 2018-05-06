package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import InitialGame.IGameView;
import serial.sendclient;


public class MainProcess{
	private Socket socket = null;
	private ArrayList<String> userData = null;
	private ArrayList<sendclient> ClientList = null;
	private static ObjectOutputStream oos = null;
	private static ObjectInputStream ois = null;
	private String logincheck = null;
	private Client client = null;
	private IGameView igame = null;
	private UserDTO dto = null;

	// 클라이언트 백그라운드 초기화 -
	MainProcess() {
		try {
			//222.238.181.109
			//192.168.35.121
			socket = new Socket("192.168.35.121", 6060);
			System.out.println("서버 연결");

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	//로그인 기능 -
	public void LoginBack(UserDTO dto) {
		this.dto = dto;
		try {
			userData = new ArrayList<String>();
			logincheck = "";

			String userid = new String(dto.getUserId());
			String userpwd = new String(dto.getUserpwd());

			userData.add(0, "userlogin");
			userData.add(1, userid);
			userData.add(2, userpwd);

			oos.writeObject(userData);
			oos.flush();
			System.out.println("데이터 전송 완료");

			try {
				logincheck = (String)ois.readObject();
				System.out.println(logincheck);

				if(logincheck.length() > 0) {
					if(logincheck.equals("login")) {
						dto.setLogincheck(true, 1);
						logincheck = "";
					}else if(logincheck.equals("id")){
						dto.setLogincheck(false, 2);
						logincheck = "";
					}else if(logincheck.equals("password")) {
						dto.setLogincheck(false, 3);
						logincheck = "";
					}else {	}				
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	//이니셜 게임 백그라운드
	public void InitialGameBack() {
		int i = 0;
		igame = new IGameView(this);
		ArrayList<String> text = new ArrayList<String>();
		ClientList = new ArrayList<sendclient>();
		Receive_msg_thread rmt = new Receive_msg_thread(igame, socket);

		try {
			text.add(0, "initial");
			oos.writeObject(text);
			oos.flush();

			System.out.println("게임 전송 완료");

			try {
				ois = new ObjectInputStream(socket.getInputStream());
				
				ClientList = (ArrayList<sendclient>)ois.readObject();

				for(sendclient user : ClientList) {
					user = ClientList.get(i);
					igame.set_userdata(i, user);
					i++;
				}
				
				rmt.start();

				igame.setTitle(dto.getUserId() + " - InitialGame!");

				igame.setVisible(true);

			} catch (ClassNotFoundException e) {e.printStackTrace();}
		} catch (IOException e1) {e1.printStackTrace();}
	}

	public void send_chat(String text) {
		ArrayList<String> textlist = new ArrayList<String>();

		if(text.equals("close")) {
			textlist.add(text);
			try {
				oos.writeObject(textlist);
				oos.flush();
				System.out.println("텍스트 전송 완료");

			} catch (IOException e) {e.printStackTrace();}

		}else if(text.equals("ready")) {
			textlist.add(text);
			try {
				oos.writeObject(textlist);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(text.equals("ready_cancle")) {
			textlist.add(text);
			try {
				oos.writeObject(textlist);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			textlist.add("chat");
			textlist.add(dto.getUserId() + " : " + text);
			try {
				oos.writeObject(textlist);
				oos.flush();
				System.out.println("텍스트 전송 완료");

			} catch (IOException e) {e.printStackTrace();}
		}
	}
	public void close_user() {
		ArrayList<String> textlist = new ArrayList<String>();
		textlist.add("close");
		textlist.add(dto.getUserId());

		try {
			oos.writeObject(textlist);
			oos.flush();
			System.out.println("종료 전송 완료");


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void exit() {
		if (ois != null) try { ois.close(); } catch(IOException e) {}
		if (oos != null) try { oos.close(); } catch(IOException e) {}
		if (socket != null) try { socket.close(); } catch(IOException e) {}
	}
}

class Receive_msg_thread extends Thread{
	private IGameView igame = null;
	private Socket soc = null;
	private static ObjectInputStream ois = null;

	Receive_msg_thread(IGameView igame, Socket soc){
		this.igame = igame;
		this.soc = soc;
	}
	public void run() {
		ArrayList<String> textlist = new ArrayList<String>();
		while(true){
			try {
				ois = new ObjectInputStream(soc.getInputStream());
				textlist = (ArrayList<String>) ois.readObject();;
				System.out.println(textlist);
				
				if(textlist.get(0).equals("chat")) {
					igame.set_msg(textlist.get(1)+"\n");
					System.out.println("대화 수신");
					
				}else if(textlist.get(0).equals("start")){
					igame.game_start(textlist.get(1));
					System.out.println("게임 시작");
					
				}else if(textlist.get(0).equals("close")){
					System.out.println("게임 종료");
					for(String text : textlist) {
						igame.set_msg(text);
					}
					Thread.interrupted();
					break;
				} else {
					igame.set_msg(textlist.get(1)+"\n");
				}
			}

			catch (ClassNotFoundException e){e.printStackTrace();}
			catch (IOException|NullPointerException e) {e.printStackTrace(); break;}
		}
	}
}

