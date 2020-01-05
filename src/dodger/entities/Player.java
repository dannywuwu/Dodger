package dodger.entities;

import dodger.gfx.Assets;
import dodger.main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Wu
 */
public class Player extends Entity {
    
    private boolean hitmarker;
    private boolean itemmarker;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_CHARACTER_WIDTH, DEFAULT_CHARACTER_HEIGHT);
        speed = 4;

        bounds.x = 20;
        bounds.y = 0;
        bounds.width = 24;
        bounds.height = 48;
    }

    @Override
    public void tick() {
        getInput();
        move();
    }

    //  Manage input
    private void getInput() {
        xMove = 0;

        //  Move left
        if ((handler.getKeyManager().left || handler.getKeyManager().leftA) && x > -20) {
            xMove = -speed;
        }
        //  Move right
        if ((handler.getKeyManager().right || handler.getKeyManager().rightD) && x < handler.getWidth() - 45) {
            xMove = speed;
        }
    }

    @Override
    public void render(Graphics g) {
        //  Draws player image
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
        
        //  Collision between entity and player
        
        //  Makes red hitmarker for player
        if (hitmarker) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.red);
            g2.fill(this.getCollisionBounds());
            hitmarker = false;
        }
        
        if (itemmarker) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.cyan);
            g2.fill(this.getCollisionBounds());
            itemmarker = false;
        }

    }

    @Override
    public boolean isEnemy() {
        return false;
    }

    public boolean getHitmarker() {
        return hitmarker;
    }

    public void setHitmarker(boolean hitmarker) {
        this.hitmarker = hitmarker;
    }

    public void setItemmarker(boolean itemmarker) {
        this.itemmarker = itemmarker;
    }

    
}
