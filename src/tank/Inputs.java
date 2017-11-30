package tank;
import org.newdawn.slick.Input;
/* This class is for handling any keyboard or mouse click inputs */
public class Inputs
	{
	static int i;
	public static int xMouse;
	public static int yMouse;
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processKeyboardInput(Input input)
		{
//		processScreenAdjustment(input);
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			if(input.isKeyPressed(Input.KEY_ESCAPE))
				{
				DisplaysPopupBox.popupEnd();
				}
			else if(input.isKeyPressed(Input.KEY_DELETE))
				{
				DisplaysPopupBox.clearEntered();
				}
			else if(input.isKeyPressed(Input.KEY_BACK))
				{
				DisplaysPopupBox.clearLastCharacter();
				}
			else if(input.isKeyPressed(Input.KEY_ENTER))
				{
				DisplaysPopupBox.finalizeMessage();
				}
			}
		else if(StateControl.currentState == StateControl.STATE_LOBBY)
			{
			if(input.isKeyPressed((Input.KEY_ENTER)))
				{
				DisplaysPopupBox.initPopup(C.POPUP_CHAT);
				}
			}
		else if(StateControl.currentState == StateControl.STATE_PLAY)
			{
			if(input.isKeyPressed(Input.KEY_ENTER))
				StateControl.enterState(StateControl.STATE_MAIN);
			}
		
			int movement=0;
			int rotation=0;
		
			if(input.isKeyDown(Input.KEY_W)){
				movement++;
			}
			if(input.isKeyDown(Input.KEY_S)){
				movement--;
			}
			if(input.isKeyDown(Input.KEY_A)){
				rotation++;
			}
			if(input.isKeyDown(Input.KEY_D)){
				rotation--;
			}
			//send movement and rotation here
			
			//debugging for rotation and movement
			/*if(rotation!=0||movement!=0){
				System.out.println("Rotation: "+rotation);
				System.out.println("Movement: "+movement);
			}*/
			//mouse position to be implemented
			
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void processScreenAdjustment(Input input)
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
		if(input.isKeyPressed(Input.KEY_LEFT))
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
		if(input.isKeyPressed(Input.KEY_UP))
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
		if(input.isKeyPressed(Input.KEY_DOWN))
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
				System.out.printf("currentHeight = %d\n", Settings.currentScreenHeight);
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
					if(Settings.playerType == C.SERVER)
						NetworkControl.exitServer();
					else
						Commands.sendClientExitsCommand(Settings.playerID);
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
					if(Settings.mapSelected < 0)
						Settings.mapSelected = Filenames.miniMap.length - 1;
					Commands.sendSetMapCommand();
					}
				else if(Settings.playerType == C.SERVER && withinCoordinates(DisplaysStateLobby.nextButton))
					{
					playClick();
					Settings.mapSelected++;
					if(Settings.mapSelected == Filenames.miniMap.length)
						Settings.mapSelected = 0;
					Commands.sendSetMapCommand();
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
//		if(withinCoordinates(DisplaysNavigationalButtons.buttonHome))
//			{
//			playClick();
//			enterState(StateControl.STATE_MAIN);
//			}
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
