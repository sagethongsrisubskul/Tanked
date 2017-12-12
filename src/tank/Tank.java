// Done:
//TODO beer powerup *DONE
//TODO cheat code for max powerups *DONE
//TODO set a cap on max powerups *DONE
//TODO fix gray lines in map3 *DONE

// Left to do:
//TODO powerups appear on the very corner of the map, not able to see all of it or collide with it
//TODO tanks run over each other
//TODO sync player damage for mines
//TODO mine activation cooldown
//TODO projectiles
//TODO map collision with walls
//TODO explosion animations
//TODO fix tank moving if moving while pause is pressed

// If time:
//TODO weighted powerup spawns? (e.g. beer and invincible should appear less often than say health)
//TODO minimap powerup spawn locator? (place small dot on minimap to indicate where the powerup is spawned)

package tank;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import jig.Entity;

public class Tank extends StateBasedGame
	{
	public static AppGameContainer application;
	public static boolean DEBUG = false;
	/*-----------------------------------------------------------------------------------------------------*/
	public Tank(String title)
		{
		super(title);
		Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	@Override
	public void initStatesList(GameContainer container) throws SlickException
		{
		int i;
		addState(new StateSplash());
		addState(new StateMain());
		addState(new StateLobby());
		addState(new StateHelpMain());
		addState(new StateHelpGameplay());
		addState(new StateHelpControls());
		addState(new StateHelpCredits());
		addState(new StatePlay());
		addState(new StateChangeScreenSize());
		ResourceManager.loadImage(Filenames.WSUV);
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
		ResourceManager.loadImage(Filenames.tank_r);
		ResourceManager.loadImage(Filenames.tank_b);
		ResourceManager.loadImage(Filenames.tank_g);
		ResourceManager.loadImage(Filenames.tank_y);
		ResourceManager.loadImage(Filenames.turret_r);
		ResourceManager.loadImage(Filenames.turret_b);
		ResourceManager.loadImage(Filenames.turret_g);
		ResourceManager.loadImage(Filenames.turret_y);
		ResourceManager.loadSound(Filenames.buttonClick);
		ResourceManager.loadSound(Filenames.ding);
		ResourceManager.loadSound(Filenames.fire);
		ResourceManager.loadSound(Filenames.shoot);
		ResourceManager.loadSound(Filenames.engine);
		ResourceManager.loadSound(Filenames.explosion);
		ResourceManager.loadSound(Filenames.mainMusic);
//		for(i = 0; i < Filenames.maps.length; i++)
//			ResourceManager.loadImage(Filenames.maps[i]);
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
			jig.Entity.antiAliasing = false;
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
