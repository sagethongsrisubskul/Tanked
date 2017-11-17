package tank;
/* This class is to handle the networking of the game. */
public class Network
	{
	/* This method is called when a player clicks on button to host a game */
	public static void setupServer()
		{
		System.out.printf("setupServer\n");
		/// If server is successful:
		successServer();
		}
	/* This method is called when a player clicks on button to join a game and enters an ip address to connect to*/
	public static void setupClient(String ipAddress)
		{
		System.out.printf("setupClient: ipAddress = %s\n", ipAddress);
		/// If client is successful:
		successClient(ipAddress);
		}
	/* This method is called when a server clicks on button to leave game */
	public static void exitServer()
		{
		/// When a server leaves the game, all clients are booted from the game and return to the main screen
		int i;
		System.out.printf("exitServer\n");
		Settings.playerType = C.UNDECIDED;
		Settings.numberActivePlayers = 0;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			Settings.playerName[i] = Strings.defaultName + (i+1);
			}

		StateControl.enterState(StateControl.STATE_MAIN);

		/// When server exits, should we boot all the participants out or have the next player automatically host?

//		displayMessage("Exited host");
		}
	/* This method is called when a client clicks on button to leave game */
	public static void exitClient()
		{
		/// When a client leaves the game, that information needs to be sent to the server
		/// The server will then update the game settings and send that information out to each other clients in the game
		/// All the active participants will update their game settings to match the server
		System.out.printf("exitClient\n");
		Settings.numberActivePlayers--;
		Settings.playerType = C.UNDECIDED;
		displayMessage(Settings.playerName[Settings.playerID] + " has left the game");
		Settings.playerName[Settings.playerID] = Strings.defaultName + Settings.playerID;
		StateControl.enterState(StateControl.STATE_MAIN);
		}
	/* Call this method when the player is successful in hosting a game */
	public static void successServer()
		{
		Settings.playerType = C.SERVER;
		Settings.numberActivePlayers++;
		StateControl.enterState(StateControl.STATE_LOBBY);
		displayMessage(Settings.playerName[Settings.playerID] + " is hosting the game");
		}
	/* Call this method when the player is successful in joining a game */
	public static void successClient(String ipAddress)
		{
		/// When a client joins the game, that information needs to be sent to the server
		/// The server will then update the game settings and send that information out to each other clients in the game
		/// All the active participants will update their game settings to match the server
		Settings.playerType = C.CLIENT;
		Settings.playerID = Settings.numberActivePlayers;
		StateControl.enterState(StateControl.STATE_LOBBY);
		playerJoinedGame();
		}
	/* Call this method when someone joins the game */
	public static void playerJoinedGame()
		{
		Settings.numberActivePlayers++;
		displayMessage(Settings.playerName[Settings.playerID] + " has joined the game");
		}
	/* Call this method to display a message on the screen */
	public static void displayMessage(String string)
		{
		DisplaysStateLobby.displayMessage(string);
		}
	}
