import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerClientHandler implements Runnable {

	private Thread aktivitet;
	Socket socket;
	DataInputStream streamIn;

	
	public ServerClientHandler(Socket s)
	{
		aktivitet = new Thread(this);
		socket = s;
		try {
			streamIn = new DataInputStream(s.getInputStream());
			
			aktivitet.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void run()
	{
		while(true)
		{
		try {
			System.out.println(streamIn.readByte());
		} catch (IOException e) {
		}
		}
		}
}
