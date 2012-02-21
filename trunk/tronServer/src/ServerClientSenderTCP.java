
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
	
	public void send(String sTemp, int id)
	{
		
		try {
			System.out.println("Försöker sända namn!");
			
			if(idNotSent)
			{
				streamOut.writeInt(id);	//Skickar id i varje paket
			}
			
			streamOut.writeUTF(sTemp);
			
			streamOut.flush();
			//streamOut.writeInt(id);
			//streamOut.flush();
		} catch (IOException e) {
			System.out.println("write IOException");
			e.printStackTrace();
		}
	}
}