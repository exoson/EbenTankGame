
package Game;

/**
 *
 * @author emil
 */
public class RocketPowerUp extends Powerup
{
    public RocketPowerUp() {
        this(0,0);
    }
    public RocketPowerUp(float x,float y) {
        init(x, y, "Rocket Power", new Cannon(new RocketAmmo()));
    }
}
