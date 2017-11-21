package tank;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class NetworkServerWriteThread extends Thread
	{
	//OutputStreamWriter osw; // declare an output stream writer to write to the client
	Scanner input; // declare a scanner
	//	Socket s; // declare a socket, represents the client
	String temp = ""; // declare a temporary string
	boolean terminated = false; // declare a flag indicating whether the read thread has terminated
	//	NetworkServerMain sm;
	NetworkServerWriteThread() // constructor for the class
		{
		// set the socket to the client
		}
	public void run() // upon starting the thread...
	{
	try
		{
		//osw=new OutputStreamWriter(s.getOutputStream()); // initialize the output stream writer to write to the client
		//input = new Scanner(System.in); // initialize the scanner to scan standard in
		while(true) // infinite loop
			{
			if(terminated == true) // check if the read thread has terminated
				break; // done
		}
			
		//s.close(); // close the socket
		//osw.close(); // close the output stream writer
		for(PrintWriter writer : NetworkServerMain.writers)
			{
			writer.close();
			}
		}
	catch (Exception e) // if there was a problem...
		{
		//e.printStackTrace();
		return;
		}
	}
	public void displayMessageToClients(String string)
		{
			for(PrintWriter writer : NetworkServerMain.writers)
			{
				writer.println(string);
			}
	}

	public void interupt() // method to interrupt the thread
	{
	System.out.println("Client has exited. Press <ENTER> to continue."); // print a message to the user indicating the client has logged out, they need to press enter to proceed past the scanner so the thread can close
	terminated = true; // set the flag indicating the read thread has ended
	}
	}
