import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;


import javax.swing.*;

/*	public class GameWindow
 * 	@version 1.0
 * 	@author Erik, Joel
 * 
 */
public class GameWindow extends JPanel implements ActionListener, Observer{
	public int mcSize = 1;
	private HashSet<ColorPoint> pointList;
	private LinkedList<ColorPoint> tempList;


	public GameWindow(KeyReader key){
		super(true);
		pointList = new HashSet<ColorPoint>();
		tempList = new LinkedList<ColorPoint>();
		addKeyListener(key);
	}

	public void init(){
		pointList.clear();

	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(ColorPoint point : pointList){
			g.setColor(point.color);
			g.fillRect(point.x, point.y, mcSize, mcSize);
			//		System.out.println(point);
		}while(!tempList.isEmpty()){
			pointList.add(tempList.get(0));
			tempList.remove(0);

		}
	}




	public void update(Observable o, Object arg){
		{
			if(o instanceof NetworkClientReceiver && arg instanceof ColorPoint){
				tempList.add((ColorPoint) arg);
				repaint();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}


