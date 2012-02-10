import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

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
	private NetworkServer networkServer;
	
	/*
	 * Skapar NetworkServer objekt
	 * och lista att sparaspelarna i
	 * och lista att spara alla spelarnas kordinater i
	 * och en timer
	 */
	public GameEngine()
	{
		networkServer = new NetworkServer();
		playerList = new LinkedList<Player>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(100, this);
		networkServer.addObserver(this);
	}
	
	/*
	 * uppdaterar sig med information om riktning från NetworkServer
	 */
	public void update(Observable o, Object arg)
	{
		if(o instanceof NetworkServer && arg instanceof CourseID)
		{
			CourseID temp = (CourseID) arg;
			for(Player p : playerList)
			{
				if(p.getId() == temp.getID())
				{
				}
			}
		}
	}
	
	public void createPlayer(int id, String name, int pos, Point point)
	{
		playerList.add(new Player(id, name, pos, point));
	}
	
	public void sendPlayerToNet()
	{
	}
	
	public void updateScore()
	{
	}
	
	public void clearGame()
	{
	}
	
	/*
	 */
	public void actionPerformed(ActionEvent e)
	{
		for(Player p : playerList)
		{
			p.move();
			if(checkCrash(p.getPoint()))
			{
				addCord(p.getPoint());
				networkServer.sendPoint(p.getPoint(), p.getId());
			}
			else
			{
				p.setAlive(false);
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

}
