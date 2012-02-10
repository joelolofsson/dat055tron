import java.io.*;
import java.net.*;


public class NetworkServer {

	ServerSocket serversocket;
	Socket klientSock;
	DataInputStream in;
	DataOutputStream out;
	
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
			in = new DataInputStream(klientSock.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			out = new DataOutputStream(klientSock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
}
