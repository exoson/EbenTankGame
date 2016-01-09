
package Game;

/**
 *
 * @author emil
 */
public class SniperPowerUp extends Powerup
{
    public SniperPowerUp() {
        this(0,0);
    }
    public SniperPowerUp(float x,float y) {
        init(x, y, "Simo Häyhä", new Cannon(new SniperAmmo()));
    }
}
