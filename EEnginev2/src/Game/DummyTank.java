
package Game;

import Graphics.Animation;
import Graphics.Frame;
import Graphics.Sprite;
import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import Main.Input;
import java.util.ArrayList;

public class DummyTank extends Gameobject
{
    public DummyTank(float x, float y, int team)
    {
        Animation[] anims = new Animation[ANIMAMT];
        ArrayList<Frame> frames = new ArrayList<>();
        frames.add(new Frame(new Sprite(Game.SQUARESIZE, 1.f*Game.SQUARESIZE),10));
        anims[AFKANIM] = new Animation(frames);
        anims[DEATHANIM] = new Animation(frames);
        ArrayList<Behavior> b = new ArrayList<>();
        b.add(new TankMovement(0, 0, Input.KEY_0, Input.KEY_0, Input.KEY_0, Input.KEY_0));
        
        b.add(new CannonBehavior(Input.KEY_0));
        init(x, y, Game.SQUARESIZE, 1.f*Game.SQUARESIZE, 0, true, HEROID, team, "Tank", anims, b);
    }
}
