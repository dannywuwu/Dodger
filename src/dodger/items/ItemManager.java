package dodger.items;

import dodger.main.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Wu
 */
public class ItemManager {
    
    private Handler handler;
    private ArrayList<Item> items;
    
    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<Item>();
    }
    
    public void tick(){
        Iterator<Item> it = items.iterator();
        //  While the iterator still has items
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.isPickedUp()){
                it.remove();
            }
        }
    } 
    
    public void render(Graphics g){
        for(Item i : items){
            i.render(g);
        }
    }
    
    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }
    
    //  Getters and Setters

    public ArrayList<Item> getItems() {
        return items;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    
    
    
}
