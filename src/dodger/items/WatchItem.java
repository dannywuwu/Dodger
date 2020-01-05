package dodger.items;

import dodger.gfx.Assets;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class WatchItem extends Item{
    
    //  The watch item addes 15 more seconds to the game timer
    
    public WatchItem(int id, int x, int y) {
        super(Assets.watch, 1, x, y);
    }
    
    @Override
    public void tick() {
        collected();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(texture, x, y, 64, 64, null);
    }
    
    @Override
    public void collected(){
        if (isPickedUp()) {
            pickedUp = true;
            //  Adds 10 seconds to game timer after being picked up
            handler.getGame().setTimeElapsed(handler.getGame().getTimeElapsed() - 10);
            handler.getMap().getSpawner().setItem(false);
        }
    }
}
