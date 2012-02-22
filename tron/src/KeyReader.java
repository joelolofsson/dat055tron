import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/** 
 * @author Group 2
 * Reads the key input from arrow keys and updates observers
 * 
 * 
 */
public class KeyReader extends Observable implements KeyListener 
{
	public static final int UP = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;
	public static final int LEFT = 4;
	private int direction;
	
	/**
	 * Handles pressed keys and notify observers
	 * 
	 * @param KeyEvent e
	 */
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			direction=UP;
			setChanged();
			notifyObservers(direction);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			direction=DOWN;
			setChanged();
			notifyObservers(direction);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			direction=LEFT;
			setChanged();
			notifyObservers(direction);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			direction=RIGHT;
			setChanged();
			notifyObservers(direction);
		}
	}
	
	public void keyReleased(KeyEvent arg0){}
	public void keyTyped(KeyEvent arg0){}
}
