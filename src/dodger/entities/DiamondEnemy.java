package dodger.entities;

import dodger.gfx.Assets;
import dodger.main.Handler;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class DiamondEnemy extends Enemy {

    public DiamondEnemy(Handler handler, float x, float y) {
        super(handler, x, y, 64, 64);
        speed = 4;

        bounds.x = 12;
        bounds.y = 12;
        bounds.width = 40;
        bounds.height = 40;
    }

    @Override
    public void render(Graphics g) {
        //  Draws diamond enemy image
        g.drawImage(Assets.enemy3, (int) x, (int) y, width, height, null);
    }

    @Override
    public void fall() {
        super.fall();

        //  Moves right 
        if (handler.getGame().getTimeElapsed() % 2 == 0) {
            xMove = speed;
        }

        //  Move left
        if (handler.getGame().getTimeElapsed() % 2 != 0) {
            xMove = -speed;
        }

    }

}
