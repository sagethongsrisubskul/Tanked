package tank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientReadThread extends Thread // class for reading input from an input stream
{
	Socket s; // declare a socket
	String temp; // declare a temporary string
	BufferedReader br; // declare a buffered reader
	boolean terminated=false; // declare a flag indicating whether the client wants to logout
	
	ClientReadThread(Socket s) // constructor for the class
	{
		this.s=s; // assign the socket to the socket given in the argument for the constructor
	}
	
	public void run() // upon starting the thread...
	{
		try
		{
			br=new BufferedReader(new InputStreamReader(s.getInputStream())); // initialize the buffered reader
			while(terminated==false && (temp=br.readLine())!=null) // while the client has not logged out...
			{
				System.out.println(temp); // ...print whatever the server is typing in
			}
		}
		catch(Exception e) // if there was a problem...
		{
			terminated=true;
			return;
		}
	}
	
	public void interrupt() // method to interrupt the thread
	{
		terminated=true; // set the flag to true indicating the client wants to logout
	}
}