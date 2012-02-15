import java.awt.Point;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;


public class ServerClientSenderUDP extends Observable {

	private InetAddress toAddr;
	private DatagramSocket socket;
	private int toPort;
	
	public ServerClientSenderUDP(Socket s)
	{
		
		toAddr = s.getInetAddress();
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
	
	public void send(Point point)
	{
		
		int x = point.x;
		int y = point.y;
		String stringtemp = "" + x + "," + y;
		byte[] data = stringtemp.getBytes();
		//System.out.println(data.toString());
		//System.out.println(x.toString());
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