import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
/**
 * @author Group 2
 * 
 * Creates instances of nessecary classes to connect and 
 * maintain a connetion to the network
 */

public class NetworkServer extends Observable implements Runnable {

	
	
	private ServerSocket serversocket;
	private Socket klientSock;
	private boolean temp = true;
	private Thread thread;
	private GameEngine gameEngine;
	private int numberOfPlayers = 0;
	
	/**
	 * Default constructor for NetworkServer
	 */
	public NetworkServer()
	{	
		try
		{
			serversocket = new ServerSocket(1337);
			thread = new Thread(this);
			gameEngine = new GameEngine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while(numberOfPlayers < 4)
		{
			try
			{
				klientSock = serversocket.accept();
				if(temp)
				{
					System.out.println(klientSock.getInetAddress().getHostName() + " har anslutit sig");
					ServerGui.players[numberOfPlayers].setText("Player " + (numberOfPlayers+1) + ": " + klientSock.getInetAddress().getHostAddress());
					gameEngine.addPlayer(new ServerClientHandler(klientSock, numberOfPlayers), new ServerClientHandlerUDP(numberOfPlayers), new ServerClientSenderUDP(klientSock.getInetAddress()), new ServerClientSenderTCP(klientSock));
					numberOfPlayers++;
				}
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		}
	}
	
	/**
	 * Starts the game and stops
	 * other clients to connetc
	 * 
	 */
	public void start()
	{
		temp = false;
		gameEngine.start(numberOfPlayers);
	}
	
	/**
	 *Starts the thread
	 */
	public void connect()
	{
		thread.start();
	}
		
		
}