
package Game;

/**
 *
 * @author emil
 */
public class ShotgunPowerUp extends Powerup
{
    public ShotgunPowerUp() {
        this(0,0);
    }
    public ShotgunPowerUp(float x,float y) {
        powerB = new SpeedUp();
        init(x, y, "Shotgun Power", new Cannon(new ShotgunAmmo()));
    }
}
