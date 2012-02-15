import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;


import javax.swing.*;

/*	public class GameWindow
 * 	@version 1.0
 * 	@author Erik, Joel
 * 
 */
public class GameWindow extends JPanel implements ActionListener, Observer{
	public int mcSize = 3;
	private HashSet<ColorPoint> pointList;
	
	
	public GameWindow(KeyReader key){
		super(true);
		pointList = new HashSet<ColorPoint>();
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
		}


	}

	
	public void update(Observable o, Object arg){
			{
			pointList.add((ColorPoint) arg);
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}


