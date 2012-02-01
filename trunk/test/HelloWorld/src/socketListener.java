import java.io.*;
import java.net.*;

public class socketListener {

	/**
	 * @param args
	 */
		public static void main(String[] args) throws IOException {
			
			int port = 1234;
			
	        System.out.println("Starting server on port: " + Integer.toString(port));
	        ServerSocket serverSocket = null;
	        try {
	        	serverSocket = 
	        	    new ServerSocket(port);
	        	} 
	        	catch (IOException e) {
	        	System.out.println
	        	    ("Could not listen on port: " + Integer.toString(port));
	        	System.exit(-1);
	        	}
	        
	        Socket clientSocket = null;
	        try {
	        clientSocket = serverSocket.accept();
	        } 
	        catch (IOException e) {
	        System.out.println("Accept failed: 4444");
	        System.exit(-1);
	        }

	        
	        PrintWriter out = new PrintWriter
	        	   (clientSocket.getOutputStream(), true);
	        	BufferedReader in = 
	        	    new BufferedReader(
	        	    new InputStreamReader(
	        	    clientSocket.getInputStream()));
	        	String inputLine, outputLine;

	        	//Teständring
	        	

	        	while ((inputLine = in.readLine()) != null)
	        	{   
	        	outputLine = inputLine;
	        	System.out.println(outputLine);
		        	if (outputLine.equals("quit")){
		        	break;
		        	} else if(outputLine.equals("Hej")){
		        		out.println("Hej själv!");
		        		
		        	}
	        	}
	        
	        
	        /*try {
	            Thread.sleep(10000);
	         }
	         catch (Exception e) {}*/
	        
	        serverSocket.close();
	        System.out.println("Server stopped.");
		}
}
