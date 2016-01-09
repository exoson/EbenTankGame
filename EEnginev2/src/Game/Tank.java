
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import Main.Input;
import java.util.ArrayList;

public class Tank extends Gameobject
{
    
    public Tank(float x, float y, int team) {
        this(x,y,team,new int[]{Input.KEY_W,Input.KEY_D,Input.KEY_S,Input.KEY_A,Input.KEY_SPACE});
    }
    public Tank(float x, float y, int team, int[] keys)
    {
        ArrayList<Behavior> b = new ArrayList<>();
        b.add(new TankMovement(3, 0.05f,keys[0],keys[1],keys[2],keys[3]));
        b.add(new CannonBehavior(keys[4]));
        
        init(x, y, Game.SQUARESIZE, 1.5f*Game.SQUARESIZE, 0, true, HEROID, team, "Tank", b);
    }
}
