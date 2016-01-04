
package Game;

import Main.Gameobject;
import Main.Vector2f;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author emil
 */
public class LaserAmmo extends AmmoBase
{
    private static final int MOVESPEED = 10,LENGTH = 2500;
    private final ArrayList<Vector2f> points = new ArrayList<>();
    
    @Override
    public void start(Gameobject go) {
        super.start(go);
        setDelay(LENGTH/MOVESPEED);
    }
    @Override
    protected void move(Gameobject go) {
        for(int i = 0; i < MOVESPEED; i++) {
            super.move(go);
            super.checkHits(go);
            points.add(new Vector2f(go.getX(),go.getY()));
        }
        while(points.size() > MOVESPEED*3) {
            points.remove(0);
        }
    }
    @Override
    public void render(Gameobject go) {
        glPushMatrix();
        {
            glLineWidth(10);
            glDisable(GL_TEXTURE_2D);
            
            glBegin(GL_LINE_STRIP);
            glColor3f(0.7f, 0, 1);
            for(Vector2f p : points) {
                glVertex2f(p.getX(), p.getY());
            }
            glEnd();
            
            glEnable(GL_TEXTURE_2D);
        }
        glPopMatrix();
    }
}
