package tank;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StateMain extends BasicGameState
	{
	@Override
	public int getID()
		{
		return 0;
		}
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
		g.drawString("Tank", 100, 100);
		g.drawString("Tyler Test",100,100);
		}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
		}
	}
