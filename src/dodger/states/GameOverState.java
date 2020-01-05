package dodger.states;

import dodger.gfx.Assets;
import dodger.gfx.Text;
import dodger.main.Handler;
import dodger.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class GameOverState extends State {

    private int highScore;

    public GameOverState(Handler handler) {
        super(handler);

        highScore = loadHighScore();
    }

    @Override
    public void tick() {

        //  Load high score
        if (handler.getMap().getStarPoints() > highScore) {
            outputHighScore(handler.getMap().getStarPoints());
            highScore = loadHighScore();
        }

        reset();
    }

    @Override
    public void render(Graphics g) {
        //  Render map, score, GAME OVER, and play again button
        handler.getMap().render(g);
        Text.drawString(g, "GAME OVER", 400, 200, true, Color.PINK, Assets.dosis128);
        Text.drawString(g, "Score: " + handler.getMap().getStarPoints(), 400, 300, true, Color.PINK, Assets.dosis64);
        Text.drawString(g, "High Score: " + highScore, 400, 375, true, Color.PINK, Assets.dosis32);
        Text.drawString(g, "PRESS SPACE TO PLAY AGAIN", 400, 425, true, Color.PINK, Assets.dosis32);
    }

    //  Loads high score from file
    private int loadHighScore() {
        String score = Utils.loadFileAsString("./res/score/highscore.txt");
        return Utils.parseInt(score.trim());
    }

    //  Outputs high score to file
    private void outputHighScore(int highScore) {
        String score = Integer.toString(highScore);
        Utils.outputStringAsFile("./res/score/highscore.txt", score);
    }

    //  Resets variables for restart
    private void reset() {
        if (handler.getKeyManager().space) {
            //  Counter to 0
            handler.getGame().setCounter(0);
            //  Time elapsed to 0
            handler.getGame().setTimeElapsed(0);
            //  Sets player speed back to default
            handler.getMap().getSpawner().getEntityManager().getPlayer().setSpeed(4);
            //  Resets points
            handler.getMap().setStarPoints(0);
            //  Resets entities and items
            handler.getMap().getSpawner().getEntityManager().clearEnemies();
            handler.getMap().getSpawner().getItemManager().getItems().clear();
            handler.getMap().getSpawner().setStar(false);
            //  Changes to gameState
            State.setState(handler.getGame().gameState);
        }
    }
}
