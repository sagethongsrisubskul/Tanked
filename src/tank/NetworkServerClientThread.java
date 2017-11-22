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
		System.out.println("A new socketClient has connected."); // print a message to the user of the socket
		rt = new NetworkServerReadThread(client); // initialize the read thread
		rt.start(); // start the read thread
		//writeThread=new WriteThread(socketClient); // initialize the write thread
		//writeThread.start(); // start the write thread
		}
	}

