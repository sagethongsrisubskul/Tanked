package tank;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class NetworkServerWriteThread extends Thread
	{
	Scanner input; // declare a scanner
	boolean terminated = false; // declare a flag indicating whether the read thread has terminated
	/*-----------------------------------------------------------------------------------------------------*/
	public void run() // upon starting the thread...
	{
	try
		{
		while(true) // infinite loop
			{
			if(terminated == true) // check if the read thread has terminated
				break; // done
			}
		for(PrintWriter writer : NetworkServerMain.writers)
			{
			writer.close();
			}
		}
	catch (Exception e) // if there was a problem...
		{
		System.out.printf("ServerWriteThread Error:\n");
		e.printStackTrace();
		return;
		}
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void interrupt() // method to interrupt the thread
	{
//	System.out.println("Client has exited. Press <ENTER> to continue."); // print a message to the user indicating the client has logged out, they need to press enter to proceed past the scanner so the thread can close
	terminated = true; // set the flag indicating the read thread has ended
	}
	}
