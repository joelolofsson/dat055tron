import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.Observable;


public class ServerClientSenderUDP extends Observable {

	private InetAddress toAddr;
	private DatagramSocket socket;
	private int toPort;
	
	public ServerClientSenderUDP(InetAddress toAddr)
	{
		this.toAddr = toAddr;
		toPort = 1338;
		
		try
		{
			socket = new DatagramSocket();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
	
	public void send(LinkedList<Point> pointList)
	{
		String tempS = "";
		
		for(Point p : pointList)
		{
			tempS = tempS + p.x + "," + p.y + ",";
		}
		byte[] data = tempS.getBytes();
		try
		{
			DatagramPacket packet = new DatagramPacket(data, data.length, toAddr, toPort);
			socket.send(packet);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}