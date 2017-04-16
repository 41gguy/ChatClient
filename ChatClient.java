import java.sql.Timestamp;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public final class ChatClient {

    public static void main(String[] args) throws Exception {

	
	System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "38001");

        try (Socket socket = new Socket("localhost", 38001)) {
        	OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os, true, "UTF-8");
            
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br2 = new BufferedReader(isr);
                      
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Please enter a user name: "); 
            String username = br.readLine();
            out.println(username);
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            ServerReader sr = new ServerReader(socket);
            sr.start();
           
            while (true) {
            	
            	String chat = br.readLine();
            	out.println("<" + username + "> " + chat + " (" + timestamp + ")");
            	out.flush();

            	if (chat.equalsIgnoreCase("exit")) {
            		System.exit(0);
            	}
 
            }
        }
        catch (Exception e) {
        	
        }
    }
}
