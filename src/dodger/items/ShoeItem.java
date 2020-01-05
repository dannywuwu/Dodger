package dodger.items;

import dodger.entities.Entity;
import dodger.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class ShoeItem extends Item{
        
    //  Permanently gives the player a speed boost (it's the shoes!)

    public ShoeItem(int id, int x, int y) {
        super(Assets.shoe, 3, x, y);
    }

    @Override
    public void tick() {
        collected();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, 48, 48, null);
    }

    @Override
    public void collected() {
        if (isPickedUp()) {
            pickedUp = true;
            handler.getMap().getSpawner().setItem(false);
            float speed = handler.getMap().getSpawner().getEntityManager().getPlayer().getSpeed();
            handler.getMap().getSpawner().getEntityManager().getPlayer().setSpeed(speed + 2);
        }
    }
}
