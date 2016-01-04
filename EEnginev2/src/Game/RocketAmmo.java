
package Game;

import Main.Delay;
import Main.Game;
import Main.Gameobject;
import Main.Util;
import Main.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class RocketAmmo extends AmmoBase
{
    ArrayList<Vector2f> path;
    private Delay trackDel;
    @Override
    public void start(Gameobject go) {
        super.start(go);
        trackDel = new Delay(1000);
        trackDel.start();
        speed = 3.5f;
    }
    @Override
    protected void move(Gameobject go) {
        if(!trackDel.over()) {
            super.move(go);
            return;
        }
        go.setIsSolid(false);
        float minDist = Float.POSITIVE_INFINITY;
        Gameobject closest = null;
        for(Gameobject g : Game.getObjects()) {
            if(g.getId() != Gameobject.HEROID) continue;
            
            float dist = Util.dist(g.getPos(), go.getPos());
            if(dist < minDist) {
                minDist = dist;
                closest = g;
            }
        }
        if(closest == null) {
            super.move(go);
        }else {
            path = Util.findPath(go, closest);
            if(path != null) {
                Vector2f dest;
                if(path.size() > 1) {
                    dest = path.get(path.size() > 2 ? path.size()-3 : 0).mult(Game.SQUARESIZE);
                } else {
                    dest = closest.getPos();
                }
                Vector2f heading = dest.minus(go.getPos());
                if(heading.length() < speed) {
                    go.move(heading);
                } else {
                    go.move(heading.normalize().mult(speed));
                }
            } else {
                super.move(go);
            }
        }
    }
    @Override
    public void render(Gameobject go) {
    }
}
