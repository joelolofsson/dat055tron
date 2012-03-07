import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/** 
 * Reads the keyboard input, is the user has pressed any of the four arrow keys
 * it will update it's observer (NetworkClient) with the new direction.
 * 
 * @author Group 2
 *  
 */
public class KeyReader extends Observable implements KeyListener 
{
	private final int UP = 1;	
	private final int RIGHT = 2;
	private final int DOWN = 3;
	private final int LEFT = 4;	
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
	/*
	 * Is not used
	 */
	public void keyReleased(KeyEvent arg0){}
	/*
	 * Is not used
	 */
	public void keyTyped(KeyEvent arg0){}
}
