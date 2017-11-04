package tank;
/* This class is for controling the current/previous state and the state que (used for back button) */
public class StateControl
	{
	public static final int STATE_NONE = -1;
	public static final int STATE_MAIN = 0;
	public static final int STATE_SETUP_GAME = 1;
	public static final int STATE_SELECT_MAP = 2;
	public static final int STATE_HELP_MAIN = 3;
	public static final int STATE_HELP_GAMEPLAY = 4;
	public static final int STATE_HELP_CONTROLS = 5;
	public static final int STATE_PLAY = 6;
	public static final int STATE_CHANGE_SCREEN_SIZE = 7;
	public static final int NUM_STATES = 8;
	/// States:
	public static int previousState = STATE_MAIN;
	public static int currentState = STATE_MAIN;
	public static int statesVisited[] = new int[NUM_STATES + 1];
	public static int numStatesQue = 0;
	/*-----------------------------------------------------------------------------------------------------*/
	public static void checkStates()
		{
		int i;
		int pop = C.NO;
		numStatesQue = 0;
		for(i = 0; i < statesVisited.length; i++)
			{
			if(pop == C.YES)
				{
				statesVisited[i] = STATE_NONE;
				}
			else if(statesVisited[i] == currentState)
				{
				pop = C.YES;
				}
			if(statesVisited[i] != STATE_NONE) numStatesQue++;
			}
		}
	/*-----------------------------------------------------------------------------------------------------*/
	public static void addCurrentState(int state)
		{
		currentState = state;
		if(currentState == STATE_MAIN)
			{
			int i;
			statesVisited[0] = STATE_MAIN;
			for(i = 1; i < statesVisited.length; i++)
				{
				statesVisited[i] = STATE_NONE;
				}
			numStatesQue = 1;
			}
		else
			{
			statesVisited[numStatesQue] = currentState;
			numStatesQue++;
			checkStates();
			}
		}
	}
