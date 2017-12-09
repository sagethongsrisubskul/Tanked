package tank;
import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.HorizontalSplitTransition;
import jig.ResourceManager;


public class StateSplash extends BasicGameState
	{
	Tank tank;
	private int timer=0;

	
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public int getID()
		{
		return StateControl.STATE_SPLASH;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
		{
		tank = (Tank) game;
		DisplaysStatePlay.initDisplays();
		ResourceManager.loadImage(Filenames.WSUV);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
		{
			timer=10000;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
		{
			
			g.drawImage(ResourceManager.getImage(Filenames.WSUV), (Settings.mainScreenWidth/3)-60,
				Settings.mainScreenHeight/3);
			Font font = new Font(g.getFont().toString(), Font.BOLD, 32);
			TrueTypeFont truetype = new TrueTypeFont(font, true);
			truetype.drawString((Settings.mainScreenWidth/2)-180,(Settings.mainScreenHeight/2)+25, "CS447 FINAL PROJECT");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
		{
			timer -= delta;
			if (timer <= 0)
				StateControl.enterState(StateControl.STATE_MAIN);
		
		}

	
	
	}