
package Main;

public class Stats 
{
    private float basehp,basedmg,armor,magres,speed,hpregen,attrange,hp;
    
    public Stats(float basehp,float basedmg
            ,float armor,float magres,float speed,float hpregen,float attrange)
    {
        this.basehp = basehp;
        this.basedmg = basedmg;
        this.armor = armor;
        this.magres = magres;
        this.speed = speed;
        this.hpregen = hpregen;
        this.attrange = attrange;
    }

    public float getBasehp() {
        return basehp;
    }

    public void setBasehp(float basehp) {
        this.basehp = basehp;
    }

    public float getBasedmg() {
        return basedmg;
    }

    public void setBasedmg(float basedmg) {
        this.basedmg = basedmg;
    }

    public float getArmor() {
        return armor;
    }

    public void setArmor(float armor) {
        this.armor = armor;
    }

    public float getMagres() {
        return magres;
    }

    public void setMagres(float magres) {
        this.magres = magres;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getHpregen() {
        return hpregen;
    }

    public void setHpregen(float hpregen) {
        this.hpregen = hpregen;
    }

    public float getAttrange() {
        return attrange;
    }

    public void setAttrange(float attrange) {
        this.attrange = attrange;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }
}
