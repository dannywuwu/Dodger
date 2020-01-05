package dodger.entities;

import dodger.main.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Wu
 */
public class EntityManager {
    
    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    
    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        
        addEntity(player);
    }
    
    public void tick() {
        Iterator<Entity> it = entities.iterator();
        //  As long as this Iterator has entities, perform loop
        while(it.hasNext()) {
            Entity e = it.next();
            e.tick();
            if(!e.isActive()){
                it.remove();
            }
        }
    }
    
    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }
    
    public void addEntity(Entity e) {
        entities.add(e);
    }
    
    public void clearEnemies(){
        for (Entity e : handler.getMap().getSpawner().getEntityManager().getEntities()){
            if(e.isEnemy()){
                e.setActive(false);
            }
        }
    }

    //  Getters and setters
    
    public Handler getHandler() {
        return handler;
    }
    
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public ArrayList<Entity> getEntities() {
        return entities;
    }
    
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
}
