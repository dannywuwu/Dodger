package dodger.states;

import dodger.main.Handler;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public abstract class State {
    
    //  State object holds current state of game that 
    private static State currentState = null;
    
    public static void setState(State state){
        currentState = state;
    }
    
    public static State getState(){
        return currentState;
    }
    
    //  Class
    
    protected Handler handler;   
    
    public State(Handler handler){
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    
}
