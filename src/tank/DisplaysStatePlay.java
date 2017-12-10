package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import javax.swing.plaf.nimbus.State;
import java.util.Set;

/* This class is for displaying all the displayables in the state. A state will call the positionDisplays
 * method during the enter method of the state. This will recalculate the position of the displays based
 * on the current screen size. The state will call the renderDisplays method during in the render method.*/
public class DisplaysStatePlay
	{
	static int i;
	public static int healthStartY = 0;
	public static int numMessages = 0;
	/// Spacings:
	public static int margin = 10;
	public static int miniMapBorder = 5;
	public static int healthBarWidth = 15;
	public static int powerupBorder = 1;
	public static int powerupWidth;
	public static int powerupHeight = 100;
	public static int powerupPadding = 2;
	public static int messageAreaHeight = 200;
	public static int messageAreaMargin = 20;
	public static int messageAreaPadding = 10;
	public static int pausePopupY = 400;
	public static int messageY = pausePopupY + 100;
	public static int highScorePadding = 20;
	/// psw (percentage of screen width):
	public static float pswMiniMap = .2f;
	public static float pswMap = 1.2f;
	public static float pswIcon = .045f;
	/// Fonts:
	public static TrueTypeFont mainFont = Fonts.fontCourier11BTTF;
	public static TrueTypeFont timeFont = Fonts.fontCourier10BTTF;
	public static TrueTypeFont scoreFont = Fonts.fontCourier13BTTF;
	public static TrueTypeFont messageTextFont = Fonts.fontCourier11BTTF;
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
	public static Color messageBackgroundColor = Color.black;
	public static Color messageTextColor = Color.white;
	/// Areas:
	public static Area messageArea = new Area();
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
    public static StringsDisplay winCondition = new StringsDisplay("", scoreFont, scoreColor, 0, 0);

	/// Tiled Map:
	public static Camera camera;

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
		/// Message area:
		messageArea.x = messageAreaMargin;
		messageArea.endX = Settings.currentScreenWidth - messageAreaMargin;
		messageArea.y = messageY;
		messageArea.endY = messageArea.y + messageAreaHeight;
		///
		winCondition.string = Strings.winConditionTypes[Settings.winCondition];
		winCondition.x = powerupArea[powerupArea.length - 1].centerStringX(scoreFont, winCondition.string);
		winCondition.y = speed.getEndY() + powerupPadding;
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderDisplays(Graphics g)
		{
		/// Border & maps:
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Settings.currentScreenWidth, Settings.currentScreenHeight);
		bottomMargin.colorSection(g, backgroundColor);

		/// Begin World Rendering:
		g.translate(camera.xPos + camera.pixelOffsetX, camera.yPos + camera.pixelOffsetY);
		g.setClip(camera.viewport);

		camera.render(g);

		/// Tanks:
		for(int i=0;i<Settings.numberActivePlayers;i++) {
			StatePlay.tanks[i].render(g);
			StatePlay.tanks[i].getTurret().render(g);
		}


		/// Powerups:
		if(Powerups.powerupFlag ==true) {
			//render power up at location
			//g.drawImage(ResourceManager.getImage(Filenames.powerupIcons[powerupIndex]).getScaledCopy(.35f), powerx, powery);
			StatePlay.powerupEntity.render(g);
			//powerupEntity.
		}

		//g.translate(-camera.pixelOffsetX, -camera.pixelOffsetY);
		g.clearClip();
		g.resetTransform();
		/// End World Rendering

		if(Tank.DEBUG)
			{
			g.drawString("Debug: " + Tank.DEBUG, 10, 140);
			g.drawString("MouseX: " + (Inputs.xMouse[Settings.playerID] - camera.xPos - camera.pixelOffsetX), 10, 160);
			g.drawString("MouseY: " + (Inputs.yMouse[Settings.playerID] - camera.yPos - camera.pixelOffsetY), 10, 180);

			g.drawString("TankX: " + StatePlay.tanks[Settings.playerID].getX(), 10, 200);
			g.drawString("TankY: " + StatePlay.tanks[Settings.playerID].getY(), 10, 220);

			g.drawString("pixelOffsetX: " + (camera.xPos + (float) camera.pixelOffsetX), 10, 240);
			g.drawString("pixelOffsetY: " + (camera.yPos + (float) camera.pixelOffsetY), 10, 260);
			g.drawString("worldWidth: " + camera.worldWitdth, 10, 280);
			g.drawString("worldHeight: " + camera.worldHeight, 10, 300);
			g.drawString("Edge: " + StatePlay.tanks[Settings.playerID].collideWorldEdge(), 10, 320);
			}

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
		if(Settings.winCondition == C.HIGH_SCORE)
			{
			timeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(timeFont, Strings.gameOver + ":"), time.y - (2 * timeFont.getHeight()), Strings.gameOver + ":", timeColor);
			timeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(timeFont, Integer.toString(StatePlay.highScoreTimer)), time.y - timeFont.getHeight(), Integer.toString(StatePlay.highScoreTimer), timeColor);
			}
		/// Score, power, speed:
		score.trueTypeFont.drawString(powerupArea[powerupArea.length - 1].centerStringX(scoreFont, Integer.toString(GameStats.score[Settings.playerID])), score.y, Integer.toString(GameStats.score[Settings.playerID]), score.color);
		power.trueTypeFont.drawString(power.x, power.y, "P: " + Integer.toString(GameStats.power[Settings.playerID]) + "/" + Integer.toString(GameStats.maxPower), power.color);
		speed.trueTypeFont.drawString(speed.x, speed.y, "S: " + Integer.toString(GameStats.speed[Settings.playerID]) + "/" + Integer.toString(GameStats.maxSpeed), speed.color);
		/// Win condition
		winCondition.renderString();
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
