import java.io.*;
import java.net.*;
import java.util.*;


public class NetworkServer extends Observable  {

	
	
	ServerSocket serversocket;
	Socket klientSock;
	ObjectInputStream in;
	DataOutputStream out;
	
	int course; 
	
	public NetworkServer()
	{
		try {
			serversocket = new ServerSocket(27015);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			klientSock = serversocket.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			in = new ObjectInputStream(klientSock.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			out = new DataOutputStream(klientSock.getOutputStream());
		} catch (IOException e) {

		}
		
		try {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
}
