package tank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerReadThread extends Thread // class for reading input from an input stream
{
	Socket s; // declare a socket
	String temp; // declare a temporary string
	BufferedReader br; // declare a buffered reader
	boolean terminated=false; // declare a flag indicating whether the socket (that buffered reader is reading from) has closed
	
	ServerReadThread(Socket s) // constructor for the class
	{
		this.s=s; // assign the socket to the socket given in the argument for the constructor
	}
	
	public void run() // upon starting the thread...
	{
		try
		{
			br=new BufferedReader(new InputStreamReader(s.getInputStream())); // initialize buffered reader
			while((temp=br.readLine()).compareTo("logout")!=0) // while the client has not logged out...
				System.out.println(temp); // ... print whatever the client is typing in
			terminated=true; // set terminated to true, the client has logged out
			s.close(); // close the socket
			br.close(); // close the buffered reader
		}
		catch(Exception e) // if there was a problem...
		{
			//e.printStackTrace();
			terminated=true;
			return;
		}
	}
}

