package tank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
public class NetworkClientReadThread extends Thread // class for reading input from an input stream
	{
	Socket socket; // declare a socket
	String temp = null; // declare a temporary string
	BufferedReader bufferedReader; // declare a buffered reader
	boolean terminated = false; // declare a flag indicating whether the socketClient wants to logout
	/*-----------------------------------------------------------------------------------------------------*/
	NetworkClientReadThread(Socket socket) // constructor for the class
	{
	this.socket = socket; // assign the socket to the socket given in the argument for the constructor
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // upon starting the thread...
	{
	try
		{
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // initialize the buffered reader
		while(terminated == false && (temp = bufferedReader.readLine()) != null) // while the socketClient has not logged out...
			{
			NetworkControl.displayMessage(temp);
			System.out.println("ClientReadThread: " + temp); // ...print whatever the socket is typing in
			}
		}
	catch (Exception e) // if there was a problem...
		{
		e.printStackTrace();
		terminated = true;
		return;
		}
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void interrupt() // method to interrupt the thread
	{
	terminated = true; // set the flag to true indicating the socketClient wants to logout
	}
	}