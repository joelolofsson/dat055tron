import java.awt.Point;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

/**
 * @author Group 2
 * 
 * Handles Outputdata to client
 */
public class ServerClientSender extends Observable {

	Socket socket;
	DataOutputStream streamOut;

	/**
	 * Default constructor for ServerClientSender
	 * 
	 * @param Socket s
	 */
	public ServerClientSender(Socket s)
	{
		System.out.println("skapar vi en serverClientSender");
		System.out.println(s.getLocalAddress().getHostAddress());
		socket = s;
		try {
			streamOut = new DataOutputStream(socket.getOutputStream());
			System.out.println("skapas ut strömmen");
			
		} catch (IOException e) {
			System.out.println("funkade inte riktigt");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Sends a Point to client
	 * 
	 * @param Point point
	 */
	public void send(Point point)
	{
		int x = point.x;
		int y = point.y;
		
		try {
			System.out.println("Försöker sända!" + x + " " + y);
			streamOut.writeInt(x);
			System.out.println("har sänt");
			streamOut.writeInt(y);
			streamOut.flush();
			//streamOut.writeInt(id);
			//streamOut.flush();
		} catch (IOException e) {
			System.out.println("write IOException");
			e.printStackTrace();
		}
	}
}