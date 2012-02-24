import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Observable;

/**
 * @author Group 2
 *
 * Send coordinates to client via UDP
 */
public class ServerClientSenderUDP extends Observable 
{
	private InetAddress toAddr;
	private DatagramSocket socket;
	private int toPort = 1338;
	
	/**
	 * Default constructor for ServerClientSenderUDP
	 * @param InetAddress toAddr
	 */
	public ServerClientSenderUDP(InetAddress toAddr)
	{
		this.toAddr = toAddr;
		try
		{
			socket = new DatagramSocket();
			socket.setSoTimeout(100);	//Sets the socket timeout to 100ms
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Send coordinates to client
	 * 
	 * @param LinkedList<Point> pointList
	 */
	public void send(LinkedList<Point> pointList)
	{
		String coordsString = "";
		for(Point p : pointList)
		{
			coordsString = coordsString + p.x + "," + p.y + ",";
		}
		byte[] data = coordsString.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, toAddr, toPort);
		byte[] receiptData = new byte[1024];
		DatagramPacket receipt = new DatagramPacket(receiptData, receiptData.length);
		//This loop will run until an OK receipt has been returned from the receiver.
		while(true)
		{
			try
			{
				//Try to send datagram packet
				socket.send(packet);
				//Try to receive the receipt
				socket.receive(receipt);
				String string = new String(receipt.getData(), 0, receipt.getLength());
				//End the loop if the receipt was valid.
				if(string.matches("OK"))
				{
					break;
				}
			}
			catch (SocketException e1)
			{		
				socket.disconnect();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}