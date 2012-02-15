import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

import javax.swing.JOptionPane;


public class ServerClientHandler extends Observable implements Runnable {

	private Thread aktivitet;
	private Socket socket;
	private DataInputStream streamIn;
	private int id;

	
	public ServerClientHandler(Socket s, int id)
	{
		this.id = id;
		aktivitet = new Thread(this);
		socket = s;
		try {
			streamIn = new DataInputStream(s.getInputStream());
			
			aktivitet.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("serverclient hanterare skapad med id " + id);
		
	}
	public void run()
	{
		while(true)
		{
			try
			{
				int[] send = {id, streamIn.readByte()};
				setChanged();
				notifyObservers(send);
			}
			catch (IOException e)
			{
			}
		}
	}
	
	public int getID()
	{
		return id;
	}
}