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

		// ����Ŭ���� ����
		MainProcess main = new MainProcess();
		main.loginView = new LoginView(); // �α���â ���̱�
		main.loginView.setMain(main); // �α���â���� ���� Ŭ����������
		main.backgroundClient();
	}
	
	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
	}

	// �׽�Ʈ������â
	public void showFrameTest(){
		loginView.dispose(); // �α���â�ݱ�
		this.testFrm = new TestFrm(); // �׽�Ʈ������ ����
	}
	
	// Ŭ���̾�Ʈ ��׶��� ������
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
