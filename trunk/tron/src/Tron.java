import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class Tron extends JFrame implements ActionListener{
	private JPanel west;
	private JPanel center;
	private JPanel south;
	private JLabel p1;
	private JLabel p2;
	private JLabel p3;
	private JLabel p4;
	private JButton start;
	private JButton paus;
	private JMenuBar menu;
	private JMenu game;
	private JMenuItem newGame;
	private JMenuItem joinGame;
	private JMenu help;
	private JMenuItem about;
	private JMenuItem howToPlay;
	private JButton okButton;
	private JDialog aboutDialogWindow;
		
	public Tron() {
			
			west = new JPanel();
			center = new JPanel();
			south = new JPanel();
			p1 = new JLabel();
			p2 = new JLabel();
			p3 = new JLabel();
			p4 = new JLabel();
			start = new JButton();
			paus = new JButton();
			menu = new JMenuBar();
			game = new JMenu("Game");
			help = new JMenu("Help");
			newGame = new JMenuItem("Create new game");
			joinGame = new JMenuItem("Join game");
			about = new JMenuItem("About");
			howToPlay = new JMenuItem("How to play");
			add(menu);
			menu.add(game);
			menu.add(help);
			game.add(newGame);
			game.add(joinGame);
			help.add(about);
			help.add(howToPlay);
			newGame.addActionListener(this);
			joinGame.addActionListener(this);
			about.addActionListener(this);
			howToPlay.addActionListener(this);
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			pack();
			
			
		}
	
	public void actionPerformed(ActionEvent e){
		Object knappTryck = e.getSource();
		if(knappTryck  instanceof JMenuItem ){
			if(knappTryck == newGame)
				System.out.println("New Game");
			if(knappTryck == joinGame)
				System.out.println("Join Game");
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
	
	/*
	public JDialog aboutDialog(){
			JOptionPane pane = new JOptionPane("This is our version of the classic game Tron.", 
			JOptionPane.INFORMATION_MESSAGE);
			JDialog aboutDialog = pane.createDialog("About");
			aboutDialog.setModal(false);
			aboutDialog.setVisible(true);
			return aboutDialog();
			
			
			JDialog aboutDialog = new JDialog();
			aboutDialog.setLayout(new GridLayout(3,1));
			JLabel emptyLabel = new JLabel();
			JLabel aboutLabel = new JLabel("This is our version of the classic game Tron.", JLabel.CENTER);
			JLabel headLabel = new JLabel("Tron", JLabel.CENTER);
			okButton = new JButton("OK");
			okButton.addActionListener(this);
			okButton.setPreferredSize(new Dimension(25, 25));
			headLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
			headLabel.setHorizontalTextPosition(JLabel.CENTER);
			aboutLabel.setHorizontalTextPosition(JLabel.CENTER);
			aboutLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
			aboutDialog.add(headLabel);
			aboutDialog.add(aboutLabel);
			aboutDialog.add(okButton);			
			aboutDialog.setVisible(true);
			aboutDialog.setSize(300,300);
			aboutDialog.setResizable(false);
			return aboutDialog;
			
			
		}
	*/
	
	
	public static void main(String[] arg){
		Tron tron = new Tron();
		
	}
}


