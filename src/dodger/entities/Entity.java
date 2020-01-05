package dodger.entities;

import dodger.main.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Wu
 */
public abstract class Entity {

    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected boolean active = true;
    protected Rectangle bounds;

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CHARACTER_WIDTH = 64,
            DEFAULT_CHARACTER_HEIGHT = 64;

    protected float speed;
    protected float xMove;
    protected float yMove;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //  By default, entity will have bounding box size from width and height
        bounds = new Rectangle(0, 0, width, height);

        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    //  Update variables
    public abstract void tick();

    //  Where entity will draw itself to the screen
    public abstract void render(Graphics g);

    public boolean checkEntityCollision() {
        for (Entity e : handler.getMap().getSpawner().getEntityManager().getEntities()) {
            //  Will not check collisions against itself
            if (e.equals(this)) {
                continue;
            }
            //  Collision between entity and player
            if (e.getCollisionBounds().intersects(getCollisionBounds())) {
                if (handler.getMap().getSpawner().getEntityManager().getPlayer().getCollisionBounds().intersects(e.getCollisionBounds())) {
                    handler.getMap().getSpawner().getEntityManager().getPlayer().setHitmarker(true);
                    return true;
                }
            }
        }
        return false;
    }

    public abstract boolean isEnemy();

    //  Move methods
    public void move() {
        moveX();
        moveY();
    }

    public void moveX() {
        x += xMove;
    }

    public void moveY() {
        y += yMove;
    }

    //  Getters and Setters
    //  Return bounding rectangle
    public Rectangle getCollisionBounds() {
        return new Rectangle((int) (x + bounds.x), (int) (y + bounds.y), bounds.width, bounds.height);
    }

    public void setCollisionBounds(int x, int y, int width, int height) {
        bounds.x = x;
        bounds.y = y;
        bounds.width = width;
        bounds.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //  Character
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

}
