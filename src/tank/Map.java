package tank;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import jig.Vector;

public class Map extends TiledMap {
	private int xPos;
	private int yPos;
	private int pixelWidth;
	private int pixelHeight;
	
	int permanentBlocks;
	int breakableBlocks;

	public Map(String ref, final int x, final int y) throws SlickException {
		super(ref);
		
		xPos = x;
		yPos = y;
		
		pixelWidth = super.width*super.tileWidth;
		pixelHeight = super.height*super.tileHeight;
		
		permanentBlocks = this.getLayerIndex("PermanentBlocks");
		breakableBlocks = this.getLayerIndex("BreakableBlocks");
		
	}
	
	public Map(String ref, String tileRef, final int x, final int y) throws SlickException {
		super(ref, tileRef);
		
		xPos = x;
		yPos = y;
		
		pixelWidth = super.width*super.tileWidth;
		pixelHeight = super.height*super.tileHeight;
		
		permanentBlocks = this.getLayerIndex("PermanentBlocks");
		breakableBlocks = this.getLayerIndex("BreakableBlocks");
		
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void render() {
		super.render(xPos, yPos);
	}

    public void render(int sX, int sY, int width, int height) {
        super.render(xPos, yPos, sX, sY, width, height);
    }
	
	public int getXWidthMax() {
		return xPos + pixelWidth;
	}
	
	public int getYHeightMax() {
		return yPos + pixelHeight;
	}

	public Vector getTileLocation(tankentity e) {
		float x = (float) (Math.floor((e.getX() - this.xPos) / this.tileWidth));
		float y = (float) (Math.floor((e.getY() - this.yPos) / this.tileHeight));		
		
		return new Vector(x,y);
	}

	public float getTileXMin(final int x) {
		return this.xPos + (x*this.tileWidth);
	}
	
	public float getTileXMax(final int x) {
		return this.xPos + (x+1)*this.tileWidth;
	}
	
	public float getTileYMin(final int y) {
		return this.yPos + (y*this.tileHeight);
	}
	
	public float getTileYMax(final int y) {
		return this.yPos + (y+1)*this.tileHeight;
	}
	
	/**
	 * Gives the pixel coordinate the center of a specified tile
	 * @param   x horizontal tile position  y vertical tile position
	 * @return  Vector of the center of the tile in pixels
	 */
	public Vector getTileCenter(final int x, final int y) {
		return new Vector(getTileXMax(x)-tileWidth/2, getTileYMax(y)-tileHeight/2);
	}
	
	public Vector getTileCenter(Vector v) {
		return new Vector(getTileXMax((int)v.getX())-tileWidth/2, getTileYMax((int)v.getY())-tileHeight/2);
	}
	
	public boolean isTilePermanent(final float x, final float y) {
		if(x >= this.width || y >= this.height || x < 0 || y < 0)
			return true;

		return getTileProperty(getTileId((int)x, (int)y, this.permanentBlocks), "Permanent", "bool").equals("true");
	}
	
	public boolean isTileBreakable(final float x, final float y) {
		return getTileId((int)x, (int)y, this.breakableBlocks) != 0;
	}
	
	public boolean isTileBlocked(final float x, final float y) {
		return isTilePermanent(x,y) || isTileBreakable(x,y); 
	}

	public Vector getTileCenter(float x, float y) {
		return this.getTileCenter((int)x, (int)y);
	}
	
	public ArrayList<Vector> getTileNeighbors(Vector v) {
		ArrayList<Vector> neighbors = new ArrayList<Vector>();
		
		if(v.getX() > 0)
			neighbors.add(new Vector(v.getX() - 1, v.getY()));
		if(v.getX() < this.width - 1)
			neighbors.add(new Vector(v.getX() + 1, v.getY()));
		if(v.getY() > 0)
			neighbors.add(new Vector(v.getX(), v.getY() - 1));
		if(v.getY() < this.height - 1)
			neighbors.add(new Vector(v.getX(), v.getY() + 1));
		
		return neighbors;
	}

}
