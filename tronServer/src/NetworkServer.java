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
	
	public NetworkServer()
	{	
		try
		{
			System.out.println("Nu har vi skapat servern");
			serversocket = new ServerSocket(1337);
			System.out.println("Vi har skapat socket");
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
		for(int j = 0; j < 4; j++)
		{
		try
		{
			klientSock = serversocket.accept();
			if(temp)
			{
			System.out.println(klientSock.getInetAddress().getHostName() + " har anslutit sig");
			ServerGui.players[j].setText("Player " + (j+1) + ": " + klientSock.getInetAddress().getHostAddress());
			gameEngine.addPlayer(new ServerClientHandler(klientSock, j), new ServerClientSenderUDP(klientSock.getInetAddress()));
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
		gameEngine.start();
	}
	
	public void connect()
	{
		thread.start();
	}
		
		
}