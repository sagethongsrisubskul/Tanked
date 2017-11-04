package tank;
import org.newdawn.slick.Color;
/* This class is for displaying the game heading under the game title in the setup & help states */
public class DisplaysHeading
	{
	public static int spaceAfterTitle = 20; /// The space between the bottom of the title and the top of the heading
	public static StringsDisplay heading = new StringsDisplay("", Fonts.fontVerdana30TTF, Color.lightGray, 0, 0);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionHeading(String string)
		{
		heading.string = string;
		heading.x = (Settings.currentScreenWidth - heading.trueTypeFont.getWidth(heading.string)) / 2;
		heading.y = DisplaysTitle.title.getEndY() + spaceAfterTitle;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderHeading()
		{
		heading.trueTypeFont.drawString(heading.x, heading.y, heading.string, heading.color);
		}
	}
