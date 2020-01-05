package dodger.ui;

import dodger.main.Handler;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Wu
 */
public class UIManager {
    
    private Handler handler;
    private ArrayList<UIObject> objects;
    
    public UIManager(Handler handler){
        this.handler = handler;
        objects = new ArrayList<>();
    }
    
    public void tick(){
        for(UIObject o : objects){
            o.tick();
        }
    }
    
    public void render(Graphics g){
        for(UIObject o : objects){
            o.render(g);
        }
    }
    
    public void onMouseMove(MouseEvent me){
        for(UIObject o : objects){
            o.onMouseMove(me);
        }
    }
    
    public void onMouseRelease(MouseEvent me){
        for(UIObject o : objects){
            o.onMouseRelease(me);
        }
    }
    
    public void addObject(UIObject o){
        objects.add(o);
    }
    
    public void removeObject(UIObject o){
        objects.remove(o);
    }

    //  Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.objects = objects;
    }
    
    
    
    
}
