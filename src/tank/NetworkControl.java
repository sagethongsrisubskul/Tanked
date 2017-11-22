package tank;
import java.io.IOException;
import java.io.PrintWriter;
/* This class is to handle the networking of the game. */
public class NetworkControl
	{
	public static NetworkServerMain sm = new NetworkServerMain();
	public static NetworkClientMain cm;
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player clicks on button to host a game */
	public static void setupServer()
		{
		System.out.printf("setupServer\n");
		sm.start();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player clicks on button to join a game and enters an ip address to connect to*/
	public static void setupClient(String ipAddress)
		{
		System.out.printf("setupClient: ipAddress = %socket\n", ipAddress);
		cm = new NetworkClientMain(ipAddress);
		cm.start();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a socket clicks on button to leave game */
	public static void exitServer()
		{
		/// When a socket leaves the game, all clients are booted from the game and return to the main screen
		int i;
		System.out.printf("exitServer\n");
		Settings.playerType = C.UNDECIDED;
		Settings.numberActivePlayers = 0;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			Settings.playerName[i] = Strings.defaultName + (i + 1);
			}
		StateControl.enterState(StateControl.STATE_MAIN);
		/// When socket exits, should we boot all the participants out or have the next player automatically host?
//		displayMessage("Exited host");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a socketClient clicks on button to leave game */
	public static void exitClient()
		{
		/// When a socketClient leaves the game, that information needs to be sent to the socket
		/// The socket will then update the game settings and send that information out to each other clients in the game
		/// All the active participants will update their game settings to match the socket
		System.out.printf("exitClient\n");
		Settings.numberActivePlayers--;
		Settings.playerType = C.UNDECIDED;
		try
			{
			sm.clientThread.rt.closeSocket();
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		displayMessage(Settings.playerName[Settings.playerID] + " has left the game");
		Settings.playerName[Settings.playerID] = Strings.defaultName + Settings.playerID;
		StateControl.enterState(StateControl.STATE_MAIN);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method when the player is successful in hosting a game */
	public static void successServer()
		{
		Settings.playerType = C.SERVER;
		Settings.numberActivePlayers++;
		StateControl.enterState(StateControl.STATE_LOBBY);
		displayMessage(Settings.playerName[Settings.playerID] + " is hosting the game");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method when the player is successful in joining a game */
	public static void successClient(String ipAddress)
		{
		/// When a socketClient joins the game, that information needs to be sent to the socket
		/// The socket will then update the game settings and send that information out to each other clients in the game
		/// All the active participants will update their game settings to match the socket
		Settings.playerType = C.CLIENT;
		Settings.playerID = Settings.numberActivePlayers;
		StateControl.enterState(StateControl.STATE_LOBBY);
		playerJoinedGame();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method when someone joins the game */
	public static void playerJoinedGame()
		{
		Settings.numberActivePlayers++;
		displayMessage(Settings.playerName[Settings.playerID] + " has joined the game");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method to display a message on the screen */
	public static void displayMessage(String string)
		{
		DisplaysStateLobby.displayMessage(string);
		}

	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when user presses enter to display a chat message */
	public static void sendMessage(String string)
		{
		if(Settings.playerType == C.SERVER)
			{
			/// If the user is the server, it will display the message on his screen and then send to message to all the clients
			displayMessage(string);
			sendMessageToClients(string);
			}
		else if(Settings.playerType == C.CLIENT)
			{
			/// If the user is a client, it will send to the socket first
			sendMessageToServer(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to send chat messages to all the clients */
	public static void sendMessageToClients(String string)
		{
		/// Sends message to all the clients. The clients need to have a thread that listens for incoming messages.
		/// When a client receives an incoming message, it will call displayMessage(String string) to display it
		for(PrintWriter writer : NetworkServerMain.writers)
			{
			writer.println(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to send chat messages to the server */
	public static void sendMessageToServer(String string)
		{
		/// Sends a message to the server. The server needs to have a thread that listens for incoming messages.
		/// When a sever receives an incoming message, it will display the message on his screen by calling
		// displayMessage(String string) and then will send the message to all clients
		sendMessageToClients(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
