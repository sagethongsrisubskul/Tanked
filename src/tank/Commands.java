package tank;
public class Commands
	{
	/*-----------------------------------------------------------------------------------------------------*/
	/* Upon receiving a command/message via the socket, this method is called. Both server and client will call this method
	*  There is no distinction */
	public static void processCommand(String string)
		{
		int n;
		System.out.printf("processCommand: %s\n", string);
		if(string.charAt(0) == '~') /// String is a command
			{
			/// Player joins game:
			if(string.charAt(1) == 'P' && string.charAt(2) == 'J')
				{
				n = Character.getNumericValue(string.charAt(3));
				Settings.numberActivePlayers = n + 1;
				if(Settings.playerType == C.UNDECIDED)
					Settings.playerType = C.CLIENT;
				if(Settings.playerID == -1)
					Settings.playerID = n;
				if(StateControl.currentState != StateControl.STATE_LOBBY)
					{
					StateControl.enterState(StateControl.STATE_LOBBY);
					}
				NetworkControl.displayMessage(Settings.playerName[n] + " has joined the game");
				}
			/// Name change:
			else if(string.charAt(1) == 'N' && string.charAt(2) == 'C')
				{
				Settings.playerName[Character.getNumericValue(string.charAt(3))] = string.substring(4, string.length());
				}
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'N')
				{
				setNames(string.substring(4,string.length()));
				}
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'C')
				{
				setColors(string.substring(3,string.length()));
				}
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'W')
				{
				Settings.winCondition = Character.getNumericValue(string.charAt(3));
				}
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'M')
				{
				Settings.mapSelected = Character.getNumericValue(string.charAt(3));
				}
			}
		else /// String is a chat message
			{
			NetworkControl.displayMessage(string);
			}

		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setColors(String string)
		{
		int i;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			Settings.playerTeamColors[i] = Character.getNumericValue(string.charAt(i));
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setNames(String string)
		{
		System.out.printf("setNames: %s\n", string);
		int names = 0; /// Number of names
		int characters = 0; /// Number of characters in name
		int current = 0; /// Current index position
		int start = 0; /// Starting index of name
		int end = 0; /// Ending index of name
		while(true)
			{
			characters = Character.getNumericValue(string.charAt(current));
			start = current + 1;
			end = start + characters;
//			System.out.printf("char = %d, start = %d, end = %d, name = %s\n", characters, start, end, string.substring(start, end));
			Settings.playerName[names] = string.substring(start, end);
			names++;
			current = end;
			if(string.charAt(current) == '~')
				break;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setPlayerJoinsForAll()
		{
		NetworkControl.sendToAll("~PJ" + Settings.numberActivePlayers);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setNameChangeForAll(String string)
		{
		NetworkControl.sendToAll("~NC" + string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setNamesForAll()
		{
		int i;
		String string = "~";
		string += "SN";
		string += (Settings.numberActivePlayers + 1);
		for(i = 0; i < Settings.numberActivePlayers; i++)
			{
			string += Settings.playerName[i].length();
			string += Settings.playerName[i];
			}
		string += "~";
		NetworkControl.sendToAll(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setPlayerColorsForAll()
		{
		int i;
		String temp = "~SC";
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			temp += Settings.playerTeamColors[i];
			}
		NetworkControl.sendToAll(temp);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setMapSelectionForAll()
		{
		NetworkControl.sendToAll("~SM" + Settings.mapSelected);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void setWinConditionsForAll()
		{
		NetworkControl.sendToAll("~SW" + Settings.winCondition);
		}
	/*-----------------------------------------------------------------------------------------------------*/

	}
