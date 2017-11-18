package tank;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;

public class ServerClientThread extends Thread {
	ServerReadThread rt; // declare a read thread
	ServerWriteThread wt; // declare a write thread
	Socket client;
	//private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	
	ServerClientThread(Socket s){
		client=s;
	}
	
	public void run() {
	
	System.out.println("A new client has connected."); // print a message to the user of the server
	
	rt=new ServerReadThread(client); // initialize the read thread
	rt.start(); // start the read thread

	
	//wt=new WriteThread(client); // initialize the write thread
	//wt.start(); // start the write thread
	
	

	
	
	}	

}

