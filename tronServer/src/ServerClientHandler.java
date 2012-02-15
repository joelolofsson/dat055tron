import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;


public class ServerClientHandler extends Observable implements Runnable {

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
				setChanged();
				notifyObservers(streamIn.readByte());
			} catch (IOException e) {
			}
		}
		}
}
