import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;


public class NetworkServer extends Observable  {

	
	
	ServerSocket serversocket;
	Socket klientSock;
	DataInputStream in;
	DataOutputStream out;
	Point point;
	int id;
	int course;
	int x;
	int y;
	
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
			out = new DataOutputStream(klientSock.getOutputStream());
		} catch (IOException e) {

		}
		
		try{
			in = new DataInputStream(klientSock.getInputStream());
		}
		catch(IOException e)
		{
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	public void sendPoint(Point point, int id)
	{
		x = (int) point.getX();
		y = (int) point.getY();
		
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(id);
	}
		
		
		
		
	}
	
	
	
}
