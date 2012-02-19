import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;


public class NetworkServer extends Observable implements Runnable {

	
	
	private ServerSocket serversocket;
	private Socket klientSock;
	private boolean temp = true;
	private Thread thread;
	private GameEngine gameEngine;
	private int numberOfPlayers = 0;
	
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
					gameEngine.addPlayer(new ServerClientHandler(klientSock, numberOfPlayers), new ServerClientSenderUDP(klientSock.getInetAddress()));
					numberOfPlayers++;
				}
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		}
	}
	
	public void start()
	{
		temp = false;
		gameEngine.start(numberOfPlayers);
	}
	
	public void connect()
	{
		thread.start();
	}
		
		
}