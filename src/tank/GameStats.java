package tank;
/* This class is for holding the game stats information */
public class GameStats
	{
	/// To add a powerup:
	/// add the icon image to the res folder (100x100),
	/// add the filename of the image in Filenames class,
	/// load the resource in Tank class,
	/// add the type in GameStats.powerType array,
	/// add string to Strings.powerupIcons array
	/*-----------------------------------------------------------------------------------------------------*/
	public static int maxHealthBase = 1000;
	public static int maxPower = 10;
	public static int maxSpeed = 10;
	public static int armorBase = 10;
	public static int powerBase = 5;
	public static int speedBase = 5;
	public static int scoreBase = 0;
	public static int levelBase = 1;
	public static int maxHealth[] = new int[C.MAX_PLAYERS];
	public static int health[] = new int[C.MAX_PLAYERS];
	public static int armor[] = new int[C.MAX_PLAYERS];
	public static int power[] = new int[C.MAX_PLAYERS];
	public static int speed[] = new int[C.MAX_PLAYERS];
	public static int score[] = new int[C.MAX_PLAYERS];
	public static int level[] = new int[C.MAX_PLAYERS];
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initGameStats()
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			maxHealth[i] = maxHealthBase;
			health[i] = maxHealthBase;
			armor[i] = armorBase;
			power[i] = powerBase;
			speed[i] = speedBase;
			score[i] = scoreBase;
			level[i] = levelBase;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
