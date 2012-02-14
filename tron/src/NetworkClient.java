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
			in = new DataInputStream(client.getInputStream()); // Skapa instr�m
			out = new DataOutputStream(client.getOutputStream()); // Skapa utstr�m
			
				Tron.setConnected(IP.getHostAddress());
			
			keyReader = key;
			keyReader.addObserver(this); // L�gg till att man observerar
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
			}
			
		}
	}
	
	
	

	
	
	
	
}
