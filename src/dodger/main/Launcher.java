package dodger.main;

/**
 *
 * @author Wu
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("Dodger", 800, 600);
        game.start();
    }

}
