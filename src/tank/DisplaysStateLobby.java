package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStateLobby
	{
	static int i;
	public static int numMessages = 0;
	public static int currentMiniMapDisplayed = 0;
	/// Spacings:
	public static int marginTitleTop = 25; /// The margin from the top of the screen to the top of the title
	public static int spaceAfterHeading = 25; /// Space between the bottom of the lobbyHeading and the top of 1st setting
	public static int spaceColumn = 25; /// The space in-between individual settings and corresponding button(s)
	public static int spaceBetweenNavigationalButtons = 50;
	public static int marginTop = 15;
	public static int marginBottom = 15;
	public static int marginSides = 50; /// The margins on the left and right sides of screen
	public static int spaceAfterIndividualSettings = 25; /// The space between the last individual setting and the player settings
	public static int spaceBetweenColumns = 30;
	public static int spaceBetweenRows = 20;
	public static int buttonSquareDimension;
	public static int messageAreaHeight = 200;
	public static int messageAreaMargin = 20;
	public static int messageAreaPadding = 10;
	public static int spaceAfterSettings = 25; /// The space between the last setting and the message display
	public static int nameHeight;
	public static int nameWidth;
	/// psw (percentage of screen width):
	public static float pswButtonRect = .15f;
	public static float pswButtonSquare = .06f;
	public static float pswLobbyBackground = 1.5f;
	public static float pswMap = 0.21f;
	public static float pswIcon = .04f;
	/// Fonts:
	public static TrueTypeFont settingTextFont = Fonts.fontVerdana20TTF;
	public static TrueTypeFont buttonTextFont = Fonts.fontVerdana15TTF;
	public static TrueTypeFont messageTextFont = Fonts.fontCourier11BTTF;
	/// Colors:
	public static Color settingTextColor = Color.white;
	public static Color buttonTextColor = Color.black;
	public static Color messageBackgroundColor = Color.black;
	public static Color messageTextColor = Color.white;
	/// Areas:
	public static Area nameArea[] = new Area[C.MAX_PLAYERS];
	public static Area buttonColor[] = new Area[C.MAX_PLAYERS];
	public static Area messageArea = new Area();
	/// Objects:
	public static Image lobbyBackground = new Image(Filenames.lobbyBackground, 0, 0, pswLobbyBackground);
	public static Image winConditionButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image helpButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image leaveGameButton = new Image(Filenames.buttonRectangle, 0, 0, pswButtonRect);
	public static Image prevButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquare);
	public static Image prevIcon = new Image(Filenames.arrowPrev, 0, 0, pswIcon);
	public static Image nextButton = new Image(Filenames.buttonSquare, 0, 0, pswButtonSquare);
	public static Image nextIcon = new Image(Filenames.arrowNext, 0, 0, pswIcon);
	
	public static Image miniMap[] = new Image[Filenames.miniMap.length];
	public static StringsDisplay heading = new StringsDisplay(Strings.lobbyHeading, DisplaysHeading.headingFont, DisplaysHeading.headingColor, 0, 0);
	public static StringsDisplay map = new StringsDisplay(Strings.map, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay help = new StringsDisplay(Strings.helpInfo, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay leaveGame = new StringsDisplay(Strings.leaveGame, buttonTextFont, buttonTextColor, 0, 0);
	public static StringsDisplay players = new StringsDisplay(Strings.players, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay winCondition = new StringsDisplay(Strings.winCondition, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay id = new StringsDisplay(Strings.id, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay name = new StringsDisplay(Strings.name, settingTextFont, settingTextColor, 0, 0);
	public static StringsDisplay team = new StringsDisplay(Strings.team, settingTextFont, settingTextColor, 0, 0);

	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			buttonColor[i] = new Area();
			nameArea[i] = new Area();
			}
		for(i = 0; i < miniMap.length; i++)
			{
			miniMap[i] = new Image(Filenames.miniMap[i], 0, 0, pswMap);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		/// Title & lobbyHeading:
		heading.x = heading.centerStringScreenX();
		heading.y = marginTop;
		/// Spacings:
		buttonSquareDimension = winConditionButton.getHeight();
		nameWidth = settingTextFont.getWidth(Settings.sampleMaxName);
		nameHeight = settingTextFont.getHeight(Settings.sampleMaxName);
//		marginSides = Settings.currentScreenWidth <= 800 ? 100 : 100 + (int)((Settings.currentScreenWidth - 800) * 0.5);
//		spaceBetweenColumns = (Settings.currentScreenWidth - id.getWidth() - name.getWidth() - team.getWidth() - joined.getWidth() - (2 * marginSides)) / 3;
//		spaceBetweenRows = Settings.currentScreenHeight <= 800 ? 25 : 25 + (int)((Settings.currentScreenHeight - 800) / C.MAX_PLAYERS);
		/// Individual settings:
		winCondition.x = (Settings.currentScreenWidth - winCondition.getWidth() - spaceColumn - winConditionButton.getWidth()) / 2;
		winCondition.y = heading.getEndY() + spaceAfterHeading;
		winConditionButton.x = winCondition.getEndX() + spaceColumn;
		winConditionButton.y = winCondition.y;
		/// Player settings:
		players.x = players.centerStringScreenX();
		players.y = winConditionButton.getEndY() + spaceAfterIndividualSettings;
		id.x = marginSides;
		id.y = players.getEndY() + spaceAfterIndividualSettings;
		name.x = id.getEndX() + spaceBetweenColumns;
		name.y = id.y;
		team.x = name.x + nameWidth + spaceBetweenColumns;
		team.y = id.y;
		/// Color settings:
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			buttonColor[i].x = team.x;
			buttonColor[i].y = team.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonSquareDimension);
			buttonColor[i].endX = buttonColor[i].x + buttonSquareDimension;
			buttonColor[i].endY = buttonColor[i].y + buttonSquareDimension;
			}
		/// Areas:
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			nameArea[i].x = name.x;
			nameArea[i].y = name.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth());
			nameArea[i].endX = nameArea[i].x + nameWidth + (spaceBetweenColumns / 2);
			nameArea[i].endY = nameArea[i].y + nameHeight;
			}
		messageArea.x = messageAreaMargin;
		messageArea.endX = Settings.currentScreenWidth - messageAreaMargin;
		messageArea.y = buttonColor[C.MAX_PLAYERS - 1].endY + spaceAfterSettings;
		messageArea.endY = messageArea.y + messageAreaHeight;
		/// Minimap:
		for(i = 0; i < Filenames.miniMap.length; i++)
			{
			miniMap[i].x = team.getEndX() + ((Settings.currentScreenWidth - team.getEndX() - miniMap[i].getWidth()) / 2);
			miniMap[i].y = nameArea[0].y;
			}
		map.x = miniMap[0].x;
		map.y = team.y;
		prevButton.x = miniMap[0].x - prevButton.getWidth() - spaceBetweenColumns;
		prevButton.y = miniMap[0].centerImageY(pswButtonSquare);
		prevIcon.x = prevButton.centerImageX(pswIcon);
		prevIcon.y = prevButton.centerImageY(pswIcon);
		nextButton.x = miniMap[0].getEndX() + spaceBetweenColumns;
		nextButton.y = miniMap[0].centerImageY(pswButtonSquare);
		nextIcon.x = nextButton.centerImageX(pswIcon);
		nextIcon.y = nextButton.centerImageY(pswIcon);
		/// Navigational buttons:
		helpButton.x = (Settings.currentScreenWidth - helpButton.getWidth() - leaveGameButton.getWidth() - spaceBetweenNavigationalButtons) / 2;
		helpButton.y = Settings.currentScreenHeight - helpButton.getHeight() - marginBottom;
		leaveGameButton.x = helpButton.getEndX() + spaceBetweenNavigationalButtons;
		leaveGameButton.y = helpButton.y;
		help.x = helpButton.centerStringX(help.trueTypeFont, help.string);
		help.y = helpButton.centerStringY(help.trueTypeFont, help.string);
		leaveGame.x = leaveGameButton.centerStringX(leaveGame.trueTypeFont, leaveGame.string);
		leaveGame.y = leaveGameButton.centerStringY(leaveGame.trueTypeFont, leaveGame.string);
		}

	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		lobbyBackground.renderImage();
		heading.renderString();
		/// Individual settings:
		winCondition.renderString();
		winConditionButton.renderImage();
		winConditionButton.renderStringInImage(buttonTextFont, Strings.winConditionTypes[Settings.winCondition], buttonTextColor);
		/// Player settings:
		players.renderString();
		id.renderString();
		name.renderString();
		team.renderString();
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			settingTextFont.drawString(id.x, id.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), String.format("%d", i+1), settingTextColor);
			buttonColor[i].colorSection(g, Settings.allColors[Settings.playerTeamColors[i]]);
			if(i < Settings.numberActivePlayers)
				{
				settingTextFont.drawString(name.x, name.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), Settings.playerName[i], settingTextColor);
//				settingTextFont.drawString(joined.x, joined.getEndY() + ((i + 1) * spaceBetweenRows) + (i * buttonColor[i].getWidth()), Strings.check, settingTextColor);
				}
			}
		/// Minimap:
		map.renderString();
		settingTextFont.drawString(map.getEndX() + 10, map.y, Integer.toString(currentMiniMapDisplayed + 1), settingTextColor);
		miniMap[currentMiniMapDisplayed].renderImage();
		prevButton.renderImage();
		prevIcon.renderImage();
		nextButton.renderImage();
		nextIcon.renderImage();
		/// Messages:
		messageArea.colorSection(g, messageBackgroundColor);
		for(i=0;i<Strings.networkMessages.length;i++)
			{
//			System.out.printf("message %d = %s, (%d, %d)\n", i, Strings.networkMessages[i], messageArea.x + messageAreaPadding, messageArea.y + messageAreaPadding + (i * 10));
			messageTextFont.drawString(messageArea.x + messageAreaPadding, messageArea.y + messageAreaPadding + (i * 10), Strings.networkMessages[i], messageTextColor);
			}
		/// Navigational buttons:
		helpButton.renderImage();
		help.renderString();
		leaveGameButton.renderImage();
		leaveGame.renderString();
		/// Popup:
		if(DisplaysPopupBox.popupDisplayed == C.YES)
			{
			DisplaysPopupBox.renderPopup(g);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void displayMessage(String string)
		{
		if(numMessages < Strings.networkMessages.length)
			{
			Strings.networkMessages[numMessages] = Strings.networkMessagePrompt + " " + string;
			numMessages++;
			}
		else
			{
			int i;
			for(i = 0; i < Strings.networkMessages.length - 1; i++)
				{
				Strings.networkMessages[i] = Strings.networkMessages[i+1];
				}
			Strings.networkMessages[Strings.networkMessages.length - 1] = Strings.networkMessagePrompt + " " + string;
			}
		}

	}
