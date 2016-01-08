
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class SniperPowerUp extends Powerup
{
    public SniperPowerUp()
    {
        this(0,0);
    }
    public SniperPowerUp(float x,float y)
    {
        Animation[] anims = new Animation[1];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Powerup.SIZE,Powerup.SIZE,"powerupsniper"),100));
        anims[0] = new Animation(frames);
        init(x, y, "Simo Häyhä", anims, new Cannon(new SniperAmmo()));
    }
}
