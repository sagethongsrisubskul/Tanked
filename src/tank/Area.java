package tank;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
/* This class is for a single defined area. Use it to define an area bounded by coordinates */
public class Area
	{
	int x;
	int y;
	int endX;
	int endY;

	public int getWidth()
		{
		return endX - x + 1;
		}
	public int getHeight()
		{
		return endY - y + 1;
		}
	public int getMiddleX() {return x + ((endX - x) / 2);}
	public int getMiddleY() {return y + ((endY - y) / 2);}

	public void colorSection(Graphics g, Color color)
		{
		g.setColor(color);
		g.fillRect(x, y, getWidth(), getHeight());
		}
	}
