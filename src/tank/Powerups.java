package tank;
import jig.Entity;

import java.util.concurrent.ThreadLocalRandom;
/// Powerups:
/// To change, add, or remove powerups, change the 3 arrays:
/// 1) Filenames.powerupIcons
/// 2) Strings.powerups
/// 3) Powerups.powerupType
/// Adjust static final variables in C class
/// Finally change the inputs in Inputs.processKeyboardInput for StatePlay
public class Powerups extends Entity
	{
	/// Powerup display:
	public static boolean powerupFlag = false; /// Controls the presence of the powerup on the screen
	public static float iconScale = .2f;
	public static int powerx = 0; // powerups x location
	public static int powery = 0; // powerups y location
	/// Powerup life cycle:
	public static int powerupElapsedTime = 0; /// The time in seconds the powerup is on its cycle
	public static int powerupInterval = 10; /// The interval in seconds a powerup will appear after the previous disappeared
	public static int powerupDuration = 10; /// The seconds the powerup will remain on the screen
	/// Powerup coord
	public static int powerupIndex = 0; /// The index of the powerup image in Filenames
	/// Powerup statuses:
//	public static int powerupType[] = {C.CONSUMABLE, C.CONSUMABLE, C.TIMED, C.TIMED, C.TIMED, C.TIMED}; /// Every powerup has a type (CONSUMABLE, TIMED, OR PERMANENT)
	public static int powerupType[] = {C.CONSUMABLE, C.CONSUMABLE, C.TIMED, C.TIMED, C.TIMED}; /// Every powerup has a type (CONSUMABLE, TIMED, OR PERMANENT)
	public static int numPowerups[][] = new int[C.MAX_PLAYERS][Strings.powerups.length]; /// The amount of collectible powerupIcons the player has
	public static int timePowerup[][] = new int[C.MAX_PLAYERS][Strings.powerups.length]; /// How many seconds are left till timed powerup is unactive
	public static int numActivatedPowerups[][] = new int[C.MAX_PLAYERS][Strings.powerups.length]; /// The number of activated powerups at any given time
	/// Powerup values:
	public static int healthIncrease = (int) (GameStats.maxHealthBase * 0.10); /// How much health is restored when activating the health powerup
	public static int mineDamage = (int) (GameStats.maxHealthBase * 0.10); /// How much damage colliding with a mine will do
	public static int speedBurst = 3; /// How much additional speed when activating the speed powerup
	public static int powerBurst = 3; /// How much additional power when activating the power powerup
	public static int invincibleActivated[] = new int[C.MAX_PLAYERS];
	public static int speedBurstTime = 30;
	public static int powerBurstTime = 30;
	public static int invincibleBurstTime = 30;
	/*-----------------------------------------------------------------------------------------------------*/
	public Powerups(final float x, final float y)
		{
		super(x, y);
		ResourceManager.loadImage(Filenames.powerupIcons[powerupIndex]);
		addImageWithBoundingBox(ResourceManager.getImage(Filenames.powerupIcons[powerupIndex]).getScaledCopy(iconScale));
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Sends powerup display command if user is the server and the powerup display cycle has begun */
	public static void sendPowerupStatus()
		{
		if(Settings.playerType == C.SERVER)
			{
			if((powerupElapsedTime == powerupInterval) && powerupFlag == false)
				{
				int xcoord = ThreadLocalRandom.current().nextInt(0, 300 + 1);
				int ycoord = ThreadLocalRandom.current().nextInt(0, 300 + 1);
				int index = ThreadLocalRandom.current().nextInt(0, Filenames.powerupIcons.length);
				NetworkControl.sendToAll("~PT" + xcoord + "," + ycoord + "," + index);
				}
			if((powerupElapsedTime == powerupInterval + powerupDuration) && powerupFlag == true)
				{
				NetworkControl.sendToAll("~PF");
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Checks for collision with the displayed powerup */
	public static void checkPowerupCollision()
		{
			for(int i=0;i<Settings.numberActivePlayers;i++) {
				if(powerupFlag == true && StatePlay.powerupEntity.collides(StatePlay.tanks[i]) != null)
					{
					tank.ResourceManager.getSound(Filenames.ding).play();
					NetworkControl.sendToAll("~PC" + Settings.playerID + powerupIndex);
					NetworkControl.sendToAll("~PF");
					}	
				}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called for every user to process someone colliding with a powerup */
	public static void powerupCollision(int playerID, int powerupIndex)
		{
		numPowerups[playerID][powerupIndex]++;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* When a player activates a powerup, that player should call this method */
	public static void sendPowerupActivation(int powerupIndex)
		{
		if(numPowerups[Settings.playerID][powerupIndex] > 0) /// Only activates if player has one in their inventory
			NetworkControl.sendToAll("~PA" + Settings.playerID + powerupIndex);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method is called for every user to process someone activating a powerup */
	public static void powerupActivation(int playerID, int powerupIndex)
		{
		if(powerupIndex == 0) /// Health
			{
			/// Add health but not to exceed max health:
			GameStats.health[playerID] = (GameStats.health[playerID] + healthIncrease) > GameStats.maxHealth[playerID] ? GameStats.maxHealth[playerID] : (GameStats.health[playerID] + healthIncrease);
			}
		else if(powerupIndex == 1) /// Mine
			{
			//TODO render mine on the map
			}
		else if(powerupIndex == 2) /// Speed
			{
			/// Increase speed but not to exceed max speed:
			GameStats.speed[playerID] = (GameStats.speed[playerID] + speedBurst) > GameStats.maxSpeed ? GameStats.maxSpeed : (GameStats.speed[playerID] + speedBurst);
			timePowerup[playerID][powerupIndex] += speedBurstTime;
			numActivatedPowerups [playerID][powerupIndex]++;
			}
		else if(powerupIndex == 3) /// Power
			{
			/// Increase power but not to exceed max power:
			GameStats.power[playerID] = (GameStats.power[playerID] + powerBurst) > GameStats.maxPower ? GameStats.maxPower : (GameStats.power[playerID] + powerBurst);
			timePowerup[playerID][powerupIndex] += powerBurstTime;
			numActivatedPowerups [playerID][powerupIndex]++;
			}
		else if(powerupIndex == 4) /// Invincible
			{
			invincibleActivated[playerID] = C.YES;
			timePowerup[playerID][powerupIndex] += invincibleBurstTime;
			numActivatedPowerups [playerID][powerupIndex]++;
			}
		numPowerups[playerID][powerupIndex]--; /// Remove one from inventory
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method will be called for every user once an activated powerup's timer hits zero */
	public static void powerupDeactivation(int playerID, int powerupIndex)
		{
		int n;
		if(powerupIndex == C.POWERUP_SPEED)
			{
			/// Decrement powerup by the number of activated powerups but not less than the base
			GameStats.speed[playerID] -= numActivatedPowerups [playerID][powerupIndex] * speedBurst;
			GameStats.speed[playerID] = GameStats.speed[playerID] < GameStats.speedBase ? GameStats.speedBase : GameStats.speed[playerID];
			}
		else if(powerupIndex == C.POWERUP_POWER)
			{
			/// Decrement powerup by the number of activated powerups but not less than the base
			GameStats.power[playerID] -= numActivatedPowerups [playerID][powerupIndex] * powerBurst;
			GameStats.power[playerID] = GameStats.power[playerID] < GameStats.powerBase ? GameStats.powerBase : GameStats.power[playerID];
			}
		else if(powerupIndex == C.POWERUP_INVINCIBLE)
			{
			invincibleActivated[playerID] = C.NO;
			}
		numActivatedPowerups [playerID][powerupIndex] = 0;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This method should be called for every user when someone collides with an enemy mine */
	public static void mineCollision(int playerID)
		{
		/// Decrease health but not to exceed zero:
		GameStats.health[playerID] = (GameStats.health[playerID] - mineDamage) < 0 ? 0 : (GameStats.health[playerID] - mineDamage);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Process the powerup command from the network. Used to display of the powerup and its status */
	public static void powerupTrueCommand(String string)
		{
		String xcoord = "";
		String ycoord = "";
		int index = 0;
		int stringsplit = 0;

		for(int i = 0; i < string.length(); i++)
			{//grab x coord from string
			if(string.charAt(i) != ',')
				{
				xcoord = xcoord + string.charAt(i);
				}
			else
				{
				stringsplit = i + 1;
				break;
				}
			}
		for(int i = stringsplit; i < string.length(); i++)
			{//grab y coord from string
			if(string.charAt(i) != ',')
				{
				ycoord = ycoord + string.charAt(i);
				}
			else
				{
				stringsplit = i + 1;
				break;
				}
			}
		index = Character.getNumericValue(string.charAt(stringsplit));
		//System.out.println("X:" +xcoord + " Y:" + ycoord + " Index:" + index );
		powerx = Integer.parseInt(xcoord);//get int from string
		powery = Integer.parseInt(ycoord);//get int from string
		powerupIndex = index;
		StatePlay.powerupEntity = new Powerups(Powerups.powerx, Powerups.powery);
		powerupFlag = true;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* Process the powerup command from the network. Used to erase the display of the powerup and its status */
	public static void powerupFalseCommand()
		{
		powerupFlag = false;
		powerupElapsedTime = 0;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
