
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import Main.Input;
import Main.Sound;
import java.util.ArrayList;

public class Tank extends Gameobject
{
    
    public Tank(float x, float y, int team) {
        this(x,y,team,new int[]{Input.KEY_W,Input.KEY_D,Input.KEY_S,Input.KEY_A,Input.KEY_SPACE});
    }
    public Tank(float x, float y, int team, int[] keys)
    {
        Animation[] anims = new Animation[ANIMAMT];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Game.SQUARESIZE, 1.5f*Game.SQUARESIZE, team == 0 ? "tankred":"tankblue"),100));
        ArrayList<Frame> dFrames = new ArrayList<>();
        dFrames.add(new Frame(new Sprite(2*Game.SQUARESIZE, 2*Game.SQUARESIZE, "xplosion0"),5));
        dFrames.add(new Frame(new Sprite(2*Game.SQUARESIZE, 2*Game.SQUARESIZE, "xplosion1"),5));
        dFrames.add(new Frame(new Sprite(2*Game.SQUARESIZE, 2*Game.SQUARESIZE, "xplosion2"),5));
        dFrames.add(new Frame(new Sprite(2*Game.SQUARESIZE, 2*Game.SQUARESIZE, "xplosion3"),5));
        dFrames.add(new Frame(new Sprite(2*Game.SQUARESIZE, 2*Game.SQUARESIZE, "xplosion4"),5));
        anims[AFKANIM] = new Animation(frames);
        anims[DEATHANIM] = new Animation(dFrames);
        ArrayList<Behavior> b = new ArrayList<>();
        b.add(new TankMovement(3, 0.05f,keys[0],keys[1],keys[2],keys[3]));
        b.add(new CannonBehavior(keys[4]));
        
        deathSound = new Sound("explosion");
        
        init(x, y, Game.SQUARESIZE, 1.5f*Game.SQUARESIZE, 0, true, HEROID, team, "Tank", anims, b);
    }
}
