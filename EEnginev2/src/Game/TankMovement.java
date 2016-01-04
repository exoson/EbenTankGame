
package Game;

import Main.Behavior;
import Main.Gameobject;
import Main.Input;
import Main.Sound;
import Main.Vector2f;

public class TankMovement extends Behavior
{
    private static final int FORWARD = 0, 
            RIGHT = 1, 
            BACKWARD = 2, 
            LEFT = 3;
    private final int[] keys;
    
    private float speed, rotSpeed;
    private Sound movingSound;
    
    public TankMovement()
    {
        this(3,0.1f, Input.KEY_W, Input.KEY_D, Input.KEY_S, Input.KEY_A);
    }
    public TankMovement(float speed, float rotSpeed, int keyForward, int keyRight, int keyBackward, int keyLeft)
    {
        this.speed = speed;
        this.rotSpeed = rotSpeed;
        keys = new int[]{keyForward,keyRight,keyBackward,keyLeft};
        movingSound = new Sound("moving");
    }
    @Override
    public void start(Gameobject go) {
        
    }
    @Override
    public void render(Gameobject go) {
        
    }
    @Override
    public void update(Gameobject go) {
        Vector2f v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(speed);
        if(Input.getKey(keys[FORWARD])) {
            movingSound.startLooping();
            go.move(v.mult(-1));
        }else if(Input.getKey(keys[BACKWARD])) {
            movingSound.startLooping();
            go.move(v);
        } else {
            movingSound.endLooping();
        }
        float rot = (Input.getKey(keys[LEFT]) ? -rotSpeed : 0) + (Input.getKey(keys[RIGHT]) ? rotSpeed : 0);
        go.rotate(rot);
    }
    public void setForward(int key){
        keys[FORWARD] = key;
    }
    public void setBackward(int key){
        keys[BACKWARD] = key;
    }
    public void setLeft(int key){
        keys[LEFT] = key;
    }
    public void setRight(int key){
        keys[RIGHT] = key;
    }

    /**
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
