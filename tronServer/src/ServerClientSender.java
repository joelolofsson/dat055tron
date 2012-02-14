import java.awt.Point;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;


public class ServerClientSender extends Observable {

	Socket socket;
	DataOutputStream streamOut;

	
	public ServerClientSender(Socket s)
	{
		socket = s;
		try {
			streamOut = new DataOutputStream(s.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void send(Point point)
	{
		int x;
		int y;
		
		x = point.x;
		y = point.y;
		
		try {
			System.out.println("Försöker sända!" + x + " " + y);
			streamOut.writeInt(x);
			streamOut.writeInt(y);
			//streamOut.writeInt(id);
			streamOut.flush();
		} catch (IOException e) {
			System.out.println("writeInt IOException");
			e.printStackTrace();
		}
	}
}