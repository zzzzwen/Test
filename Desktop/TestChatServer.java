import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TestChatServer extends Frame{
	public static void main(String[] args) throws Exception{
		TestChatServer tcs= new TestChatServer(1234);
		tcs.startchat();
	}
	
	TextArea ta= new TextArea();
	public void launchFrame(){
		setBounds(0,0,300,400);
		add(ta, BorderLayout.CENTER);
		this.addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing (WindowEvent e){
					setVisible(false);
					System.exit(0);
				}
			}
		);
		setVisible(true);
	}
	
	
	ServerSocket ss = null;
	ArrayList<ChatsSocket> socketset = new ArrayList<ChatsSocket>();
	
	public TestChatServer(int portId) throws Exception{
		ss = new ServerSocket(portId);
		launchFrame();
	}
	
	public void startchat() throws Exception{
		boolean flag = true;
		
		while(flag){
			Socket socket = ss.accept();
			socketset.add(new ChatsSocket(socket));
			ta.append("Client" + socket.getInetAddress() + ":" + socket.getPort() + "  connected");
			ta.append("Total no. of clients = " + socketset.size());
			ta.append("\n");
		}
	}
	
	class ChatsSocket implements Runnable{
		
		Socket socket=null;
		public ChatsSocket(Socket socket){
			this.socket = socket;
			new Thread(this).start();
		}
		
		public void send(String str) throws Exception{
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(str);
		}
		
		public void run(){
			try {
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String str = dis.readUTF();
				System.out.println(str);
				
				while (str != null && str.length() != 0){
					for (Iterator i = socketset.iterator(); i.hasNext();){
						ChatsSocket cs = (ChatsSocket) i.next();
						if (cs != this){
							cs.send(str);
						}
					}
					str = dis.readUTF();
				}
				
				dis.close();
				dos.close();
				socketset.remove(this);
			} catch (Exception e){
			}
		}

	}
}