package tank;
import jig.Entity;
import jig.Vector;
public class projectile extends Entity
	{
	private Vector velocity;
	private Vector acceleration;
	private Vector worldpos;
	private double angle;
	public int lifetime;
	public int playerID = 0;
	//x,y=coordinates, r=angle,mv=muzzle velocity, l=lifetime
	public projectile(final float x, final float y, final double r, final float mv, final int l)
		{
		super(x, y);
		angle = r;
		if(mv != 0)
			{
			//is shot
			ResourceManager.loadImage(Filenames.shot);
			addImageWithBoundingBox(ResourceManager.getImage(Filenames.shot));
			lifetime = 5000;
			}
		else
			{
			//is mine
			ResourceManager.loadImage(Filenames.powerupIcons[1]);
			addImageWithBoundingBox(ResourceManager.getImage(Filenames.powerupIcons[1]).getScaledCopy(Powerups.iconScale));
			playerID = l;
			lifetime = 30000;
			}
		this.setRotation(angle);
		velocity = new Vector(mv * (float) Math.sin(angle * (Math.PI / 180.0f)), -mv * (float) Math.cos(angle * (Math.PI / 180.0f)));
		}
	public void update(final int delta)
		{
		translate(velocity.scale(delta * 0.1f));
		}
	}
