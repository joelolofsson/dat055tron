import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
/**
 * @author Group 2
 * 
 * Handles the Indata from a client
 */

public class ServerClientHandlerTCP extends Observable
{
	private DataInputStream streamIn;
	private int id;

	/**
	 * Default constructor for ServerClientHandler
	 * 
	 * @param Socket s
	 * @param int id
	 */
	public ServerClientHandlerTCP(Socket s, int id)
	{
		this.id = id;
		
		try
		{
			streamIn = new DataInputStream(s.getInputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
		if(n.equals(""))
		{
			n = "TheStig";
		}
		return n;
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
