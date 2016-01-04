
package Game;

import Main.Gameobject;
import Main.Vector2f;

/**
 *
 * @author emil
 */
public class SinAmmo extends AmmoBase
{
    
    private float age = 0;
    
    public SinAmmo() {
        super();
    }
    @Override
    protected void move(Gameobject go) 
    {
        age += 1f/2;
        Vector2f v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2+Math.sin(age)),(float)Math.sin(go.getRotation()+Math.PI/2+Math.sin(age))).mult(-speed);
        if(!go.move(v)){
            go.setRotation(-go.getRotation());
            v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2+Math.sin(age)),(float)Math.sin(go.getRotation()+Math.PI/2+Math.sin(age))).mult(-speed);
            if(!go.move(v)) {
                go.setRotation((float)Math.PI+go.getRotation());
                v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2+Math.sin(age)),
                        (float)Math.sin(go.getRotation()+Math.PI/2+Math.sin(age))).mult(-speed);
                go.move(v);
            }
        }
    }
}
