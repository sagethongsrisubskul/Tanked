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

	/* This method is called when a player clicks on button to join a game */
	public static void setupClient(String ipAddress)
		{
		System.out.printf("setupClient: ipAddress = %s\n", ipAddress);

		/// If client is successful:
		successClient(ipAddress);
		}

	/* This method is called when a server clicks on button to exit host game */
	public static void exitServer()
		{
		System.out.printf("exitServer\n");
		displayMessage("Exited host");
		}

	/* This method is called when a client clicks on button to exit join game */
	public static void exitClient()
		{
		System.out.printf("exitClient\n");
		displayMessage("Exited game");
		}

	/* Call this method when the player is successful in hosting a game */
	public static void successServer()
		{
		Settings.playerType = C.SERVER;
		displayMessage("Success hosting game");
		}

	/* Call this method when the player is successful in joining a game */
	public static void successClient(String ipAddress)
		{
		Settings.playerType = C.CLIENT;
		displayMessage("Success joining game (ipAddress = '" + ipAddress + "')");
		}

	/* Call this method when player is hosting a game and a player joins */
	public static void playerJoinedGame()
		{
		Settings.numberActivePlayers++;
		displayMessage("Number of active players = " + Integer.toString(Settings.numberActivePlayers));
		}

	/* Call this method to display a message on the screen */
	public static void displayMessage(String string)
		{
		DisplaysStateMain.displayMessage(string);
		}

	/* This method is called when sending a message */
	public static void messageSend(String string)
		{
		DisplaysStateMain.displayMessage(string);
		}
	}
