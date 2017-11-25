package tank;
import java.io.IOException;
import java.io.PrintWriter;
/* This class is to handle the networking of the game. */
public class NetworkControl
	{
	public static NetworkServerMain serverMain = new NetworkServerMain();
	public static NetworkClientMain clientMain;
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player clicks on button to host a game */
	public static void setupServer()
		{
		System.out.printf("setupServer\n");
		serverMain.start();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a player clicks on button to join a game and enters an ip address to connect to*/
	public static void setupClient(String ipAddress)
		{
		System.out.printf("setupClient: ipAddress = %s\n", ipAddress);
		clientMain = new NetworkClientMain(ipAddress);
		clientMain.start();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a server clicks on button to leave game or closes application */
	public static void exitServer()
		{
		System.out.printf("exitServer\n");
		/// When server exits:
		// Close all sockets
		// Both server and clients settings reset
		// Both server and clients return to main screen

//		int i;
//		Settings.playerType = C.UNDECIDED;
//		Settings.numberActivePlayers = 0;
//		for(i = 0; i < C.MAX_PLAYERS; i++)
//			{
//			Settings.playerName[i] = Strings.defaultName + (i + 1);
//			}
//		StateControl.enterState(StateControl.STATE_MAIN);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called when a client clicks on button to leave game or closes application */
	public static void exitClient()
		{
		System.out.printf("exitClient\n");
		/// When a client exits:
		// Socket closed to client
		// Client's settings reset
		// Client returns to main screen
		// All other active players should reflect that the client has left the game

//		Settings.numberActivePlayers--;
//		Settings.playerType = C.UNDECIDED;
//		try
//			{
//			serverMain.clientThread.readThread.closeSocket();
//			}
//		catch (IOException e)
//			{
//			e.printStackTrace();
//			}
//		displayMessage(Settings.playerName[Settings.playerID] + " has left the game");
//		Settings.playerName[Settings.playerID] = Strings.defaultName + Settings.playerID;
//		StateControl.enterState(StateControl.STATE_MAIN);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method when the player is successful in hosting a game */
	public static void successServer()
		{
		System.out.printf("successServer\n");
		Settings.playerType = C.SERVER;
		Settings.numberActivePlayers++;
		Settings.playerID = 0;
		StateControl.enterState(StateControl.STATE_LOBBY);
		displayMessage(Settings.playerName[Settings.playerID] + " is hosting the game");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* The server will call this method when a client successful joins the game */
	public static void successClient()
		{
		System.out.printf("successClient\n");
		Commands.setPlayerJoinsForAll();
		Commands.setNamesForAll();
		Commands.setWinConditionsForAll();
		Commands.setMapSelectionForAll();
		Commands.setPlayerColorsForAll();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to display a chat message on the screen */
	public static void displayMessage(String string)
		{
		DisplaysStateLobby.displayMessage(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Call this method to send a command or a chat message to everyone */
	public static void sendToAll(String string)
		{
		System.out.printf("sendToAll: %s\n", string);
		if(Settings.playerType == C.SERVER)
			{
			/// A server will process the command on his side and then send the command to all clients
			Commands.processCommand(string);
			sendToClients(string);
			}
		else
			{
			/// A client will simply send the command to the server, who will in turn process the command and send it to all clients
			sendToServer(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to send commands and messages to all the clients */
	public static void sendToClients(String string)
		{
		System.out.printf("sendToClients: %s\n", string);
		for(PrintWriter writer : NetworkServerMain.writers)
			{
			writer.println(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called to send commands and messages to the server */
	public static void sendToServer(String string)
		{
		System.out.printf("sendToServer: %s\n", string);
		NetworkClientMain.printWriter.println(string);
		}
	}
