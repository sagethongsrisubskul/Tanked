package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
public class StateMain extends BasicGameState
	{
	Tank tank;
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
		Inputs.xMouse = x;
		Inputs.yMouse = y;
		Inputs.processMouseInput();
		Inputs.xMouse = -1;
		Inputs.yMouse = -1;
		}
	/*-----------------------------------------------------------------------------------------------------*/
//	@Override
//	public void keyPressed(int key, char c)
//		{
////		System.out.printf("'%c' '%d'\n", c, key);
//		}
	}
