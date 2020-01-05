package dodger.states;

import dodger.main.Handler;
import dodger.main.Map;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class GameState extends State {

    private Map map;

    public GameState(Handler handler) {
        super(handler);
        map = new Map(handler, "./res/map/map.txt");
        handler.setMap(map);
    }

    @Override
    public void tick() {
        map.tick();
        //  Game over
        if (handler.getGame().getTimeElapsed() >= 60) {
            State.setState(handler.getGame().gameoverState);
        }
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
    }
}
