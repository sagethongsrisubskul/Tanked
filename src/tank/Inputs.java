package tank;
import org.newdawn.slick.Input;

import jig.Vector;

import static tank.StatePlay.tanks;

/* This class is for handling any keyboard or mouse click inputs */
public class Inputs
	{
	static int i;
	public static int localxMouse;
	public static int localyMouse;
	public static int localmovement;
	public static int localrotation;
	public static int xMouse[] = new int[4];
	public static int yMouse[] = new int[4];
	public static int movement[] = new int[4];
	public static int rotation[] = new int[4];
	public static int xpos[] = new int[4];
	public static int ypos[] = new int[4];
	public static int hullangle[] = new int[4];
	public static Vector vectors[] = new Vector[4];
	public static float pitchEngineBase = .5f;
	public static float pitchEngineFactor = .05f;
	public static float volumeEngine = .7f;
	public static float volumePowerupCollision = .5f;
	public static float volumeFire = 1f;
	public static float volumeMineDetonation = 1.5f;
	public static float volumeExplosion = 1f;
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processKeyboardInput(Input input)
		{
//		processScreenAdjustment(input);
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			if(input.isKeyDown(Input.KEY_ESCAPE))
				{
				DisplaysPopupBox.popupEnd();
				}
			else if(input.isKeyDown(Input.KEY_DELETE))
				{
				DisplaysPopupBox.clearEntered();
				}
			else if(input.isKeyDown(Input.KEY_BACK))
				{
				DisplaysPopupBox.clearLastCharacter();
				}
			else if(input.isKeyDown(Input.KEY_ENTER))
				{
				DisplaysPopupBox.finalizeMessage();
				}
			}
		else if(StateControl.currentState == StateControl.STATE_LOBBY)
			{
			if(input.isKeyDown((Input.KEY_ENTER)))
				{
				DisplaysPopupBox.initPopup(C.POPUP_CHAT);
				}
			}
		else if(StateControl.currentState == StateControl.STATE_PLAY)
			{
			if(GameStats.gameOver == C.NO && GameStats.health[Settings.playerID] > 0)
				{
				if(input.isKeyPressed(Input.KEY_F12))
					Tank.DEBUG = !Tank.DEBUG;
				if(input.isKeyPressed(Input.KEY_SPACE))
					NetworkControl.sendToAll("~GP");
				if(StatePlay.gamePaused == C.NO) /// Only allows input if game is not paused
					{
					/// Tank movement
					for(int i = 0; i < Settings.numberActivePlayers; i++)
						{
						movement[i] = 0;
						rotation[i] = 0;
						}
					if(input.isKeyDown(Input.KEY_W))
						{
						movement[Settings.playerID]++;
						NetworkControl.sendToAll("~PM" + Settings.playerID + movement[Settings.playerID]);
						if (!ResourceManager.getSound(Filenames.engine).playing())
							ResourceManager.getSound(Filenames.engine).play(pitchEngineBase + GameStats.speed[Settings.playerID] * pitchEngineFactor, volumeEngine);
						}
					if(input.isKeyDown(Input.KEY_S))
						{
						movement[Settings.playerID]--;
						NetworkControl.sendToAll("~PM" + Settings.playerID + movement[Settings.playerID]);
						if(!ResourceManager.getSound(Filenames.engine).playing())
							ResourceManager.getSound(Filenames.engine).play(pitchEngineBase + GameStats.speed[Settings.playerID] * pitchEngineFactor, volumeEngine);
						}
					if(input.isKeyDown(Input.KEY_A))
						{
						rotation[Settings.playerID]--;
						NetworkControl.sendToAll("~PR" + Settings.playerID + rotation[Settings.playerID]);
						if(!ResourceManager.getSound(Filenames.engine).playing())
							ResourceManager.getSound(Filenames.engine).play(pitchEngineBase + GameStats.speed[Settings.playerID] * pitchEngineFactor, volumeEngine);
						}
					if(input.isKeyDown(Input.KEY_D))
						{
						rotation[Settings.playerID]++;
						NetworkControl.sendToAll("~PR" + Settings.playerID + rotation[Settings.playerID]);
						if(!ResourceManager.getSound(Filenames.engine).playing())
							ResourceManager.getSound(Filenames.engine).play(pitchEngineBase + GameStats.speed[Settings.playerID] * pitchEngineFactor, volumeEngine);
						}
					if(!input.isKeyDown(Input.KEY_W) && !input.isKeyDown(Input.KEY_S) && !input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D))
						ResourceManager.getSound(Filenames.engine).stop();
					//send movement and rotation here
					//debugging for rotation and movement
			/*if(rotation!=0||movement!=0){
				System.out.println("Rotation: "+rotation);
				System.out.println("Movement: "+movement);
			}*/
					//mouse position to be implemented
					/// Powerup activated:
					if(input.isKeyPressed(Input.KEY_1)) Powerups.sendPowerupActivation(C.POWERUP_HEALTH);
					else if(input.isKeyPressed(Input.KEY_2)) {
						Powerups.SendMineCord();
						Powerups.sendPowerupActivation(C.POWERUP_MINE);
					}
					else if(input.isKeyPressed(Input.KEY_3)) Powerups.sendPowerupActivation(C.POWERUP_SPEED);
					else if(input.isKeyPressed(Input.KEY_4)) Powerups.sendPowerupActivation(C.POWERUP_POWER);
					else if(input.isKeyPressed(Input.KEY_5)) Powerups.sendPowerupActivation(C.POWERUP_INVINCIBLE);
//					else if(input.isKeyPressed(Input.KEY_6)) Powerups.sendPowerupActivation(C.POWERUP_INVISIBLE);
					/// Cheat keys:
					else if(input.isKeyPressed(Input.KEY_F1)) NetworkControl.sendToAll("~PC" + Settings.playerID + C.POWERUP_HEALTH);
					else if(input.isKeyPressed(Input.KEY_F2)) NetworkControl.sendToAll("~PC" + Settings.playerID + C.POWERUP_MINE);
					else if(input.isKeyPressed(Input.KEY_F3)) NetworkControl.sendToAll("~PC" + Settings.playerID + C.POWERUP_SPEED);
					else if(input.isKeyPressed(Input.KEY_F4)) NetworkControl.sendToAll("~PC" + Settings.playerID + C.POWERUP_POWER);
					else if(input.isKeyPressed(Input.KEY_F5)) NetworkControl.sendToAll("~PC" + Settings.playerID + C.POWERUP_INVINCIBLE);
//					else if(input.isKeyPressed(Input.KEY_F6))
//						NetworkControl.sendToAll("~PC" + Settings.playerID + C.POWERUP_INVISIBLE);
					}
				else /// Game is paused
					{
					if(input.isKeyPressed((Input.KEY_ENTER)))
						{
						DisplaysPopupBox.initPopup(C.POPUP_CHAT);
						}
					}
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processScreenAdjustment(Input input)
		{
		/* Right arrow : Width + */
		if(input.isKeyDown(Input.KEY_RIGHT))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenWidth <= (Settings.maxPlayScreenWidth - Settings.screenAdjustment))
					{
					Settings.currentScreenWidth += Settings.screenAdjustment;
					Settings.playScreenWidth += Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				if(Settings.currentScreenWidth <= (Settings.maxMainScreenWidth - Settings.screenAdjustment))
					{
					Settings.currentScreenWidth += Settings.screenAdjustment;
					Settings.mainScreenWidth += Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
		/* Left arrow : Width - */
		if(input.isKeyDown(Input.KEY_LEFT))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenWidth >= (Settings.minPlayScreenWidth + Settings.screenAdjustment))
					{
					Settings.currentScreenWidth -= Settings.screenAdjustment;
					Settings.playScreenWidth -= Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				if(Settings.currentScreenWidth >= (Settings.minMainScreenWidth + Settings.screenAdjustment))
					{
					Settings.currentScreenWidth -= Settings.screenAdjustment;
					Settings.mainScreenWidth -= Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
		/* Up arrow : Height + */
		if(input.isKeyDown(Input.KEY_UP))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenHeight <= (Settings.maxPlayScreenHeight - Settings.screenAdjustment))
					{
					Settings.currentScreenHeight += Settings.screenAdjustment;
					Settings.playScreenHeight += Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				if(Settings.currentScreenHeight <= (Settings.maxMainScreenHeight - Settings.screenAdjustment))
					{
					Settings.currentScreenHeight += Settings.screenAdjustment;
					Settings.mainScreenHeight += Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
	/* Down arrow : Height - */
		if(input.isKeyDown(Input.KEY_DOWN))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenHeight >= (Settings.minPlayScreenHeight + Settings.screenAdjustment))
					{
					Settings.currentScreenHeight -= Settings.screenAdjustment;
					Settings.playScreenHeight -= Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
//				System.out.printf("currentHeight = %d\n", Settings.currentScreenHeight);
				if(Settings.currentScreenHeight >= (Settings.minMainScreenHeight + Settings.screenAdjustment))
					{
					Settings.currentScreenHeight -= Settings.screenAdjustment;
					Settings.mainScreenHeight -= Settings.screenAdjustment;
					StateControl.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processMouseInput()
		{
	/* STATE MAIN +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		if(StateControl.currentState == StateControl.STATE_MAIN)
			{
			if(DisplaysPopupBox.popupDisplayed == C.YES) processPopupClick();
			else
				{
				if(withinCoordinates(DisplaysStateMain.buttonHost))
					{
					playClick();
					Settings.playerType = C.SERVER;
					NetworkControl.setupServer();
					}
				else if(withinCoordinates(DisplaysStateMain.buttonJoin))
					{
					playClick();
					Settings.playerType = C.CLIENT;
					DisplaysPopupBox.initPopup(C.POPUP_IP_ADDRESS);
					}
				}
			}
	/* STATE LOBBY ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_LOBBY)
			{
//			processNavigationalClick();
			if(DisplaysPopupBox.popupDisplayed == C.YES) processPopupClick();
			else
				{
				if(Settings.playerType == C.SERVER && withinCoordinates(DisplaysStateLobby.winConditionButton))
					{
					playClick();
					Settings.winCondition++;
					if(Settings.winCondition > Strings.winConditionTypes.length - 1) Settings.winCondition = 0;
					Commands.sendSetWinConditionCommand();
					}
				else if(withinCoordinates(DisplaysStateLobby.helpButton))
					{
					playClick();
					StateControl.enterState(StateControl.STATE_HELP_MAIN);
					}
				else if(withinCoordinates(DisplaysStateLobby.leaveGameButton))
					{
					playClick();
					if(Settings.playerType == C.SERVER) NetworkControl.exitServer();
					else Commands.sendClientExitsCommand(Settings.playerID);
					}
				else if(Settings.playerType == C.SERVER && withinCoordinates(DisplaysStateLobby.launchGameButton))
					{
					playClick();
					Commands.sendLaunchGameCommand();
					}
				else if(Settings.playerType == C.SERVER && withinCoordinates(DisplaysStateLobby.prevButton))
					{
					playClick();
					Settings.mapSelected--;
					if(Settings.mapSelected < 0) Settings.mapSelected = Filenames.miniMap.length - 1;
					Commands.sendSetMapCommand();
					}
				else if(Settings.playerType == C.SERVER && withinCoordinates(DisplaysStateLobby.nextButton))
					{
					playClick();
					Settings.mapSelected++;
					if(Settings.mapSelected == Filenames.miniMap.length) Settings.mapSelected = 0;
					Commands.sendSetMapCommand();
					}
				else if(Settings.playerType == C.SERVER && Settings.winCondition == C.HIGH_SCORE && withinCoordinates(DisplaysStateLobby.highScoreTimerButton))
					{
					playClick();
					Settings.highScoreTimerIndex++;
					if(Settings.highScoreTimerIndex == StatePlay.highScoreTimerOptions.length)
						Settings.highScoreTimerIndex = 0;
					Commands.sendSetWinConditionCommand();
					}
				else
					{
					for(i = 0; i < C.MAX_PLAYERS; i++)
						{
						if(Settings.playerType == C.SERVER && withinCoordinates(DisplaysStateLobby.buttonColor[i]))
							{
							playClick();
							Settings.playerTeamColors[i] = getNextColor(i);
							Commands.sendSetColorsCommand();
							}
						else if(Settings.playerID == i && withinCoordinates(DisplaysStateLobby.nameButton))
							{
							playClick();
							DisplaysPopupBox.initPopup(C.POPUP_NAME);
							}
						}
					}
				}
			}
	/* STATE HELP MAIN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_MAIN)
			{
			processNavigationalClick();
			if(withinCoordinates(DisplaysStateHelpMain.buttons[0]))
				{
				playClick();
				StateControl.enterState(StateControl.STATE_HELP_GAMEPLAY);
				}
			else if(withinCoordinates(DisplaysStateHelpMain.buttons[1]))
				{
				playClick();
				StateControl.enterState(StateControl.STATE_HELP_CONTROLS);
				}
			else if(withinCoordinates(DisplaysStateHelpMain.buttons[2]))
				{
				playClick();
				StateControl.enterState(StateControl.STATE_HELP_CREDITS);
				}
			}
	/* STATE HELP GAMEPLAY ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_GAMEPLAY)
			{
			processNavigationalClick();
			processHelpNavigationalClick(StateControl.currentState);
			}
	/* STATE HELP CONTROLS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_CONTROLS)
			{
			processNavigationalClick();
			processHelpNavigationalClick(StateControl.currentState);
			}
	/* STATE HELP CREDITS +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_CREDITS)
			{
			processNavigationalClick();
			}
	/* STATE PLAY +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_PLAY)
			{
//			processNavigationalClick();
			if(GameStats.gameOver == C.NO && GameStats.health[Settings.playerID] > 0)
				{
				if(StatePlay.gamePaused == C.NO) /// Only allows input if game is not paused
					{
					for(i = 0; i < Strings.powerups.length; i++)
						{
						if(withinCoordinates(DisplaysStatePlay.powerupArea[i])) /// Health
							{
							playClick();
							Powerups.sendPowerupActivation(i);
							}
						}
					if(withinCoordinates(DisplaysStatePlay.mapArea))
						{
						//TODO handle projectile
						ResourceManager.getSound(Filenames.fire).play(1, Inputs.volumeFire);
						}
					}
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processPopupClick()
		{
		if(Inputs.withinCoordinates(DisplaysPopupBox.buttonEsc))
			{
			playClick();
			DisplaysPopupBox.popupEnd();
			}
		else if(Inputs.withinCoordinates(DisplaysPopupBox.buttonClear))
			{
			playClick();
			DisplaysPopupBox.clearEntered();
			}
		else if(Inputs.withinCoordinates(DisplaysPopupBox.buttonBack))
			{
			playClick();
			DisplaysPopupBox.clearLastCharacter();
			}
		else if(Inputs.withinCoordinates(DisplaysPopupBox.buttonEnter))
			{
			playClick();
			DisplaysPopupBox.finalizeMessage();
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processHelpNavigationalClick(int state)
		{
		if(withinCoordinates(DisplaysButtonsHelpNavigation.buttonPrev))
			{
			playClick();
			if(state == StateControl.STATE_HELP_GAMEPLAY) DisplaysStateHelpGameplay.currentHelpPage = 0;
			else DisplaysStateHelpControls.currentHelpPage = 0;
			}
		else if(withinCoordinates(DisplaysButtonsHelpNavigation.buttonRew))
			{
			playClick();
			if(state == StateControl.STATE_HELP_GAMEPLAY && DisplaysStateHelpGameplay.currentHelpPage > 0)
				DisplaysStateHelpGameplay.currentHelpPage--;
			else if(state == StateControl.STATE_HELP_CONTROLS && DisplaysStateHelpControls.currentHelpPage > 0)
				DisplaysStateHelpControls.currentHelpPage--;
			}
		else if(withinCoordinates(DisplaysButtonsHelpNavigation.buttonFF))
			{
			playClick();
			if(state == StateControl.STATE_HELP_GAMEPLAY && DisplaysStateHelpGameplay.currentHelpPage < DisplaysStateHelpGameplay.totalHelpPages - 1)
				DisplaysStateHelpGameplay.currentHelpPage++;
			else if(state == StateControl.STATE_HELP_CONTROLS && DisplaysStateHelpControls.currentHelpPage < DisplaysStateHelpControls.totalHelpPages - 1)
				DisplaysStateHelpControls.currentHelpPage++;
			}
		else if(withinCoordinates(DisplaysButtonsHelpNavigation.buttonNext))
			{
			playClick();
			if(state == StateControl.STATE_HELP_GAMEPLAY)
				DisplaysStateHelpGameplay.currentHelpPage = DisplaysStateHelpGameplay.totalHelpPages - 1;
			else DisplaysStateHelpControls.currentHelpPage = DisplaysStateHelpControls.totalHelpPages - 1;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processNavigationalClick()
		{
		if(withinCoordinates(DisplaysNavigationalButtons.buttonBack))
			{
			playClick();
			StateControl.backState();
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void playClick()
		{
		if(Settings.playButtonClick == C.YES) ResourceManager.getSound(Filenames.buttonClick).play();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static boolean withinCoordinates(Image image)
		{
		if(localxMouse > image.x && localxMouse < image.getEndX() && localyMouse > image.y && localyMouse < image.getEndY())
			return true;
		return false;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static boolean withinCoordinates(Area area)
		{
		if(localxMouse > area.x && localxMouse < area.endX && localyMouse > area.y && localyMouse < area.endY)
			return true;
		return false;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static boolean withinCoordinates(int x, int y, int endX, int endY)
		{
		if(localxMouse > x && localxMouse < endX && localyMouse > y && localyMouse < endY) return true;
		return false;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static int colorAlreadyInUse(int player, int color)
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			if(i != player && Settings.playerTeamColors[i] == color) return C.YES;
			}
		return C.NO;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static int getNextColor(int player)
		{
		int nextColor = Settings.playerTeamColors[player];
		while(true)
			{
			nextColor++;
			if(nextColor > Settings.allColors.length - 1) nextColor = 0;
//			if(colorAlreadyInUse(player, nextColor) == C.NO) return nextColor;
			return nextColor;
			}
		}
	}
