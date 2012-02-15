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