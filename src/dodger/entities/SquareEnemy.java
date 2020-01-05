package dodger.entities;

import dodger.gfx.Assets;
import dodger.main.Handler;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class SquareEnemy extends Enemy {

    public SquareEnemy(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_CHARACTER_WIDTH, DEFAULT_CHARACTER_HEIGHT);
        speed = 2;
    }

    @Override
    public void render(Graphics g) {
        //  Draws square enemy image
        g.drawImage(Assets.enemy1, (int) x, (int) y, width, height, null);
    }

}
