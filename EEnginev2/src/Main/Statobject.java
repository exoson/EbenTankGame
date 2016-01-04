
package Main;

import Graphics.Animation;
import java.util.ArrayList;

public class Statobject extends Gameobject
{
    public static final float SRANGE = 400;
    public static final float DAMPING = 0.7f;
    
    private Animation[] projanims;
    private boolean ranged;
    private float basehp,basedmg,armor,magres,speed,hpregen,attrange,hp,projspeed,projsx,projsy;
    private long attspeed;
    protected Statobject target;
    protected Vector2f targetv;
    protected Delay attdel;
    public void init(float x, float y, float sx, float sy,int id,String name,Animation[] anims, ArrayList<Behavior> b,float basehp,float basedmg
            ,float armor,float magres,float speed,float hpregen,float attrange,long attspeed)
    {
        setRanged(false);
        init(x,y,sx,sy,id,name,anims,b);
        setTargetv(getPos());
        this.setArmor(armor);
        this.setAttrange(attrange);
        this.setAttspeed(attspeed);
        this.setBasedmg(basedmg);
        this.setBasehp(basehp);
        this.setHpregen(hpregen);
        this.setMagres(magres);
        this.setSpeed(speed);
        attdel = new Delay(attspeed);
        attdel.end();
        setHp(basehp);
    }
    public void init(float x, float y, float sx, float sy,int id,String name,Animation[] anims,ArrayList<Behavior> b,float basehp,float basedmg
            ,float armor,float magres,float speed,float hpregen,float attrange,long attspeed,float projsx,float projsy,float projspeed,Animation[] projanims)
    {
        setRanged(true);
        this.projanims = projanims;
        this.projspeed = projspeed;
        this.projsx = projsx;
        this.projsy = projsy;
        init(x,y,sx,sy,id,name,anims,b);
        setTargetv(getPos());
        this.setArmor(armor);
        this.setAttrange(attrange);
        this.setAttspeed(attspeed);
        this.setBasedmg(basedmg);
        this.setBasehp(basehp);
        this.setHpregen(hpregen);
        this.setMagres(magres);
        this.setSpeed(speed);
        attdel = new Delay(attspeed);
        attdel.end();
        setHp(basehp);
    }
    @Override
    public void update()
    {
        if(hp <= 0) {
            die();
        }
        if(target != null) {
            if(target.getHp() <= 0){
                target = null;
            }
        }
    }
    protected void attack() 
    {
        if(!ranged){
            target.dmg(basedmg);
            attdel.start();
        }else{
            Game.initObject(new Projectile(target,basedmg,projspeed,getX(),getY(),projsx,projsy,projanims, new ArrayList<Behavior>()));
            attdel.start();
        }
        
    }
    protected boolean chase()
    {
        Vector2f movev = getTargetv().minus(getPos());
        if(movev.length() < movev.normalize().mult(speed * Time.getdelta() * DAMPING).length()) {
            Vector2f temp = getPos().add(movev);
            if(temp.getX() < 0 || temp.getX() > Map.MAPSIZE * Game.SQUARESIZE || temp.getY() < 0 || temp.getY() > Map.MAPSIZE * Game.SQUARESIZE) {
                return false;
            }
            setPos(temp);
            return true;
        }
        else {
            Vector2f temp = getPos().add(movev.normalize().mult(speed * Time.getdelta() * DAMPING));
            if(temp.getX() < 0 || temp.getX() > Map.MAPSIZE * Game.SQUARESIZE - sx || temp.getY() < 0 || temp.getY() > Map.MAPSIZE * Game.SQUARESIZE - sy) {
                return false;
            }
            setPos(temp);
            return true;
        }
    }
    public void dmg(float amt)
    {
        setHp(getHp() - amt);
    }
    public void settarget(Statobject s)
    {
        setTarget(s);
    }
    protected void die(){
        curanim = DEATHANIM;
    }
    /**
     * @return the basehp
     */
    public float getBasehp() {
        return basehp;
    }

    /**
     * @param basehp the basehp to set
     */
    public void setBasehp(float basehp) {
        this.basehp = basehp;
    }

    /**
     * @return the basedmg
     */
    public float getBasedmg() {
        return basedmg;
    }

    /**
     * @param basedmg the basedmg to set
     */
    public void setBasedmg(float basedmg) {
        this.basedmg = basedmg;
    }

    /**
     * @return the armor
     */
    public float getArmor() {
        return armor;
    }

    /**
     * @param armor the armor to set
     */
    public void setArmor(float armor) {
        this.armor = armor;
    }

    /**
     * @return the magres
     */
    public float getMagres() {
        return magres;
    }

    /**
     * @param magres the magres to set
     */
    public void setMagres(float magres) {
        this.magres = magres;
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

    /**
     * @return the hpregen
     */
    public float getHpregen() {
        return hpregen;
    }

    /**
     * @param hpregen the hpregen to set
     */
    public void setHpregen(float hpregen) {
        this.hpregen = hpregen;
    }

    /**
     * @return the attrange
     */
    public float getAttrange() {
        return attrange;
    }

    /**
     * @param attrange the attrange to set
     */
    public void setAttrange(float attrange) {
        this.attrange = attrange;
    }

    /**
     * @return the attspeed
     */
    public float getAttspeed() {
        return attspeed;
    }

    /**
     * @param attspeed the attspeed to set
     */
    public void setAttspeed(long attspeed) {
        this.attspeed = attspeed;
    }

    /**
     * @return the target
     */
    public Statobject getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(Statobject target) {
        this.target = target;
    }

    /**
     * @return the targetv
     */
    public Vector2f getTargetv() {
        return targetv;
    }

    /**
     * @param targetv the targetv to set
     */
    public void setTargetv(Vector2f targetv) {
        this.targetv = targetv;
    }

    /**
     * @return the hp
     */
    public float getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    public void setHp(float hp) {
        this.hp = hp;
    }

    /**
     * @return the ranged
     */
    public boolean isRanged() {
        return ranged;
    }

    /**
     * @param ranged the ranged to set
     */
    public void setRanged(boolean ranged) {
        this.ranged = ranged;
    }

}
