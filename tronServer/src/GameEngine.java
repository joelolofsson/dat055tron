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
	private Player player;
	private ServerClientSenderUDP serverClientSenderUDP;
	
	/*
	 * Skapar NetworkServer objekt
	 * och lista att sparaspelarna i
	 * och lista att spara alla spelarnas kordinater i
	 * och en timer
	 */
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(1, this);
	}
	
	/*
	 * uppdaterar sig med information om riktning från NetworkServer
	 */
	public void update(Observable o, Object arg)
	{
		int[] recived;
		if(o instanceof ServerClientHandler)
		{
			recived = (int[]) arg;

			System.out.println("Knapptryck från klien id:");
			System.out.println(recived[0]);
			playerList.get(recived[0]).setCourse(recived[1]);
			System.out.println("har satt id " + recived[0] + " med kurs " + recived[1]);
		}
	}
	
	public void addPlayer(ServerClientHandler serverClientHandler, ServerClientSenderUDP serverClientSenderUDP)
	{
		this.serverClientSenderUDP = serverClientSenderUDP;
		serverClientHandler.addObserver(this);

		playerList.add(new Player(serverClientHandler.getID(), "calle", 1, new Point(200, 200)));
	}
	
	public void updateScore()
	{
	}
	
	public void clearGame()
	{
	}

	public void actionPerformed(ActionEvent e)
	{
		for(Player p : playerList)
		{
		System.out.println("går in i action");
			p.move();
			serverClientSenderUDP.send(p.getPoint());
			if(checkCrash(p.getPoint()))
			{
				System.out.println("Spelaren har inte krashat");
				System.out.println(p.getPoint().getX());
				serverClientSenderUDP.send(p.getPoint());
				addCord(p.getPoint());
				//networkServer.sendPoint(p.getPoint(), p.getId());
			}
			else
			{
				p.setAlive(false);
				//timer.stop();
			}
		}
	}
	
	public void addCord(Point p)
	{
		cords.add(p);
	}
	
	/*
	 * kollar om masken har krachat
	 */
	public boolean checkCrash(Point p)
	{
		for(Point pos : cords)
		{
			if(pos.equals(p) || p.x < 0 || p.x > 400 || p.y < 0 || p.y > 400)
			{
				return false;
			}
		}
		return true;
	}
	
	public void start()
	{
		timer.start();
	}

}
