
package Game;

import Graphics.Animation;
import Main.Behavior;
import Main.Gameobject;
import Main.Vector2f;
import java.util.ArrayList;

public class Ammo extends Gameobject
{
    public final int shootingKey;
    public final CannonBehavior shooter;
    
    public Ammo(float x, float y, float sx, float sy, float rot, Behavior behavior, int shootingKey)
    {
        ArrayList<Behavior> b = new ArrayList<>();
        b.add(behavior);
        init(x, y, sx, sy, rot, true, PROJECTILEID, -1, "Ammo", b);
        this.shootingKey = shootingKey;
        shooter = null;
    }
    public Ammo(float x, float y, float rot, Behavior b, int shootingKey, CannonBehavior shooter) {
        ArrayList<Behavior> temp = new ArrayList<>();
        temp.add(b);
        init(temp);
        this.setPos(new Vector2f(x,y));
        this.setRotation(rot);
        this.setTeam(-1);
        this.shootingKey = shootingKey;
        this.shooter = shooter;
    }
}
