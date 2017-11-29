package tank;
public class Commands
	{
	/*-----------------------------------------------------------------------------------------------------*/
	/* Use this method to control all the commands in the game.
	 * To issue a command, call NetworkControl.sendToAll(String string). This will send the command string to every user.
	 * Every user will then call this method on the respective system.
	 * Format of a command: ~ x y ~
	 * (x = a 2-letter acronym of the command, y = any string of characters that further describe the command, the last ~ is optional)
	 * Upon receiving a command/message via the socket, this method is called to process the command.
	 * This method is called for both server and client, as all machines variables will need to be in sync.
	 * If you need a distinction between server and client, you need to include an if statement in the processing of the command.
	 * For multi-line command process, please include a separate method and call that method from here.
	 * See examples below.
	 * */
	public static void processCommand(String string)
		{
		int n;
		System.out.printf("processCommand: %s\n", string);
		if(string.charAt(0) == '~') /// String is a command
			{
			if(string.charAt(1) == 'P' && string.charAt(2) == 'J')
				playerJoins(string);
			else if(string.charAt(1) == 'N' && string.charAt(2) == 'C')
				Settings.playerName[Character.getNumericValue(string.charAt(3))] = string.substring(4, string.length());
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'N')
				setNames(string.substring(4, string.length()));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'C')
				setColors(string.substring(3, string.length()));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'W')
				Settings.winCondition = Character.getNumericValue(string.charAt(3));
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'M')
				Settings.mapSelected = Character.getNumericValue(string.charAt(3));
			else if(string.charAt(1) == 'G' && string.charAt(2) == 'P') Settings.numberActivePlayers = Character.getNumericValue(string.charAt(3));
			else if(string.charAt(1) == 'L' && string.charAt(2) == 'G') launchGame();
			else if(string.charAt(1) == 'S' && string.charAt(2) == 'E') NetworkControl.exitServer();
			else if(string.charAt(1) == 'C' && string.charAt(2) == 'E') NetworkControl.exitClient(Character.getNumericValue(string.charAt(3)));

			}
		else /// String is a chat message
			{
			NetworkControl.displayMessage(string);
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendClientExitsCommand(int clientID)
		{
		NetworkControl.sendToAll("~CE" + Integer.toString(clientID));
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendServerExitsCommand()
		{
		NetworkControl.sendToClients("~SE");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void playerJoins(String string)
		{
		int i;
		int activeIDs[] = new int[C.MAX_PLAYERS];
		int firstOpenID = -1;
		Settings.numberActivePlayers = 1;
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			activeIDs[i] = Character.getNumericValue(string.charAt(i + 3));
			if(activeIDs[i] == C.YES)
				Settings.numberActivePlayers++;
			}

//		Settings.numberActivePlayers = activeIDs + 1;
		if(Settings.playerType == C.UNDECIDED) Settings.playerType = C.CLIENT;
		for(i = 1; i < C.MAX_PLAYERS; i++)
			{
			if(activeIDs[i] == C.NO)
				{
				firstOpenID = i;
				Settings.activeIDs[i] = C.YES;
				if(Settings.playerID == C.NO_ID)
					Settings.playerID = i;
				break;
				}
			}
		Settings.activeIDs[0] = C.YES;
		if(StateControl.currentState != StateControl.STATE_LOBBY)
			{
			StateControl.enterState(StateControl.STATE_LOBBY);
			}
		NetworkControl.displayMessage(Settings.playerName[firstOpenID] + " has joined the game");
		System.out.printf("activePlayers = %d, active[0] = %d, active[1] = %d, active[2] = %d, active[3] = %d, Player type = %d, player ID = %d, player name = %s\n", Settings.numberActivePlayers, Settings.activeIDs[0], Settings.activeIDs[1], Settings.activeIDs[2], Settings.activeIDs[3], Settings.playerType, Settings.playerID, Settings.playerName[Settings.playerID]);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void launchGame()
		{
		StateControl.enterState(StateControl.STATE_PLAY);
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
			if(string.charAt(current) == '~') break;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendGetPlayersCommand(int numActivePlayers)
		{
		NetworkControl.sendToAll("~GP" + numActivePlayers);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendLaunchGameCommand()
		{
		NetworkControl.sendToAll("~LG");
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendPlayerJoinedCommand()
		{
		int i;
		String string = "~PJ";
		for(i = 0; i < C.MAX_PLAYERS; i++)
			{
			string += Settings.activeIDs[i];
			}
		NetworkControl.sendToAll(string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendNameChangeCommand(String string)
		{
		NetworkControl.sendToAll("~NC" + string);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendSetNamesCommand()
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
	public static void sendSetColorsCommand()
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
	public static void sendSetMapCommand()
		{
		NetworkControl.sendToAll("~SM" + Settings.mapSelected);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void sendSetWinConditionCommand()
		{
		NetworkControl.sendToAll("~SW" + Settings.winCondition);
		}
	/*-----------------------------------------------------------------------------------------------------*/
	}
