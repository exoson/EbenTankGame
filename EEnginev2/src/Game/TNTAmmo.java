package Game;

import Main.Game;
import Main.Gameobject;
import Main.Input;

/**
 *
 * @author emil
 */
public class TNTAmmo extends AmmoBase
{
    private static final float SPREAD = (float)Math.PI*2;
    private static final int AMMOAMT = 10,
            AMMOLIFETIME = 750;
    
    public TNTAmmo() {
        super();
    }
    
    @Override
    public void start(Gameobject go) {
        super.start(go);
        go.setSX(20);
        go.setSY(20);
    }
    
    @Override
    protected void move(Gameobject go) {
        super.move(go);
        if(Input.getKeyPressed(((Ammo)go).shootingKey)) {
            go.remove();
            for(int i = 0; i < AMMOAMT; i++) {
                ShardAmmo b = new ShardAmmo();
                b.setDelay(AMMOLIFETIME);
                Game.initObject(new Ammo(go.getX(), go.getY(), go.getRotation() + ((float)i-AMMOAMT/2f)/AMMOAMT*SPREAD, b,-1, ((Ammo)go).shooter));
            }
            ((Ammo)go).shooter.reload();
        }
    }
    
}
