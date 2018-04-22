package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;


public class MainProcess{
	private Socket socket = null;
	private LoginView loginView;
	private ArrayList userData = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private String logincheck = null;


	public void LoginGui(LoginView loginView) {
		this.loginView = loginView;
	}

	public static void main(String[] args)  throws IOException {
		MainProcess mainp = new MainProcess();
		UserDTO dto = new UserDTO();
		mainp.backgroundClient(dto);
	}

	// 클라이언트 백그라운드
	public void backgroundClient(UserDTO dto) {
		userData = new ArrayList();
		logincheck = "";

		try {
			socket = new Socket("localhost", 6060);
			System.out.println("서버 연결");

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			
			String userid = new String(dto.getUserId());
			String userpwd = new String(dto.getUserpwd());

			userData.add(0, "userlogin");
			userData.add(1, userid);
			userData.add(2, userpwd);

			try {
				oos.writeObject(userData);
				oos.flush();
				System.out.println("데이터 전송 완료");
				
				ois = null;

				ois = new ObjectInputStream(socket.getInputStream());

				try {
					logincheck = (String)ois.readObject();
					System.out.println(logincheck);

					if(logincheck.length() > 0) {
						if(logincheck.equals("login")) {
							dto.setLogincheck(true, 1);
						}else if(logincheck.equals("id")){
							dto.setLogincheck(false, 2);
						}else if(logincheck.equals("password")) {
							dto.setLogincheck(false, 3);
						}else {	}				
					}

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			} catch(IOException e) {
				e.printStackTrace();
			}



			//LoginThread ct = new LoginThread(socket);
			//ct.start();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			if (ois != null) try { ois.close(); } catch(IOException e) {}
			if (oos != null) try { oos.close(); } catch(IOException e) {}
			if (socket != null) try { socket.close(); } catch(IOException e) {}
		}
	}
}

/*
 * class LoginThread extends Thread{
	ArrayList userData = new ArrayList();
	private InputStream is = null;
	private OutputStream os = null;
	private UserDTO dto;

	public LoginThread(Socket soc) {
		try {
			os = new DataOutputStream(soc.getOutputStream());
			is = new DataInputStream(soc.getInputStream());
			userData.add(dto.getUserId());
			userData.add(dto.getUserpwd());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		DataList d = new DataList();
		try {
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}
}
 */
