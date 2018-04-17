package Client;

import java.awt.EventQueue;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;


public class JoinProcess{
	JoinView joinview;
	
	public static void main(String[] args)  throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinView frame = new JoinView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Ŭ���� ����
		JoinProcess join = new JoinProcess();
		join.backgroundJoin();
	}
	
	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
	}
	
	// �׽�Ʈ������â
	public void showFrameTest(){
		joinview.dispose(); // ȸ������ â�ݱ�
	}
	
	// Ŭ���̾�Ʈ ��׶��� ������
	public void backgroundJoin() {
		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		try {
			socket = new Socket("localhost", 6060);
		
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			dos = new DataOutputStream(out);
			//dos.writeUTF(UserId);
			
			JoinThread ct = new JoinThread(socket.getInputStream());
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

class JoinThread extends Thread{
	ArrayList userData = new ArrayList();
	private InputStream is = null;
	public JoinThread(InputStream is) {
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
