package dodger.main;

import dodger.entities.DiamondEnemy;
import dodger.entities.EntityManager;
import dodger.entities.Player;
import dodger.entities.RectangleEnemy;
import dodger.entities.SquareEnemy;
import dodger.items.IceItem;
import dodger.items.ItemManager;
import dodger.items.ShoeItem;
import dodger.items.ResetItem;
import dodger.items.StarItem;
import dodger.items.WatchItem;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Wu
 */
public class Spawner {

    private Handler handler;
    //  Entities
    private EntityManager entityManager;
    //  Items
    private ItemManager itemManager;

    private boolean star = false;
    private boolean item = false;

    private int enemyCount;

    Random r = new Random();

    public Spawner(Handler handler) {
        this.handler = handler;

        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);

        enemyCount = 0;
    }

    public void tick() {
        enemyGenerator();
        itemGenerator();

        itemManager.tick();
        entityManager.tick();

    }

    public void render(Graphics g) {
        //  Items
        itemManager.render(g);

        //  Entities
        entityManager.render(g);
    }

    //  Generates Enemies
    public void enemyGenerator() {
        int time = handler.getGame().getCounter();

        //  If the player gets too many stars, time will be a negative value
        if (time < 0) {
            //  Multiplying 2 negatives makes time positive again
            time = time * -1;
        }

        //  Maximum enemy count
        if (enemyCount <= 10) {
            enemyCount = 1 + (time) / 2;
            if (enemyCount >= 10) {
                //  If the player is still alive after 30s, increased enemyCount
                if (time > 30) {
                    enemyCount = 20;
                } else if (time > 60) {
                    enemyCount = 25;
                } else if (time > 90) {
                    enemyCount = 30;
                } else if (time > 120) {
                    enemyCount = 40;
                } else if (time > 150) {
                    enemyCount = 50;
                }
            }
        }

        //  Generates random numbers from 0 to 20, randomness in spawning
        int type = r.nextInt(20);
        //  Random x spawn within game width
        int xSpawn = r.nextInt(handler.getWidth() - 50);

        if (enemyCount > entityManager.getEntities().size()) {
            if (type == 0) {
                entityManager.addEntity(new SquareEnemy(handler, xSpawn, 0));
            } else if (type == 1 && time > 10 && time % 2 == 0) {
                //  Rectangles spawn after 10 seconds and spawns on even times
                entityManager.addEntity(new RectangleEnemy(handler, xSpawn - 50, 0));
            } else if (type == 2 && time > 20) {
                //  Diamonds spawn after 20 seconds; Spawns on times divisible by 3
                entityManager.addEntity(new DiamondEnemy(handler, xSpawn, 0));
            }
        }
    }

    //  Generates Items
    public void itemGenerator() {

        int xSpawn;
        //  Star generation
        if (!star) {
            xSpawn = r.nextInt(handler.getWidth() - 64);
            itemManager.addItem(new StarItem(0, xSpawn, handler.getHeight() - 110));
            star = true;
        }

        int time = handler.getGame().getCounter();

        if (!item) {
            xSpawn = r.nextInt(handler.getWidth() - 64);
            //  Random item spawns
            int rand = r.nextInt(3000);

            if (rand == 0) {
                itemManager.addItem(new WatchItem(0, xSpawn, handler.getHeight() - 110));
                item = true;
            } else if (rand == 1) {
                //  Freeze Spawn
                itemManager.addItem(new IceItem(0, xSpawn, handler.getHeight() - 110));
                item = true;
            } else if (rand == 2) {
                //  Shoe spawn
                itemManager.addItem(new ShoeItem(0, xSpawn, handler.getHeight() - 110));
                item = true;
            } else if (rand == 3) {
                //    Repel spawn
                itemManager.addItem(new ResetItem(0, xSpawn, handler.getHeight() - 110));
                item = true;
            }
        }
    }

    //  Getters and Setters
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public void setStar(boolean star) {
        this.star = star;
    }

    public void setItem(boolean item) {
        this.item = item;
    }

}
