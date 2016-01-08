
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class ArmorPowerUp extends Powerup
{
    public ArmorPowerUp()
    {
        this(0,0);
    }
    public ArmorPowerUp(float x,float y)
    {
        Animation[] anims = new Animation[1];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Powerup.SIZE,Powerup.SIZE,"poweruparmor"),100));
        anims[0] = new Animation(frames);
        powerB = new Armor();
        init(x, y, "Armor", anims, new Cannon(new BasicAmmo()));
    }
}
