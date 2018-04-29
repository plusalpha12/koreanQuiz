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

			System.out.println("1 ���� �غ�");
			System.out.println("2 Ŭ���̾�Ʈ ���");

			while (true) {

				Socket client = server.accept();
				Server_thread ST = new Server_thread(client, igm, map, wuser);
				ST.run();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
}

// Ŭ���̾�Ʈ ���� ������
class Server_thread extends Thread{

	//private BufferedReader	br;			// Ŭ���̾�Ʈ�κ��� ���޹��� �޼����� �о���� ���۸޸𸮸� ���� ����
	//private PrintWriter		pw;			// Ŭ���̾�Ʈ�� �޼����� ���� ����Ʈ ������
	private Socket soc = null;	// Ŭ���̾�Ʈ ����
	private InitialGameManager igm = null;
	private HashMap<String, Client> map = null;
	private ArrayList<Client> wuser = null;
	
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
	}

	public void run() {
		userdata = new ArrayList();
		Database db = new Database(dto);

		try{
			System.out.println("Ŭ���̾�Ʈ ����");

			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());

			try {
				userdata = (ArrayList)ois.readObject();


				if(userdata.size() > 0) {

					if(userdata.get(0).equals("userjoin")) {
						
						String newuserid = new String(userdata.get(1).toString());
						String newuserpwd = new String(userdata.get(2).toString());
						String newusername = new String(userdata.get(3).toString());
						
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
					}else if(userdata.get(0).equals("userlogin")) {
						System.out.println(userdata.get(0));

						String userid = new String(userdata.get(1).toString());
						String userpwd = new String(userdata.get(2).toString());

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
					}
					
					else if (userdata.get(0).equals("initial")) {
						wuser.add(c);
						System.out.println("���� ���");
						if(wuser.size()>3 || Timeout()) {
							InitialGameRoom igroom = igm.createRoom(this, wuser);
							System.out.println("������� Ŭ���̾�Ʈ ����");
						}

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
		}
		catch(SocketException | NullPointerException se){
			System.out.println("Ŭ���̾�Ʈ ����");
			//System.exit(0);
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (ois != null) try { ois.close(); } catch(IOException e) {}
			if (oos != null) try { oos.close(); } catch(IOException e) {}
			if (soc != null) try { soc.close(); } catch(IOException e) {}
		}
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
}
