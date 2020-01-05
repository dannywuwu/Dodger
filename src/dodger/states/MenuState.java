package dodger.states;

import dodger.gfx.Assets;
import dodger.gfx.ImageLoader;
import dodger.main.Handler;
import dodger.ui.ClickListener;
import dodger.ui.UIImage;
import dodger.ui.UIImageButton;
import dodger.ui.UIManager;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        //  Title banner    (logo coordinates at 175, 50, 450, 270)
        uiManager.addObject(new UIImage(0, 0, 800, 600, ImageLoader.loadImage("/textures/title.png")));

        //  Start button
        uiManager.addObject(new UIImageButton(336, 400, 128, 96, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                //  Close UIManager
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

}
