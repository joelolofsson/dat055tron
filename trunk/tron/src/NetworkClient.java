import java.awt.Color;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;



public class NetworkClient extends Observable implements Observer
{
	
	private Socket client;
	private DataOutputStream out;
	private KeyReader keyReader;
	private NetworkClientReceiverUDP netWorkClientReceiver;
	private NetworkClientReceiverTCP netWorkClientReceiverTCP;
	private DatagramSocket datagramSocket;
	
	public NetworkClient(InetAddress IP, int port, String nickname, KeyReader key)
	{
		try
		{
			client = new Socket(IP,port);
			datagramSocket = new DatagramSocket((port+1));
			out = new DataOutputStream(client.getOutputStream());
			out.writeUTF(nickname);
			Tron.setConnected(IP.getHostAddress());
			keyReader = key;
			keyReader.addObserver(this);
			netWorkClientReceiver = new NetworkClientReceiverUDP(datagramSocket);
			netWorkClientReceiver.addObserver(Tron.center);
			netWorkClientReceiverTCP = new NetworkClientReceiverTCP(client);
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			System.out.print("Create Socket fel");
		}
	}
	
	public void update(Observable o, Object arg)
	{
		
		
		if(o instanceof KeyReader && arg instanceof Integer) // Kolla så koordinaterna vi får in är från Keyreader och typen är int
		{
			try
			{
				out.write((Integer)arg);
				System.out.println("har skickat kordinat");
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.out.print("Update fel");
				Tron.setConnected("Connection error!");	
			}
			
		}
	}
}
