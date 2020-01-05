package dodger.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Wu
 */
public class ImageLoader {
    
    public static BufferedImage loadImage(String path){
        try{
        //  Loads image from its path location
        return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e){
            e.printStackTrace();
            //  No image = close the game
            System.exit(1);
        }
        return null;
    }
}
