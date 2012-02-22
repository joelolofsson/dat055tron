import java.awt.Color;
import java.awt.Point;

/**
 *@author Group 2 
 * 
 *A Point object with features of x,y coordinates and color
 */
public class ColorPoint extends Point 
{

	public Color color;

	/**
	 * Default constructor for ColorPoint
	 * 
	 * @param int x
	 * @param int y
	 * @param Color color
	 */
	public ColorPoint(int x, int y, Color color)
	{
		super(x,y);
		this.color = color;
	}
}
