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
	private int numberOfPlayers;
	private int reset;
	
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		serverClientSenderUDPList = new LinkedList<ServerClientSenderUDP>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(10, this);
	}
	
	public void update(Observable o, Object arg)
	{
		int[] recived;
		if(o instanceof ServerClientHandler)
		{
			recived = (int[]) arg;
			playerList.get(recived[0]).setCourse(recived[1]);
		}
	}
	
	public void addPlayer(ServerClientHandler serverClientHandler, ServerClientSenderUDP serverClientSenderUDP)
	{
		int id = serverClientHandler.getID();
		serverClientSenderUDPList.add(serverClientSenderUDP);
		serverClientHandler.addObserver(this);
		playerList.add(new Player(id, "calle", 3, new Point((((id + 1) % 2) + 1) * 150, ((id / 2) + 1) * 150)));
	}
	
	public void updateScore()
	{
	}
	
	public void clearGame()
	{
		cords.clear();
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		System.out.println("omg�ngen slut");
		for(Player p : playerList)
		{
			p.reset();
			p.setAlive(true);
		}
		numberOfPlayers = reset;
	}

	public void actionPerformed(ActionEvent e)
	{
		
		LinkedList<Point> temp = new LinkedList<Point>();
		
		if(numberOfPlayers < 2)
		{
			clearGame();
			temp.add(new Point(0, 0));
		}
		else
		{
		for(Player p : playerList)
		{	
			if(p.isAlive())
			{
				p.move();
				if(checkCrash(p.getPoint()))
				{
					p.setAlive(false);
				}
				cords.add(p.getPoint());
			}
			
			temp.add(p.getPoint());
			
		}
		}
		for(ServerClientSenderUDP s : serverClientSenderUDPList)
		{
			s.send(temp);
		}
	}
	
	public boolean checkCrash(Point p)
	{
		for(Point pos : cords)
		{
			if(pos.equals(p) || p.x < 0 || p.x > 400 || p.y < 0 || p.y > 400)
			{
				numberOfPlayers--;
				return true;
			}
		}
		return false;
	}
	
	public void start(int numberOfPlayers)
	{
		timer.start();
		this.numberOfPlayers = numberOfPlayers;
		reset = numberOfPlayers;
	}

}
