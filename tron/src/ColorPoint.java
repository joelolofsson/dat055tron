import java.awt.Color;
import java.awt.Point;

/**
 *Extends java.awt.Point to contain the color of the actual point
 *
 *@author Group 2 
 * 
 *
 */
public class ColorPoint extends Point 
{

	private Color color;

	/**
	 * Default constructor for ColorPoint
	 * Sets the x and y position in Point by a (super)-call
	 * and the color.
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
	
	/**
	 * Returns the color of the point
	 * @return Color
	 * 
	 */
	public Color getColor()
	{
		return color;
	}
}
