
package Game;

import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import Main.Input;
import Main.Vector2f;

public class CannonBehavior extends Behavior {
    
    private int shootingKey;
    
    private boolean isReloaded;
    private Cannon cannon;
    
    public CannonBehavior(int sKey)
    {
        this.shootingKey = sKey;
        this.cannon = new Cannon(new BasicAmmo());
        this.isReloaded = true;
    }
    @Override
    public void start(Gameobject go) {
        
    }
    @Override
    public void render(Gameobject go) {
        
    }
    @Override
    public void update(Gameobject go) {
        if(Input.getKeyPressed(shootingKey)){
            // FIXME implement this in some nicer way

            if(isReloaded) {
                Vector2f v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(-go.getSY());
                Game.initObject(cannon.getAmmo(go.getX() + v.getX(), go.getY() + v.getY(), go.getRotation(), shootingKey, this));
                isReloaded = false;
                if(!BasicAmmo.class.isInstance(cannon.getBehavior())) {
                    cannon = new Cannon(new BasicAmmo());
                }
            }
        }
    }
    /**
     * @param shootingKey the shootingKey to set
     */
    public void setShootingKey(int shootingKey) {
        this.shootingKey = shootingKey;
    }

    public int getShootingKey() {
        return shootingKey;
    }
    public boolean setCannon(Cannon c) {
        if(BasicAmmo.class.isInstance(cannon.getBehavior())) {
            cannon = c;
            isReloaded = true;
            return true;
        }
        return false;
    }
    public void reload() {
        isReloaded = true;
    }
}
