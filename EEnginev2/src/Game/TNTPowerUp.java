
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class TNTPowerUp extends Powerup
{
    public TNTPowerUp()
    {
        this(0,0);
    }
    public TNTPowerUp(float x,float y)
    {
        Animation[] anims = new Animation[1];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Powerup.SIZE,Powerup.SIZE,"poweruptnt"),100));
        anims[0] = new Animation(frames);
        
        init(x, y, "TNT Power", anims, new Cannon(new TNTAmmo()));
    }
}
