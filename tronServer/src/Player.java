import java.awt.Point;

/**
 * Class that creates and handles the player-objects.
 * 
 * @author Group 2
 * 
 * 
 */
public class Player
{
	private int id;
	private int course;
	private Point point;
	private String name;
	private int score = 0;
	private boolean alive = true;
	private int startX;
	private int startY;
	private boolean allowTurn = true;
	private final int UP = 1;
	private final int RIGHT = 2;
	private final int DOWN = 3;
	private final int LEFT = 4;
	
	/**
	 * Default constructor for Players
	 * 
	 * @param id	Player id
	 * @param name	Nickname of the player
	 * @param course	Players current course
	 * @param point	Players current x and y coordinate.
	 */
	public Player(int id, String name, int course, Point point)
	{
		this.id = id;
		this.name = name;
		this.course = course;
		this.point = point;
		startX = point.x;
		startY = point.y;
	}
	
	/**
	* 
    * Returns a players id
	* 
	* @return id
	*/
	public int getId()
	{
		return id;
	}
	
	/**
	* 
    * Returns a players course
	* 
	* @return course
	*/
	public int getCourse()
	{
		return course;
	}
	
	/**
	* 
    * Returns a players coordinates in a point object
	* 
	* @return Point
	*/
	public Point getPoint()
	{
		return new Point(point.x, point.y);
	}
	
	/**
	* 
    * Resets a players cordinates to initial values.
	* 
	* 
	*/
	public void reset()
	{
		point.x = startX;
		point.y = startY;
	}
	
	/**
	* 
    * Returns a players name
	* 
	* @return name
	*/
	public String getName()
	{
		return name;
	}
	
	/**
	* 
    * Returns a players score
	* 
	* @return score
	*/
	public int getScore()
	{
		return score;
	}
	
	/**
	* 
    * Sets a players course.
    * If the absolute value for current course and new course is 2, 
    * the course will not be changed.
    * Doing this would be going in apposit direction and the player would 
    * automatically die.   
	* 
	* @param course The new course.
	*/
	public void setCourse(int course)
	{
		if(!(Math.abs(this.course-course) == 2) && allowTurn)
		{
			this.course = course;
			allowTurn = false;
		}
		
	}
	
	/**
	* 
    * Sets a players score
	* 
	* @param score
	*/
	public void setScore(int score)
	{
		this.score = this.score + score;
	}
	
	
	/**
	* 
    * Returns a player object alive status
	* 
	* @return alive Holds the current status of the player is dead or alive.
	*/
	public boolean isAlive()
	{
		return alive;
	}
	
	/**
	* 
    * Sets a players alive status
	* 
	* @param alive The new status of the player.
	*/
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}

	
	/**
	 * Calculates the next coordinate depending of current coordinate
	 * and the user course.
	 */
	public void move()
	{
		switch(course)
		{
			case UP: point.y = point.y - 1;
					break;
			case RIGHT: point.x = point.x + 1;
					break;
			case DOWN: point.y = point.y + 1;
					break;
			case LEFT: point.x = point.x - 1;
					break;
		}	
		allowTurn = true;
	}
}