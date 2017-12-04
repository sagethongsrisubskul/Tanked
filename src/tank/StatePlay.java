package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class StatePlay extends BasicGameState
	{
	Tank tank;
	PlayGame playGame = new PlayGame();
	public static int elapsedTime;
	public static int hours;
	public static int minutes;
	public static int seconds;
	public static tankentity tank1;
//	public static boolean powerupFlag=false;
//	public static int powerx=0;//power ups x location
//	public static int powery=0;//power ups y location
//	public static int powerupIndex=0;
	public static Powerups powerupEntity;
//	public static int powerupElapsedTime = 0;
	
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public int getID()
		{
		return StateControl.STATE_PLAY;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
		DisplaysStatePlay.initDisplays();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
		AppGameContainer gc = (AppGameContainer) container;
		gc.setDisplayMode(Settings.playScreenWidth, Settings.playScreenHeight, false);
		StateControl.addCurrentState(getID());
		Settings.currentScreenWidth = Settings.playScreenWidth;
		Settings.currentScreenHeight = Settings.playScreenHeight;
		DisplaysStatePlay.positionDisplays();
		GameStats.initGameStats();
		elapsedTime = hours = minutes = seconds = 0;
		tank1=new tankentity(200,200,'r');
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		DisplaysStatePlay.renderDisplays(g);
		tank1.render(g);
		tank1.getTurret().render(g);

		if(powerupEntity.powerupFlag ==true) {
		//render power up at location
		//g.drawImage(ResourceManager.getImage(Filenames.powerupIcons[powerupIndex]).getScaledCopy(.35f), powerx, powery);
		powerupEntity.render(g);
		//powerupEntity.
		}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		Input input = container.getInput();
		Inputs.processKeyboardInput(input);
		input.clearKeyPressedRecord();
		
		Inputs.xMouse=input.getMouseX();
		Inputs.yMouse=input.getMouseY();
		
		updateTime(delta);
		tank1.control(Inputs.movement, Inputs.rotation);
		tank1.aimTurret(Inputs.xMouse, Inputs.yMouse);
		tank1.update(delta);
		Powerups.sendPowerupStatus();
		
		/*
		 * if(powerupEntity.collides(tankentity)){
		 * 		Network.sendToAll("~PF");
		 * 		//handle tank power up
		 * 		if(powerupIndex==0){
		 * 			//method for tank power up??
		 * 		}
		 * 
		 * }
		 */
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void mouseClicked(int button, int x, int y, int numClicked)
		{
		Inputs.xMouse = x;
		Inputs.yMouse = y;
		Inputs.processMouseInput();
		Inputs.xMouse = -1;
		Inputs.yMouse = -1;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public void updateTime(int delta)
		{
		elapsedTime += delta;
		if(elapsedTime >= 1000)
			{
			seconds++;
			processPowerupTime();
			elapsedTime -= 1000;
			}
		if(seconds == 60)
			{
			minutes++;
			seconds = 0;
			}
		if(minutes == 60)
			{
			hours++;
			minutes = 0;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	/* This will method will be called every second and will decrement any activated timed powerups by one second */
	public void processPowerupTime()
		{
		int i, j;
		Powerups.powerupElapsedTime++;
		for(i = 0; i < C.MAX_PLAYERS; i++) /// Cycle through all players
			{
			for(j = 0; j < Strings.powerups.length; j++) /// Cycle through all powerups
				{
				if(Powerups.timePowerup[i][j] > 0)
					{
					if(Powerups.timePowerup[i][j] == 1) /// If the powerup is about to expire
						Powerups.powerupDeactivation(i, j); /// Deactivate powerup
					Powerups.timePowerup[i][j]--;
					}
				}
			}
		}
	}