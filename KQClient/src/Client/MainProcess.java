package Client;

import java.awt.EventQueue;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;


public class MainProcess{
	LoginView loginView;
	TestFrm testFrm;
	
	public static void main(String[] args)  throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainProcess frame = new MainProcess();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 메인클래스 실행
		MainProcess main = new MainProcess();
		main.loginView = new LoginView(); // 로그인창 보이기
		main.loginView.setMain(main); // 로그인창에게 메인 클래스보내기
		main.backgroundClient();
	}
	
	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
	}

	// 테스트프레임창
	public void showFrameTest(){
		loginView.dispose(); // 로그인창닫기
		this.testFrm = new TestFrm(); // 테스트프레임 오픈
	}
	
	// 클라이언트 백그라운드 스레드
	public void backgroundClient() {
		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			socket = new Socket("localhost", 6060);
		
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			dos = new DataOutputStream(out);
			//dos.writeUTF(UserId);
			
			CThread ct = new CThread(socket.getInputStream());
			ct.start();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {
			try {
				dos.close();
				socket.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}

class CThread extends Thread{
	private InputStream is = null;
	public CThread(InputStream is) {
		this.is = is;
	}
	public void run() {
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(is);
			String msg = "";
			while(true) {
				msg = dis.readUTF();
				System.out.println(msg);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
