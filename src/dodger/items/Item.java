package dodger.items;

import dodger.main.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Wu
 */
public abstract class Item {

    //  Handler
    public static Item[] items = new Item[256];

    //  Class
    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected final int id;

    protected Rectangle bounds;
    protected boolean pickedUp = false;

    protected int x, y;

    public Item(BufferedImage texture, int id, int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.id = id;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    //  Sets item position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public abstract void collected();

    //  If player touches item, return true
    public boolean isPickedUp() {
        if (handler.getMap().getSpawner().getEntityManager().getPlayer().getCollisionBounds().intersects(bounds)) {
            handler.getMap().getSpawner().getEntityManager().getPlayer().setItemmarker(true);
            return true;
        }
        return false;
    }

    //  Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
