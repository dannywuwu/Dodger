package dodger.items;

import dodger.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class ResetItem extends Item{
    
    //  Resets enemies

    public ResetItem(int id, int x, int y) {
        super(Assets.reset, 4, x, y);
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
            handler.getMap().getSpawner().getEntityManager().clearEnemies();
            handler.getMap().getSpawner().setItem(false);
        }
    }
}
