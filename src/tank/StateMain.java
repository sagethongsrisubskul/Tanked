package tank;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class StateMain extends BasicGameState
	{
	Tank tank;
	public static Music music;
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public int getID()
		{
		return StateControl.STATE_MAIN;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
		Settings.initSettings();
		DisplaysStateMain.initDisplays();
		StateControl.initStateControl(tank);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
		AppGameContainer gc = (AppGameContainer) container;
		gc.setDisplayMode(Settings.mainScreenWidth, Settings.mainScreenHeight, false);
		StateControl.addCurrentState(getID());
		Settings.currentScreenWidth = Settings.mainScreenWidth;
		Settings.currentScreenHeight = Settings.mainScreenHeight;
		DisplaysStateMain.positionDisplays();
		DisplaysNavigationalButtons.positionButtons();
		DisplaysButtonsHelpNavigation.positionButtons();
		music = new Music(Filenames.mainMusic);
		music.loop();
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
//				System.out.printf("popupDisplayed: %d < %d\n", DisplaysPopupBox.charactersEntered, DisplaysPopupBox.maxCharacters);
				DisplaysPopupBox.getPopupInput(input);
				}
			}
		input.clearKeyPressedRecord();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		DisplaysStateMain.renderDisplays(g);
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
//	@Override
//	public void keyPressed(int key, char c)
//		{
////		System.out.printf("'%c' '%d'\n", c, key);
//		}
	}
