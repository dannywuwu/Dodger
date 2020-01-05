package dodger.main;

import dodger.display.Display;
import dodger.gfx.Assets;
import dodger.gfx.SpriteSheet;
import dodger.input.KeyManager;
import dodger.input.MouseManager;
import dodger.states.GameOverState;
import dodger.states.GameState;
import dodger.states.MenuState;
import dodger.states.State;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Wu
 */
public class Game implements Runnable {

    private Display display;
    public int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;  //  Tells computer how to draw things to screen
    private Graphics g;

    private SpriteSheet sheet;

    //  States
    public State gameState;
    public State menuState;
    public State gameoverState;

    //  Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //  Handler
    private Handler handler;
    
    //  Timer
    private int timeElapsed;
    private int counter;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    //  Initialises graphics
    private void init() {
        display = new Display(title, width, height);

        //  Adds input listeners
        
        display.getFrame().addKeyListener(keyManager);

        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);

        //  gameState inherits from State, here states are initialized as specifically
        gameState = new GameState(handler);
        gameoverState = new GameOverState(handler);
        menuState = new MenuState(handler);
        State.setState(menuState);
    }

    //  Updates everything for game
    private void tick() {

        //  Updates keyManager
        keyManager.tick();

        //  State exists, tick
        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    //  Drawing
    private void render() {
        //  Finds how many buffers the canvas will use
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {    //  No buffer strategy
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();   //  Creates paintbrush
        //  Clear screen
        g.clearRect(0, 0, width, height);

        //  If a State exists, render
        if (State.getState() != null) {
            State.getState().render(g);
        }

        //  End drawing
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;  //   How many times tick/render will run every second 
        //  Max time (nanoseconds) allowed for tick/render methods to achieve 60fps
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();  //  Gives current time in nanoseconds
        long timer = 0;
        int ticks = 0;
        timeElapsed = 0;
        counter = 0;
        
        while (running) {
            now = System.nanoTime();
            //  How much time we have until we call tick/render methods again
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            //  After 1 second, reset variables and increase timeElapsed and counter
            
            if (timer >= 1000000000) {
                if(State.getState().equals(gameState)){
                    timeElapsed++;
                    counter++;
                }
                ticks = 0;
                timer = 0;
            }
        }

        stop(); //  Stop thread
    }

    //  Start thread
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start(); //  calls run method
    }

    //  Stop thread
    public synchronized void stop() {
        if (!running) {  //  Game already stopped
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //  Getters and Setters
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }
    
    public int getTimeElapsed() {
        return timeElapsed;
    }
    
    public void setTimeElapsed(int time){
        this.timeElapsed = time;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    

}
