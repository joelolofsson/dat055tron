import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;
/**
 * @author Group 2
 * 
 * Handles Indata from server with protocoll TCP
 * 
 */

public class NetworkClientReceiverTCP extends Observable implements Runnable {
        
        private DataInputStream dataInputStream;
        private Thread thread;
        private int clientId = -1;

        /**
         * Default constructor for NetworkClientReceiverTCP
         * @param Socket socket
         */
        public NetworkClientReceiverTCP(Socket socket)
        {
                try {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        thread = new Thread(this);
                        thread.start();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        /**
         * Receives and handle playername
         */
        public void run()
        {
                while(true)
                {
                        try 
                        {
	                        System.out.println("Försöker ta emot data");
	                        
	                        if(clientId == -1)
	                        {
	                        	clientId = new Integer(dataInputStream.readUTF().toString()).intValue();
	                        }
	                        
	                        int packetId = dataInputStream.readInt();
	                        String s;
	                        if(packetId == 1)
	                        {
	                        	//Vi har fått namn
	                        	s = dataInputStream.readUTF().toString();
	                        	System.out.println("har tagit emot namn" + s);
	                        	
	                        	String[] temp = s.split(",");
	 	                        for(int i = 0; i < temp.length; i++)
	 	                        {
	 	                        	if(i == 0)
	 	                        	{
	 	                        		Tron.playersLabel[i].setText(temp[i]);
	 	                        		Tron.playersLabel[i].setForeground(Color.cyan);
	 	                        	}
	 	                        	else if(i == 1)
	 	                        	{
	 	                        		Tron.playersLabel[i].setText(temp[i]);
	 	                        		Tron.playersLabel[i].setForeground(Color.green);
	 	                        	}
	 	                        	else if(i == 2)
	 	                        	{
	 	                        		Tron.playersLabel[i].setText(temp[i]);
	 	                        		Tron.playersLabel[i].setForeground(Color.red);
	 	                        	}
	 	                        	else if(i == 3)
	 	                        	{
	 	                        		Tron.playersLabel[i].setText(temp[i]);
	 	                        		Tron.playersLabel[i].setForeground(Color.blue);
	 	                        	}
	 	                        }
	                        } else if(packetId == 2)
	                        {
	                        	//Vi har fått poäng
	                        	s = dataInputStream.readUTF().toString();
	                        	System.out.println("har tagit emot poäng" + s);
	                        	
	                        	//Lägg koden här
	                        }

	                       
                        }
                        catch (IOException e1)
                        {
                                e1.printStackTrace();
                        }
                }
        }
        public int getClientId()
        {
        	return clientId;
        }
        
        public void setClientId(int clientId)
        {
        	this.clientId =  clientId;
        }
        
}