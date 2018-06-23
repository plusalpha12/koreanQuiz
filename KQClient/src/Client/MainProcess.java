package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import InitialGame.AchieveView;
import InitialGame.IGameView;
import SentenceGame.SGameView;
import serial.sendclient;


public class MainProcess{
	private Socket socket = null;
	private ArrayList<String> userData = null;
	private ArrayList<sendclient> ClientList = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private String logincheck = null;
	
	private IGameView igame = null;
	private SGameView sgame = null;
	private AchieveView aview = null;
	private UserDTO dto = null;
	
	ArrayList<String> wordlist = new ArrayList<String>();
	ArrayList<String> defilist = new ArrayList<String>();
	ArrayList<String> textlist = new ArrayList<String>();
	ArrayList<String> coSentences = null; //Correct
	ArrayList<String> wrWords = null; //Wrong 

	// Ŭ���̾�Ʈ ��׶��� �ʱ�ȭ -
	MainProcess() {
		try {
			//222.238.181.109
			//192.168.35.43
			socket = new Socket("192.168.35.43", 6060);
			System.out.println("���� ����");

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	//�α��� ��� -
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
			System.out.println("������ ���� �Ϸ�");

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
				if(socket != null) try{socket.close();} catch(IOException e1){}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			if(socket != null) try{socket.close();} catch(IOException e){}
		}
	}

	//�̴ϼ� ���� ��׶���
	@SuppressWarnings("unchecked")
	public void InitialGameBack(boolean single) {
		int i = 0;
		igame = new IGameView(this);
		ClientList = new ArrayList<sendclient>();
		Receive_msg_thread rmt = new Receive_msg_thread(igame, socket);
		textlist = new ArrayList<String>();

		try {
			if(single)
				textlist.add(0, "initial_single");
			else
				textlist.add(0, "initial");
			oos.writeObject(textlist);
			oos.flush();

			System.out.println("���� ���� �Ϸ�");

			ois = new ObjectInputStream(socket.getInputStream());
			try {
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
		textlist.clear();
	}

	public void AchieveBack() {
		textlist = new ArrayList<String>();

		try {
			textlist.add("achieve");
			oos.writeObject(textlist);
			oos.flush();
			
		} catch (IOException e) {e.printStackTrace();}
		aview = new AchieveView(this);
		textlist.clear();
	}
	
	@SuppressWarnings("unchecked")
	public void AchieveWord(int i) {
		textlist = new ArrayList<String>();
		wordlist = new ArrayList<String>();
		switch(i) {
		case 0:			textlist.add("��");			break;
		case 1:			textlist.add("��");			break;
		case 2:			textlist.add("��");			break;
		case 3:			textlist.add("��");			break;
		case 4:			textlist.add("��");			break;
		case 5:			textlist.add("��");			break;
		case 6:			textlist.add("��");			break;
		case 7:			textlist.add("��");			break;
		case 8:			textlist.add("��");			break;
		case 9:			textlist.add("��");			break;
		case 10:		textlist.add("��");			break;
		case 11:		textlist.add("��");			break;
		case 12:		textlist.add("��");			break;
		case 13:		textlist.add("��");			break;
		case 14:		textlist.add("��");			break;
		case 15:		textlist.add("��");			break;
		case 16:		textlist.add("��");			break;
		case 17:		textlist.add("��");			break;
		case 18:		textlist.add("��");			break;
		}
		try {
			oos.writeObject(textlist);
			oos.flush();
			System.out.println("�ʼ� ����");
			try {
				wordlist = (ArrayList<String>)ois.readObject();
				System.out.println(wordlist);
				defilist = (ArrayList<String>)ois.readObject();
				System.out.println(defilist);
				
				aview.setWord(wordlist, defilist);
			} catch (ClassNotFoundException e) {e.printStackTrace();}	
		} catch (IOException e1) {e1.printStackTrace();}
		textlist.clear();
	}

	@SuppressWarnings("unchecked")
	public void SentenceGameBack() {
		coSentences = new ArrayList<String>(); //Correct
		wrWords = new ArrayList<String>(); //Wrong 
		textlist = new ArrayList<String>();

		try {
			textlist.add(0, "sentence");
			oos.writeObject(textlist);
			oos.flush();

			System.out.println("���� ���� �Ϸ�");

			try {
				coSentences = (ArrayList<String>)ois.readObject();
				wrWords = (ArrayList<String>)ois.readObject();


			} catch (ClassNotFoundException e) {e.printStackTrace();}
		} catch (IOException e1) {e1.printStackTrace();}

		sgame = new SGameView(coSentences, wrWords);
		textlist.clear();
	}

	public void send_chat(String text) {
		textlist = new ArrayList<String>();

		if(text.equals("close")) {
			textlist.add(text);
			try {
				oos.writeObject(textlist);
				oos.flush();
				System.out.println("�ؽ�Ʈ ���� �Ϸ�");

			} catch (IOException e) {e.printStackTrace();}

		}else if(text.equals("ready") || text.equals("ready_cancle")) {
			textlist.add(text);
			try {
				oos.writeObject(textlist);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			textlist.add("chat");
			textlist.add(dto.getUserId() + " : ");
			textlist.add(text);
			try {
				oos.writeObject(textlist);
				oos.flush();
				System.out.println("�ؽ�Ʈ ���� �Ϸ�");

			} catch (IOException e) {e.printStackTrace();}
		}
	}
	
	public void close_user() {
		textlist = new ArrayList<String>();
		textlist.add("close");
		textlist.add(dto.getUserId());

		try {
			oos.writeObject(textlist);
			oos.flush();
			System.out.println("���� ���� �Ϸ�");


		} catch (IOException e) {
			e.printStackTrace();
		}

		textlist.clear();
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
	@SuppressWarnings("unchecked")
	public void run() {
		ArrayList<String> textlist = new ArrayList<String>();
		while(true){
			try {
				ois = new ObjectInputStream(soc.getInputStream());
				textlist = (ArrayList<String>) ois.readObject();
				System.out.println(textlist);

				if(textlist.get(0).equals("chat")) {
					igame.set_msg(textlist.get(1)+textlist.get(2)+"\n");
					System.out.println("��ȭ ����");

				}else if(textlist.get(0).equals("join")){
					textlist.remove(0);
					for(String text : textlist) {
						igame.set_msg(text + "\n");
					}
					System.out.println("���� ���� �ȳ�");

				}else if(textlist.get(0).equals("start")){
					igame.game_start(textlist.get(1));

				}else if(textlist.get(0).equals("quiz")){
					System.out.println(textlist);
					igame.set_msg(textlist.get(1)+textlist.get(2)+textlist.get(3)+"\n");
					igame.set_userscore(textlist.get(5), textlist.get(4));
					igame.set_quiz(textlist.get(6));
					System.out.println("�ʼ�����");

				}else if(textlist.get(0).equals("close")){
					System.out.println("���� ����");
					break;
				}
			}
			catch (ClassNotFoundException e){e.printStackTrace(); break;}
			catch (IOException|NullPointerException e) {e.printStackTrace(); break;}
		}
		System.out.println("�ʼ� ���� ����");
	}
}

