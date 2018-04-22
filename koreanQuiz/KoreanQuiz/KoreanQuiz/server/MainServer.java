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

	private ServerSocket server_Soc;	// 서버소켓

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(ServerSocket server = new ServerSocket(6060)) {
			while (true) {
				System.out.println("1 서버 준비");
				System.out.println("2 클라이언트 대기");

				Socket client = server.accept();
				Server_thread ST = new Server_thread(client);

				ST.run();
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
}

class Server_thread extends Thread{

	//private BufferedReader	br;			// 클라이언트로부터 전달받은 메세지를 읽어들일 버퍼메모리를 가진 리더
	//private PrintWriter		pw;			// 클라이언트로 메세지를 보낼 프린트 라이터
	private Socket soc = null;	// 클라이언트 소켓
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private ArrayList userdata = null;
	private String logincheck;

	Server_thread(Socket soc){
		this.soc = soc;
	}

	public void run() {
		userdata = new ArrayList();
		Database db = new Database();
		logincheck = "";
		boolean test = false;

		try{
			System.out.println("클라이언트 연결");

			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());

			try {
				userdata = (ArrayList)ois.readObject();

				System.out.println(userdata.get(0));

				if(userdata.size() > 0) {
					//로그인 클릭시
					if(userdata.get(0).toString().equals("userlogin")) {

						String userid = new String(userdata.get(1).toString());
						String userpwd = new String(userdata.get(2).toString());
				
						test = db.IdCheck(userid);
						
						System.out.println(userid + " " + userpwd);
						
						if(test) {
							if(db.PwdCheck(userid, userpwd)) {
								System.out.println("승인");
								oos.writeObject("login");
								oos.flush();
							}else {
								//비밀번호가 틀렸을 시
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

						//회원가입 클릭시
					}else if(userdata.get(0).equals("userjoin")){
						oos = new ObjectOutputStream(soc.getOutputStream());

						String userid = new String(userdata.get(1).toString());
						String userpwd = new String(userdata.get(2).toString());
						String username = new String(userdata.get(3).toString());

						db.Join_user(userid, userpwd, username);

						//초성 퀴즈
					}else if(userdata.get(0).equals("initial")) {
						oos = new ObjectOutputStream(soc.getOutputStream());
						/*
	    	           br = new BufferedReader(new InputStreamReader(soc.getInputStream()));	// 클라이언트로부터 데이터를 읽어올 준비를 합니다
	    	           pw = new PrintWriter(soc.getOutputStream());							// 클라이언트로 데이터를 보낼 준비를 합니다

	    	           String readData = "";

	    	           while(!(readData = br.readLine()).equals(null)){				//상대쪽에서 접속을 끊을때까지 기다립니다.
	    	                System.out.println("from Client > "+ readData);			//클라리언트가 보낸 메세지를 읽습니다.
	    	                pw.println(readData);										//읽은 메세지 그대로 클라이언트 한테 보냅니다.
	    	                pw.flush();												//프린트라이터 메모리를 초기화 시킵니다. 이 메소드가 행해져야 실질적으로 데이터가 전송됨
	    	                }
						 */

						//문장 맞추기
					}else if(userdata.get(0).equals("sentence")) {
						oos = new ObjectOutputStream(soc.getOutputStream());

					}else {

					}
				}
			} catch(ClassNotFoundException e){e.printStackTrace();}
		}
		catch(SocketException | NullPointerException se){
			System.out.println("클라이언트가 연결을 종료하여 프로그램을 종료합니다.");
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