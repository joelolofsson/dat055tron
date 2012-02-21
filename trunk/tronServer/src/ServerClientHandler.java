import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;


public class ServerClientHandler extends Observable implements Runnable {

	private Thread aktivitet;
	private DataInputStream streamIn;
	private int id;

	public ServerClientHandler(Socket s, int id)
	{
		this.id = id;
		aktivitet = new Thread(this);
		try
		{
			streamIn = new DataInputStream(s.getInputStream());
			System.out.println("har tagit emot");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
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
				e.printStackTrace();
			}
		}
	}
	
	public String namn()
	{
		String n = "";
		try
		{
			n = streamIn.readUTF().toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		aktivitet.start();
		return n;
	}
	
	public int getID()
	{
		return id;
	}
}
