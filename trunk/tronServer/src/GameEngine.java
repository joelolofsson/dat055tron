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
 * @author Group 2
 * 
 * Handles all gamelogic
 */
public class GameEngine implements ActionListener, Observer {
	
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
	 * @param ServerClientHandler serverClientHandler
	 * @param ServerClientSenderUDP serverClientSenderUDP
	 */
	public void addPlayer(ServerClientHandler serverClientHandler, ServerClientHandlerUDP serverClientHandlerUDP, ServerClientSenderUDP serverClientSenderUDP, ServerClientSenderTCP serverClientSenderTCP)
	{
		String namn;
		int id = serverClientHandlerUDP.getID();
		serverClientSenderUDPList.add(serverClientSenderUDP);
		serverClientSenderTCPList.add(serverClientSenderTCP);
		namn = serverClientHandler.namn();
		System.out.println(namn.toString());
		serverClientHandlerUDP.addObserver(this);
		playerList.add(new Player(id, namn, 3, new Point((((id + 1) % 2) + 1) * 150, ((id / 2) + 1) * 150)));
		serverClientSenderTCP.send(0, new Integer(id).toString());			//Skickar id till klient
		sendPlayers();
	}
	
	public void updateScore()
	{
	}
	/**
	 * Starts the timer which starts the game.
	 * 
	 * @param int numberOfPlayers
	 */
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
		String poang = "";
		if(numberOfPlayers < 2)
		{
			for(Player p : playerList)
			{
				if(p.isAlive() == true)
				{
					p.setScore(reset-numberOfPlayers);
				}
				poang = poang + p.getScore() + ",";
			}
			for(ServerClientSenderTCP s : serverClientSenderTCPList)
			{
				s.send(2, poang);
			}
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
	
	/**
	 * Starts the timer which starts the game.
	 * 
	 * @param int numberOfPlayers
	 */
	public void start(int numberOfPlayers)
	{
		this.numberOfPlayers = numberOfPlayers;
		reset = numberOfPlayers;
		System.out.println("Startar game");
		
		sendPlayers();
		timer.start();
	}

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
			System.out.println("Skickar namn...");
		}
	}
}
