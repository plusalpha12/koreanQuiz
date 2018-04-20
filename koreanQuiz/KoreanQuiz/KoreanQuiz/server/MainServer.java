package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MainServer {

	private ServerSocket server_Soc;	// ��������

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(ServerSocket server = new ServerSocket(6060)) {
			while (true) {
				System.out.println("1 ���� �غ�");
				System.out.println("2 Ŭ���̾�Ʈ ���");

				Socket client = server.accept();
				Server_thread ST = new Server_thread(client);

				ST.run();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
}

class Server_thread extends Thread{

	//private BufferedReader	br;			// Ŭ���̾�Ʈ�κ��� ���޹��� �޼����� �о���� ���۸޸𸮸� ���� ����
	//private PrintWriter		pw;			// Ŭ���̾�Ʈ�� �޼����� ���� ����Ʈ ������
	private Socket soc = null;	// Ŭ���̾�Ʈ ����
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private ArrayList userdata = null;
	private String logincheck = null;
	private Database db = null;

	Server_thread(Socket soc){
		this.soc = soc;
	}

	public void run() {
		userdata = new ArrayList();

		try{
			System.out.println("Ŭ���̾�Ʈ ����");

			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());
			
			try {
				userdata = (ArrayList)ois.readObject();

				if(userdata.size() > 0) {

					//�α��� Ŭ����
					if(userdata.get(0).equals("userlogin")) {

						String userid = new String(userdata.get(1).toString());
						String userpwd = new String(userdata.get(2).toString());

						System.out.println(userid + " " + userpwd);


						if(db.IdCheck(userid)) {
							System.out.println("����");
							if(db.PwdCheck(userid, userpwd)) {
								System.out.println("����");
							}else {
								//��й�ȣ�� Ʋ���� ��
								System.out.println("��й�ȣ");
								try {
									oos.writeObject("password");
									oos.flush();
								} catch(IOException e) { e.printStackTrace(); }
							}
						}else {
							//���̵� �������� ���� ��
							System.out.println("���̵�");
							oos.writeObject("id");
							oos.flush();
						}
						oos.writeObject("password");
						oos.flush();

						//ȸ������ Ŭ����
					}else if(userdata.get(0).equals("userjoin")){
						oos = new ObjectOutputStream(soc.getOutputStream());

						String userid = new String(userdata.get(1).toString());
						String userpwd = new String(userdata.get(2).toString());
						String username = new String(userdata.get(3).toString());

						db.Join_user(userid, userpwd, username);

						//�ʼ� ����
					}else if(userdata.get(0).equals("initial")) {
						oos = new ObjectOutputStream(soc.getOutputStream());
						/*
	    	           br = new BufferedReader(new InputStreamReader(soc.getInputStream()));	// Ŭ���̾�Ʈ�κ��� �����͸� �о�� �غ� �մϴ�
	    	           pw = new PrintWriter(soc.getOutputStream());							// Ŭ���̾�Ʈ�� �����͸� ���� �غ� �մϴ�

	    	           String readData = "";

	    	           while(!(readData = br.readLine()).equals(null)){				//����ʿ��� ������ ���������� ��ٸ��ϴ�.
	    	                System.out.println("from Client > "+ readData);			//Ŭ�󸮾�Ʈ�� ���� �޼����� �н��ϴ�.
	    	                pw.println(readData);										//���� �޼��� �״�� Ŭ���̾�Ʈ ���� �����ϴ�.
	    	                pw.flush();												//����Ʈ������ �޸𸮸� �ʱ�ȭ ��ŵ�ϴ�. �� �޼ҵ尡 �������� ���������� �����Ͱ� ���۵�
	    	                }
						 */

						//���� ���߱�
					}else if(userdata.get(0).equals("sentence")) {
						oos = new ObjectOutputStream(soc.getOutputStream());

					}else {

					}
				}
			} catch(ClassNotFoundException e){e.printStackTrace();}
		}
		catch(SocketException | NullPointerException se){
			System.out.println("Ŭ���̾�Ʈ�� ������ �����Ͽ� ���α׷��� �����մϴ�.");
			//System.exit(0);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (ois != null) try { ois.close(); } catch(IOException e) {}
			if (oos != null) try { oos.close(); } catch(IOException e) {}
			if (soc != null) try { soc.close(); } catch(IOException e) {}
		}
	}
}