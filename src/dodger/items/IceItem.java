package dodger.items;

import dodger.entities.Entity;
import dodger.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class IceItem extends Item {
    
    //  Slows all enemies on screen

    public IceItem(int id, int x, int y) {
        super(Assets.ice, 2, x, y);
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
            for (Entity e : handler.getMap().getSpawner().getEntityManager().getEntities()) {
                if (e.isEnemy()) {
                    e.setSpeed(0.5f);
                }
            }
            handler.getMap().getSpawner().setItem(false);
        }
    }
}
