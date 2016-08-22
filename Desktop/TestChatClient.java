import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class TestChatClient extends Frame{
	public Socket socket = null;
	
	TextField tf = new TextField();
	TextArea ta = new TextArea();
	public void launchFrame(){
		
		add(ta, BorderLayout.CENTER);
		add(tf, BorderLayout.SOUTH);
		
		this.addWindowListener(
			new WindowAdapter() 
			{
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
			);
		
		tf.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					try {
						String str = tf.getText();
						TestChatClient.this.send(str);
						tf.setText("");
						ta.append("		" + str + "\n");
					} catch (Exception e) {}
				}
			}
		);
		setBounds(0,0,300,400);
		this.setVisible(true);
		tf.requestFocus();
	}
	
	public TestChatClient() throws Exception{
		socket = new Socket("127.0.0.1", 1234);
		launchFrame();
		(new Thread(new Receive())).start();
	}
	
	public static void main(String[] args) throws Exception {
						
		TestChatClient tcc = new TestChatClient();
							
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
							
		String str = br.readLine();
							
		DataOutputStream dos = new DataOutputStream(tcc.socket.getOutputStream());
							
		while (str != null && str.length()!=0 ){
			System.out.println(str);
			dos.writeUTF(str);
			dos.flush();
			str = br.readLine();
		}
							
		br.close();
		tcc.socket.close();
						
	}
	
	public void send(String str) throws Exception{
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF(str);
	}
	
	class Receive implements Runnable {
		public void run(){
			try {
				DataInputStream dis = new DataInputStream (socket.getInputStream());
				String str = dis.readUTF();
				while (str != null && str.length()!=0 ){
					ta.append(str + "\n");
					str = dis.readUTF();
				}
			} catch (Exception e){}
		}
	}
	
}
