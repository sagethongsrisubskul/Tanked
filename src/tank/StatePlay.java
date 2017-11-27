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
	PlayGame playGame;
	int elapsedTime;
	int hours;
	int minutes;
	int seconds;
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
		}
	}