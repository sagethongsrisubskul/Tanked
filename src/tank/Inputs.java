package tank;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
/* This class is for handling any keyboard or mouse click inputs */
public class Inputs
	{
	static int i;
	public static int xMouse;
	public static int yMouse;
	public static int tempState;
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processKeyboardInput(Input input, Tank tank, GameContainer container)
		{
		processScreenAdjustment(tank, input);
		if(DisplaysPopupIpAddress.popupDisplayed == C.YES)
			{
			if(input.isKeyPressed(Input.KEY_ESCAPE))
				{
				popupEnd();
				}
			else if(input.isKeyPressed(Input.KEY_DELETE))
				{
				DisplaysPopupIpAddress.clearEntered();
				}
			else if(input.isKeyPressed(Input.KEY_BACK))
				{
				DisplaysPopupIpAddress.clearLastCharacter();
				}
			else if(input.isKeyPressed(Input.KEY_ENTER))
				{
				DisplaysPopupIpAddress.enterIPAddress();
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processScreenAdjustment(Tank tank, Input input)
		{
		/* Right arrow : Width + */
		if(input.isKeyPressed(Input.KEY_RIGHT))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenWidth <= (Settings.maxPlayScreenWidth - Settings.screenAdjustment))
					{
					Settings.currentScreenWidth += Settings.screenAdjustment;
					Settings.playScreenWidth += Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				if(Settings.currentScreenWidth <= (Settings.maxMainScreenWidth - Settings.screenAdjustment))
					{
					Settings.currentScreenWidth += Settings.screenAdjustment;
					Settings.mainScreenWidth += Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
		/* Left arrow : Width - */
		if(input.isKeyPressed(Input.KEY_LEFT))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenWidth >= (Settings.minPlayScreenWidth + Settings.screenAdjustment))
					{
					Settings.currentScreenWidth -= Settings.screenAdjustment;
					Settings.playScreenWidth -= Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				if(Settings.currentScreenWidth >= (Settings.minMainScreenWidth + Settings.screenAdjustment))
					{
					Settings.currentScreenWidth -= Settings.screenAdjustment;
					Settings.mainScreenWidth -= Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
		/* Up arrow : Height + */
		if(input.isKeyPressed(Input.KEY_UP))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenHeight <= (Settings.maxPlayScreenHeight - Settings.screenAdjustment))
					{
					Settings.currentScreenHeight += Settings.screenAdjustment;
					Settings.playScreenHeight += Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				if(Settings.currentScreenHeight <= (Settings.maxMainScreenHeight - Settings.screenAdjustment))
					{
					Settings.currentScreenHeight += Settings.screenAdjustment;
					Settings.mainScreenHeight += Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
	/* Down arrow : Height - */
		if(input.isKeyPressed(Input.KEY_DOWN))
			{
			if(StateControl.currentState == StateControl.STATE_PLAY)
				{
				if(Settings.currentScreenHeight >= (Settings.minPlayScreenHeight + Settings.screenAdjustment))
					{
					Settings.currentScreenHeight -= Settings.screenAdjustment;
					Settings.playScreenHeight -= Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			else
				{
				System.out.printf("currentHeight = %d\n", Settings.currentScreenHeight);
				if(Settings.currentScreenHeight >= (Settings.minMainScreenHeight + Settings.screenAdjustment))
					{
					Settings.currentScreenHeight -= Settings.screenAdjustment;
					Settings.mainScreenHeight -= Settings.screenAdjustment;
					tank.enterState(StateControl.STATE_CHANGE_SCREEN_SIZE);
					}
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processMouseInput(Tank tank)
		{
	/* STATE MAIN +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		if(StateControl.currentState == StateControl.STATE_MAIN)
			{
			if(DisplaysPopupIpAddress.popupDisplayed == C.YES)
				{
				if(Inputs.withinCoordinates(DisplaysPopupIpAddress.buttonEsc))
					{
					playClick();
					popupEnd();
					}
				else if(Inputs.withinCoordinates(DisplaysPopupIpAddress.buttonClear))
					{
					playClick();
					DisplaysPopupIpAddress.clearEntered();
					}
				else if(Inputs.withinCoordinates(DisplaysPopupIpAddress.buttonBack))
					{
					playClick();
					DisplaysPopupIpAddress.clearLastCharacter();
					}
				else if(Inputs.withinCoordinates(DisplaysPopupIpAddress.buttonEnter))
					{
					playClick();
					DisplaysPopupIpAddress.enterIPAddress();
					}
				}
			else if(Settings.playerType == C.UNDECIDED)
				{
				if(withinCoordinates(DisplaysStateMain.buttonHost))
					{
					playClick();
					Network.setupServer();
					}
				else if(withinCoordinates(DisplaysStateMain.buttonJoin))
					{
					playClick();
					DisplaysPopupIpAddress.initPopup();
					}
				}
			else if(Settings.playerType == C.SERVER)
				{
				if(withinCoordinates(DisplaysStateMain.buttonHost))
					{
					playClick();
					Settings.playerType = C.UNDECIDED;
					Network.exitServer();
					}
				else if(withinCoordinates(DisplaysStateMain.buttons[0]))
					{
					playClick();
					enterState(tank, StateControl.STATE_PLAY);
					}
				else if(withinCoordinates(DisplaysStateMain.buttons[1]))
					{
					playClick();
					enterState(tank, StateControl.STATE_SETUP_GAME);
					}
				else if(withinCoordinates(DisplaysStateMain.buttons[2]))
					{
					playClick();
					enterState(tank, StateControl.STATE_SELECT_MAP);
					}
				else if(withinCoordinates(DisplaysStateMain.buttons[3]))
					{
					playClick();
					enterState(tank, StateControl.STATE_HELP_MAIN);
					}
				}
			else
				{
				if(withinCoordinates(DisplaysStateMain.buttonJoin))
					{
					playClick();
					Settings.playerType = C.UNDECIDED;
					Network.exitClient();
					}
				else if(withinCoordinates(DisplaysStateMain.buttons[3]))
					{
					playClick();
					enterState(tank, StateControl.STATE_HELP_MAIN);
					}
				}
			}
	/* STATE SETUP GAME +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_SETUP_GAME)
			{
			processNavigationalClick(tank);
			if(withinCoordinates(DisplaysStateSetupGame.winConditionButton))
				{
				playClick();
				Settings.winCondition++;
				if(Settings.winCondition > Strings.winConditionTypes.length - 1)
					Settings.winCondition = 0;
				}
			for(i = 0; i < C.MAX_PLAYERS; i++)
				{
				if(withinCoordinates(DisplaysStateSetupGame.buttonColor[i]))
					{
					playClick();
					Settings.playerTeamColors[i] = getNextColor(i);
					}
				}

			}
	/* STATE SELECT MAP +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_SELECT_MAP)
			{
			processNavigationalClick(tank);
			for(i=0; i< Filenames.miniMap.length; i++)
				{
				if(withinCoordinates(DisplaysStateSelectMap.miniMapArea[i]))
					{
					playClick();
					Settings.mapSelected = i;
					}
				}
			}
	/* STATE HELP MAIN ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_MAIN)
			{
			processNavigationalClick(tank);
			if(withinCoordinates(DisplaysStateHelpMain.buttons[0]))
				{
				playClick();
				enterState(tank, StateControl.STATE_HELP_GAMEPLAY);
				}
			else if(withinCoordinates(DisplaysStateHelpMain.buttons[1]))
				{
				playClick();
				enterState(tank, StateControl.STATE_HELP_CONTROLS);
				}
			}
	/* STATE HELP GAMEPLAY ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_GAMEPLAY)
			{
			processNavigationalClick(tank);
			processHelpNavigationalClick(StateControl.currentState);
			}
	/* STATE HELP CONTROLS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_HELP_CONTROLS)
			{
			processNavigationalClick(tank);
			processHelpNavigationalClick(StateControl.currentState);
			}
	/* STATE PLAY +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
		else if(StateControl.currentState == StateControl.STATE_PLAY)
			{
			processNavigationalClick(tank);
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
	public static void processNavigationalClick(Tank tank)
		{
		if(withinCoordinates(DisplaysNavigationalButtons.buttonHome))
			{
			playClick();
			enterState(tank, StateControl.STATE_MAIN);
			}
		else if(withinCoordinates(DisplaysNavigationalButtons.buttonBack))
			{
			playClick();
			backState(tank);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void playClick()
		{
		if(Settings.playButtonClick == C.YES) ResourceManager.getSound(Filenames.buttonClick).play();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void popupEnd()
		{
		DisplaysPopupIpAddress.clearEntered();
		DisplaysPopupIpAddress.popupDisplayed = C.NO;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void backState(Tank tank)
		{
		tempState = StateControl.currentState;
		StateControl.currentState = StateControl.previousState;
		StateControl.previousState = tempState;
		if(StateControl.numStatesQue > 1)
			{
			tank.enterState(StateControl.statesVisited[StateControl.numStatesQue - 2]);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void enterState(Tank tank, int state)
		{
		StateControl.previousState = StateControl.currentState;
		tank.enterState(state);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static boolean withinCoordinates(Image image)
		{
		if(xMouse > image.x && xMouse < image.getEndX() && yMouse > image.y && yMouse < image.getEndY())
			return true;
		return false;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static boolean withinCoordinates(Area area)
		{
		if(xMouse > area.x && xMouse < area.endX && yMouse > area.y && yMouse < area.endY) return true;
		return false;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static boolean withinCoordinates(int x, int y, int endX, int endY)
		{
		if(xMouse > x && xMouse < endX && yMouse > y && yMouse < endY) return true;
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
