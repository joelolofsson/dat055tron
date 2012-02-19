import java.awt.*;
/**
 * 
 * @author Tobias Hallberg, Carl Johan Adolfsson
 * 
 * Class that creates and handles the player-objects.
 */
public class Player
{

	private int id;
	private int course;
	private Point point;
	private String name;
	private int score = 0;
	private boolean alive = true;
	private int rX;
	private int rY;
	
	public Player(int id, String name, int course, Point point)
	{
		this.id = id;
		this.name = name;
		this.course = course;
		this.point = point;
		rX = point.x;
		rY = point.y;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getCourse()
	{
		return course;
	}
	
	public Point getPoint()
	{
		return new Point(point.x, point.y);
	}
	
	public void reset()
	{
		point.x = rX;
		point.y = rY;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setCourse(int course)
	{
		if(!(Math.abs(this.course-course) == 2))
		this.course = course;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	
	/**
	 * Course translation table:
	 * 1 = "north/up"
	 * 2 = "east/right"
	 * 3 = "south/down"	
	 * 4 = "west/left"
	 */
	public void move()
	{
		switch(course)
		{
			case 1: point.y = point.y - 1;
					break;
			case 2: point.x = point.x + 1;
					break;
			case 3: point.y = point.y + 1;
					break;
			case 4: point.x = point.x - 1;
					break;
		}	
	}
}
