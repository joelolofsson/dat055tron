
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;

/**
 * @author Group 2
 * 
 * Sends name and score to client via TCP
 */

public class ServerClientSenderTCP extends Observable
{
	private DataOutputStream streamOut;
	private boolean connected;
	
	/**
	 * Default constructor for ServerClientSender
	 * @param Socket s
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
	 * @param int msgType
	 * @param String data
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