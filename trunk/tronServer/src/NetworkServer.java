import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

import javax.swing.JOptionPane;
/**
 * @author Group 2
 * 
 * Creates instances of nessecary classes to connect and 
 * maintain a connetion to the network
 */

public class NetworkServer extends Observable implements Runnable 
{
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private boolean accConnections = true;
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
			serverSocket = new ServerSocket(1337);
			thread = new Thread(this);
			gameEngine = new GameEngine();
		}
		catch (BindException e1)
		{
			JOptionPane.showMessageDialog(null,
					"You can only start once instance of the server at a time!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
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
				clientSocket = serverSocket.accept();
				if(accConnections)
				{
					ServerGui.players[numberOfPlayers].setText("Player " + (numberOfPlayers+1) + ": " + clientSocket.getInetAddress().getHostAddress());
					gameEngine.addPlayer(new ServerClientHandlerTCP(clientSocket, numberOfPlayers), new ServerClientHandlerUDP(numberOfPlayers), 
							new ServerClientSenderUDP(clientSocket.getInetAddress()), new ServerClientSenderTCP(clientSocket));
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
	 * other clients to connect
	 * 
	 */
	public void start()
	{
		accConnections = false;
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