import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;
/**
 * @author Group 2
 * 
 * Handles the Indata from a client
 */

public class ServerClientHandler extends Observable implements Runnable {

	private Thread aktivitet;
	private DataInputStream streamIn;
	private int id;

	/**
	 * Default constructor for ServerClientHandler
	 * 
	 * @param Socket s
	 * @param int id
	 */
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
	
	/**
	 * Notifies observers if a event is recieved
	 * 
	 * 
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				int[] send = {id, streamIn.readByte()};	//Tar emot knapptryck via TCP
				System.out.println("Tog emot en knapptryckning");
				setChanged();
				notifyObservers(send);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns the name of a connected player
	 * 
	 * @return String
	 */
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

	/**
	 * Starts the thread
	 */
	public void start()
	{
		aktivitet.start();
	}
	

	/**
	 * Returns the id for the connected player
	 * 
	 * @return int
	 */
	public int getID()
	{
		return id;
	}
}
