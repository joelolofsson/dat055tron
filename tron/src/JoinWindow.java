import java.awt.*;
import java.net.*;
import java.awt.event.*;
import javax.swing.*;


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

	public JoinWindow(){
		setTitle("Join game");
		setLayout(new GridLayout(4,2));

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
			}
		}if(buttonPressed == bCancel){
			dispose();
		}




	}
}
