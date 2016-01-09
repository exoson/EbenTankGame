
package Game;

/**
 *
 * @author emil
 */
public class TNTPowerUp extends Powerup
{
    public TNTPowerUp() {
        this(0,0);
    }
    public TNTPowerUp(float x,float y) {
        
        init(x, y, "TNT Power", new Cannon(new TNTAmmo()));
    }
}
