package tank;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

public class Camera
    {

    private static int viewportTileWidth = 30;
    private static int viewportTileHeight = 19;
    TiledMap map;
    int worldWitdth;
    int worldHeight;
    Rectangle viewport;
    int viewportWidth;
    int viewportHeight;

    int xPos;
    int yPos;

    int tileWidth;
    int tileHeight;

    int tileOffsetX;
    int tileOffsetY;

    int pixelOffsetX;
    int pixelOffsetY;

    public Camera(String mapPath, final int x, final int y) throws SlickException
        {
        xPos = x;
        yPos = y;
        map = new TiledMap(mapPath);

        tileWidth = map.getTileWidth();
        tileHeight = map.getTileHeight();

        worldWitdth = map.getWidth() * tileWidth;
        worldHeight = map.getHeight() * tileHeight;

        viewportWidth = viewportTileWidth * tileWidth;
        viewportHeight = viewportTileHeight * tileHeight;

        viewport = new Rectangle(xPos, yPos, viewportWidth, viewportHeight);

        tileOffsetX = 0;
        tileOffsetY = 0;

        pixelOffsetX = 0;
        pixelOffsetY = 0;

        }

    public void update(tankentity player, int delta)
        {
        pixelOffsetX = Math.min(0, -(int)player.getX());
        pixelOffsetY = Math.min(0, -(int)player.getY());
        }


    public void render(Graphics g)
        {
        g.setClip(viewport);
        //map.render(xPos + pixelOffsetX, yPos + pixelOffsetY, tileOffsetX, tileOffsetY, viewportTileWidth + 1, viewportTileHeight + 1);
        map.render(xPos + pixelOffsetX, yPos + pixelOffsetY);

        g.clearClip();

        }


    }
