import java.awt.Color;
import java.awt.Point;


/**
 *@author Group 2 
 * 
 *A Point object with features of x,y cordinates and color
 */
public class ColorPoint extends Point {
	
	public Color color;
	
	/**
	 * Default constructor for ColorPoint
	 * 
	 * @param int x
	 * @param int y
	 * @param Color Color
	 */
		public ColorPoint(int x, int y, Color color)
		{
			super(x,y);
			this.color=color;
		}
}
