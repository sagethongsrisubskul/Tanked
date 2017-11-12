package tank;
/* This class is to handle the networking of the game */
public class Network
	{

	/* This method is called when a player clicks on button to host a game */
	public static void isServer()
		{
		System.out.printf("isServer\n");
		}

	/* This method is called when a player clicks on button to join a game */
	public static void isClient(String ipAddress)
		{
		System.out.printf("isClient: ipAddress = %s\n", ipAddress);
		}

	/* This method is called when a server clicks on button to exit host game */
	public static void exitServer()
		{
		System.out.printf("exitServer\n");
		}

	/* This method is called when a client clicks on button to exit join game */
	public static void exitClient()
		{
		System.out.printf("exitClient\n");
		}
	}
