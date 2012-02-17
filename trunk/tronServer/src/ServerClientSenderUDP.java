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
		System.out.println(toAddr.getHostAddress());
		toPort = 1338;
		
		try {
			socket = new DatagramSocket();
			//System.out.println("Socket skapad");
		} catch (SocketException e) {
			System.out.println("Socket EJ skapad");
			e.printStackTrace();
		}
	}
	
	public void send(LinkedList<Point> pointList)
	{
		
		//String stringtemp = "" + x + "," + y;
		String tempS = "";
		
		for(Point p : pointList){
			tempS = tempS + p.x + "," + p.y + ";";
		}
		
		byte[] data = tempS.getBytes();
		try
		{
			DatagramPacket packet = new DatagramPacket(data, data.length, toAddr, toPort);
			//System.out.println("Paket skapat");
			socket.send(packet);
		}
		catch (IOException e)
		{
			System.out.println("Kan ej skicka datagram");
			e.printStackTrace();
		}
	}
}