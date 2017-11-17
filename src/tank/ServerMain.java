package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class ServerMain 
{
	ServerSocket server; // declare a server socket
	Socket client; // declare a socket representing the client
	String temp; // declare a temporary string
	ServerReadThread rt; // declare a read thread
	ServerWriteThread wt; // declare a write thread
	ServerClientThread ct;
	int portnumber=1201;
	public static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	public void go() // start the server
	{
		try {
			server=new ServerSocket(portnumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // initialize the server
		wt=new ServerWriteThread();
		wt.start();
		while(true) // infinite loop, allow the client to connect again after exiting
		{
			System.out.println("SERVERMAINTEST");
			try
			{	
				
				client=server.accept(); // initialize the client
				System.out.println("Client Connected");
		
				ct= new ServerClientThread(client);
				ct.start();
				
				writers.add(new PrintWriter(client.getOutputStream(), true));
				
				//client.close(); // close the client socket
				//server.close(); // close the server socket
			}
			catch(Exception e) // if there was a problem...
			{
				System.out.println("Error, originated from server class.");
				//e.printStackTrace();
				return;
			}
		}
	}
}