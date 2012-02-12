import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;


import javax.swing.*;

/*	public class GameWindow
 * 	@version 1.0
 * 	@author Erik, Joel
 * 
 */
public class GameWindow extends JPanel implements ActionListener{
	public int mcSize = 3;
	private HashSet<ColorPoint> pointList;
	private Timer timer = new Timer(10, this);
	
	
	public GameWindow(KeyReader key){
		super(true);
		pointList = new HashSet<ColorPoint>();
		addKeyListener(key);
	}
	
	public void init(){
		pointList.clear();
		timer.start();
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(ColorPoint point : pointList){
			g.setColor(point.color);
			g.fillRect(point.x, point.y, mcSize, mcSize);
	//		System.out.println(point);
		}


	}

	public void actionPerformed(ActionEvent e){
		requestFocus();
		repaint();

	}

}


