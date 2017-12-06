package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import jig.Vector;
public class StatePlay extends BasicGameState
	{
	Tank tank;
	PlayGame playGame = new PlayGame();
	public static int elapsedTime;
	public static int hours;
	public static int minutes;
	public static int seconds;
	public static tankentity tanks[]=new tankentity[4];
//	public static boolean powerupFlag=false;
//	public static int powerx=0;//power ups x location
//	public static int powery=0;//power ups y location
//	public static int powerupIndex=0;
	public static Powerups powerupEntity;
//	public static int powerupElapsedTime = 0;
	public int x=0;
	public int y=0;
	public int timer=0;
	
	
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
		System.out.println(Settings.numberActivePlayers);
		for(int i=0;i<Settings.numberActivePlayers;i++) {
			if(Settings.playerTeamColors[i]==C.RED) {
				tanks[i]=new tankentity(200,200,'r');
			}
			else if(Settings.playerTeamColors[i]==C.BLUE) {
				tanks[i]=new tankentity(200,200,'b');
			}
			else if(Settings.playerTeamColors[i]==C.GREEN) {
				tanks[i]=new tankentity(200,200,'g');
			}
			else if(Settings.playerTeamColors[i]==C.YELLOW) {
				tanks[i]=new tankentity(200,200,'y');
			}
		}
		
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		DisplaysStatePlay.renderDisplays(g);
		for(int i=0;i<Settings.numberActivePlayers;i++) {
			tanks[i].render(g);
			tanks[i].getTurret().render(g);
		}

		if(Powerups.powerupFlag ==true) {
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
		
		//Inputs.xMouse[Settings.playerID]=input.getMouseX();
		//Inputs.yMouse[Settings.playerID]=input.getMouseY();
		if(timer>=10) {
			NetworkControl.sendToAll("~PX"+Settings.playerID+input.getMouseX());
			NetworkControl.sendToAll("~PY"+Settings.playerID+input.getMouseY());
			x=(int) tanks[Settings.playerID].getX();
			y=(int) tanks[Settings.playerID].getY();
			NetworkControl.sendToAll("~PV"+Settings.playerID+x);
			NetworkControl.sendToAll("~PB"+Settings.playerID+y);
			timer=0;
		}
		
		timer+=delta;
		updateTime(delta);
		
		for(int i=0;i<Settings.numberActivePlayers;i++) {
			Inputs.vectors[i]=new Vector(Inputs.xpos[i],Inputs.ypos[i]);
		}
		//System.out.println(Settings.playerID);
		for(int i=0;i<Settings.numberActivePlayers;i++) {
			if(i==Settings.playerID) {
				tanks[i].control(Inputs.movement[i], Inputs.rotation[i]);
				tanks[i].aimTurret(Inputs.xMouse[i], Inputs.yMouse[i]);
				tanks[i].update(delta,i);
			}
			
			if(i!=Settings.playerID) {
				tanks[i].control(Inputs.movement[i],Inputs.rotation[i]);
				tanks[i].setRotation(Inputs.hullangle[i]);
				tanks[i].aimTurret(Inputs.xMouse[i], Inputs.yMouse[i]);
				tanks[i].setPosition(Inputs.vectors[i]);
				tanks[i].update(delta,i);
				
			}
		}
		Powerups.sendPowerupStatus();
		Powerups.checkPowerupCollision();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void mouseClicked(int button, int x, int y, int numClicked)
		{
		Inputs.localxMouse = x;
		Inputs.localyMouse = y;
		Inputs.processMouseInput();
		Inputs.localxMouse = -1;
		Inputs.localyMouse = -1;
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