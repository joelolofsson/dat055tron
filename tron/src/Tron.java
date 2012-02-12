import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*	public class Tron
 * 	@version 1.0
 * 	@author Erik, Joel
 * 
 */

public class Tron extends JFrame implements ActionListener{
	private JPanel west;
	private GameWindow center;
	private JPanel south;
	private JLabel p1;
	private JLabel p2;
	private JLabel p3;
	private JLabel p4;
	private JLabel connected;
	private JButton start;
	private JButton paus;
	private JMenuBar menu;
	private JMenu game;
	private JMenuItem newGame;
	private JMenuItem joinGame;
	private JMenuItem exit;
	private JMenu help;
	private JMenuItem about;
	private JMenuItem howToPlay;
	public JoinWindow a;
	public CreateGameWindow b;
	public NetworkClient networkClient;
	
	public Tron() {

		//Set layout of main window
		setLayout(new BorderLayout());
		west = new JPanel();
		center = new GameWindow();
		south = new JPanel();

		//Set labels
		p1 = new JLabel("Player 1");
		p2 = new JLabel("Player 2");
		p3 = new JLabel("Player 3");
		p4 = new JLabel("Player 4");
		connected = new JLabel("Connected to server: ");

		//Create menu bar, buttons and items
		start = new JButton("Start");
		paus = new JButton("Paus");
		menu = new JMenuBar();
		game = new JMenu("Game");
		help = new JMenu("Help");
		newGame = new JMenuItem("Create new game");
		joinGame = new JMenuItem("Join game");
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");
		howToPlay = new JMenuItem("How to play");

		//Add menu to the border layout and correct place in the menu bar
		menu.add(game);
		menu.add(help);
		game.add(newGame);
		game.add(joinGame);
		game.add(exit);
		help.add(about);
		help.add(howToPlay);

		//Initiate action listener for the buttons.
		newGame.addActionListener(this);
		joinGame.addActionListener(this);
		exit.addActionListener(this);
		about.addActionListener(this);
		howToPlay.addActionListener(this);

		//Set layout for the west panel for start/paus button and player list.
		west.setPreferredSize(new Dimension(80,400));
		west.setLayout(new BoxLayout(west,BoxLayout.Y_AXIS));
		west.add(p1); west.add(p2); west.add(p3); west.add(p4);
		west.add(Box.createVerticalGlue());
		west.add(Box.createRigidArea(new Dimension(15,0)));
		west.add(start);
		west.add(paus);

		//Set layout of the center panel (gaming window)
		center.setPreferredSize(new Dimension(400,400));
		center.setBackground(Color.black);
		south.add(connected);

		//Add panels to the border layout and initiate visibility, size and exit operation of main window
		add(west, BorderLayout.WEST);
		add(south, BorderLayout.SOUTH);
		add(menu, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		setTitle("Tron");
		setResizable(false);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		center.requestFocus();




	}

	public void actionPerformed(ActionEvent e){
		Object knappTryck = e.getSource();
		if(knappTryck  instanceof JMenuItem ){
			if(knappTryck == newGame)
				b = new CreateGameWindow();
			if(knappTryck == joinGame)
				a = new JoinWindow(networkClient);
			if(knappTryck == exit)
				System.exit(0);		
			if(knappTryck == about)
				JOptionPane.showMessageDialog(this,
						"This is our version of the classic game Tron.", "About", JOptionPane.DEFAULT_OPTION);
			if(knappTryck == howToPlay)
				JOptionPane.showMessageDialog(this,
						"To create a game:" +
								"\nChoose 'Game' from main menu -> 'Create game' "+
								"\nEnter the port number and the score limit required to win the game." +
								"\nWait for users to enter and then press start." +
								"\n"+
								"\nTo connect to a game:"+
								"\nChoose 'Game' from main menu -> 'Join game'."+
								"\nEnter the ip adress and port number for the host and your nickname."+
								"\nPress connect."+
								"\n"+
								"\nTo play the game:"+
								"\nEnter arrow keys (up, down, left and right) to change direction of your motorcycle."+
								"\nTry to avoid hitting the edges of the game area, your own or other players previous track."+
								"\nIf you hit either of this object's you die. Last man standing wins the current round.", "About", JOptionPane.DEFAULT_OPTION);
		}



	}


	public static void main(String[] arg){
		Tron tron = new Tron();

	}
}


