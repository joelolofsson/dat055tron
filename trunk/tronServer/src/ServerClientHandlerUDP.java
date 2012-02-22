import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Observable;
/**
 * @author Group 2
 * 
 * Handles the Indata from a client
 */

public class ServerClientHandlerUDP extends Observable implements Runnable {

	private Thread aktivitet;
	private DatagramPacket packet;
	private DatagramSocket socket;
	private int id;

	/**
	 * Default constructor for ServerClientHandler
	 * 
	 * @param Socket s
	 * @param int id
	 */
	public ServerClientHandlerUDP(int id)
	{
		this.id = id;
		try {
			socket = new DatagramSocket(1339 + id);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		byte[] data = new byte[1024];
		packet = new DatagramPacket(data, data.length);
		aktivitet = new Thread(this);
		this.start();
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
			
			
				try {
					socket.receive(packet);
					String tempstring = new String(packet.getData(), 0, packet.getLength());
					System.out.println("Tog emot knapptryck (UDP): " + tempstring);
					
					int[] send = {id, new Integer(tempstring).intValue()};
					setChanged();
					notifyObservers(send);
					
					DatagramSocket sendSocket = new DatagramSocket();
					
					byte[] data = new String("OK").getBytes();
					
					DatagramPacket sendPacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
					sendSocket.send(sendPacket);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
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
