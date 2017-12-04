package tank;
import java.util.concurrent.ThreadLocalRandom;

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
	public static boolean powerupflag=false;
	public static int powerx=0;//power ups x location
	public static int powery=0;//power ups y location
	public static int hours;
	public static int minutes;
	public static int seconds;
	public static int powerupindex=0;
	public static Powerups powerupentity;
	
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
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
			DisplaysStatePlay.renderDisplays(g);
			
			
			
		
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		Input input = container.getInput();
		Inputs.processKeyboardInput(input);
		input.clearKeyPressedRecord();
		updateTime(delta);
		Powerups.powerstatus();
		
		/*
		 * if(powerupentity.collides(tankentity)){
		 * 		Network.sendToAll("~PF");
		 * 		//handle tank power up
		 * 		if(powerupindex==0){
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
		
		/*if(Settings.playerType==C.SERVER) {
		/*	
			Still to do:
			1.) Check when tank collides with powerx and powery. If so then set power on tank. Remove power from map.
			***DONE***2.) Rescale images of power ups on screen
			3.) need to scale the random numbers for each bounds of map ??map coords??
			4.) Change time for spawn
		
			
		//handle powerup timers
			int xcoord=0;
			int ycoord=0;
			int index=0;
			if((seconds==5) && powerupflag==false) {
				//spawn power ups on map
				//make rand coordinates
				xcoord=ThreadLocalRandom.current().nextInt(0, 300 + 1);
				ycoord=ThreadLocalRandom.current().nextInt(0, 300 + 1);
				index=ThreadLocalRandom.current().nextInt(0,5+1);
				//send to clients
				NetworkControl.sendToAll("~PT"+xcoord+","+ycoord+","+index);
				//powerupflag=true;
			}
				
			if((seconds==10) && powerupflag == true) {
				//delete power up on screen if not picked up
				//send to clients
				NetworkControl.sendToAll("~PF");
				//powerupflag=false;
			}
			//end of power up timers
		}
	*/
		
		
		}
		
	}