package tank;
import jig.Entity;
import jig.Vector;

public class projectile extends Entity{
	private Vector velocity;
	private Vector acceleration;
	private Vector worldpos;
	private double angle;

	//x,y=coordinates, r=angle,mv=muzzle velocity, l=lifetime
	public projectile(final float x, final float y, final double r, final float mv, final int l){
		super(x,y);
		
		angle=(180f/Math.PI)*Math.atan2(x,-y);
		
		velocity=new Vector(mv*(float)Math.sin(angle*(Math.PI/180.0f)),-mv*(float)Math.cos(angle*(Math.PI/180.0f)));
		
	}
	
	public void update(final int delta,int i){
		translate(velocity.scale(delta*0.1f));
	}
	
}
