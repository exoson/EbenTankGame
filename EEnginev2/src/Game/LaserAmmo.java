
package Game;

import Graphics.Renderer;
import Main.Gameobject;
import Main.Vector2f;
import Main.Vector4f;
import java.util.ArrayList;

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
        Renderer.renderLines(points, 5, new Vector4f(1f,1,1f,1));
    }
}
