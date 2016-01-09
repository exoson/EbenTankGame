
package Game;

/**
 *
 * @author emil
 */
public class ArmorPowerUp extends Powerup
{
    public ArmorPowerUp() {
        this(0,0);
    }
    public ArmorPowerUp(float x,float y) {
        powerB = new Armor();
        init(x, y, "Armor", new Cannon(new BasicAmmo()));
    }
}
