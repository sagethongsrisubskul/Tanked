package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tank extends StateBasedGame
	{
	/*-----------------------------------------------------------------------------------------------------*/
	public Tank(String title)
		{
		super(title);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void initStatesList(GameContainer container) throws SlickException
		{
		addState(new StateMain());
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void main(String[] args)
		{
		AppGameContainer appGameContainer;
		try
			{
			appGameContainer = new AppGameContainer(new Tank("Tank"));
			appGameContainer.setDisplayMode(800, 800, false);
			appGameContainer.setTargetFrameRate(60);
			appGameContainer.setShowFPS(false);
			appGameContainer.setVSync(true);
			appGameContainer.start();
			}
		catch (SlickException e)
			{
			e.printStackTrace();
			}
		}
	}
