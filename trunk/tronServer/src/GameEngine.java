import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

/**
 * Handles all gamelogic in the game such as adding players,
 * clearing game, make sure new coordinates is ok and sends information to the clients.
 * 
 * 
 * @author Group 2
 * 
 * 
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
	 * Sets up a list with players, a list with different ServerclientSenders,
	 * a list with (all used) coordinates and sets the timer (for the speed of the game).
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
	 * @param o Class who called notifyObservers
	 * @param arg	Object passed from the class o.  In this case an int[].
	 * int[0] = Player ID, int[1] = Player course
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
	 * Creates and add player to playerlist with its different serverclient*-classes. 
	 * 
	 * @param serverClientHandlerTCP
	 * @param serverClientHandlerUDP
	 * @param serverClientSenderUDP
	 * @param serverClientSenderTCP
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
	 * Is called when a game round has ended and a new game will start.
	 * The list with coordinates will be cleared, players will be
	 * set to initial start values and set as alive.
	 * It will sleep for 5 sec and then the game starts again.
	 * 
	 * 
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
	 * Updates coordinates every time the timer has reached is value, if a active game
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
	 * 
	 * @param p new (calculated) coordinate
	 * @return true if this is a crash (coordinate has aleady been used), false if not.
	 * 
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
	 * @param numberOfPlayers Number of players in the game.
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
