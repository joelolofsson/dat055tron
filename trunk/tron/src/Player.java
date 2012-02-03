import java.awt.Color;
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
	private int posX;
	private int posY;
	private String name;
	private int score = 0;
	private boolean alive = true;
	
	public Player(Color color, String name, int course, int posX, int posY){
		this.color = color;
		this.name = name;
		this.course = course;
		this.posX = posX;
		this.posY = posY;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getCourse() {
		return course;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
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
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean isAlive() {
		return alive;
	}
}
