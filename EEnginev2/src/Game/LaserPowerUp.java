
package Game;

/**
 *
 * @author emil
 */
public class LaserPowerUp extends Powerup
{
    public LaserPowerUp() {
        this(0,0);
    }
    public LaserPowerUp(float x,float y) {
        powerB = new SniperCrosshair();
        init(x, y, "Laser Power", new Cannon(new LaserAmmo(),"laser"));
    }
}
