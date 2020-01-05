package dodger.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Wu
 */
public class SpriteSheet {
    
    private BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height){
        //  Gets buffered image of area specified by parameters
        return sheet.getSubimage(x, y, width, height);
    }
}
