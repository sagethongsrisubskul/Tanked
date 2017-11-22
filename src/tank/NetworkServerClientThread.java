package tank;
import java.net.Socket;
public class NetworkServerClientThread extends Thread
	{
	NetworkServerReadThread readThread; // declare a read thread
	Socket socket;
	NetworkServerClientThread(Socket socket)
		{
		this.socket = socket;
		}
	public void run()
		{
		System.out.println("A new socketClient has connected."); // print a message to the user of the socket
		readThread = new NetworkServerReadThread(socket); // initialize the read thread
		readThread.start(); // start the read thread
		}
	}