package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import jig.Vector;

import static tank.DisplaysStatePlay.camera;

public class StatePlay extends BasicGameState
	{
	Tank tank;
	PlayGame playGame = new PlayGame();
	static int i;
	public static int elapsedTime;
	public static int hours;
	public static int minutes;
	public static int seconds;
	public static int highScoreTimer; /// Seconds
	public static int highScoreTimerOptions[] = {1, 5, 10, 15, 20, 30}; /// Minutes
	public static tankentity tanks[] = new tankentity[4];
	//	public static boolean powerupFlag=false;
//	public static int powerx=0;//power ups x location
//	public static int powery=0;//power ups y location
//	public static int powerupIndex=0;
	public static Powerups powerupEntity;
	//	public static int powerupElapsedTime = 0;
	public int x = 0;
	public int y = 0;
	public int timer = 0;
	public static int gamePaused = C.NO;

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
		int i;
		AppGameContainer gc = (AppGameContainer) container;
		gc.setDisplayMode(Settings.playScreenWidth, Settings.playScreenHeight, false);
		StateControl.addCurrentState(getID());
		Settings.currentScreenWidth = Settings.playScreenWidth;
		Settings.currentScreenHeight = Settings.playScreenHeight;
		DisplaysStatePlay.positionDisplays();
		camera = new Camera(Filenames.maps[Settings.mapSelected], 10, 125);
		GameStats.initGameStats();
		elapsedTime = hours = minutes = seconds = 0;
		for(i = 0; i < Settings.numberActivePlayers; i++)
			{
			if(Settings.playerTeamColors[i] == C.RED)
				{
				tanks[i] = new tankentity(200, 200, 'r');
				}
			else if(Settings.playerTeamColors[i] == C.BLUE)
				{
				tanks[i] = new tankentity(200, 200, 'b');
				}
			else if(Settings.playerTeamColors[i] == C.GREEN)
				{
				tanks[i] = new tankentity(200, 200, 'g');
				}
			else if(Settings.playerTeamColors[i] == C.YELLOW)
				{
				tanks[i] = new tankentity(200, 200, 'y');
				}
			}
		GameStats.recordNumberTeams();
		highScoreTimer = 60 * highScoreTimerOptions[Settings.highScoreTimerIndex];
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		DisplaysStatePlay.renderDisplays(g);

		if(GameStats.gameOver == C.YES)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.colors[GameStats.winningTeam] + Strings.wins, C.CENTER, C.CENTER, 10, Fonts.fontCourier20BTTF, Color.black, Color.white);
			}
		else if(StatePlay.gamePaused == C.YES)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.gamePaused, C.CENTER, DisplaysStatePlay.pausePopupY, 10, Fonts.fontCourier15BTTF, Color.black, Color.white);
			DisplaysStatePlay.messageArea.colorSection(g, DisplaysStatePlay.messageBackgroundColor);
			for(i = 0; i < Strings.networkMessages.length; i++)
				{
				DisplaysStatePlay.messageTextFont.drawString(DisplaysStatePlay.messageArea.x + DisplaysStatePlay.messageAreaPadding, DisplaysStatePlay.messageArea.y + DisplaysStatePlay.messageAreaPadding + (i * 10), Strings.networkMessages[i], DisplaysStatePlay.messageTextColor);
				}
			if(DisplaysPopupBox.popupDisplayed == C.YES)
				{
				DisplaysPopupBox.renderPopup(g);
				}
			}
		else if(GameStats.health[Settings.playerID] <= 0)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.gameOver, C.CENTER, C.CENTER, 10, Fonts.fontCourier20BTTF, Color.black, Color.white);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		Input input = container.getInput();
		Inputs.processKeyboardInput(input);
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			if(DisplaysPopupBox.charactersEntered < DisplaysPopupBox.maxCharacters)
				{
				DisplaysPopupBox.getPopupInput(input);
				}
			}
		input.clearKeyPressedRecord();
		//Inputs.xMouse[Settings.playerID]=input.getMouseX();
		//Inputs.yMouse[Settings.playerID]=input.getMouseY();
		if(GameStats.gameOver == C.NO && GameStats.health[Settings.playerID] > 0)
			{
			if(timer >= 10)
				{
				NetworkControl.sendToAll("~PX" + Settings.playerID + (input.getMouseX() - camera.xPos - camera.pixelOffsetX));
				NetworkControl.sendToAll("~PY" + Settings.playerID + (input.getMouseY() - camera.yPos - camera.pixelOffsetY));
				x = (int) tanks[Settings.playerID].getX();
				y = (int) tanks[Settings.playerID].getY();
				NetworkControl.sendToAll("~PV" + Settings.playerID + x);
				NetworkControl.sendToAll("~PB" + Settings.playerID + y);
				timer = 0;
				}
			timer += delta;
			if(gamePaused == C.NO) updateTime(delta);
			for(int i = 0; i < Settings.numberActivePlayers; i++)
				{
				Inputs.vectors[i] = new Vector(Inputs.xpos[i], Inputs.ypos[i]);
				}
			//System.out.println(Settings.playerID);
			for(int i = 0; i < Settings.numberActivePlayers; i++)
				{
				if(i == Settings.playerID)
					{
					tanks[i].control(Inputs.movement[i], Inputs.rotation[i]);
					tanks[i].aimTurret(Inputs.xMouse[i], Inputs.yMouse[i]);
					tanks[i].update(delta, i);
					}
				if(i != Settings.playerID)
					{
					tanks[i].control(Inputs.movement[i], Inputs.rotation[i]);
					tanks[i].setRotation(Inputs.hullangle[i]);
					tanks[i].aimTurret(Inputs.xMouse[i], Inputs.yMouse[i]);
					tanks[i].setPosition(Inputs.vectors[i]);
					tanks[i].update(delta, i);
					}
				}
			Powerups.sendPowerupStatus();
			Powerups.checkPowerupCollision();
			}
		camera.update(tanks[Settings.playerID], delta);
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
			if(Settings.winCondition == C.HIGH_SCORE)
				{
				highScoreTimer--;
				if(highScoreTimer == 0) GameStats.checkWinCondition();
				}
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
					Powerups.timePowerup[i][j]--; /// Decrement one second off the powerup's time
					}
				}
			}
		}
	}