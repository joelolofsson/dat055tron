import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.Observer;

/**
 * Creates and sends event to server
 * 
 * @author Group 2
 * 
 * 
 */
public class NetworkClient extends Observable implements Observer
{
	
	private Socket client;
	private DataOutputStream out;
	private KeyReader keyReader;
	private NetworkClientReceiverUDP netWorkClientReceiver;
	private NetworkClientReceiverTCP netWorkClientReceiverTCP;
	private DatagramSocket datagramSocket;
	private DatagramSocket datagramSocketSend;
	private InetAddress IP;
	
	/**
	 * Default constructor for NetworkClient
	 * 
	 * @param InetAddress IP
	 * @param int port
	 * @param String nickname
	 * @param KeyReader key
	 */
	public NetworkClient(InetAddress IP, int port, String nickname, KeyReader key)
	{
		try
		{
			this.IP = IP;
			client = new Socket(IP,port);
			datagramSocket = new DatagramSocket((port+1));
			datagramSocketSend = new DatagramSocket();
			datagramSocketSend.setSoTimeout(100);
			out = new DataOutputStream(client.getOutputStream());
			out.writeUTF(nickname);
			Tron.setConnected(IP.getHostAddress());
			keyReader = key;
			keyReader.addObserver(this);
			netWorkClientReceiver = new NetworkClientReceiverUDP(datagramSocket);
			netWorkClientReceiver.addObserver(Tron.center);
			netWorkClientReceiverTCP = new NetworkClientReceiverTCP(client);
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
			System.out.print("Create Socket fel");
		}
	}
	
	/**
	 * Sends the new direction to the server via UDP.
	 * It will wait for a receipt from the server, if no receipt is received within
	 * 100ms it will resend the direction (and do this until a receipt is received).
	 * 
	 * 
	 * @param Observable o
	 * @param Object arg
	 */
	public void update(Observable o, Object arg)
	{	
		if(o instanceof KeyReader && arg instanceof Integer)
		{
			byte[] data = arg.toString().getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, IP, 1339 + netWorkClientReceiverTCP.getClientId());
			byte[] receiptData = new byte[1024];
			DatagramPacket receipt = new DatagramPacket(receiptData, receiptData.length);
			//This loop will run until an OK receipt has been returned from the receiver.
			while(true)
			{
				try
				{
					//Try to send datagram packet
					datagramSocketSend.send(packet);
					//Try to receive the receipt
					datagramSocketSend.receive(receipt);
					String string = new String(receipt.getData(), 0, receipt.getLength());
					//End the loop if the receipt was valid.
					if(string.matches("OK"))
					{
						break;
					}
				}
				catch (SocketTimeoutException e2)
				{
				}
				catch(IOException e)
				{
					e.printStackTrace();
					System.out.print("Update fel");
					Tron.setConnected("Connection error!");	
				}
			}
		}
	}
}
