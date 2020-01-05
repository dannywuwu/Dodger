package dodger.input;

import dodger.ui.UIManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Wu
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;

    public MouseManager() {

    }
    
    public void setUIManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
//  Left click
        if (me.getButton() == MouseEvent.BUTTON1) {
            leftPressed = true;
        } //  Right click
        else if (me.getButton() == MouseEvent.BUTTON3) {
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //  Left click
        if (me.getButton() == MouseEvent.BUTTON1) {
            leftPressed = false;
        } //  Right click
        else if (me.getButton() == MouseEvent.BUTTON3) {
            rightPressed = false;
        }
        
        //  If UIManager exists
        if(uiManager != null){
            uiManager.onMouseRelease(me);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        mouseX = me.getX();
        mouseY = me.getY();
        
        //  If UIManager exists
        if(uiManager != null){
            uiManager.onMouseMove(me);
        }
    }
    
    //  Getters
    
    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRigtPressed(){
        return rightPressed;
    }
    
    public int getMouseX(){
        return mouseX;
    }
    
    public int getMouseY(){
        return mouseY;
    }
}
