package dodger.main;

import dodger.gfx.Assets;
import dodger.gfx.Text;
import dodger.tiles.Tile;
import dodger.utils.Utils;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class Map {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    //  Spawns enemies
    private Spawner spawner;

    private int starPoints;

    public Map(Handler handler, String path) {
        this.handler = handler;
        loadMap(path);

        spawner = new Spawner(handler);

        starPoints = 0;

        spawner.getEntityManager().getPlayer().setX(spawnX);
        spawner.getEntityManager().getPlayer().setY(spawnY);
    }

    public void tick() {
        spawner.tick();
    }

    //  Renders tiles
    public void render(Graphics g) {
        //  Tile operations
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }

        drawStarCount(g);
        drawTimer(g);

        spawner.render(g);
    }

    //  Gets tile at x, y position
    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];

        //  Tile not in game
        if (t == null) {
            return Tile.floorTile;
        }

        return t;
    }

    //  Loads map from map.txt file
    private void loadMap(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    //  Calculates for timer
    public int countdown(int time) {
        int timeElapsed = 60 - time;

        //  Timer not 0, game not done
        if (timeElapsed > 0) {
            return timeElapsed;
        }

        return 0;
    }

    //  Draw timer
    public void drawTimer(Graphics g) {
        if (handler.getMap().getSpawner().getEntityManager().getPlayer().getHitmarker() || handler.getGame().getTimeElapsed() > 40) {
            //  Draw red timer if player is hit or low on time
            Text.drawString(g, Integer.toString(countdown(handler.getGame().getTimeElapsed())), 700, 100, true, Color.RED, Assets.dosis64);
        } else {
            //  Draw white timer
            Text.drawString(g, Integer.toString(countdown(handler.getGame().getTimeElapsed())), 700, 100, true, Color.WHITE, Assets.dosis64);
        }
    }

    //  Draw star count
    public void drawStarCount(Graphics g) {
        //  Draw star count
        Text.drawString(g, "Points: " + Integer.toString(getStarPoints()), 100, 100, true, Color.YELLOW, Assets.dosis32);
    }

    //  Getters and Setters
    public Spawner getSpawner() {
        return spawner;
    }

    public int getStarPoints() {
        return starPoints;
    }

    public void setStarPoints(int starPoints) {
        this.starPoints = starPoints;
    }
}
