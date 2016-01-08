
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class LaserPowerUp extends Powerup
{
    public LaserPowerUp()
    {
        this(0,0);
    }
    public LaserPowerUp(float x,float y)
    {
        Animation[] anims = new Animation[1];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Powerup.SIZE,Powerup.SIZE,"poweruplaser"),100));
        anims[0] = new Animation(frames);
        
        powerB = new SniperCrosshair();
        init(x, y, "Laser Power", anims, new Cannon(new LaserAmmo(),"laser"));
    }
}
