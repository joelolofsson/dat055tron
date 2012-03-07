import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Creates a GUI for server application.
 * It will create a NetworkServer and set this to accept new connections.
 * The gui will be updates when players connect.
 * When hitting "Start"-button a start-method in NetworkServer will be called.
 * 
 * @author Group 2
 * 
 * 
 * 
 */

public class ServerGui extends JFrame implements ActionListener
{	
	private JButton start;
	private JLabel ipAdress;
	private NetworkServer networkServer;
	private InetAddress IP;
	public static JLabel[] players = new JLabel[4];
	
	/**
	 * Default constructor for ServerGui.
	 * Sets the layou, creates the networkserver and set this to accept new connections.
	 * 
	 */
	public ServerGui()
	{
		try
		{
			IP = InetAddress.getLocalHost();
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		JPanel meny = new JPanel();
		start = new JButton("Start");
		networkServer = new NetworkServer();
		ipAdress = new JLabel("Created new server on " + IP.getHostAddress());
		setTitle("TronServer");
		meny.setLayout(new FlowLayout());
		meny.add(start);
		meny.add(ipAdress);
		add(meny);
		JPanel player = new JPanel();
		player.setLayout(new BoxLayout(player,BoxLayout.Y_AXIS));
		for(int i = 0; i < players.length; i++)
		{
			players[i] = new JLabel("Player " + (i+1));
			player.add(players[i]);
		}
		add(player);
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		start.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		networkServer.connect();
		
	}
	
	/**
	 * Handles actions when "Start" is pressed.
	 * 
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == start)
		{
			networkServer.start();
		}
	}
	
	/**
	 * Creates a server GUI
	 * 
	 * @param String[] arg
	 */
	public static void main(String[] arg)
	{
		ServerGui serverGui = new ServerGui();
	}
}
