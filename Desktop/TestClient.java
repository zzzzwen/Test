import java.net.*;
import java.io.*;

public class TestClient implements Runnable{
	public static Socket socket = null;

	public static void main(String[] args) throws Exception {

		try{
			socket = new Socket("127.0.0.1" , 1203);
		} catch (Exception e) { System.out.println("client connection error");}
		
		TestClient TC = new TestClient();

		Thread t = new Thread(TC);
		t.start();

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String words = br.readLine();
		while (!(words.equals("byebye"))) {
			System.out.println("		" + words);
			words = br.readLine();
		}

		br.close();
	}

	public void run() {
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
