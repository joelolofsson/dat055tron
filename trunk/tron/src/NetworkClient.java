import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Observable;
import java.util.Observer;



public class NetworkClient extends Observable implements Observer
{
	private Socket client;
	
	private DataInputStream in;
	private DataOutputStream out;
	private KeyReader keyReader;
	private Color color;
	public NetworkClient(InetAddress IP, int port, String nickname)
	{
		try
		{
			
			client = new Socket(IP,port);
			in = new DataInputStream(client.getInputStream());
			out = new DataOutputStream(client.getOutputStream());
			
			keyReader = new KeyReader();
			keyReader.addObserver(this);
			receiveColorPoint();
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
				int x = in.readInt();
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
				setChanged();
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
		if(o instanceof KeyReader && arg instanceof Integer)
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
