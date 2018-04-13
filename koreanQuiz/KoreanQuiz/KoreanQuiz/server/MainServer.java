package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
 
public class MainServer {
    
    private ServerSocket server_Soc;	// 서버소켓

    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	
        try(ServerSocket server = new ServerSocket(6060)) {
        	
        	while (true) {
        		
                System.out.println(getTime()+"서버 준비");
                System.out.println(getTime()+"클라이언트 대기");
                
        		Socket client = server.accept();
                Server_thread ST = new Server_thread(client);
                ST.run();
            }
 
        } catch (IOException e) { e.printStackTrace(); }
    }

	static String getTime(){
        SimpleDateFormat f = new SimpleDateFormat("[HH:mm:ss]");
        return f.format(new Date());
    }
}

class Server_thread extends Thread{
	
    private BufferedReader	br;			// 클라이언트로부터 전달받은 메세지를 읽어들일 버퍼메모리를 가진 리더
    private PrintWriter		pw;			// 클라이언트로 메세지를 보낼 프린트 라이터
    private Socket			soc;		// 클라이언트 소켓
	
	Server_thread(Socket soc){
		this.soc = soc;
	}
	
	public void run() {
        try{
        	
            System.out.println("클라이언트 연결");
            
            br = new BufferedReader(new InputStreamReader(soc.getInputStream()));	// 클라이언트로부터 데이터를 읽어올 준비를 합니다
            pw = new PrintWriter(soc.getOutputStream());							// 클라이언트로 데이터를 보낼 준비를 합니다
            
            String readData = "";
            
            while(!(readData = br.readLine()).equals(null)){				//상대쪽에서 접속을 끊을때까지 기다립니다.
                 System.out.println("from Client > "+ readData);			//클라리언트가 보낸 메세지를 읽습니다.
                 pw.println(readData);										//읽은 메세지 그대로 클라이언트 한테 보냅니다.
                 pw.flush();												//프린트라이터 메모리를 초기화 시킵니다. 이 메소드가 행해져야 실질적으로 데이터가 전송됨
                 }
            soc.close();
        }
        catch(SocketException | NullPointerException se){
            System.out.println("클라이언트가 연결을 종료하여 프로그램을 종료합니다.");
            System.exit(0);
            }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
}