import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;
/**
 * Receives a datagram containing player direction from the client.
 * NotifyObservers with the new value.
 * When a datagram is received a receipt will be sent to the client.
 * @author Group 2
 * 
 * 
 */

public class ServerClientHandlerUDP extends Observable implements Runnable
{
	private Thread aktivitet;
	private DatagramPacket packet;
	private DatagramSocket socket;
	private int id;

	/**
	 * Default constructor for ServerClientHandler
	 * 
	 * @param id player id.
	 */
	public ServerClientHandlerUDP(int id)
	{
		this.id = id;
		try 
		{
			socket = new DatagramSocket(1339 + id);
		} catch (SocketException e)
		{
			e.printStackTrace();
		}
		byte[] data = new byte[1024];
		packet = new DatagramPacket(data, data.length);
		aktivitet = new Thread(this);
		this.start();
	}
	
	/**
	 * Notifies observers if a new direction is received. is recieved
	 * 
	 */
	public void run()
	{
		while(true)
		{
				try
				{
					socket.receive(packet);
					String tempstring = new String(packet.getData(), 0, packet.getLength());					
					int[] send = {id, new Integer(tempstring).intValue()};
					setChanged();
					notifyObservers(send);	
					//Create a receipt-package and send it to the original sender
					DatagramSocket sendSocket = new DatagramSocket();
					byte[] data = new String("OK").getBytes();					
					DatagramPacket sendPacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
					sendSocket.send(sendPacket);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		}
	
	/**
	 * Start the thread
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
