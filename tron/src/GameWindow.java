import java.awt.Graphics;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**	
 * 	Creates the actual panel where the game is taking place.
 * 	Receives colorpoints, saves these in a list and then repaint the whole list.
 * 	
 * 	@author Group 2
 *  
 *  
 */
public class GameWindow extends JPanel implements Observer
{
	private int mcSize = 3;
	private HashSet<ColorPoint> pointList;
	private LinkedList<ColorPoint> tempList;
	
	/**
	 * Default constructor for GameWindow
	 * 
	 * @param KeyReader key  - I used to add the key as a listener for gamewindow
	 * 
	 */
	public GameWindow(KeyReader key)
	{
		super(true);
		pointList = new HashSet<ColorPoint>();
		tempList = new LinkedList<ColorPoint>();
		addKeyListener(key);
	}

	/**
	 * Draw all points from the list with saves colorpoints
	 * and updates the list with the one from a temporary list
	 * 
	 * @param Graphics g
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(ColorPoint point : pointList)
		{
			g.setColor(point.getColor());
			g.fillRect(point.x, point.y, mcSize, mcSize);
		}
		while(!tempList.isEmpty())
		{
			try
			{
			pointList.add(tempList.get(0));
			tempList.remove(0);
			}
			catch(NullPointerException e)
			{
				tempList.clear();
				break;
			}
		}
	}

	/**
	 * Is called when NetworkClientReceiverUDP sets notifyObserver()
	 * Adds the new point to a temporarylist, or clears the list if
	 * a new game will begin.
	 * 
	 * @param Observable o
	 * @param Object arg
	 */
	public void update(Observable o, Object arg)
	{
			if(o instanceof NetworkClientReceiverUDP && arg instanceof ColorPoint)
			{
				tempList.add((ColorPoint) arg);
				repaint();
			}
			else if(o instanceof NetworkClientReceiverUDP && arg instanceof String)
			{
				pointList.clear();
				tempList.clear();
			}
	}
}


