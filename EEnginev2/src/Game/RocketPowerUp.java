
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class RocketPowerUp extends Powerup
{
    public RocketPowerUp()
    {
        this(0,0);
    }
    public RocketPowerUp(float x,float y)
    {
        Animation[] anims = new Animation[1];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Powerup.SIZE,Powerup.SIZE,"poweruprocket"),100));
        anims[0] = new Animation(frames);
        
        init(x, y, "Rocket Power", anims, new Cannon(new RocketAmmo()));
    }
}
