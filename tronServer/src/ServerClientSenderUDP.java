import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
			socket.setSoTimeout(100);
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
		while(true)
		{
			try
			{
				socket.send(packet);
				socket.receive(receipt);
				String string = new String(receipt.getData(), 0, receipt.getLength());
				if(string.matches("OK"))
				{
					break;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}