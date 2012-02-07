import java.awt.*;
/**
 * 
 * @author Tobias Hallberg
 * 
 * Class creates and handles the player-objects.
 * 
 * Course translation table:
 * 1 = "north/up"
 * 2 = "east/right"
 * 3 = "south/down"
 * 4 = "west/left"
 */
public class Player {

	private Color color;
	private int course;
	private Point point;
	private String name;
	private int score = 0;
	private boolean alive = true;
	
	public Player(Color color, String name, int course, Point point){
		this.color = color;
		this.name = name;
		this.course = course;
		this.point = point;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getCourse() {
		return course;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setCourse(int course) {
		this.course = course;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
	
		//1 = upp 2 = höger
		//3 = ner 4 = vänster
		public void calcNewCord()
		{
				if(course == 1)
				{
					point.y = point.y - 1;
				}
				else if(course == 2)
				{
					point.x = point.x + 1;
				}
				else if(course == 3)
				{
					point.y = point.y + 1;
				}
				else if(course == 4)
				{
					point.x = point.x - 1;
				}
		}
}
