import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;


public class GameEngine implements ActionListener {
	
	LinkedHashSet<Point> cords;
	java.util.List<Player> playerList;
	Timer timer;
	
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		cords = new LinkedHashSet<Point>();
		timer = new Timer(100, this);
	}

	public static void main(String[] args)
	{
		GameEngine gameEngine = new GameEngine();
	}
	
	public void createPlayer(Color color, String name, int pos, int xpos, int ypos)
	{
		playerList.add(new Player(color, name, pos, xpos, ypos));
	}
	
	//1 = upp 2 = höger
	//3 = ner 4 = vänster
	public void calcNewCord()
	{
		int tempX;
		int tempY;
		for(Player p : playerList)
		{
			tempX = p.getPosX();
			tempY = p.getPosY();
			if(p.getCourse() == 1)
			{
				p.setPosY(tempY - 1);
			}
			else if(p.getCourse() == 2)
			{
				p.setPosX(tempX + 1);
			}
			else if(p.getCourse() == 3)
			{
				p.setPosY(tempY + 1);
			}
			else if(p.getCourse() == 4)
			{
				p.setPosX(tempX - 1);
			}
		}
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
	
	public boolean crash()
	{
		return true;
	}
	
	public void actionPerformed(ActionEvent e)
	{
	}

}
