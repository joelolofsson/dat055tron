import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**	public class CreateGameWindow
 * 	@version 1.0
 * 	@author Erik, Joel
 * 
 */
public class CreateGameWindow extends JDialog implements ActionListener {
	private JButton bCreate;
	private JButton bCancel;
	private JLabel portLabel;
	private JLabel winLabel;
	private JTextField portTextField;
	private JTextField winTextField;
	private int portInt;
	private int winLimit;

	
	public CreateGameWindow(){
		setTitle("Create new game");
		setLayout(new GridLayout(3,2));

		//ipTextField.addActionListener(this);
		portTextField = new JTextField(5);
		winTextField = new JTextField(5);
		//portTextField.addActionListener(this);

		//Create buttons
		bCreate = new JButton("Create");
		bCancel = new JButton("Cancel");
		bCreate.addActionListener(this);
		bCancel.addActionListener(this);


		//Create Labels for each text field
		portLabel = new JLabel("Port:");
		winLabel = new JLabel("Win limit");

		//Add labels, text fields and buttons
		add(portLabel); add(portTextField);
		add(winLabel); add(winTextField);
		add(bCreate); add(bCancel);
		setSize(300,150);
		setVisible(true);

	}



	public void actionPerformed(ActionEvent e){
		JButton buttonPressed = (JButton) e.getSource();
		boolean isOK = true;
		if(buttonPressed == bCreate){
			try{
				portInt = Integer.parseInt(portTextField.getText());
			} 
			catch (NumberFormatException fel){
				isOK = false;
				//	JOptionPane.showMessageDialog(this,
				//            "Only numbers 0-9 is allowed for port number", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if(portInt < 1025 && portInt < 65536){
				isOK = false;
				JOptionPane.showMessageDialog(this,
						"Please enter a port number between 1025 and 65535", "Error", JOptionPane.ERROR_MESSAGE);

			}
			try{
				winLimit = Integer.parseInt(winTextField.getText());
			} 
			catch (NumberFormatException fel){
				isOK = false;
				JOptionPane.showMessageDialog(this,
						"Only numbers 0-9 is allowed for win limit", "Error", JOptionPane.ERROR_MESSAGE);
			}

			if(isOK){
				dispose();
				//Tron.gameEngine = new GameEngine();
				//Tron.gameEngine.thread.start();
				System.out.println("Game created on port " + portInt + " with win limit" + winLimit );
				
			}
		}if(buttonPressed == bCancel){
			dispose();
		}


	}




}

