import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;


public class NetworkServer extends Observable implements Runnable {

	
	
	ServerSocket serversocket;
	Socket klientSock;
	DataInputStream in;
	DataOutputStream out;
	Point point;
	int idCourse;
	int course=0;
	int i =1;
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
		int i = 0;
		while(temp)
		{
		try
		{
			klientSock = serversocket.accept();
			System.out.println(klientSock.getInetAddress().getHostName() + " har anslutit sig");
			ServerGui.players[i].setText("Player " + (i+1) + ": " + klientSock.getInetAddress().getHostAddress());
			gameEngine.addPlayer(new ServerClientHandler(klientSock), new ServerClientSender(klientSock));
			i++;
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
		try
		{
			new Socket(serversocket.getInetAddress(), serversocket.getLocalPort()).close();
			gameEngine.start();
		}
		catch(IOException e)
		{
		}
		System.out.println("försöker stoppa conent");
		System.out.println(temp);
	}
	
	public void connect()
	{
		thread.start();
	}
	
	
	public void sendPoint(Point point, int id)
	{
	

	}
		
		
}