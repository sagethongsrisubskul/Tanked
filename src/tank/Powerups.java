package tank;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class Powerups extends Entity{
	
	public Powerups(final float x, final float y) {
		super(x,y);
		addImageWithBoundingBox(ResourceManager.getImage(Filenames.powerupIcons[StatePlay.powerupindex]).getScaledCopy(.35f));
	}
	
	public static void powerstatus() {
		if(Settings.playerType==C.SERVER) {
			/*	
				Still to do:
				1.) Check when tank collides with powerx and powery. If so then set power on tank. Remove power from map.
				***DONE***2.) Rescale images of power ups on screen
				3.) need to scale the random numbers for each bounds of map ??map coords??
				4.) Change time for spawn
			*/
				
			//handle powerup timers
				int xcoord=0;
				int ycoord=0;
				int index=0;
				if((StatePlay.seconds==5) && StatePlay.powerupflag==false) {
					//spawn power ups on map
					//make rand coordinates
					xcoord=ThreadLocalRandom.current().nextInt(0, 300 + 1);
					ycoord=ThreadLocalRandom.current().nextInt(0, 300 + 1);
					index=ThreadLocalRandom.current().nextInt(0,5+1);
					//send to clients
					NetworkControl.sendToAll("~PT"+xcoord+","+ycoord+","+index);
					//powerupflag=true;
				}
					
				if((StatePlay.seconds==10) && StatePlay.powerupflag == true) {
					//delete power up on screen if not picked up
					//send to clients
					NetworkControl.sendToAll("~PF");
					//powerupflag=false;
				}
				//end of power up timers
			}
	}
	
	

}
