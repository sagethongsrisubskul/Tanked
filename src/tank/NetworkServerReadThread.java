package tank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
public class NetworkServerReadThread extends Thread // class for reading input from an input stream
	{
	Socket socket; // declare a socket
	String temp; // declare a temporary string
	BufferedReader bufferedReader; // declare a buffered reader
	boolean terminated = false; // declare a flag indicating whether the socket (that buffered reader is reading from has closed
	/*-----------------------------------------------------------------------------------------------------*/
	NetworkServerReadThread(Socket s) // constructor for the class
	{
	this.socket = s; // assign the socket to the socket given in the argument for the constructor
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // upon starting the thread...
	{
	try
		{
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // initialize buffered reader
		while(socket != null && (temp = bufferedReader.readLine()) != null) // while the socketClient has not logged out...
			{

			NetworkControl.displayMessage(temp);
			NetworkControl.sendMessageToClients(temp);
//			System.out.println(temp); // ... print whatever the socketClient is typing in
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
	public void closeSocket() throws IOException
		{
		terminated = true; // set terminated to true, the socketClient has logged out
		socket.close(); // close the socket
		bufferedReader.close(); // close the buffered reader
		}
	}

