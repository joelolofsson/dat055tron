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
 * Send coordinates via datagram to client.
 * Will resend the coordinate until a receipt is received from the client.
 * If no receipt is received within 10 attempts to send, we assume the user has
 * dissconnected.
 * 
 * @author Group 2
 *
 * 
 */
public class ServerClientSenderUDP extends Observable 
{
	private InetAddress toAddr;
	private DatagramSocket socket;
	private int toPort = 1338;
	
	/**
	 * Default constructor for ServerClientSenderUDP
	 * @param toAddr The adress to the client which will receive the datagram.
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
	 * @param  pointList List with the new coordinates.
	 */
	public void send(LinkedList<Point> pointList)
	{
		int i = 0;
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
		while(i < 10)
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
				break; 	
			}

			catch (SocketTimeoutException e2)
			{
				i++;
				//Try to receive the receipt 10 times. If no receipt is received we assume the
				//client has disconnected.
			}
		
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}