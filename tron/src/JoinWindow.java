import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**	
 * Creates a dialog when a user wants to join game.
 * It will ask for ip, port and nickname and then create a  
 * NetworkClient which will handle the connection to the server.
 * 
 * @author Group 2
 * 
 * 
 */
public class JoinWindow extends JDialog implements ActionListener 
{
	private JButton bConnect;
	private JButton bCancel;
	private JLabel ipLabel;
	private JLabel portLabel;
	private JLabel nickNameLabel;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JTextField nickNameTextField;
	private int portInt;
	private String nickNameString;
	private InetAddress ipadress;
	private NetworkClient networkClient;
	private KeyReader key;

	/**
	 * Default constructor for JoinWindow
	 * 
	 * @param networkClient	Handles communication with the server
	 * @param key		Is passed as an parameter when creating the NetworkClient
	 */
	public JoinWindow(NetworkClient networkClient, KeyReader key)
	{
		setTitle("Join game");
		setModal(true);
		this.key = key;
		setLayout(new GridLayout(4,2));
		this.networkClient = networkClient;

		//Create text fields
		ipTextField = new JTextField(11);
		portTextField = new JTextField(5);
		nickNameTextField = new JTextField(30);

		//Set default values
		portTextField.setText("1337");

		
		//Create buttons
		bConnect = new JButton("Connect");
		bCancel = new JButton("Cancel");
		bConnect.addActionListener(this);
		bCancel.addActionListener(this);

		//Sets the default button
		getRootPane().setDefaultButton(bConnect);
		
		//Create Labels for each text field
		ipLabel = new JLabel("IP adress:");
		portLabel = new JLabel("Port:");
		nickNameLabel = new JLabel("Nickname:");

		//Add labels, text fields and buttons
		add(ipLabel); add(ipTextField);
		add(portLabel); add(portTextField);
		add(nickNameLabel); add(nickNameTextField);
		add(bConnect); add(bCancel);
		nickNameTextField.addKeyListener(enter);
		pack();
		setVisible(true);
	}

	/**
	 * Handles actions from pressed buttons 
	 * and takes care of the user input data
	 * 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		JButton buttonPressed = (JButton) e.getSource();
		if(buttonPressed == bConnect)
		{
			connectPressed();
		}
		if(buttonPressed == bCancel)
		{
			dispose();
		}
	}
	/**
	 * This method is called then a user has either pressed the "connect"-button
	 * or has pressed enter in the nickname-field.
	 * This is where the input data will be saved and the NetworkClient created.
	 * 
	 */
	public void connectPressed()
	{
		boolean isOK = true;
		try
		{
			ipadress = InetAddress.getByName(ipTextField.getText());
		}
		catch (UnknownHostException fel)	
		{
			isOK = false;
			JOptionPane.showMessageDialog(this,
					"You need to enter a correct ip adress", "Error", JOptionPane.ERROR_MESSAGE);
		}
		try
		{
			portInt = Integer.parseInt(portTextField.getText());
		} 
		catch (NumberFormatException fel)
		{
			isOK = false;
			JOptionPane.showMessageDialog(this,
					"Only numbers 0-9 is allowed for port number", "Error", JOptionPane.ERROR_MESSAGE);
		}
		nickNameString = nickNameTextField.getText();
		if(isOK)
		{
			dispose();
			networkClient = new NetworkClient(ipadress, portInt, nickNameString, key);
		}
	}

	/**
	 * Keylistener for nickname textfield. Hitting "Enter" in this textfield
	 * will execute same command as if the button "Connect" was pressed.
	 * 
	 */
	KeyListener enter = new KeyAdapter()
	{
		public void keyReleased( KeyEvent e )
		{
			if( e.getKeyCode() == KeyEvent.VK_ENTER )
			{
				connectPressed();


			}



		}
	};
}
