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
 * @author Group 2
 * 
 * Handles all gamelogic
 */
public class GameEngine implements ActionListener, Observer
{	
	private LinkedHashSet<Point> cords;
	private java.util.List<Player> playerList;
	private Timer timer; 
	private LinkedList<ServerClientSenderUDP> serverClientSenderUDPList;
	private LinkedList<ServerClientSenderTCP> serverClientSenderTCPList;
	private int numberOfPlayers;
	private int reset;
	
	/**
	 * Default constructor for GameEngine
	 */
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		serverClientSenderUDPList = new LinkedList<ServerClientSenderUDP>();
		serverClientSenderTCPList = new LinkedList<ServerClientSenderTCP>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(10, this);
	}
	
	/**
	 * Sets course for each player
	 * @param Observable o
	 * @param Object arg
	 * arg is int[] 
	 * int[0] = Player ID
	 * int[1] = Player course
	 */
	public void update(Observable o, Object arg)
	{
		int[] recived;
		if(o instanceof ServerClientHandlerUDP && arg instanceof int[])
		{
			recived = (int[]) arg;
			playerList.get(recived[0]).setCourse(recived[1]);
		}
	}
	
	/**
	 * 
	 * Creates and add player to playerlist.  
	 * 
	 * @param ServerClientHandlerTCP serverClientHandlerTCP
	 * @param ServerClientHandlerUDP serverClientHandlerUDP
	 * @param ServerClientSenderUDP serverClientSenderUDP
	 * @param ServerClientSenderTCP serverClientSenderTCP
	 */
	public void addPlayer(ServerClientHandlerTCP serverClientHandlerTCP, ServerClientHandlerUDP serverClientHandlerUDP,
			ServerClientSenderUDP serverClientSenderUDP, ServerClientSenderTCP serverClientSenderTCP)
	{
		
		int id = serverClientHandlerUDP.getID();//Gets connected client id
		serverClientSenderUDPList.add(serverClientSenderUDP);
		serverClientSenderTCPList.add(serverClientSenderTCP);
		String namn = serverClientHandlerTCP.namn();
		serverClientHandlerUDP.addObserver(this);
		/*
		 * Creates new player and sets start coordinates and course
		 */
		int x,y;
		x = (((id + 1) % 2) + 1) * 150;
		y = ((id / 2) + 1) * 150;
		playerList.add(new Player(id, namn, 3, new Point(x, y)));
		serverClientSenderTCP.send(0, new Integer(id).toString());
		sendPlayers();
	}
	
	/**
	 * Empty cords, reset player start course and sleeps for 5 sec
	 */
	private void clearGame()
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
		for(Player p : playerList)
		{
			p.reset();
			p.setAlive(true);
		}
		numberOfPlayers = reset;
	}
	
	/**
	 * Sends score to clients
	 */
	private void endOfGame()
	{
		String score = "";
		for(Player p : playerList)
		{
			if(p.isAlive() == true)
			{
				p.setScore(reset-numberOfPlayers);
			}
			score = score + p.getScore() + ",";
		}
		for(ServerClientSenderTCP s : serverClientSenderTCPList)
		{
			s.send(2, score);
		}
	}
	
	/**
	 * Updates cords, if a active game
	 * if not a active game, send score to clients and reset game.
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		LinkedList<Point> temp = new LinkedList<Point>();
		
		if(numberOfPlayers < 2)
		{
			endOfGame();
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
						p.setScore(reset - numberOfPlayers - 1);
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
	
	/**
	 * Checks if a players cordinate already exists in the list
	 * if returned true, a crash has been detected
	 * @param Point p
	 * @return boolean
	 */
	private boolean checkCrash(Point p)
	{
		for(Point pos : cords)
		{
			if(pos.equals(p) || p.x < 0 || p.x >= 398 || p.y < 0 || p.y >= 398)
			{
				numberOfPlayers--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Starts the timer which starts the game.
	 * 
	 * @param int numberOfPlayers
	 */
	public void start(int numberOfPlayers)
	{
		this.numberOfPlayers = numberOfPlayers;
		reset = numberOfPlayers;
		sendPlayers();
		timer.start();
	}
	
	/**
	 * Send playernames to clients
	 */
	private void sendPlayers()
	{
		for(ServerClientSenderTCP s : serverClientSenderTCPList)
		{
			String sTemp = "";
			for(Player p : playerList)
			{
				sTemp = sTemp + p.getName() + ",";
			}
			s.send(1, sTemp);
		}
	}
}
