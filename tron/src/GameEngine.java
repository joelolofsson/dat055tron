import java.awt.*;
import java.util.*;


public class GameEngine {
	
	LinkedHashSet<Point> cords;
	List<Player> playerList;
	
	public GameEngine()
	{
		playerList = new LinkedList<Player>();
		cords = new LinkedHashSet<Point>();
	}

	public static void main(String[] args)
	{
	}
	
	public void createPlayer()
	{
		playerList.add(new Player());
	}
	
	public void newChord()
	{
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
	}

}
