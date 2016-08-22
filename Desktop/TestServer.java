import java.net.*;
import java.io.*;

public class TestServer implements Runnable{
	public static ServerSocket server = null;
	public static Socket socket = null;

	public static void main(String[] args) throws Exception{
		try{
			server = new ServerSocket(1203);
		} catch (Exception e) { System.out.println("failed to connect");}

		try{
			socket = server.accept();
		} catch (Exception e) { System.out.println("client connection error");}
		
		TestServer TS = new TestServer();

		Thread t = new Thread(TS);
		t.start();

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String words = br.readLine();
		while (!(words.equals("byebye"))) {
			System.out.println("		" + words);
			words = br.readLine();
		}
		br.close();
		server.close();
	}

	public void run(){
		try {
			BufferedReader bre = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			String str = bre.readLine();
			while (!(str.equals("byebye"))) {
				pw.println(str);
				pw.flush();
				str = bre.readLine();
			} 
			pw.close();
			bre.close();
		} catch (Exception e){}
	}


}
