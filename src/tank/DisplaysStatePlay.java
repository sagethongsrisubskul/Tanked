package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStatePlay
	{
	static int i;
	public static int healthStartY = 0;
	/// Spacings:
	public static int margin = 10;
	public static int miniMapBorder = 5;
	public static int healthBarWidth = 15;
	public static int powerupBorder = 1;
	public static int powerupWidth;
	public static int powerupHeight = 100;
	public static int powerupPadding = 2;
	/// psw (percentage of screen width):
	public static float pswMiniMap = .2f;
	public static float pswMap = 1.2f;
	public static float pswIcon = .045f;
	/// Fonts:
	public static TrueTypeFont mainFont = Fonts.fontCourier11BTTF;
	public static TrueTypeFont timeFont = Fonts.fontCourier10BTTF;
	public static TrueTypeFont scoreFont = Fonts.fontCourier13BTTF;
	/// Colors:
	public static Color mainColor = Color.white;
	public static Color timeColor = Color.white;
	public static Color backgroundColor = Color.black;
	public static Color miniMapColor = Color.white;
	public static Color healthBarColor = Color.white;
	public static Color healthColor = Color.red;
	public static Color powerupBackgroundColor = Color.white;
	public static Color powerupColor = Color.black;
	public static Color scoreColor = Color.green;
	public static Color powerColor = Color.red;
	public static Color speedColor = Color.red;
	/// Areas:
	public static Area mapArea = new Area();
	public static Area healthBarArea = new Area();
	public static Area miniMapArea = new Area();
	public static Area bottomMargin = new Area();
	public static Area rightMargin = new Area();
	public static Area powerupTotalArea = new Area();
	public static Area powerupArea[] = new Area[Strings.powerups.length + 1];
	/// Objects:
	public static Image miniMap = new Image(Filenames.miniMap[Settings.mapSelected], 0, 0, pswMiniMap);
	public static Image map = new Image(Filenames.map2, 0, 0, pswMap);
	public static Image powerupIcon[] = new Image[Filenames.powerupIcons.length];
	public static StringsDisplay time = new StringsDisplay("", timeFont, timeColor, 0, 0);
	public static StringsDisplay powerupStrings[] = new StringsDisplay[Strings.powerups.length];
	public static StringsDisplay score = new StringsDisplay("", scoreFont, scoreColor, 0, 0);
	public static StringsDisplay power = new StringsDisplay("", mainFont, powerColor, 0, 0);
	public static StringsDisplay speed = new StringsDisplay("", mainFont, speedColor, 0, 0);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initDisplays()
		{
		for(i = 0; i < powerupArea.length; i++)
			powerupArea[i] = new Area();
		for(i = 0; i < powerupStrings.length; i++)
			powerupStrings[i] = new StringsDisplay(Strings.powerups[i], mainFont, mainColor, 0, 0);
		for(i = 0; i < powerupIcon.length; i++)
			powerupIcon[i] = new Image(Filenames.powerupIcons[i], 0, 0, pswIcon);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionDisplays()
		{
		/// Borders:
		mapArea.x = margin;
		mapArea.y = (2 * margin) + (2 * powerupBorder) + powerupHeight;
		mapArea.endX = Settings.currentScreenWidth - margin;
		mapArea.endY = Settings.currentScreenHeight - margin;
		bottomMargin.x = 0;
		bottomMargin.y = Settings.currentScreenHeight - margin;
		bottomMargin.endX = Settings.currentScreenWidth;
		bottomMargin.endY = Settings.currentScreenHeight;
		rightMargin.x = Settings.currentScreenWidth - margin;
		rightMargin.y = 0;
		rightMargin.endX = Settings.currentScreenWidth;
		rightMargin.endY = Settings.currentScreenHeight;
		/// Minimap:
		miniMapArea.x = Settings.currentScreenWidth - margin - (2 * miniMapBorder) - miniMap.getWidth();
		miniMapArea.y = margin;
		miniMapArea.endX = miniMapArea.x + (2 * miniMapBorder) + miniMap.getWidth();
		miniMapArea.endY = miniMapArea.y + (2 * miniMapBorder) + miniMap.getHeight();
		miniMap.x = miniMapArea.x + miniMapBorder;
		miniMap.y = miniMapArea.y + miniMapBorder;
		miniMap.filename = Filenames.miniMap[Settings.mapSelected];
		/// Map: (for placeholder display purposes)
		map.x = mapArea.x;
		map.y = mapArea.y;
		/// Healthbar:
		healthBarArea.x = Settings.currentScreenWidth - margin - healthBarWidth;
		healthBarArea.y = miniMapArea.endY + 1;
		healthBarArea.endX = Settings.currentScreenWidth - margin;
		healthBarArea.endY = Settings.currentScreenHeight - margin;
		/// Powerups:
		powerupTotalArea.x = margin;
		powerupTotalArea.y = margin;
		powerupTotalArea.endX = miniMapArea.x - margin;
		powerupTotalArea.endY = margin + (2 * powerupBorder) + powerupHeight;
		powerupWidth = (powerupTotalArea.getWidth() - ((powerupArea.length + 1) * powerupBorder)) / powerupArea.length;
		powerupArea[0].x = powerupTotalArea.x + powerupBorder;
		powerupArea[0].y = powerupTotalArea.y + powerupBorder;
		powerupArea[0].endX = powerupArea[0].x + powerupWidth;
		powerupArea[0].endY = powerupArea[0].y + powerupHeight;
		powerupStrings[0].x = powerupArea[0].centerStringX(mainFont, powerupStrings[0].string);
		powerupStrings[0].y = powerupArea[0].y + (2 * powerupPadding) + mainFont.getHeight();
		for(i = 1; i < powerupArea.length; i++)
			{
			powerupArea[i].x = powerupArea[i - 1].endX + powerupBorder + 1;
			powerupArea[i].y = powerupArea[0].y;
			powerupArea[i].endX = powerupArea[i].x + powerupWidth;
			powerupArea[i].endY = powerupArea[0].endY;
			}
		powerupArea[powerupArea.length - 1].endX = powerupTotalArea.endX - powerupBorder;
		for(i = 0; i < powerupStrings.length; i++)
			{
			powerupStrings[i].x = powerupArea[i].centerStringX(mainFont, powerupStrings[i].string);
			powerupStrings[i].y = powerupStrings[0].y;
			}
		for(i = 0; i < powerupIcon.length; i++)
			{
			powerupIcon[i].x = powerupArea[i].centerImageX(powerupIcon[i].getWidth());
			powerupIcon[i].y = powerupStrings[i].getEndY() + powerupPadding;
			}
		/// Time:
		time.x = powerupArea[powerupArea.length - 1].centerStringX(timeFont, "00:00:00");
		time.y = powerupArea[powerupArea.length - 1].endY - powerupPadding - mainFont.getHeight();
		/// Score, power, speed:
		score.x = powerupArea[powerupArea.length - 1].centerStringX(scoreFont, "12345");
		score.y = powerupArea[powerupArea.length - 1].y + powerupPadding;
		power.x = powerupArea[powerupArea.length - 1].centerStringX(mainFont, "12/12");
		power.y = score.getEndY() + powerupPadding;
		speed.x = powerupArea[powerupArea.length - 1].centerStringX(mainFont, "12/12");
		speed.y = power.getEndY() + powerupPadding;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		/// Border & maps:
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Settings.currentScreenWidth, Settings.currentScreenHeight);
		map.renderImage();
		bottomMargin.colorSection(g, backgroundColor);
		rightMargin.colorSection(g, backgroundColor);
		miniMapArea.colorSection(g, miniMapColor);
		miniMap.renderImage();
		/// Healthbar:
		healthBarArea.colorSection(g, healthBarColor);
		setHealthStartY();
		g.setColor(healthColor);
		g.fillRect(healthBarArea.x, healthStartY, healthBarWidth + 1, healthBarArea.endY - healthStartY + 1);
		/// Powerups:
		powerupTotalArea.colorSection(g, powerupBackgroundColor);
		for(i = 0; i < powerupArea.length; i++)
			powerupArea[i].colorSection(g, powerupColor);
		for(i = 0; i < powerupStrings.length; i++)
			{
			mainFont.drawString(powerupArea[i].centerStringX(mainFont, Integer.toString(i + 1)), powerupArea[i].y + powerupPadding, Integer.toString(i + 1), mainColor);
			powerupStrings[i].renderString();
			mainFont.drawString(powerupIcon[i].getEndX() + powerupPadding, powerupIcon[i].getEndY() - powerupPadding - mainFont.getHeight(), "x " + Integer.toString(Powerups.numPowerups[Settings.playerID][i]), mainColor);
			if(Powerups.powerupType[i] == C.TIMED)
				mainFont.drawString(powerupArea[i].centerStringX(mainFont, Integer.toString(Powerups.timePowerup[Settings.playerID][i])), powerupArea[i].endY - powerupPadding - mainFont.getHeight(), Integer.toString(Powerups.timePowerup[Settings.playerID][i]), mainColor);
			}
		for(i = 0; i < powerupIcon.length; i++)
			powerupIcon[i].renderImage();
		/// Time:
		timeFont.drawString(time.x, time.y, String.format("%02d:%02d:%02d", StatePlay.hours, StatePlay.minutes, StatePlay.seconds), timeColor);
		/// Score, power, speed:
		score.trueTypeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(scoreFont, Integer.toString(GameStats.score[Settings.playerID])), score.y, Integer.toString(GameStats.score[Settings.playerID]), score.color);
		power.trueTypeFont.drawString(power.x, power.y, "P: " + Integer.toString(GameStats.power[Settings.playerID]) + "/" + Integer.toString(GameStats.maxPower), power.color);
		speed.trueTypeFont.drawString(speed.x, speed.y, "S: " + Integer.toString(GameStats.speed[Settings.playerID]) + "/" + Integer.toString(GameStats.maxSpeed), speed.color);
		if(GameStats.gameOver == C.YES)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.colors[GameStats.winningTeam] + Strings.wins, C.CENTER, C.CENTER, 10, Fonts.fontCourier20BTTF, Color.black, Color.white);
			}
		else if(StatePlay.gamePaused == C.YES)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.gamePaused, C.CENTER, C.CENTER, 10, Fonts.fontCourier15BTTF, Color.black, Color.white);
			}
		else if(GameStats.health[Settings.playerID] <= 0)
			{
			DisplaysMessagePopup.renderMessage(g, Strings.gameOver, C.CENTER, C.CENTER, 10, Fonts.fontCourier20BTTF, Color.black, Color.white);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setHealthStartY()
		{
		double r = 1.0 - ((double) GameStats.health[Settings.playerID] / (double) GameStats.maxHealth[Settings.playerID]);
		healthStartY = healthBarArea.y + (int) (healthBarArea.getHeight() * r);
//		System.out.printf("healthMax = %d, health = %d; healthbar = (%d,%d) to (%d,%d) h=%d; r = %f, start = %d, end = %d\n", GameStats.maxHealth[Settings.playerID], GameStats.health[Settings.playerID], healthBarArea.x, healthBarArea.y, healthBarArea.endX, healthBarArea.endY, healthBarArea.getHeight(), r, healthStartY, healthBarArea.getHeight() - healthStartY);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
