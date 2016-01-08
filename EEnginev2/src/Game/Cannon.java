
package Game;

import Main.Behavior;
import Main.Sound;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emil
 */
public class Cannon
{
    private final Behavior ammoBehavior;
    private final Sound shootingSound;
    
    public Cannon(Behavior ammoBehavior) {
        this(ammoBehavior,"shoot");
    }
    public Cannon(Behavior ammoBehavior, String shootingSound) {
        this.ammoBehavior = ammoBehavior;
        this.shootingSound = new Sound(shootingSound);
    }
    
    public Ammo getAmmo(float x, float y, float rot,int shootingKey, CannonBehavior shooter) {
        try {
            shootingSound.playClip();
            return new Ammo(x, y, rot, ammoBehavior.getClass().newInstance(), shootingKey, shooter);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Cannon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Behavior getBehavior() {
        return ammoBehavior;
    }
}
