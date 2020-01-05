package dodger.entities;

import dodger.main.Handler;

/**
 *
 * @author Wu
 */
public abstract class Enemy extends Entity {

    public Enemy(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

    @Override
    public void tick() {
        fall();
        move();
        hit();
    }

    //  Method to make enemy fall from sky
    public void fall() {

        //  Enemies fall if they are not frozen
            if (y >= 0) {
                yMove = speed;
            }

            if (y > handler.getHeight() - 115) {
                //  Remove enemy once it hits the floor
                active = false;
            }
    }

    public void hit() {
        //  Collision, remove enemy
        if (checkEntityCollision()) {
            active = false;
            handler.getGame().setTimeElapsed(handler.getGame().getTimeElapsed() + 10);
        }
    }

    @Override
    public boolean isEnemy() {
        return true;
    }

    

}
