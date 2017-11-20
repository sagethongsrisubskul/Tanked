package tank;
import java.net.Socket;
public class NetworkServerClientThread extends Thread
	{
	NetworkServerReadThread rt; // declare a read thread
	NetworkServerWriteThread wt; // declare a write thread
	Socket client;
	//private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	NetworkServerClientThread(Socket s)
		{
		client = s;
		}
	public void run()
		{
		System.out.println("A new client has connected."); // print a message to the user of the server
		rt = new NetworkServerReadThread(client); // initialize the read thread
		rt.start(); // start the read thread
		//wt=new WriteThread(client); // initialize the write thread
		//wt.start(); // start the write thread
		}
	}

