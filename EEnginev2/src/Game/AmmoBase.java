
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import Main.Behavior;
import Main.Delay;
import Main.Game;
import Main.Gameobject;
import static Main.Gameobject.AFKANIM;
import static Main.Gameobject.ANIMAMT;
import Main.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public abstract class AmmoBase extends Behavior
{

    protected float speed = 10;
    
    
    protected float SX = 10, SY = 10;
    protected Delay timeToDie;
    
    public AmmoBase() {
        timeToDie = new Delay(2500);
        timeToDie.start();
    }
    @Override
    public void start(Gameobject go) {
        go.setSX(SX);
        go.setSY(SY);
        go.setId(Gameobject.PROJECTILEID);
        go.setName("Ammo");
        go.setIsSolid(true);
    }
    @Override
    public void update(Gameobject go) {
        move(go);
        checkHits(go);
        if(timeToDie.over()) {
            destroy(go);
        }
    }
    @Override
    public void render(Gameobject go) {
        
    }
    protected void checkHits(Gameobject go) {
        ArrayList<Gameobject> colls = Game.sphereCollide(go.getX(),go.getY(),go.getSX()/2);
        for(Gameobject coll : colls) {
            if(coll.getId() == Gameobject.HEROID) {
                coll.setAnim(Gameobject.DEATHANIM);
                destroy(go);
            }
        }
    }
    /**
     * Called every update so that children of AmmoBase can update the gameobject
     * @param go Master Gameobject
     */
    protected void move(Gameobject go) {
        Vector2f v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(-speed);
        if(!go.move(v)){
            go.setRotation(-go.getRotation());
            v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(-speed);
            if(!go.move(v)) {
                go.setRotation((float)Math.PI+go.getRotation());v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(-speed);
                go.move(v);
            }
        }
    }
    public void setDelay(int i) {
        timeToDie = new Delay(i);
        timeToDie.start();
    }
    protected void destroy(Gameobject go) {
        go.remove();
        ((Ammo)go).shooter.reload();
    }
}
