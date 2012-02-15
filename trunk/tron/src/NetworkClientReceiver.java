import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;


public class NetworkClientReceiver extends Observable implements Runnable {
	
	private DataInputStream dataInputStream;
	private Thread thread;

	public NetworkClientReceiver(Socket socket)
	{
		try {
			dataInputStream = new DataInputStream(socket.getInputStream());
			thread = new Thread(this);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		int x;
		int y;
		while(true)
		{
			try 
			{
				System.out.println("försöker ta emot");
				x = dataInputStream.readInt();
				y = dataInputStream.readInt();
				ColorPoint cPoint = new ColorPoint(x,y,Color.BLUE);
				setChanged();
				notifyObservers(cPoint);
			System.out.println("har tagit emot x: " + x + "y: " + y);
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
