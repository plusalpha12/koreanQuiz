package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class MainProcess{
	private Socket socket = null;
	private ArrayList<String> userData = null;
	private static ObjectOutputStream oos = null;
	private static ObjectInputStream ois = null;
	private String logincheck = null;

	// Ŭ���̾�Ʈ ��׶���
	MainProcess() {
		try {
			socket = new Socket("192.168.35.121", 6060);
			System.out.println("���� ����");

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(socket.getInputStream());

		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void LoginBack(UserDTO dto) {
		
		try {
			userData = new ArrayList<String>();
			
			String userid = new String(dto.getUserId());
			String userpwd = new String(dto.getUserpwd());
			
			userData.add(0, "userlogin");
			userData.add(1, userid);
			userData.add(2, userpwd);
			logincheck = "";
			
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
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void InitialGameBack() {

	}
	
	public void exit() {
		if (ois != null) try { ois.close(); } catch(IOException e) {}
		if (oos != null) try { oos.close(); } catch(IOException e) {}
		if (socket != null) try { socket.close(); } catch(IOException e) {}
	}
}

