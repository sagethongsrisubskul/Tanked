package tank;
import org.newdawn.slick.Color;
/* This class contains all the game settings */
public class Settings
	{
	/*-----------------------------------------------------------------------------------------------------*/
	/// Players:
	public static int playerType = C.UNDECIDED; /// Choices are undecided, server, and client
	public static int numberActivePlayers = 0; /// As players join game, this variable will change
	public static int playerID = 0; /// ID of the player
	public static String playerName[] = new String[C.MAX_PLAYERS]; /// As players join, they can log in with a name
	public static int maxCharactersName = 12;
	public static String sampleMaxName = "123456789012";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Map:
	public static int mapSelected = 0;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Sounds:
	public static int playButtonClick = C.YES;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Screen:
	public static int targetFrameRate = 60;
	public static int screenAdjustment = 100; /// When user presses key to resize screen, this is the increment
	/// Screen dimensions (Main):
	public static int mainScreenWidthStart = 1000;
	public static int mainScreenHeightStart = 750;
	public static int mainScreenWidth = mainScreenWidthStart;
	public static int mainScreenHeight = mainScreenHeightStart;
	public static int minMainScreenWidth = 700;
	public static int minMainScreenHeight = 600;
	public static int maxMainScreenWidth; /// Max screen dimensions are set by the container at launch
	public static int maxMainScreenHeight;
	/// Screen dimensions (Play):
	public static int playScreenWidth = 1000;
	public static int playScreenHeight = 750;
	public static int minPlayScreenWidth = 800;
	public static int minPlayScreenHeight = 600;
	public static int maxPlayScreenWidth;
	public static int maxPlayScreenHeight;
	/// Screen dimensions current:
	public static int currentScreenHeight = mainScreenHeight;
	public static int currentScreenWidth = mainScreenWidth;
	/*-----------------------------------------------------------------------------------------------------*/
	/// Player colors:
	public static Color allColors[] = {Color.red, Color.yellow, Color.green, Color.blue};
	public static int playerTeamColors[] = new int[C.MAX_PLAYERS];
	/*-----------------------------------------------------------------------------------------------------*/
	public static int winCondition = C.DEATHMATCH;

	/*-----------------------------------------------------------------------------------------------------*/
	public static void initSettings()
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			playerName[i] = "Player" + (i+1);
			playerTeamColors[i] = i;
			}
		}
	}