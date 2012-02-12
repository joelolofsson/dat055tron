import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerClientHandler implements Runnable {

	private Thread aktivitet;
	Socket socket;
	DataInputStream streamIn;
	DataOutputStream streamUt;
	
	public ServerClientHandler(Socket s)
	{
		aktivitet = new Thread(this);
		socket = s;
		try {
			streamIn = new DataInputStream(s.getInputStream());
			streamUt = new DataOutputStream(s.getOutputStream());
			aktivitet.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void run()
	{
	}
}
