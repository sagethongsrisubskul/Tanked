package tank;

/* This class holds all the strings in the game */
public class Strings
	{
	/*-----------------------------------------------------------------------------------------------------*/
	/// Main State:
	public static final String gameTitle = "Tank";
	public static final String authors[] = {"Samuel Riesterer", "Benjamin DeCamp", "Sage Thongsrisubskul", "Tyler Coy"};
	public static final String hostGame = "Host Game";
	public static final String exitHost = "Exit Host";
	public static final String joinGame = "Join Game";
	public static final String exitJoin = "Exit Join";
	public static final String defaultName = "Player";
	public static final String selectMap = "Select Map";
	public static final String networkMessages[] = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
	public static final String networkMessagePrompt = ">>";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Lobby State:
	public static final String lobbyHeading = gameTitle + " Lobby";
	public static final String players = "Players";
	public static final String id = "ID";
	public static final String name = "Name";
	public static final String team = "Team";
	public static final String joined = "Joined";
	public static final String map = "Map";
	public static final String check = "X";
	public static final String helpInfo = "Help/Info";
	public static final String leaveGame = "Leave Game";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Setup Win Conditions State:
	public static final String winCondition = "Win Condition";
	public static final String deathmatch = "Deathmatch";
	public static final String protectBase = "Protect Base";
	public static final String destruction = "Destruction";
	public static final String findRelic = "Find Relic";
	public static final String winConditionTypes[] = {deathmatch, protectBase, destruction, findRelic};
	/*-----------------------------------------------------------------------------------------------------*/
	/// Popup:
	public static final String enterName = "Enter Name:";
	public static final String enterIPAddress = "Enter IP Address:";
	public static final String enterMessage = "Enter message:";
	public static final String esc = "Esc";
	public static final String max = "Max";
	public static final String clear = "Clear";
	public static final String back = "Back";
	public static final String enter = "Enter";
	/*-----------------------------------------------------------------------------------------------------*/
	/// Select Map State:
	public static final String miniMap[] = {
		"This is a description of map 1.\nThis is a description of map 1.\nThis is a description of map 1.\nThis is a description of map 1.",
		"This is a description of map 2.\nThis is a description of map 2.\nThis is a description of map 2.\nThis is a description of map 2.",
		"This is a description of map 3.\nThis is a description of map 3.\nThis is a description of map 3.\nThis is a description of map 3.",
	};
	/*-----------------------------------------------------------------------------------------------------*/
	/// Help State:
	public static final String gameControls = "Game Controls";
	public static final String gameplay = "Gameplay";
	public static final String gameControlsText = String.format("" +

		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"Help info game controls text. Help info game controls text. \nHelp info game controls text. Help info game controls text. Help info game controls text. " +
		"");
	public static final String gamePlayText = String.format("" +

		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"Help info gameplay text. Help info gameplay text. \nHelp info gameplay text. Help info gameplay text. Help info gameplay text. " +
		"");
	/*-----------------------------------------------------------------------------------------------------*/
	/// Keyboard shortcuts:

	/*-----------------------------------------------------------------------------------------------------*/
	public static String[] formatString(String string, int screenWidth, int screenHeight)
		{
		/// Given a string and a screen size, will breakup the string into lines and pages and put the pages
		/// into an array, all based on the screen size and the size of the font
		int currentPos;
		char c;
		int needToContinue = C.NO;
		int pagesCurrent = 0;
		int linesPerPage = screenHeight / 20;
		int charPerLine = screenWidth / 9;
		int linesLeft = linesPerPage;
		int charLeft = charPerLine;
		int firstLetterPos = 0;
		String stringArray[] = new String[20];
		StringBuilder sb = new StringBuilder("");
//		System.out.printf("charPerLine = %d; linesPerPage = %d\n", charPerLine, linesPerPage);
//		System.out.print("\n\n****************************************\n");
		for(currentPos = 0; currentPos < string.length(); currentPos++)
			{
			c = string.charAt(currentPos);
			/// Beginning of new line so record first letter position of line:
			if(charLeft == charPerLine)
				{
//				System.out.printf("*** First letter pos = %d\n\n", currentPos);
//				System.out.print(c);
				firstLetterPos = currentPos;
				}
			/// Newline character is at the beginning of the page so disregard it:
			if(linesLeft == linesPerPage && firstLetterPos == currentPos && c == '\n')
				{
//				System.out.print("*** Newline at beginning of page\n");
//				System.out.print(c);
				continue;
				}
			if(c != '\n' && c != '~')
				{
//				System.out.print(c);
				charLeft--;
				}
			/// No more characters can fit in the current line so append string and increment line:
			if(charLeft == 0)
				{
				for(; currentPos > firstLetterPos; currentPos--) /// Move backwards until a space is encountered
					{
//					System.out.printf("*** CurrentPos = %d\n\n", currentPos);
					c = string.charAt(currentPos);
					if(c == ' ')
						{
						sb.append(string.substring(firstLetterPos, currentPos)); /// Append from firstLetterPos to currentPos, thereby excluding the space
						sb.append('\n');
						linesLeft--;
//						System.out.printf("*** New line: Lines left = %d\n", linesLeft);
						charLeft = charPerLine;
//						needToContinue = C.YES;
						break;
						}
					}
				}
			/// Encountered a newline:
			if(c == '\n')
				{
//				System.out.printf("*** Encountered a newline character\n\n");
				sb.append(string.substring(firstLetterPos, currentPos + 1)); /// Append from firstLetterPos to currentPos + 1, which includes the newline
				linesLeft--;
//				System.out.printf("*** New lineC: Lines left = %d\n", linesLeft);
				charLeft = charPerLine;
				}
			/// Start a new page:
			if(linesLeft == 0 || currentPos == string.length() - 1)
				{
//				System.out.printf("\n\n*** New page: %d\n\n", pagesCurrent);
//				System.out.printf("%d: %socket", pagesCurrent, sb.toString());
				stringArray[pagesCurrent] = sb.toString(); /// Save the entire stringBuilder to array index
				sb = new StringBuilder(""); /// Start a new stringBuilder
				pagesCurrent++;
				charLeft = charPerLine;
				linesLeft = linesPerPage;
				continue;
				}
			/// ~# is a marker in string where # is a number to signify the next number of lines need to stay together.
			if(c == '~')
				{
//				System.out.printf("*** Found ~:\n\n");
				if(Character.getNumericValue(string.charAt(currentPos + 1)) > linesLeft) /// If there is not room in the page to print the next number of lines
					{
//					System.out.printf("%d: %socket", pagesCurrent, sb.toString());
					stringArray[pagesCurrent] = sb.toString(); /// Save the entire stringBuilder to array index
					sb = new StringBuilder(""); /// Start a new stringBuilder
					currentPos++; /// Skip over the number after the '~' marker
					pagesCurrent++;
					charLeft = charPerLine;
					linesLeft = linesPerPage;
					continue;
					}
				else /// There is room to print the next number of liens together
					{
					currentPos++; /// Skip over the number after the '~' marker
					continue;
					}
				}
			}
		String returnArray[] = new String[pagesCurrent];
//		System.out.print("\n********************************************\n");
		for(int i = 0; i < pagesCurrent; i++)
			{
			returnArray[i] = stringArray[i];
//			System.out.printf("\nPage %d:\n\n%socket", i, returnArray[i]);
			}
		return returnArray;
		}
	}
