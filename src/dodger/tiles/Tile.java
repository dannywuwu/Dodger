package dodger.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Wu
 */
public abstract class Tile {
    
    //  Stores all tiles
    public static Tile[] tiles = new Tile[256];
    public static Tile wallTile = new WallTile(0);
    public static Tile floorTile = new FloorTile(1);
    
    //  Default tile height
    public static final int TILEWIDTH = 50, TILEHEIGHT = 50;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        
        //  Sets tile array index to specific tile
        tiles[id] = this;
    }
    
    //  Draws tile
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
}
