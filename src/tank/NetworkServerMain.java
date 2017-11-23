package tank;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
public class NetworkServerMain extends Thread
	{
	ServerSocket serverSocket; // declare a socket socket
	Socket socketClient; // declare a socket representing the socketClient
	NetworkServerWriteThread writeThread; // declare a write thread
	NetworkServerClientThread clientThread;
	public static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // start the socket
	{
	try
		{
		serverSocket = new ServerSocket(Settings.portnumber);
		}
	catch (IOException e)
		{
		e.printStackTrace();
		}
	// initialize the socket:
	NetworkControl.successServer();
	writeThread = new NetworkServerWriteThread();
	writeThread.start();
	// infinite loop, allow the socketClient to connect again after exiting:
	while(true)
		{
		try
			{
			socketClient = serverSocket.accept(); // initialize the socketClient
			System.out.println("Client Connected");
			clientThread = new NetworkServerClientThread(socketClient);
			clientThread.start();
			writers.add(new PrintWriter(socketClient.getOutputStream(), true));
			}
		catch (Exception e) // if there was a problem...
			{
			e.printStackTrace();
			System.out.println("Error, originated from socket class.");
			return;
			}
		}
	}
	}