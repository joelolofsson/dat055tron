
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;


public class ServerClientSenderTCP extends Observable {

	Socket socket;
	DataOutputStream streamOut;
	boolean idNotSent = true;

	
	public ServerClientSenderTCP(Socket s)
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
	
	public void send(int  typ, String sTemp)
	{
		try {
			System.out.println("Försöker sända namn!");
			streamOut.writeInt(typ);
			streamOut.writeUTF(sTemp);		
			streamOut.flush();
		} catch (IOException e) {
			System.out.println("write IOException");
			e.printStackTrace();
		}
	}
}