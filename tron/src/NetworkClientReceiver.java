import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;


public class NetworkClientReceiver extends Observable implements Runnable {
	
	private DataInputStream dataInputStream;
	private Thread thread;
	private DatagramPacket packet;
	private DatagramSocket socket;
	public NetworkClientReceiver(DatagramSocket socket)
	{
		byte[] data = new byte[1024];
		this.socket=socket;
		//try {
			packet = new DatagramPacket(data, data.length);
			
		//	dataInputStream = new DataInputStream(socket.getInputStream());
			thread = new Thread(this);
			thread.start();
	//	} catch (IOException e) {
	//		e.printStackTrace();
	//	}
	}
	
	public void run()
	{
		Integer x;
		Integer y;
		while(true)
		{
			try 
			{
				System.out.println("f�rs�ker ta emot");
				socket.receive(packet);
				x = Integer.parseInt(new String(packet.getData(), 0, packet.getLength()));
				socket.receive(packet);
				y = Integer.parseInt(new String(packet.getData(), 0, packet.getLength()));
				ColorPoint cPoint = new ColorPoint(x,y,Color.BLUE);
				setChanged();
				notifyObservers(cPoint);
			System.out.println("har tagit emot x: " + x + "y: " + y);
			} catch (SocketException e2) {						//Connection reset
				Tron.setConnected("Connection error!");			//Set connection status in the game window
				break;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
