package dodger.entities;

import dodger.gfx.Assets;
import dodger.main.Handler;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Wu
 */
public class RectangleEnemy extends Enemy{
    
    public RectangleEnemy(Handler handler, float x, float y){
        super(handler, x, y, 96, 96);
        speed = 7;
        
        bounds.x = 0;
        bounds.y = 15;
        bounds.width = 96;
        bounds.height = 66;
    }
    
    @Override
    public void render(Graphics g) {
        //  Draws rectangle enemy image
        g.drawImage(Assets.enemy2, (int) x, (int) y, width, height, null);
    }

    @Override
    public void fall() {
        Random r = new Random();
        
        //  Rectangle enemy randomly "drops" down, it falls very fast
        int drop = r.nextInt(30);
        
        if (drop == 0) {
            yMove = speed;
        }

        if (y > handler.getHeight() - 140) {
            //  Remove enemy once it hits the floor
            active = false;
        }
    }
    
    
}
