import java.awt.*;
/**
 * 
 * @author Group 2
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
	
	/**
	 * Default constructor for Players
	 * 
	 * @param int id
	 * @param String name
	 * @param int course
	 * @param Point point
	 */
	public Player(int id, String name, int course, Point point)
	{
		this.id = id;
		this.name = name;
		this.course = course;
		this.point = point;
		rX = point.x;
		rY = point.y;
	}
	
	/**
	* 
    * Returns a players id
	* 
	* @return int
	*/
	public int getId()
	{
		return id;
	}
	
	/**
	* 
    * Returns a players course
	* 
	* @return int
	*/
	public int getCourse()
	{
		return course;
	}
	
	/**
	* 
    * Returns a players cordinates in a point object
	* 
	* @return Point
	*/
	public Point getPoint()
	{
		return new Point(point.x, point.y);
	}
	
	/**
	* 
    * Resets a players cordinates
	* 
	* 
	*/
	public void reset()
	{
		point.x = rX;
		point.y = rY;
	}
	
	/**
	* 
    * Returns a players name
	* 
	* @return String
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* 
    * Returns a players score
	* 
	* @return int
	*/
	public int getScore()
	{
		return score;
	}
	
	/**
	* 
    * Sets a players course
	* 
	* @param int course
	*/
	public void setCourse(int course)
	{
		if(!(Math.abs(this.course-course) == 2))
		this.course = course;
	}
	
	/**
	* 
    * Sets a players score
	* 
	* @param int score
	*/
	public void setScore(int score)
	{
		this.score = this.score + score;
	}
	
	
	/**
	* 
    * Returns a player object alive status
	* 
	* @return int
	*/
	public boolean isAlive()
	{
		return alive;
	}
	
	/**
	* 
    * Sets a players alive status
	* 
	* @param boolean alive
	*/
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
