import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

/**
 * Handles the Indata (name) from a client via TCP
 * 
 * @author Group 2
 * 
 * 
 */
public class ServerClientHandlerTCP extends Observable
{
	private DataInputStream streamIn;
	private int id;

	/**
	 * Default constructor for ServerClientHandler
	 * 
	 * @param s The socket for TCP communication.
	 * @param id THe id for the current player.
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
	 * If the name is blank (player has not set his name when connecting)
	 * the name "TheStig" will be set.
	 * @return Name of the player
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
	 * @return id
	 */
	public int getID()
	{
		return id;
	}
}
