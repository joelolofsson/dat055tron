import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**	
 *@author Group 2
 * 
 * 	Handles the main GUI and menu. 
 * 
 */

public class Tron extends JFrame implements ActionListener{
	private JPanel west;
	public static GameWindow center;
	private JPanel south;
	private JLabel[] playersLabel;
	private static JLabel connected;
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
	private NetworkClient networkClient;
	private KeyReader key = new KeyReader();
	
	/**
	 * Default constructor for Tron
	 * 
	 */
	
	public Tron() {

		//Set layout of main window
		setLayout(new BorderLayout());
		west = new JPanel();
		center = new GameWindow(key);
		south = new JPanel();

		//Set labels
		playersLabel = new JLabel[4];
		for(int i = 0; i < playersLabel.length; i++){
			playersLabel[i] = new JLabel("P" + (i+1) + " not connected");
			west.add(playersLabel[i]);
		}
		connected = new JLabel("Connected to server: n/a");

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
		//west.setPreferredSize(new Dimension(80,400));
		west.setLayout(new BoxLayout(west,BoxLayout.Y_AXIS));
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
	/**
	 * Set text in the textfield Connected
	 * @param String IP
	 * @return void
	 */
	public static void setConnected(String IP)
	{
		connected.setText("Connected to server: "+ IP);
	}
	/**
	 * Action performed
	 * 
	 * @param ActionEvent e
	 * @return void
	 */
	public void actionPerformed(ActionEvent e){
		Object knappTryck = e.getSource();
		if(knappTryck  instanceof JMenuItem ){
			if(knappTryck == newGame)
			{
				//new CreateGameWindow();
			}
			if(knappTryck == joinGame)
				new JoinWindow(networkClient, key);
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

	/**
	 * Creates a Tron GUI
	 * 
	 * @param String[] arg
	 */
	public static void main(String[] arg){
		Tron tron = new Tron();

	}
}


