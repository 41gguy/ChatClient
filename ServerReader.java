import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReader extends Thread {

	private Socket socket;
	InputStream is; 
	InputStreamReader isr; 
	BufferedReader br = null; 
	OutputStream os; 
	PrintStream out; 
	
	public ServerReader(Socket socket) {
		super("EchoServerThread");
		
		System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "22222");
		  
		this.socket = socket;

	}
	
	public void run() {
		
		try {
			  InputStream is = socket.getInputStream();
              InputStreamReader isr = new InputStreamReader(is, "UTF-8");
              BufferedReader br = new BufferedReader(isr);
              
              OutputStream os = socket.getOutputStream();
              PrintStream out = new PrintStream(os, true, "UTF-8");
             
               while (socket.isConnected()) {
              	System.out.println("<Server> " + br.readLine());
              }
			
			
		}
		catch (Exception e) {
			
		}
	}

}
