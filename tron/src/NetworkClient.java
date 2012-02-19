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
	private DataInputStream in;
	private DataOutputStream out;
	private KeyReader keyReader;
	private Color color;
	private NetworkClientReceiver netWorkClientReceiver;
	private DatagramSocket datagramSocket;
	
	public NetworkClient(InetAddress IP, int port, String nickname, KeyReader key)
	{
		try
		{
			client = new Socket(IP,port); // Skapa socket
			datagramSocket = new DatagramSocket((port+1));
			in = new DataInputStream(client.getInputStream()); // Skapa instr�m
			out = new DataOutputStream(client.getOutputStream()); // Skapa utstr�m
			//out.writeBytes(nickname);

			//out.writeChars(nickname);

			
				Tron.setConnected(IP.getHostAddress());
			
			keyReader = key;
			keyReader.addObserver(this); // L�gg till att man observerar
			netWorkClientReceiver = new NetworkClientReceiver(datagramSocket);
			netWorkClientReceiver.addObserver(Tron.center);
			
			//receiveColorPoint();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("Create Socket fel");
		}
	}
	
	private void receiveColorPoint()
	{
		try
		{
			while(true)
			{
				int x = in.readInt();  //L�s in fr�n instr�mmen
				int y = in.readInt();
				int tempColor = in.readInt();
				if(tempColor == 1)
				{
					color = Color.red;
				}
				else if(tempColor == 2)
				{
					color = Color.blue;
				}
				else if(tempColor == 3)
				{
					color = Color.green;
				}
				setChanged(); // S�ga till observen att de �r klart
				notifyObservers(new ColorPoint(x,y,color));
				System.out.println("Vi har f�tt in kordinater:" + x + " " + y);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.print("Receive fel");
		}
	}
	public void update(Observable o, Object arg)
	{
		
		
		if(o instanceof KeyReader && arg instanceof Integer) // Kolla s� koordinaterna vi f�r in �r fr�n Keyreader och typen �r int
		{
			try
			{
				out.write((Integer)arg); 
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
