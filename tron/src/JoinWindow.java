import java.awt.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;

/**	
 * 	@author Group 2
 * 
 * Handles the JoinWindow GUI and creates NetworkClients for connection to server.
 * 
 */
public class JoinWindow extends JDialog implements ActionListener {
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
	 * @param NetworkClient networkClient
	 * @param KeyReader key
	 */
	public JoinWindow(NetworkClient networkClient, KeyReader key){
		setTitle("Join game");
		setModal(true);
		this.key = key;
		setLayout(new GridLayout(4,2));
		this.networkClient = networkClient;

		//Create text fields
		ipTextField = new JTextField(11);
		portTextField = new JTextField(5);
		nickNameTextField = new JTextField(30);

		//Create buttons
		bConnect = new JButton("Connect");
		bCancel = new JButton("Cancel");
		bConnect.addActionListener(this);
		bCancel.addActionListener(this);


		//Create Labels for each text field
		ipLabel = new JLabel("IP adress:");
		portLabel = new JLabel("Port:");
		nickNameLabel = new JLabel("Nickname:");

		//Add labels, text fields and buttons
		add(ipLabel); add(ipTextField);
		add(portLabel); add(portTextField);
		add(nickNameLabel); add(nickNameTextField);
		add(bConnect); add(bCancel);
		pack();
		setVisible(true);
	
	}

	
	/**
	 * Handles actions from pressed buttons 
	 * and takes care of the user inputdata
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e){
		JButton buttonPressed = (JButton) e.getSource();
		boolean isOK = true;
		if(buttonPressed == bConnect){
			//ipString = ipTextField.getText();
			try{
				ipadress = InetAddress.getByName(ipTextField.getText());
			}
			catch (UnknownHostException fel)	{
				isOK = false;
				JOptionPane.showMessageDialog(this,
						"You need to enter a correct ip adress", "Error", JOptionPane.ERROR_MESSAGE);
			}
			try{
				portInt = Integer.parseInt(portTextField.getText());
			} 
			catch (NumberFormatException fel){
				isOK = false;
				JOptionPane.showMessageDialog(this,
						"Only numbers 0-9 is allowed for port number", "Error", JOptionPane.ERROR_MESSAGE);
			}
			nickNameString = nickNameTextField.getText();

			if(isOK){
				System.out.println(ipadress + " : " + portInt + " : " + nickNameString);
				dispose();
				networkClient = new NetworkClient(ipadress, portInt, nickNameString, key);
			}
		}if(buttonPressed == bCancel){
			dispose();
		}

	}
}
