package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateSelectMap
	{
	static int i;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spaceAfterHeading = 25; /// Space between the bottom of the heading and the top of 1st map
	public static int spaceBetweenMaps = 10;
	public static int marginSides = 50; /// The margins on the left and right sides of screen
	/// psw (percentage of screen width):
	public static float pswMap = 0.15f;
	/// Fonts:
	public static TrueTypeFont mapTextFont = Fonts.fontVerdana20TTF;
	/// Colors:
	public static Color mapSelectionColor = Color.lightGray;
	public static Color mapTextColorLight = Color.white;
	public static Color mapTextColorDark = Color.black;
	/// Areas:
	public static Area miniMapArea[] = new Area[Filenames.miniMap.length];
	/// Objects:
	public static Image miniMap[] = new Image[Filenames.miniMap.length];
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initMaps()
		{
		for(i = 0; i < miniMap.length; i++)
			{
			miniMap[i] = new Image(Filenames.miniMap[i], 0, 0, pswMap);
			miniMapArea[i] = new Area();
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		DisplaysTitle.positionTitle(marginTitleTop);
		DisplaysNavigationalButtons.positionButtons();
		DisplaysHeading.positionHeading(Strings.selectMap);
		for(i = 0; i < miniMap.length; i++)
			{
			miniMapArea[i].x = marginSides;
			miniMapArea[i].y = DisplaysHeading.heading.getEndY() + spaceAfterHeading + (i * miniMap[i].getHeight()) + (i * 2 * spaceBetweenMaps);
			miniMapArea[i].endX = Settings.currentScreenWidth - marginSides;
			miniMapArea[i].endY = miniMapArea[i].y + miniMap[i].getHeight() + (2 * spaceBetweenMaps);
			miniMap[i].x = miniMapArea[i].x + spaceBetweenMaps;
			miniMap[i].y = miniMapArea[i].y + spaceBetweenMaps;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		DisplaysTitle.renderTitle();
		DisplaysNavigationalButtons.renderNavigationalButtons();
		DisplaysHeading.renderHeading();
		for(i = 0; i < miniMap.length; i++)
			{
			if(i == Settings.mapSelected)
				{
				miniMapArea[i].colorSection(g, mapSelectionColor);
				g.setColor(mapTextColorDark);
				}
			else
				g.setColor(mapTextColorLight);
			miniMap[i].renderImage();
			g.drawString(Strings.miniMap[i], miniMap[i].getEndX() + spaceBetweenMaps, miniMap[i].y);
			}
		}
	}
