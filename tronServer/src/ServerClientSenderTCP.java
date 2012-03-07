
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;

/**
 * Sends name and score to client via TCP.
 * @author Group 2
 * 
 * 
 */

public class ServerClientSenderTCP extends Observable
{
	private DataOutputStream streamOut;
	private boolean connected;
	
	/**
	 * Default constructor for ServerClientSender
	 * @param s The socket for tcp communication.
	 */
	public ServerClientSenderTCP(Socket s)
	{
		connected = true;
		try 
		{
			streamOut = new DataOutputStream(s.getOutputStream());	
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

	/**
	 * Sends name and score to client
	 * 
	 * @param  msgType Is used as an id so the client know what kind of
	 * information is comming.
	 * @param  data	Either name or score.
	 */
	public void send(int msgType, String data)
	{
		try 
		{
			//If msgType = 1, send name. If msgType = 2, send score.
			streamOut.writeInt(msgType);
			streamOut.writeUTF(data);		
			streamOut.flush();
		} 
		catch (SocketException e) 
		{
			try {
				streamOut.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}