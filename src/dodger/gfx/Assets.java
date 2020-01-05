package dodger.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 *
 * @author Wu
 */
public class Assets {
    
    private static final int width = 32, height = 32;
    
    public static Font dosis32;
    public static Font dosis64;
    public static Font dosis128;
    
    public static BufferedImage player, enemy1, enemy2, enemy3, watch, ice, shoe, reset, floor, wall, star;
    public static BufferedImage[] btn_start;
    
    //  Loads in all assets
    public static void init(){
        dosis32 = FontLoader.loadFont("./res/fonts/dosis.ttf", 32);
        dosis64 = FontLoader.loadFont("./res/fonts/dosis.ttf", 64);
        dosis128 = FontLoader.loadFont("./res/fonts/dosis.ttf", 128);
        
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
        
        player = sheet.crop(0, 0, width, height);
        enemy1 = sheet.crop(width, 0, width, height);
        enemy2 = sheet.crop(width * 2, 0, width, height);
        enemy3 = sheet.crop(width * 3, 0, width, height);
        
        watch = sheet.crop(0, height, width, height);
        ice = sheet.crop(width, height, width, height);
        shoe = sheet.crop(width * 2, height, width, height);
        reset = sheet.crop(width * 3, height, width, height);
         
        floor = sheet.crop(0, height * 2, width, height);
        wall = sheet.crop(width, height * 2, width, height);
        
        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(width * 2, height * 2, width, height);
        btn_start[1] = sheet.crop(width * 3, height * 2, width, height);
        
        star = sheet.crop(0, height * 3, width, height);
        
    }
}
