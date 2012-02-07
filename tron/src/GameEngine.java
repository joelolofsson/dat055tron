import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

/**
 * 
 * @author Carl-Johan Adolfsson, Tobias Hallberg
 * 
 * The main class that handles the game server.
 */
public class GameEngine implements ActionListener {
	
	LinkedHashSet<Point> cords;
	java.util.List<Player> playerList;
	Timer timer;
	
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(100, this);
		
		//Creates a border that surrounds the game plan.
		for(int i = 0; i < 399; i ++)
		{
			cords.add(new Point(0, i));
		}
		for(int i = 0; i < 399; i ++)
		{
			cords.add(new Point(i, 400));
		}
		for(int i = 0; i < 399; i ++)
		{
			cords.add(new Point(400, i));
		}
		for(int i = 0; i < 399; i ++)
		{
			cords.add(new Point(i, 0));
		}
	}

	public static void main(String[] args)
	{
		GameEngine gameEngine = new GameEngine();
	}
	
	public void createPlayer(Color color, String name, int pos, Point point)
	{
		playerList.add(new Player(color, name, pos, point));
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
	
	public void actionPerformed(ActionEvent e)
	{
		for(Player p : playerList)
		{
			p.setCourse(1);
			p.move();
			if(checkCrash(p.getPoint()))
			{
				addCord(p.getPoint());
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
	
	public boolean checkCrash(Point p)
	{
		Point temp = new Point(p.x, p.y);
		for(Point pos : cords)
		{
			if(pos.equals(temp))
			{
				return false;
			}
		}
		return true;
	}

}
