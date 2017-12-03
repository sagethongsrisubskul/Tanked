package tank;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;

public class turretentity extends Entity{

	private tankentity parent;
	
	public turretentity(final float x, final float y,final char team, tankentity parent){
		super(x,y);
		
		this.parent=parent;
		switch(team){
			case 'r': addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_r).getScaledCopy(0.05f));
			break;
			
			case 'g': addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_g).getScaledCopy(0.05f));
			break;
			
			case 'b': addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_b).getScaledCopy(0.05f));
			break;
			
			case 'y': addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_y).getScaledCopy(0.05f));
			break;
		}
		
	}
	
	public void update(final int delta){
		this.setPosition(parent.getPosition());
		this.setRotation(parent.getTurretAngle());
	}
	
}
