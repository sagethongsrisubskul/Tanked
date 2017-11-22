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
	public static PrintWriter pw;
	String ipnumber = null;
	NetworkClientMain(String ip)
		{
		ipnumber = ip;
		}
	NetworkClientMain()
		{
		}
	public void run() // start the client
	{
	try
		{
		server = new Socket(ipnumber, 1201); // attempt to connect to the IP on port 1201
		rt = new NetworkClientReadThread(server); // initialize the read thread and pass the server socket
		rt.start(); // start the read thread
		pw = new PrintWriter(server.getOutputStream(), true);
		/// If client is successful:
		NetworkControl.successClient(ipnumber);
		}
	catch (Exception e)  // if there was a problem...
		{
		System.out.println("A problem occured.");
		return;
		}
	}
	}