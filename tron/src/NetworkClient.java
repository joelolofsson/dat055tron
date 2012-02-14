import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;



public class NetworkClient extends Observable implements Observer
{
	private Socket client;
	
	private DataInputStream in;
	private DataOutputStream out;
	private KeyReader keyReader;
	private Color color;
	public NetworkClient(InetAddress IP, int port, String nickname, KeyReader key)
	{
		try
		{
			
			client = new Socket(IP,port); // Skapa socket
			in = new DataInputStream(client.getInputStream()); // Skapa inström
			out = new DataOutputStream(client.getOutputStream()); // Skapa utström
			
				Tron.setConnected(IP.getHostAddress());
			
			keyReader = key;
			keyReader.addObserver(this); // Lägg till att man observerar
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
				int x = in.readInt();  //Läs in från inströmmen
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
				setChanged(); // Säga till observen att de är klart
				notifyObservers(new ColorPoint(x,y,color));
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
		if(o instanceof KeyReader && arg instanceof Integer) // Kolla så koordinaterna vi får in är från Keyreader och typen är int
		{
			try
			{
				out.write((Integer)arg); 
			}
			catch(IOException e)
			{
				e.printStackTrace();
				System.out.print("Update fel");
			}
			
		}
	}
	
	
	

	
	
	
	
}
