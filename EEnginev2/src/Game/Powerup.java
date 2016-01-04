
package Game;

import Graphics.Animation;
import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class Powerup extends Gameobject
{
    public static final float SIZE = Game.SQUARESIZE / 1.5f;
    private Cannon powerUp;
    
    @Override
    public void update() 
    {
        ArrayList<Gameobject> gos = Game.gameObjectCollide(this);
        for(Gameobject go : gos) {
            if(go.getId() == HEROID) {
                if(((CannonBehavior)go.getBehavior("CannonBehavior")).setCannon(powerUp)){
                    remove();
                    break;
                }
            }
        }
    }
    
    protected void init(float x, float y, String name, Animation[] anims, Cannon cannon) {
        init(x, y, SIZE, SIZE, 0, false, POWERUPID, 3, name, anims, new ArrayList<Behavior>());
        this.powerUp = cannon;
    }
}
