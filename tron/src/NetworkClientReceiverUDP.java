import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;


public class NetworkClientReceiverUDP extends Observable implements Runnable {
	
	private Thread thread;
	private DatagramPacket packet;
	private DatagramSocket socket;
	public NetworkClientReceiverUDP(DatagramSocket socket)
	{
		byte[] data = new byte[1024];
		this.socket=socket;
			packet = new DatagramPacket(data, data.length);
			thread = new Thread(this);
			thread.start();
	}
	
	public void run()
	{
		Integer x;
		Integer y;
		while(true)
		{
			try 
			{
				socket.receive(packet);
				String tempstring = new String(packet.getData(), 0, packet.getLength());
				String[] tempstring2 = tempstring.split(",");
				for(int i = 0, j = 0; i < tempstring2.length; j = j + 1, i = i + 2)
				{
					x = Integer.parseInt(tempstring2[i]);
					y = Integer.parseInt(tempstring2[i + 1]);
					if(tempstring2.length == 2)
					{
						setChanged();
						notifyObservers("Reset");
					}
					else if(j == 0)
					{
						setChanged();
						notifyObservers(new ColorPoint(x,y,Color.CYAN));
					}
					else if(j == 1)
					{
						setChanged();
						notifyObservers(new ColorPoint(x,y,Color.GREEN));
					}
					else if(j == 2)
					{
						setChanged();
						notifyObservers(new ColorPoint(x,y,Color.RED));
					}
					else if(j == 3)
					{
						setChanged();
						notifyObservers(new ColorPoint(x,y,Color.BLUE));
					}
				}
			}
			catch (SocketException e2)
			{
				Tron.setConnected("Connection error!");
				break;
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
