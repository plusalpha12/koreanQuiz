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

	public static void main(String[] args) {

		try(ServerSocket server = new ServerSocket(6060)) {

			System.out.println("1 서버 준비");
			System.out.println("2 클라이언트 대기");

			while (true) {

				Socket client = server.accept();
				Server_thread ST = new Server_thread(client, igm);
				ST.run();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
}

// 클라이언트 관리 스레드
class Server_thread extends Thread{

	//private BufferedReader	br;			// 클라이언트로부터 전달받은 메세지를 읽어들일 버퍼메모리를 가진 리더
	//private PrintWriter		pw;			// 클라이언트로 메세지를 보낼 프린트 라이터
	private Socket soc = null;	// 클라이언트 소켓
	private HashMap<String, Socket> userlist = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private ArrayList userdata = null;
	private HashMap<String, Client> map = null;
	private ArrayList<Client> wuser = null;
	private UserDTO dto = null;
	private Client c = null;
	private InitialGameManager igm = null;

	Server_thread(Socket soc, InitialGameManager igm){
		this.soc = soc;
		this.igm = igm;
		oos = null;
		ois = null;
		dto = new UserDTO();
	}

	public void run() {
		userdata = new ArrayList();
		Database db = new Database(dto);

		try{
			System.out.println("클라이언트 연결");

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
							System.out.println("이미 존재하는 아이디");
							oos.writeObject("dupid");
							oos.flush();
						}else if(db.nameCheck(newusername)) {
							System.out.println("이미 존재하는  닉네임");
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
								System.out.println("승인");
								oos.writeObject("login");
								oos.flush();
								System.out.println(dto.getUserId() + dto.getUserName());
							}else {
								System.out.println("비밀번호");
								oos.writeObject("password");
								oos.flush();
							}
						}else {
							//아이디가 존재하지 않을 시
							System.out.println("아이디");
							oos.writeObject("id");
							oos.flush();
						}
					}
					
					else if (userdata.get(0).equals("initial")) {
						wuser.add(c);
						System.out.println("유저 대기");
						if(wuser.size()>3 || Timeout()) {
							InitialGameRoom igroom = igm.createRoom(wuser);
							System.out.println("대기중인 클라이언트 입장");
						}

						//문장 맞추기
					}else if(userdata.get(0).equals("sentence")) {

					}else {
						/*
		    	           br = new BufferedReader(new InputStreamReader(soc.getInputStream()));	// 클라이언트로부터 데이터를 읽어올 준비를 합니다
		    	           pw = new PrintWriter(soc.getOutputStream());							// 클라이언트로 데이터를 보낼 준비를 합니다

		    	           String readData = "";

		    	           while(!(readData = br.readLine()).equals(null)){				//상대쪽에서 접속을 끊을때까지 기다립니다.
		    	                System.out.println("from Client > "+ readData);			//클라리언트가 보낸 메세지를 읽습니다.
		    	                pw.println(readData);									//읽은 메세지 그대로 클라이언트 한테 보냅니다.
		    	                pw.flush();												//프린트라이터 메모리를 초기화 시킵니다. 이 메소드가 행해져야 실질적으로 데이터가 전송됨
		    	                }
						 */
					}
				}
			} catch(ClassNotFoundException e){e.printStackTrace();}
		}
		catch(SocketException | NullPointerException se){
			System.out.println("클라이언트 종료");
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
}
