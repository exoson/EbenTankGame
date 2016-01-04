package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import Main.Gameobject;
import static Main.Gameobject.AFKANIM;
import static Main.Gameobject.DEATHANIM;
import static Main.Gameobject.WALKANIM;
import Main.Input;
import Main.Sound;
import java.util.ArrayList;

/**
 *
 * @author emil
 */
public class TNTAmmo extends AmmoBase
{
    private boolean exploded;
    public TNTAmmo() {
        super();
        exploded = false;
        anims = new Animation[4];
        ArrayList<Frame> aFrames = new ArrayList<>();
        ArrayList<Frame> adFrames = new ArrayList<>();
        aFrames.add(new Frame(new Sprite(20,20,"ammo"),100));
        adFrames.add(new Frame(new Sprite(20*(float)Math.pow(1.1, 5),20*(float)Math.pow(1.1, 5),"ammo"),5));
        adFrames.add(new Frame(new Sprite(20*(float)Math.pow(1.1, 10),20*(float)Math.pow(1.1, 10),"ammo"),5));
        adFrames.add(new Frame(new Sprite(20*(float)Math.pow(1.1, 15),20*(float)Math.pow(1.1, 15),"ammo"),5));
        adFrames.add(new Frame(new Sprite(20*(float)Math.pow(1.1, 20),20*(float)Math.pow(1.1, 20),"ammo"),5));
        anims[AFKANIM] = new Animation(aFrames);
        anims[WALKANIM] = new Animation(aFrames);
        anims[DEATHANIM] = new Animation(adFrames);
    }
    
    @Override
    public void start(Gameobject go) {
        super.start(go);
        go.setDeathSound(new Sound("explosion"));
    }
    
    @Override
    protected void move(Gameobject go) {
        super.move(go);
        if(Input.getKeyPressed(((Ammo)go).shootingKey)) {
            exploded = true;
            go.setAnim(Gameobject.DEATHANIM);
            ((Ammo)go).shooter.reload();
        }
        if(exploded) {
            go.scale(1.1f);
        }
        
    }
    
}
