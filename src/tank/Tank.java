package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tank extends StateBasedGame
	{
	public static AppGameContainer application;
	/*-----------------------------------------------------------------------------------------------------*/
	public Tank(String title)
		{
		super(title);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void initStatesList(GameContainer container) throws SlickException
		{
		int i;
		addState(new StateMain());
		addState(new StateLobby());
		addState(new StateHelpMain());
		addState(new StateHelpGameplay());
		addState(new StateHelpControls());
		addState(new StateHelpCredits());
		addState(new StatePlay());
		addState(new StateChangeScreenSize());
		ResourceManager.loadImage(Filenames.title);
		ResourceManager.loadImage(Filenames.logo);
		ResourceManager.loadImage(Filenames.camoBackground);
		ResourceManager.loadImage(Filenames.lobbyBackground);
		ResourceManager.loadImage(Filenames.buttonRectangle);
		ResourceManager.loadImage(Filenames.buttonSquare);
		ResourceManager.loadImage(Filenames.navHome);
		ResourceManager.loadImage(Filenames.navBack);
		ResourceManager.loadImage(Filenames.navFF);
		ResourceManager.loadImage(Filenames.navNext);
		ResourceManager.loadImage(Filenames.navPrev);
		ResourceManager.loadImage(Filenames.navRew);
		ResourceManager.loadImage(Filenames.arrowNext);
		ResourceManager.loadImage(Filenames.arrowPrev);
		ResourceManager.loadSound(Filenames.buttonClick);
//		for(i = 0; i < Filenames.maps.length; i++)
//			ResourceManager.loadImage(Filenames.maps[i]);
		ResourceManager.loadImage(Filenames.map2);
		for(i = 0; i < Filenames.miniMap.length; i++)
			ResourceManager.loadImage(Filenames.miniMap[i]);
		for(i = 0; i < Filenames.powerupIcons.length; i++)
			ResourceManager.loadImage(Filenames.powerupIcons[i]);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void main(String[] args)
		{
		AppGameContainer appGameContainer;

		try
			{
			appGameContainer = new AppGameContainer(new Tank(Strings.gameTitle));
			appGameContainer.setDisplayMode(Settings.mainScreenWidth, Settings.mainScreenHeight, false);
			application = appGameContainer;
			Settings.maxMainScreenHeight = appGameContainer.getScreenHeight();
			Settings.maxPlayScreenHeight = appGameContainer.getScreenHeight();
			Settings.maxMainScreenWidth = appGameContainer.getScreenWidth();
			Settings.maxPlayScreenWidth = appGameContainer.getScreenWidth();
			appGameContainer.setTargetFrameRate(Settings.targetFrameRate);
			appGameContainer.setShowFPS(false);
			appGameContainer.setVSync(true);
			appGameContainer.start();
			}
		catch (SlickException e)
			{
			e.printStackTrace();
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void exitProgram()
		{
		application.exit();
		}
	}
