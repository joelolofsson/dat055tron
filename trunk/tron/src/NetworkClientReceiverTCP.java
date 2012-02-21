import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;


public class NetworkClientReceiverTCP extends Observable implements Runnable {
        
        private DataInputStream dataInputStream;
        private Thread thread;

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
        
        public void run()
        {
                while(true)
                {
                        try 
                        {
                        System.out.println("försöker ta emot");
                        String s = dataInputStream.readUTF().toString();
                        System.out.println("har tagit emot namn" + s);
                        setChanged();
						notifyObservers(s);
                        } catch (IOException e1) {
                                e1.printStackTrace();
                        }
                }
        }
}