import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ServerGui extends JFrame implements ActionListener {
	
	private JButton connect;
	private JButton start;
	private JLabel ipAdress;
	private NetworkServer networkServer;
	InetAddress IP;
	public static JLabel[] players = new JLabel[4];
	
	public ServerGui()
	{
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		JPanel meny = new JPanel();
		connect = new JButton("Connect");
		start = new JButton("Start");
		networkServer = new NetworkServer();
		ipAdress = new JLabel("Created new server on " + IP.getHostAddress());
		meny.setLayout(new FlowLayout());
		meny.add(connect);
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
		connect.addActionListener(this);
		start.addActionListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == connect)
		{
			networkServer.connect();
		}
		else if(e.getSource() == start)
		{
			networkServer.start();
		}
	}
	
	public static void main(String[] arg)
	{
		ServerGui serverGui = new ServerGui();
	}
	

}
