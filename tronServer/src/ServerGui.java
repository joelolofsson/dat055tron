import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ServerGui extends JFrame implements ActionListener {
	
	private JButton connect;
	private JButton start;
	private JLabel ipAdress;
	private NetworkServer networkServer;
	private GameEngine gameEngine;
	InetAddress IP;
	
	public ServerGui()
	{
		try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		connect = new JButton("Connect");
		start = new JButton("Start");
		networkServer = new NetworkServer();
		ipAdress = new JLabel("Created new server on " + IP.getHostAddress());
		setLayout(new FlowLayout());
		connect.addActionListener(this);
		start.addActionListener(this);
		add(connect);
		add(start);
		add(ipAdress);
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
