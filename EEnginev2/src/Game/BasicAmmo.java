
package Game;

import Main.Gameobject;
import Main.Vector2f;

/**
 *
 * @author emil
 */
public class BasicAmmo extends AmmoBase {
    
    public BasicAmmo() {
        super();
    }

    @Override
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
}
