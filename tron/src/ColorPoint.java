import java.awt.Color;
import java.awt.Point;



public class ColorPoint extends Point {
	
	public Color color;
	
		public ColorPoint(int x, int y, Color color)
		{
			super(x,y);
			this.color=color;
		}
}
