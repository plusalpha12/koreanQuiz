package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainServer {

	private static InitialGameManager igm = null;
	private static HashMap<String, Client> map = null;
	private static ArrayList<Client> wuser = null;

	public static void main(String[] args) {

		igm = new InitialGameManager();
		map = new HashMap<String, Client>();
		wuser = new ArrayList<Client>();

		try(ServerSocket server = new ServerSocket(6060)) {
			while (true) {
				System.out.println("1 ���� �غ�");
				System.out.println("2 Ŭ���̾�Ʈ ���");
				Socket client = server.accept();
				System.out.println("3 Ŭ���̾�Ʈ ����");
				Server_thread ST = new Server_thread(client, igm, map, wuser);
				System.out.println("4 ������ ����");
				ST.run();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
}

// Ŭ���̾�Ʈ ���� ������
class Server_thread extends Thread{

	private Socket soc = null;	// Ŭ���̾�Ʈ ����
	private InitialGameManager igm = null;
	private HashMap<String, Client> map = null;
	private ArrayList<Client> wuser = null;
	private Database db = null;

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	private ArrayList userdata = null;
	private UserDTO dto = null;
	private Client c = null;

	Server_thread(Socket soc, InitialGameManager igm, HashMap<String, Client> map, ArrayList<Client> wuser){
		this.soc = soc;
		this.igm = igm;
		this.map = map;
		this.wuser = wuser;
		oos = null;
		ois = null;
		dto = new UserDTO();
		db = new Database(dto);
		try{
			System.out.println("Ŭ���̾�Ʈ ����");
			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());
		}
		catch(SocketException | NullPointerException se){
			System.out.println("Ŭ���̾�Ʈ ����");
			try {soc.close();
			}catch (IOException e) {e.printStackTrace();}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		userdata = new ArrayList();


		while(true) {
			try {
				userdata = (ArrayList)ois.readObject();

				if(userdata.size() > 0) {

					if(userdata.get(0).equals("userjoin")) {
						userjoin();

					}else if(userdata.get(0).equals("userlogin")) {
						userlogin();

					}else if (userdata.get(0).equals("initial")) {
						wuser.add(c);
						System.out.println(wuser.get(0).getUserid() + wuser.get(0).getUsername());
						System.out.println("���� ���");
						/*
						if((wuser.size() > 3 && wuser.size() <= 4) || Timeout()) {
							InitialGameRoom igroom = igm.createRoom(this, wuser);
							System.out.println("������� Ŭ���̾�Ʈ ����");
							while(wuser != null) {
								receive_chat();
							}
						}
						*/

						//���� ���߱�
					}else if(userdata.get(0).equals("sentence")) {

					}else {
						/*
		    	           br = new BufferedReader(new InputStreamReader(soc.getInputStream()));	// Ŭ���̾�Ʈ�κ��� �����͸� �о�� �غ� �մϴ�
		    	           pw = new PrintWriter(soc.getOutputStream());							// Ŭ���̾�Ʈ�� �����͸� ���� �غ� �մϴ�

		    	           String readData = "";

		    	           while(!(readData = br.readLine()).equals(null)){				//����ʿ��� ������ ���������� ��ٸ��ϴ�.
		    	                System.out.println("from Client > "+ readData);			//Ŭ�󸮾�Ʈ�� ���� �޼����� �н��ϴ�.
		    	                pw.println(readData);									//���� �޼��� �״�� Ŭ���̾�Ʈ ���� �����ϴ�.
		    	                pw.flush();												//����Ʈ������ �޸𸮸� �ʱ�ȭ ��ŵ�ϴ�. �� �޼ҵ尡 �������� ���������� �����Ͱ� ���۵�
		    	                }
						 */
					}
				}
			} catch(ClassNotFoundException e){e.printStackTrace();}
			catch (IOException | NullPointerException e) {
				e.printStackTrace();
				System.out.println("Ŭ���̾�Ʈ ����");
				try {soc.close();
				} catch (IOException e1) {e1.printStackTrace();	}
			}
		}
	}

	public void userlogin() {

		String userid = new String(userdata.get(1).toString());
		String userpwd = new String(userdata.get(2).toString());

		try {
			if(db.IdCheck(userid)) {
				if(db.PwdCheck(userid, userpwd)) {
					System.out.println("����");
					oos.writeObject("login");
					oos.flush();
					c = new Client(dto.getUserId(), dto.getUserName());
				}else {
					System.out.println("��й�ȣ");
					oos.writeObject("password");
					oos.flush();
				}
			}else {
				//���̵� �������� ���� ��
				System.out.println("���̵�");
				oos.writeObject("id");
				oos.flush();
			}
		}catch(IOException e) {e.printStackTrace();}
	}

	public void userjoin() {
		String newuserid = new String(userdata.get(1).toString());
		String newuserpwd = new String(userdata.get(2).toString());
		String newusername = new String(userdata.get(3).toString());

		try {
			if(db.IdCheck(newuserid)) {
				System.out.println("�̹� �����ϴ� ���̵�");
				oos.writeObject("dupid");
				oos.flush();
			}else if(db.nameCheck(newusername)) {
				System.out.println("�̹� �����ϴ�  �г���");
				oos.writeObject("dupname");
				oos.flush();
			}else{
				db.Join_user(newuserid, newuserpwd, newusername);
				oos.writeObject("join");
				oos.flush();
			}
		}catch(IOException e) {e.printStackTrace();}
	}

	private boolean Timeout() {
		for(int i = 10; i > 0; i--) {
			try {
				Thread.sleep(1000);
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public void Send_userdata(Client c) {
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			oos.writeObject(c);
			oos.flush();

		}catch(IOException e){
			e.printStackTrace();			
		}
	}

	public void receive_chat() {

		ArrayList textlist = new ArrayList();
		try {
			textlist = (ArrayList)ois.readObject();
		}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
}
