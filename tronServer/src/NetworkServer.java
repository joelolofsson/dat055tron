import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

import javax.swing.JOptionPane;
/**
 * Allows new connections to the server. When a user connects this will be
 * added as a player and ServerClient-Classes (four) will be created to handle
 * communations in different ways.
 * 
 * maintain a connetion to the network
 * @author Group 2
 *
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
	 * Default constructor for NetworkServer. 
	 * Sets up the sersocket and and new thread where new connections will be accepted.
	 * If there is an error while setting the socket we assume the user already has 
	 * a server running on the acctual port, and therefor we send an error message
	 * and will terminate this new instance of the server.
	 * 
	 * Also creates a GameEngine.
	 * 
	 * 
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
					"You can only start one instance of the server at a time!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * When the thread is started we will listen for new connections.
	 * When a user has connected a player will be added and ServerClient*-classes created)
	 * 
	 * 
	 */
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