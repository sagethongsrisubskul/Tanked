package tank;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class tankentity extends Entity {
	
	private Vector velocity;
	
	private Vector worldpos;
	private float hullangle;
	private float turretangle;
	
	public tankentity(final float x, final float y){
		super(x,y);
		
		addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_r).getScaledCopy(0.1f));
		
	}

	public void update(final int delta){
		translate(velocity.scale(delta));
	}
	
}
