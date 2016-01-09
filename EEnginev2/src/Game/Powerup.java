
package Game;

import Graphics.Animation;
import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import Main.Sound;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class Powerup extends Gameobject
{
    public static final float SIZE = Game.SQUARESIZE / 1.5f;
    protected Cannon powerUp;
    protected PowerUpBehavior powerB;
    private boolean isPicked;
    private Sound onPickUp = new Sound("loading");
    @Override
    public void update() {
        if(!isPicked) {
            ArrayList<Gameobject> gos = Game.gameObjectCollide(this);
                for(Gameobject go : gos) {
                    if(go.getId() == HEROID) {
                        if(powerUp!=null) {
                            if(((CannonBehavior)go.getBehavior("CannonBehavior")).setCannon(powerUp, this)) {
                                remove();
                                onPickUp.playClip();
                                isPicked = true;
                            }
                        }
                        if(powerB != null) {
                                if(go.getBehavior(powerB.getClass().getName().split("\\.")[1]) == null) {
                                remove();
                                go.addBehaviors(powerB);
                                powerB.start(go);
                                onPickUp.playClip();
                                isPicked = true;
                            }
                        }
                    }
                }
        }
    }
    
    protected void init(float x, float y, String name, Cannon cannon) {
        init(x, y, SIZE, SIZE, 0, false, POWERUPID, 3, name, new ArrayList<Behavior>());
        this.powerUp = cannon;
    }
}
