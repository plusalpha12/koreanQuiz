package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class JoinProcess{
	private JoinView joinview;
	private Socket socket = null;
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private ArrayList userData = null;
	private String Joincheck = null;

	public void JoinView(JoinView joinView) {
		this.joinview = joinView;
	}
	
	public static void main(String[] args)  throws IOException {
	}

	// 클라이언트 백그라운드 회원가입 스레드
	public void backgroundJoin(UserDTO dto) {
		userData = new ArrayList();
		Joincheck = "";
		oos = null;
		ois = null;

		try {
			socket = new Socket("192.168.35.121", 6060);

			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();

			String userid = new String(dto.getNewUserId());
			String userpwd = new String(dto.getNewUserpwd());
			String username = new String(dto.getNewUsername());

			userData.add(0, "userjoin");
			userData.add(1, userid);
			userData.add(2, userpwd);
			userData.add(3, username);
			
			try {
				oos.writeObject(userData);
				oos.flush();
				System.out.println("데이터 전송 완료");
				ois = new ObjectInputStream(socket.getInputStream());

				
				try {
					Joincheck = (String)ois.readObject();
					System.out.println(Joincheck);

					if(Joincheck.length() > 0) {
						if(Joincheck.equals("join")) {
							dto.setJoincheck(true, 1);
						}else if(Joincheck.equals("dupid")){
							dto.setJoincheck(false, 2);
						}else if(Joincheck.equals("dupname")) {
							dto.setJoincheck(false, 3);
						}else {}				
					}

				} catch (ClassNotFoundException e) {e.printStackTrace();}
			} catch(IOException e) {e.printStackTrace();}
		}catch(IOException e){e.printStackTrace();}
		finally {
			if (ois != null) try { ois.close(); } catch(IOException e) {}
			if (oos != null) try { oos.close(); } catch(IOException e) {}
			if (socket != null) try { socket.close(); } catch(IOException e) {}
		}
	}
}
