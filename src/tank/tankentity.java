package tank;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class tankentity extends Entity {
	
	private Vector velocity;
	
	public tankentity(final float x, final float y){
		super(x,y);
		
	}

	public void update(final int delta){
		translate(velocity.scale(delta));
	}
	
}
