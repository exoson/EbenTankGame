
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
    private Powerup pUp;
    
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
        if(go.getIsDead()) return;
        
        if(Input.getKeyPressed(shootingKey)) {
            // FIXME implement this in some nicer way

            if(isReloaded) {
                Vector2f v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(-go.getSY());
                Game.initObject(cannon.getAmmo(go.getX() + v.getX(), go.getY() + v.getY(), go.getRotation(), shootingKey, this));
                isReloaded = false;
                if(!BasicAmmo.class.isInstance(cannon.getBehavior())) {
                    cannon = new Cannon(new BasicAmmo());
                    pUp = null;
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
    public boolean setCannon(Cannon c, Powerup p) {
        if(BasicAmmo.class.isInstance(cannon.getBehavior())) {
            cannon = c;
            pUp = p;
            isReloaded = true;
            return true;
        }
        return false;
    }
    public void reload() {
        isReloaded = true;
    }

    /**
     * @return the pUp
     */
    public Powerup getpUp() {
        return pUp;
    }
}
