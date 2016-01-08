
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class ShotgunPowerUp extends Powerup
{
    public ShotgunPowerUp()
    {
        this(0,0);
    }
    public ShotgunPowerUp(float x,float y)
    {
        Animation[] anims = new Animation[1];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Powerup.SIZE,Powerup.SIZE,"powerupshotgun"),100));
        anims[0] = new Animation(frames);
        powerB = new SpeedUp();
        init(x, y, "Shotgun Power", anims, new Cannon(new ShotgunAmmo()));
    }
}
