import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;


public class KeyReader extends Observable implements KeyListener {
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 4;
	private int direction;
	
	public KeyReader(){
		
		
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			direction=UP;
			setChanged();
			notifyObservers(direction);
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			direction=DOWN;
			setChanged();
			notifyObservers(direction);
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			direction=LEFT;
			setChanged();
			notifyObservers(direction);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			direction=RIGHT;
			setChanged();
			notifyObservers(direction);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
