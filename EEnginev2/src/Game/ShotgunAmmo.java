
package Game;

import Main.Game;
import Main.Gameobject;

/**
 *
 * @author emil
 */
public class ShotgunAmmo extends AmmoBase
{
    private static final float SPREAD = (float)Math.PI/3;
    private static final int AMMOAMT = 5,
            AMMOLIFETIME = 750;
    
    public ShotgunAmmo(){
        super();
    }
    @Override
    public void move(Gameobject go) {
        go.remove();
        for(int i = 0; i < AMMOAMT; i++) {
            BasicAmmo b = new BasicAmmo();
            b.setDelay(AMMOLIFETIME);
            Game.initObject(new Ammo(go.getX(), go.getY(), go.getRotation() + ((float)i-AMMOAMT/2f)/AMMOAMT*SPREAD, b,-1, ((Ammo)go).shooter));
        }
    }
}
