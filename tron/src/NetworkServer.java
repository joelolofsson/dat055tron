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
	int idCourse;
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
			out = new DataOutputStream(klientSock.getOutputStream());
		} catch (IOException e) {

		}
		
		try{
			in = new DataInputStream(klientSock.getInputStream());
		}
		catch(IOException e)
		{
			
		}
		
		while(true)
		{
		course = in.readInt();
		idCourse = in.readInt();
		setChanged();
		notifyObservers(new CourseID(course, idCourse));
		
		}
		
		
		
		
		
		
		
		
	}
	
	
	public void sendPoint(Point point, int id)
	{
		int x;
		int y;
		
		x = (int) point.getX();
		y = (int) point.getY();
		
		try {
			out.writeInt(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeInt(y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.writeInt(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
		
		
}
	
	
	

