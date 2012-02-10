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
	int course=0;
	int i =1;
	
	public NetworkServer()
	{
		System.out.println("Nu har vi skapat servern");
		
		try {
			serversocket = new ServerSocket(27015);
		} catch (IOException e) {
			System.out.println("IOException på new ServerSocket(27015");
			e.printStackTrace();
		}
		
		try {
			klientSock = serversocket.accept();
			setChanged();
			notifyObservers(i);
			System.out.println("Någon har anslutit sig!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IOException på serversocket.accept");
			e.printStackTrace();
		}
		
		try {
			out = new DataOutputStream(klientSock.getOutputStream());
		} catch (IOException e) {
			System.out.println("IOException på klientSock.getOutputStream()");
		}
		
		try{
			in = new DataInputStream(klientSock.getInputStream());
			System.out.println("klientSock.getInputStream()");
		}
		catch(IOException e)
		{
			System.out.println("IOException klientSock.getInputStream()");
		}
		
		while(true)
		{
		try {
			course = in.readInt();
			if(course != 0)
			{
				break;
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		idCourse = 1;
		setChanged();
		notifyObservers(new CourseID(course, idCourse));
		course = 0;
				
		
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
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
}
	
	
	

