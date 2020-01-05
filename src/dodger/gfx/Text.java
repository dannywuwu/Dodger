package dodger.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 *
 * @author Wu
 */
public class Text {
    
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font){
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos;
        
        //  Drawing from center
        if(center){
            //  Pixel width
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            //  Pixel height
            y = (yPos - fm.getHeight()/ 2) + fm.getAscent();
        }
        
        //  Draw String
        g.drawString(text, x, y);
    }
    
}
