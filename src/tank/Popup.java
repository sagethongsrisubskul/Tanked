package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
public class Popup
	{
	public static int i;
	public static int popupDisplayed = C.NO;
	public static int charactersEntered = 0;
	public static char[] ipAddressEntered = new char[12];
	/// Margins:
	public static int outerBorderWidth = 2;
	public static int outerFillWidth = 10;
	public static int innerBorderWidth = 1;
	public static int innerFillWidth = 400; /// Multiple of 5
	public static int innerFillHeight = 150;
	public static int enterBorderWidth = 1;
	public static int enterWidth = 125;
	public static int enterHeight = 30;
	public static int buttonWidth;
	public static int buttonHeight;
	public static int buttonBorderWidth = 1;
	/// Fonts:
	public static TrueTypeFont enterAmountFont = Fonts.fontVerdana14TTF;
	public static TrueTypeFont buttonFont = Fonts.fontVerdana14TTF;
	public static TrueTypeFont enterDigitFont = Fonts.fontCourier15BTTF;
	/// Colors:
	public static Color borderColor = Color.black;
	public static Color outerColor = Color.black;
	public static Color innerColor = Color.lightGray;
	public static Color enterColor = Color.white;
	public static Color buttonColor = Color.darkGray;
	public static Color textColor = Color.black;
	/// Areas:
	public static Area outerBorder = new Area();
	public static Area outerFill = new Area();
	public static Area innerBorder = new Area();
	public static Area innerFill = new Area();
	public static Area enterBorder = new Area();
	public static Area enterFill = new Area();
	public static Area buttonEsc = new Area();
	public static Area buttonClear = new Area();
	public static Area buttonBack = new Area();
	public static Area buttonEnter = new Area();
	public static int popupWidth;
	public static int popupHeight;
	/// Objects:
	public static StringsDisplay enterAmount = new StringsDisplay(Strings.enterIPAddress, enterAmountFont, textColor, 0, 0);
	public static StringsDisplay buttonEscText = new StringsDisplay(Strings.esc, buttonFont, textColor, 0, 0);
	//	public static StringsDisplay buttonMaxText = new StringsDisplay(Strings.max, buttonFont, textColor, 0, 0);
	public static StringsDisplay buttonClearText = new StringsDisplay(Strings.clear, buttonFont, textColor, 0, 0);
	public static StringsDisplay buttonBackText = new StringsDisplay(Strings.back, buttonFont, textColor, 0, 0);
	public static StringsDisplay buttonEnterText = new StringsDisplay(Strings.enter, buttonFont, textColor, 0, 0);
	/*-----------------------------------------------------------------------------------------------------*/
	public static void positionPopup()
		{
		popupWidth = (2 * outerBorderWidth) + (2 * outerFillWidth) + (2 * innerBorderWidth) + innerFillWidth;
		popupHeight = (2 * outerBorderWidth) + (2 * outerFillWidth) + (2 * innerBorderWidth) + innerFillHeight;
		outerBorder.x = (Settings.currentScreenWidth - popupWidth) / 2;
		outerBorder.y = (Settings.currentScreenHeight - popupHeight) / 2;
		outerBorder.endX = outerBorder.x + popupWidth;
		outerBorder.endY = outerBorder.y + popupHeight;
		outerFill.x = outerBorder.x + outerBorderWidth;
		outerFill.y = outerBorder.y + outerBorderWidth;
		outerFill.endX = outerBorder.endX - outerBorderWidth;
		outerFill.endY = outerBorder.endY - outerBorderWidth;
		innerBorder.x = outerFill.x + outerFillWidth;
		innerBorder.y = outerFill.y + outerFillWidth;
		innerBorder.endX = outerFill.endX - outerFillWidth;
		innerBorder.endY = outerFill.endY - outerFillWidth;
		innerFill.x = innerBorder.x + innerBorderWidth;
		innerFill.y = innerBorder.y + innerBorderWidth;
		innerFill.endX = innerBorder.endX - innerBorderWidth;
		innerFill.endY = innerBorder.endY - innerBorderWidth;
		enterBorder.x = innerFill.x + (innerFill.getWidth() - ((2 * enterBorderWidth) + enterWidth)) / 2;
		enterBorder.y = innerFill.y + (innerFill.getHeight() - ((2 * enterBorderWidth) + enterHeight + buttonHeight)) / 2;
		enterBorder.endX = enterBorder.x + (2 * enterBorderWidth) + enterWidth;
		enterBorder.endY = enterBorder.y + (2 * enterBorderWidth) + enterHeight;
		enterFill.x = enterBorder.x + enterBorderWidth;
		enterFill.y = enterBorder.y + enterBorderWidth;
		enterFill.endX = enterBorder.endX - enterBorderWidth;
		enterFill.endY = enterBorder.endY - enterBorderWidth;
		buttonWidth = innerFill.getWidth() / 4;
		buttonHeight = ((innerFillHeight - enterBorder.getHeight()) / 2) / 2;
		buttonEsc.x = innerFill.x;
		buttonEsc.y = innerFill.endY - buttonHeight;
		buttonEsc.endX = buttonEsc.x + buttonWidth;
		buttonEsc.endY = buttonEsc.y + buttonHeight;
		buttonClear.x = buttonEsc.x + buttonWidth;
		buttonClear.y = buttonEsc.y;
		buttonClear.endX = buttonClear.x + buttonWidth;
		buttonClear.endY = buttonEsc.endY;
		buttonBack.x = buttonClear.x + buttonWidth;
		buttonBack.y = buttonEsc.y;
		buttonBack.endX = buttonBack.x + buttonWidth;
		buttonBack.endY = buttonEsc.endY;
		buttonEnter.x = buttonBack.x + buttonWidth;
		buttonEnter.y = buttonEsc.y;
		buttonEnter.endX = buttonEnter.x + buttonWidth;
		buttonEnter.endY = buttonEsc.endY;
		enterAmount.x = enterAmount.centerStringX(innerFill);
		enterAmount.y = innerFill.y + 5;
		buttonEscText.x = buttonEscText.centerStringX(buttonEsc);
		buttonEscText.y = buttonEscText.centerStringY(buttonEsc);
		buttonClearText.x = buttonClearText.centerStringX(buttonClear);
		buttonClearText.y = buttonClearText.centerStringY(buttonClear);
		buttonBackText.x = buttonBackText.centerStringX(buttonBack);
		buttonBackText.y = buttonBackText.centerStringY(buttonBack);
		buttonEnterText.x = buttonEnterText.centerStringX(buttonEnter);
		buttonEnterText.y = buttonEnterText.centerStringY(buttonEnter);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void renderPopup(Graphics g)
		{
		/// Areas:
		outerBorder.colorSection(g, borderColor);
		outerFill.colorSection(g, outerColor);
		innerBorder.colorSection(g, borderColor);
		innerFill.colorSection(g, innerColor);
		enterBorder.colorSection(g, borderColor);
		enterFill.colorSection(g, enterColor);
		/// Buttons:
		buttonEsc.colorSection(g, borderColor);
		g.setColor(buttonColor);
		g.fillRect(buttonEsc.x + buttonBorderWidth, buttonEsc.y + buttonBorderWidth, buttonEsc.getWidth() - (2 * buttonBorderWidth), buttonEsc.getHeight() - (2 * buttonBorderWidth));
//		buttonMax.colorSection(g, borderColor);
//		g.setColor(buttonColor);
//		g.fillRect(buttonMax.x + buttonBorderWidth, buttonMax.y + buttonBorderWidth, buttonMax.getWidth() - (2 * buttonBorderWidth), buttonMax.getHeight() - (2 * buttonBorderWidth));
		buttonClear.colorSection(g, borderColor);
		g.setColor(buttonColor);
		g.fillRect(buttonClear.x + buttonBorderWidth, buttonClear.y + buttonBorderWidth, buttonClear.getWidth() - (2 * buttonBorderWidth), buttonClear.getHeight() - (2 * buttonBorderWidth));
		buttonBack.colorSection(g, borderColor);
		g.setColor(buttonColor);
		g.fillRect(buttonBack.x + buttonBorderWidth, buttonBack.y + buttonBorderWidth, buttonBack.getWidth() - (2 * buttonBorderWidth), buttonBack.getHeight() - (2 * buttonBorderWidth));
		buttonEnter.colorSection(g, borderColor);
		g.setColor(buttonColor);
		g.fillRect(buttonEnter.x + buttonBorderWidth, buttonEnter.y + buttonBorderWidth, buttonEnter.getWidth() - (2 * buttonBorderWidth), buttonEnter.getHeight() - (2 * buttonBorderWidth));
		enterAmountFont.drawString(enterAmount.x, enterAmount.y, enterAmount.string, enterAmount.color);
		/// Button text:
		buttonFont.drawString(buttonEscText.x, buttonEscText.y, buttonEscText.string, buttonEscText.color);
		buttonFont.drawString(buttonClearText.x, buttonClearText.y, buttonClearText.string, buttonClearText.color);
		buttonFont.drawString(buttonBackText.x, buttonBackText.y, buttonBackText.string, buttonBackText.color);
		buttonFont.drawString(buttonEnterText.x, buttonEnterText.y, buttonEnterText.string, buttonEnterText.color);
		/// Render digits:
		for(i=0;i<ipAddressEntered.length;i++)
			enterDigitFont.drawString(enterFill.x + (i * enterDigitFont.getWidth("0")) + 5, enterFill.y + 5, String.format("%c", ipAddressEntered[i]), textColor);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void getPopupInput(Input input)
		{
		if(charactersEntered < ipAddressEntered.length)
			{
			char c = ' ';
			if(input.isKeyPressed(Input.KEY_0)) c = '0';
			else if(input.isKeyPressed(Input.KEY_1)) c = '1';
			else if(input.isKeyPressed(Input.KEY_2)) c = '2';
			else if(input.isKeyPressed(Input.KEY_3)) c = '3';
			else if(input.isKeyPressed(Input.KEY_4)) c = '4';
			else if(input.isKeyPressed(Input.KEY_5)) c = '5';
			else if(input.isKeyPressed(Input.KEY_6)) c = '6';
			else if(input.isKeyPressed(Input.KEY_7)) c = '7';
			else if(input.isKeyPressed(Input.KEY_8)) c = '8';
			else if(input.isKeyPressed(Input.KEY_9)) c = '9';
			else if(input.isKeyPressed(Input.KEY_PERIOD)) c = '.';

			if(c != ' ')
				{
				ipAddressEntered[charactersEntered] = c;
				charactersEntered++;
				}
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void initPopup()
		{
		popupDisplayed = C.YES;
		clearEntered();
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void clearEntered()
		{
		int i;
		charactersEntered = 0;
		for(i = 0; i < ipAddressEntered.length; i++)
			ipAddressEntered[i] = ' ';
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void clearLastCharacter()
		{
		if(charactersEntered > 0)
			{
			ipAddressEntered[charactersEntered - 1] = ' ';
			charactersEntered--;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void enterIPAddress()
		{
		int i;
		String string = "";
		popupDisplayed = C.NO;
		Settings.playerType = C.CLIENT;
		for(i=0;i<charactersEntered;i++)
			string += ipAddressEntered[i];
		Network.isClient(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
