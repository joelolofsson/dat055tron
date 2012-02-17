import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * @author Carl-Johan Adolfsson, Tobias Hallberg
 * 
 * The main class that handles the game server.
 */
public class GameEngine implements ActionListener, Observer {
	
	private LinkedHashSet<Point> cords;
	private java.util.List<Player> playerList;
	private Timer timer;
	private LinkedList<ServerClientSenderUDP> serverClientSenderUDPList;
	
	/*
	 * Skapar NetworkServer objekt
	 * och lista att sparaspelarna i
	 * och lista att spara alla spelarnas kordinater i
	 * och en timer
	 */
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		serverClientSenderUDPList = new LinkedList<ServerClientSenderUDP>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(10, this);
	}
	
	/*
	 * uppdaterar sig med information om riktning fr�n NetworkServer
	 */
	public void update(Observable o, Object arg)
	{
		int[] recived;
		if(o instanceof ServerClientHandler)
		{
			recived = (int[]) arg;

			System.out.println("Knapptryck fr�n klient-id:" + recived[0]);
			playerList.get(recived[0]).setCourse(recived[1]);
			System.out.println("har satt id " + recived[0] + " med kurs " + recived[1]);
		}
	}
	
	public void addPlayer(ServerClientHandler serverClientHandler, ServerClientSenderUDP serverClientSenderUDP)
	{
		serverClientSenderUDPList.add(serverClientSenderUDP);
		serverClientHandler.addObserver(this);
		playerList.add(new Player(serverClientHandler.getID(), "calle", 1, new Point((serverClientHandler.getID() + 1)*30, 200)));
	}
	
	public void updateScore()
	{
	}
	
	public void clearGame()
	{
	}

	public void actionPerformed(ActionEvent e)
	{
		
		LinkedList<Point> temp = new LinkedList<Point>();
		
		for(Player p : playerList)
		{
			System.out.println("g�r in i action");
			
			if(p.isAlive()){
				p.move();
				System.out.println("Spelare " + p.getId() + "lever.");
				if(checkCrash(p.getPoint()))
				{
					System.out.println("Spelare " + p.getId() + "�r d�d!");
					p.setAlive(false);
				}
				cords.add(p.getPoint());
			}
			
			temp.add(p.getPoint());
			
		}
		
		for(Player p : playerList)
		{
			
			serverClientSenderUDPList.get(p.getId()).send(temp);
		}
	}
	
	/*
	 * kollar om masken har krachat
	 */
	public boolean checkCrash(Point p)
	{
		for(Point pos : cords)
		{
			if(pos.equals(p))// || p.x < 0 || p.x > 400 || p.y < 0 || p.y > 400)
			{
				System.out.println("crash");
				return true;
			}
		}
		return false;
	}
	
	public void start()
	{
		timer.start();
	}

}
