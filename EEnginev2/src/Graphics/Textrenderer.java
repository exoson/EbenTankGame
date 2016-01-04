
package Graphics;

import java.awt.Font;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;


public class Textrenderer
{
    private static TrueTypeFont font;
    
    public static void init()
    {
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, false);
    }
    /**
     * Renders a string with prespecified font.
     * @param s String to be rendered
     * @param x x coordinate for where to render the text.
     * @param y y coordinate for where to render the text.
     * @param color Color to use when rendering.
     */
    public static void drawString(String s,float x,float y,Color color)
    {
        glPushMatrix();
        {
            font.drawString(x, y, s,color);
        }
        glPopMatrix();
    }
}