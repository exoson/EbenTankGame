
package Main;

import Graphics.Animation;
import java.util.ArrayList;

public abstract class Gameobject 
{
    public static final int ANIMAMT = 4;
    
    public static final int AFKANIM = 0,
            WALKANIM = 1,
            ATTANIM = 2,
            DEATHANIM = 3;
    
    public static final int HEROID = 0,MINIONID = 1,PROJECTILEID = 2,POWERUPID = 3;
    
    protected Sound deathSound;
    
    protected int curanim = AFKANIM;
    private int id;
    private int team;
    protected float sx,sy;
    private float rotation;
    private Vector2f pos;
    private Animation[] anim;
    private String name;
    private final boolean[] flags = new boolean[5];
    public Vector2f[] collided;
    
    protected ArrayList<Behavior> behaviors ,removedBehaviors = new ArrayList<>();
    
    public void update()
    {
        setIsHit(false);
        if(curanim == DEATHANIM) {
            setIsHit(true);
            if(getIsInvincible()) {
                curanim = AFKANIM;
            }
        }
        setIsDead((curanim == DEATHANIM));
        
        if(getIsDead()) {
            if(deathSound != null) deathSound.playClip();
        }
        
        for(Behavior b : behaviors) {
            b.update(this);
        }
        behaviors.removeAll(removedBehaviors);
        removedBehaviors.removeAll(removedBehaviors);
    }
    
    public void render()
    {
        float alpha = 1;
        if(!Game.getLevel().getMap().getVisibility((int)(getX()/Game.SQUARESIZE), 
                (int)(getY()/Game.SQUARESIZE),
                Game.getTeam()) && Game.getFow()) {
            alpha = 0;
        }
        if(curanim == DEATHANIM) {
            if(getAnims()[curanim].render(pos,rotation, new Vector2f(sx,sy),1,1,1,alpha)) {
                remove();
            }
        }else if(getAnims()[curanim].render(pos,rotation, new Vector2f(sx,sy),1,1,1,alpha)) {
            curanim = AFKANIM;
        }

        for(Behavior b : behaviors) {
            b.render(this);
        }
    }
    
    
    protected void init(float x, float y, float sx, float sy,float rotation, boolean isSolid,int id, int team, String name,Animation[] anims, ArrayList<Behavior> b)
    {
        this.setId(id);
        this.setName(name);
        this.setPos(new Vector2f(x,y));
        this.sx = sx;
        this.sy = sy;
        this.setRotation(rotation);
        this.flags[1] = isSolid;
        this.setTeam(team);
        anim = anims;
        this.behaviors = b;
        for(Behavior be : b) {
            be.start(this);
        }
    }
    protected void init(float x, float y, float sx, float sy,float rotation, boolean isSolid,int id,String name,Animation[] anims, ArrayList<Behavior> b)
    {
        init(x, y, sx, sy, rotation, isSolid, id, 0, name, anims, b);
    }
    protected void init(float x, float y, float sx, float sy,float rotation,int id,String name,Animation[] anims, ArrayList<Behavior> b) {
        init(x, y, sx, sy, rotation, true, id, name, anims, b);
    }
    protected void init(float x, float y, float sx, float sy,int id,String name,Animation[] anims, ArrayList<Behavior> b) {
        init(x, y, sx, sy, 0, true, id, name, anims, b);
    }
    protected void init(ArrayList<Behavior> b) {
        this.behaviors = b;
        for(Behavior be : b) {
            be.start(this);
        }
    }
    public boolean checkCollisions() {
        Square[][] sqrs = Game.getLevel().getMap().getSquares();
        for(int x = 0; x < sqrs.length; x++) {
            for(int y = 0; y < sqrs[x].length; y++) {
                if(sqrs[x][y].getWalkable()) continue;
                
                Vector2f[] corners = new Vector2f[4];
                corners[0] = new Vector2f((x-1/2f) * Game.SQUARESIZE, (y-1/2f) * Game.SQUARESIZE);
                corners[1] = new Vector2f((x+1/2f) * Game.SQUARESIZE, (y-1/2f) * Game.SQUARESIZE);
                corners[2] = new Vector2f((x-1/2f) * Game.SQUARESIZE, (y+1/2f) * Game.SQUARESIZE);
                corners[3] = new Vector2f((x+1/2f) * Game.SQUARESIZE, (y+1/2f) * Game.SQUARESIZE);
                if(Physics.rectRectCollision(this, corners)) {
                    collided = corners;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean move(Vector2f v)
    {
        if(getIsSolid()) {
            setPos(getPos().add(v));
            if(checkCollisions()) {
                setPos(getPos().minus(v));
                return false;
            }
        } else {
            setPos(getPos().add(v));
        }
        return true;
    }
    public boolean rotate(float amt)
    {
        if(getIsSolid()) {
            rotation += amt;
            if(checkCollisions()){
                rotation -= amt;
                return false;
            }
        } else {
            rotation += amt;
        }
        return true;
    }
    public void remove()
    {
        flags[0] = true;
    }
    public boolean getRemove()
    {
        return flags[0];
    }
    public boolean getIsSolid()
    {
        return flags[1];
    }
    public void setIsSolid(boolean val) {
        flags[1] = val;
    }
    public boolean getIsDead() {
        return flags[2];
    }
    public void setIsDead(boolean val) {
        flags[2] = val;
    }
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public float getX()
    {
        return getPos().getX();
    }
    public float getY()
    {
        return getPos().getY();
    }
    public float getSX()
    {
        return sx;
    }
    public float getSY()
    {
        return sy;
    }
    public void setX(float var)
    {
        getPos().setX(var);
    }
    public void setY(float var)
    {
        getPos().setY(var);
    }
    public void setSX(float var)
    {
        sx = var;
    }
    public void setSY(float var)
    {
        sy = var;
    }
    public void setAnim(int anim) {
        curanim = anim;
    }

    /**
     * @return the rotation
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * @param rotation the rotation to set
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * @return the pos
     */
    public Vector2f getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    /**
     * @return the team
     */
    public int getTeam() {
        return team;
    }
    
    /**
     * @param b adds the behavior in gameobject's behaviors
     */
    public void addBehaviors(Behavior b) {
        behaviors.add(b);
    }
    /**
     * @param name the name of the Behavior to be found.
     * @return the Behavior if found.
     */
    public Behavior getBehavior(String name) {
        for(Behavior b : behaviors) {
            String[] split = b.getClass().getName().split("\\.");
            if(split[split.length == 0 ? 0 : split.length-1].equals(name)) {
                return b;
            }
        }
        return null;
    }
    public void removeBehavior(Behavior b) {
        removedBehaviors.add(b);
    }
    /**
     * @param mod the scale to be set.
     */
    public void scale(float mod) {
        sx *= mod;
        sy *= mod;
    }

    /**
     * @return the anim
     */
    public Animation[] getAnims() {
        return anim;
    }
    /**
     * @param anims the Animation array to be set.
     */
    public void setAnims(Animation[] anims) {
        this.anim = anims;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     * @param deathSound the deathSound to set
     */
    public void setDeathSound(Sound deathSound) {
        this.deathSound = deathSound;
    }

    public boolean getIsInvincible() {
        return flags[3];
    }
    public void setIsInvincible(boolean b) {
        flags[3] = b;
    }
    public boolean getIsHit() {
        return flags[4];
    }
    public void setIsHit(boolean val) {
        flags[4] = val;
    }
}
