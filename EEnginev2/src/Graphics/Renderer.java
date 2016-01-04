
package Graphics;

import Main.Game;
import Main.Vector2f;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author emil
 */
public class Renderer 
{
    public static void setColor(Color4f c) {
        glColor4f(c.getR(), c.getG(), c.getB(),c.getA());
    }
    public static void setPointSize(float size) {
            glPointSize(size);
    }
    public static void setLineSize(float size) {
            glLineWidth(size);
    }
    public static void renderPoints(ArrayList<Vector2f> points, float size, Color4f c) {
        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);
            
            setPointSize(size);
            
            glBegin(GL_POINTS);
            for(Vector2f v : points) {
                glVertex2f(v.getX() * Game.SQUARESIZE, v.getY() * Game.SQUARESIZE);
            }
            glEnd();
            
            glEnable(GL_TEXTURE_2D);
        }
        glPopMatrix();
    }
    public static void renderLines(ArrayList<Vector2f> points, float size, Color4f c) {
        glPushMatrix();
        {
            setLineSize(size);
            glDisable(GL_TEXTURE_2D);
            
            glBegin(GL_LINE_STRIP);
            setColor(c);
            for(Vector2f p : points) {
                glVertex2f(p.getX(), p.getY());
            }
            glEnd();
            
            glEnable(GL_TEXTURE_2D);
        }
        glPopMatrix();
    }
    public static void renderSquares(ArrayList<Vector2f> corners, Color4f c) {
        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);
            
            glBegin(GL_QUADS);
            setColor(c);
            for(Vector2f p : corners) {
                glVertex2f(p.getX(), p.getY());
            }
            glEnd();
            
            glEnable(GL_TEXTURE_2D);
        }
        glPopMatrix();
    }
}
