package tank;
import jig.Entity;
import jig.Vector;

public class tankentity extends Entity {
	
	private Vector velocity;
	
	private Vector worldpos;
	private double hullangle;
	private double turretangle;
	private int movement;
	private int rotation;
	
	private turretentity turret;
	/*-----------------------------------------------------------------------------------------------------*/
	public tankentity(final float x, final float y,final char team){
		super(x,y);
		
		switch(team){
			case 'r':
				ResourceManager.loadImage(Filenames.tank_r);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_r).getScaledCopy(0.25f));
			break;
			
			case 'g':
				ResourceManager.loadImage(Filenames.tank_g);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_g).getScaledCopy(0.25f));
			break;
			
			case 'b':
				ResourceManager.loadImage(Filenames.tank_b);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_b).getScaledCopy(0.25f));
			break;
			
			case 'y':
				ResourceManager.loadImage(Filenames.tank_y);
				addImageWithBoundingBox(ResourceManager.getImage(Filenames.tank_y).getScaledCopy(0.25f));
			break;
		}
		
		turret=new turretentity(x,y,team,this);
		velocity=new Vector(0.0f,0.0f);
		hullangle=0;
		turretangle=0;
		//addImageWithBoundingBox(ResourceManager.getImage(Filenames.turret_r).getScaledCopy(0.05f));
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void update(final int delta){
		translate(velocity.scale(delta*0.1f));
		
		this.setRotation(this.getRotation()+rotation*1.2f);
		hullangle=this.getRotation();
		turret.update(delta);
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public double getTurretAngle(){
		return turretangle;
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void aimTurret(float x2,float y2){
		float x1=this.getX();
		float y1=this.getY();
		
		float x=x2-x1;
		float y=y2-y1;
		
		turretangle=(180f/Math.PI)*Math.atan2(x,-y);
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public void control(int movement, int rotation){
		this.movement=movement;
		this.rotation=rotation;
		
		//why aren't these working?
		//velocity.setX(movement*(float)Math.sin(rotation*(180/Math.PI)));
		//velocity.setY(movement*(float)Math.cos(rotation*(180/Math.PI)));
		velocity=new Vector(movement*(float)Math.sin(hullangle*(Math.PI/180.0f)),-movement*(float)Math.cos(hullangle*(Math.PI/180.0f)));
		
	}
	/*-----------------------------------------------------------------------------------------------------*/
	public turretentity getTurret(){
		return turret;
	}
}
