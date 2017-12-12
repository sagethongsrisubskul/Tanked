package tank;
import jig.Collision;
import jig.Entity;
import jig.Vector;

public class tankentity extends Entity {

	public enum Edge {Top, Bottom, Left, Right, TopRight, TopLeft, BottomLeft, BottomRight, None};
	
	private Vector velocity;
	
	private Vector worldpos;
	private double hullangle;
	private int angle=0;
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
	public void update(final int delta,int i){
		translate(velocity.scale(delta*(float)(.035 * GameStats.speed[Settings.playerID])));
		
		this.setRotation(this.getRotation()+(float)rotation*GameStats.speed[Settings.playerID]/2);
		hullangle=this.getRotation();
		angle=(int) this.getRotation();
		NetworkControl.sendToAll("~PH"+i+angle);
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
	public void control(int mov, int rot){
		this.movement=mov;
		this.rotation=rot;
		
		//why aren't these working?
		//velocity.setX(movement*(float)Math.sin(rotation*(180/Math.PI)));
		//velocity.setY(movement*(float)Math.cos(rotation*(180/Math.PI)));
		//velocity=new Vector(movement*(float)Math.sin(hullangle*(Math.PI/180.0f)),-movement*(float)Math.cos(hullangle*(Math.PI/180.0f)));

		float x = movement*(float)Math.sin(hullangle*(Math.PI/180.0f));
		float y = -movement*(float)Math.cos(hullangle*(Math.PI/180.0f));

		switch(collideWorldEdge())
		{
			case Top:
				if(y < 0)
					y = 0;
				break;
			case TopLeft:
				if(y < 0)
					y = 0;
				if(x < 0)
					x = 0;
				break;
			case TopRight:
				if(y < 0)
					y = 0;
				if(x > 0)
					x = 0;
				break;
			case Bottom:
				if(y > 0)
					y = 0;
				break;
			case BottomLeft:
				if(y > 0)
					y = 0;
				if(x < 0)
					x = 0;
				break;
			case BottomRight:
				if(y > 0)
					y = 0;
				if(x > 0)
					x = 0;
				break;
			case Left:
				if(x < 0)
					x = 0;
				break;
			case Right:
				if(x > 0)
					x = 0;
				break;
		}

		velocity = new Vector(x, y);


		for(int i = 0; i < Settings.numberActivePlayers; i++)
		{
			if(i != Settings.playerID)
			{
				Collision result = this.collides(StatePlay.tanks[i]);
				if(result != null)
				{
					Vector v = result.getMinPenetration();
					velocity = v.project(velocity);
				}
			}
		}



	}
	/*-----------------------------------------------------------------------------------------------------*/
	public turretentity getTurret(){
		return turret;
	}

	/*-----------------------------------------------------------------------------------------------------*/
	public Edge collideWorldEdge(){
		if(this.getX() >= (DisplaysStatePlay.camera.worldWitdth - this.getCoarseGrainedWidth() / 2))
			{
			if(this.getY() >= (DisplaysStatePlay.camera.worldHeight -this.getCoarseGrainedHeight() / 2))
				return Edge.BottomRight;
			else if(this.getY() <= ( this.getCoarseGrainedHeight() / 2))
				return Edge.TopRight;
			else
				return Edge.Right;
			}
		else if(this.getX() <= ( this.getCoarseGrainedWidth() / 2))
			{
			if(this.getY() >= (DisplaysStatePlay.camera.worldHeight -this.getCoarseGrainedHeight() / 2))
				return Edge.BottomLeft;
			else if(this.getY() <= ( this.getCoarseGrainedHeight() / 2))
				return Edge.TopLeft;
			else
				return Edge.Left;
			}
		else if(this.getY() >= (DisplaysStatePlay.camera.worldHeight -this.getCoarseGrainedHeight() / 2))
			return Edge.Bottom;
		else if(this.getY() <= ( this.getCoarseGrainedHeight() / 2))
			return Edge.Top;
		else
			return Edge.None;
	}

}
