package dodger.items;

import dodger.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class StarItem extends Item {

    public StarItem(int id, int x, int y) {
        super(Assets.star, 0, x, y);
        
        bounds.x = x + 14;
    }

    @Override
    public void tick() {
        collected();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, 64, 64, null);
    }
    
    
    public void collected(){
        if (isPickedUp()) {
            pickedUp = true;
            //  Adds 5 seconds to game timer after being picked up
            handler.getGame().setTimeElapsed(handler.getGame().getTimeElapsed() - 2);
            //  Adds 1 point after being picked up
            handler.getMap().setStarPoints(handler.getMap().getStarPoints() + 1);
            handler.getMap().getSpawner().setStar(false);
        }
    }
    
}