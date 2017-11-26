package tank;
import org.newdawn.slick.Graphics;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStatePlay
	{
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spacingAfterTitle = 20; /// The space between the bottom of the title and the top of the lobbyHeading
	/// psw (percentage of screen width):
	/// Fonts:
	/// Colors:
	/// Areas:
	/// Objects:

	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
//		DisplaysTitle.positionTitle(marginTitleTop);
//		DisplaysNavigationalButtons.positionButtons();
//		DisplaysHeading.positionHeading("Play Game");
		}

	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
//		DisplaysTitle.renderTitle();
//		DisplaysNavigationalButtons.renderNavigationalButtons();
//		DisplaysHeading.renderHeading();
		}
	}
