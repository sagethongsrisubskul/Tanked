package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateSetupGame
	{
	static int i;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spaceAfterHeading = 25; /// Space between the bottom of the heading and the top of 1st setting
	public static int spaceColumn = 25; /// The space in-between individual settings and corresponding button(s)
	public static int marginSides = 50; /// The margins on the left and right sides of screen
	public static int spaceAfterIndividualSettings = 25; /// The space between the last individual setting and the player settings
	public static int spaceBetweenColumns = 10;
	public static int spaceBetweenRows = 10;
	public static int buttonSquareDimension;
	/// psw (percentage of screen width):
	public static float pswButtonRect = .15f;
	public static float pswButtonSquare = .04f;
	/// Fonts:
	public static TrueTypeFont settingTextFont = Fonts.fontVerdana20TTF;
	public static TrueTypeFont buttonTextFont = Fonts.fontVerdana15TTF;
	/// Colors:
	public static Color settingTextColor = Color.white;
	public static Color buttonTextColor = Color.black;
	/// Areas:
	public static Area buttonColor[] = new Area[C.MAX_PLAYERS];
	/// Objects:
	public static StringsDisplay players = new StringsDisplay(Strings.players, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay winCondition = new StringsDisplay(Strings.winCondition, settingTextFont, settingTextColor, 0, 0);
	public static Image winConditionButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static StringsDisplay id = new StringsDisplay(Strings.id, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay name = new StringsDisplay(Strings.name, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay color = new StringsDisplay(Strings.team, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay joined = new StringsDisplay(Strings.joined, settingTextFont, settingTextColor, 0, 0);

	/*-----------------------------------------------------------------------------------------------------*/
	public static void initButtons()
		{
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			buttonColor[i] = new Area();
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		/// Spacings:
		buttonSquareDimension = winConditionButton.getHeight();
		marginSides = Settings.currentScreenWidth <= 800 ? 100 : 100 + (int)((Settings.currentScreenWidth - 800) * 0.5);
		spaceBetweenColumns = (Settings.currentScreenWidth - id.getWidth() - name.getWidth() - color.getWidth() - joined.getWidth() - (2 * marginSides)) / 3;
		spaceBetweenRows = Settings.currentScreenHeight <= 800 ? 25 : 25 + (int)((Settings.currentScreenHeight - 800) / C.MAX_PLAYERS);
		/// Title & heading:
		DisplaysTitle.positionTitle(marginTitleTop);
		DisplaysNavigationalButtons.positionNavigationalButtons();
		DisplaysHeading.positionHeading(Strings.setupPlayers);
		/// Individual settings:
		winCondition.x = (Settings.currentScreenWidth - winCondition.getWidth() - spaceColumn - winConditionButton.getWidth()) / 2;
		winCondition.y = DisplaysHeading.heading.getEndY() + spaceAfterHeading;
		winConditionButton.x = winCondition.getEndX() + spaceColumn;
		winConditionButton.y = winCondition.y;
		/// Player settings:
		players.x = players.centerStringScreenX();
		players.y = winConditionButton.getEndY() + spaceAfterIndividualSettings;
		id.x = marginSides;
		id.y = players.getEndY() + spaceAfterIndividualSettings;
		name.x = id.getEndX() + spaceBetweenColumns;
		name.y = id.y;
		color.x = name.getEndX() + spaceBetweenColumns;
		color.y = id.y;
		joined.x = color.getEndX() + spaceBetweenColumns;
		joined.y = id.y;
		/// Color settings:
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			buttonColor[i].x = color.x;
			buttonColor[i].y = color.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonSquareDimension);
			buttonColor[i].endX = buttonColor[i].x + buttonSquareDimension;
			buttonColor[i].endY = buttonColor[i].y + buttonSquareDimension;
			}
		}

	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		DisplaysTitle.renderTitle();
		DisplaysNavigationalButtons.renderNavigationalButtons();
		DisplaysHeading.renderHeading();
		winCondition.renderString();
		winConditionButton.renderImage();
		winConditionButton.renderStringInImage(buttonTextFont, Strings.winConditionTypes[Settings.winCondition], buttonTextColor);
		players.renderString();
		id.renderString();
		name.renderString();
		color.renderString();
		joined.renderString();
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			settingTextFont.drawString(id.x, id.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), String.format("%d", i+1), settingTextColor);
			settingTextFont.drawString(name.x, name.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), Settings.playerName[i], settingTextColor);
			buttonColor[i].colorSection(g, Settings.allColors[Settings.playerTeamColors[i]]);
//			g.setColor(Settings.allColors[Settings.playerTeamColors[i]]);
//			g.fillRect(buttonColor[i].x, buttonColor[i].y, buttonColor[i].getWidth(), buttonColor[i].getHeight());
			if(i < Settings.numberActivePlayers)
				settingTextFont.drawString(joined.x, joined.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), Strings.check, settingTextColor);
			}
		}
	}
