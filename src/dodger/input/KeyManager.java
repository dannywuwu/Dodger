package dodger.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Wu
 */
public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean left, leftA, right, rightD, space;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void tick() {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];

        leftA = keys[KeyEvent.VK_A];
        rightD = keys[KeyEvent.VK_D];
        
        space = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        //  True if pressed, if statement protects from error
        if (ke.getKeyCode() < 0 || ke.getKeyCode() >= keys.length) {
            return;
        }
        keys[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //  False if released, if statement protects from error
        if (ke.getKeyCode() < 0 || ke.getKeyCode() >= keys.length) {
            return;
        }
        keys[ke.getKeyCode()] = false;
    }

}
