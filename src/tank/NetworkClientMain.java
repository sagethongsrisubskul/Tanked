package tank;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class NetworkClientMain extends Thread
	{
	Socket server; // declare a socket, represents the server
	//BufferedReader br; // declare a buffered reader
	String temp; // declare a temporary string
	NetworkClientReadThread rt; // declare a read thread
	Scanner input;
	String ipnumber = null;
	NetworkClientMain(String ip)
		{
		ipnumber = ip;
		}
	public void run() // start the client
	{
	try
		{
		//System.out.print("Enter the IP you wish to connect to: "); // inform the user of the client to enter an IP address
		//temp=new Scanner(System.in).nextLine(); // capture the input
		server = new Socket(ipnumber, 1201); // attempt to connect to the IP on port 1201
		System.out.println("You have connected to the server. Use keyword \"logout\" to exit."); // inform the user of the client that they connected successfully
		rt = new NetworkClientReadThread(server); // initialize the read thread and pass the server socket
		rt.start(); // start the read thread
		//OutputStreamWriter osw=new OutputStreamWriter(server.getOutputStream()); // initialize the output stream writer to write to the server
		PrintWriter pw = new PrintWriter(server.getOutputStream(), true);
		Scanner input = new Scanner(System.in); // initialize the scanner to scan from standard in
		/// If client is successful:
		NetworkControl.successClient(ipnumber);

		while(true) // infinite loop
			{
			temp = input.nextLine(); // read a line from standard in
			// read a line from standard in
			//osw.write(temp); // write the line to the server
			pw.println(temp);
			//osw.flush(); // flush output stream writer
			if(temp.compareTo("logout\n") == 0) // check if the user of the client typed the keyword logout
				break; // done
			}
		System.out.println("Finished the session and successfully logged out."); // print a message to the user of the client informing them that they are now disconnected from the server
		rt.interrupt(); // interrupt the read thread
		pw.close(); // close the output stream writer
		input.close(); // close the scanner
		server.close(); // close the socket
		}
	catch (Exception e)  // if there was a problem...
		{
		System.out.println("A problem occured.");
		return;
		}
	}
	}