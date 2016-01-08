
package Main;

import Graphics.Sprite;

public class UIobject
{
    public static final int 
            BUTTON = 0,
            PANEL = 1,
            TEXT = 2,
            MINMAP = 3;
    protected float sx,sy,rotation;
    protected Vector2f pos;
    protected Sprite spr;
    protected int type;
    private boolean displayed;
    public void update()
    {
        
    }
    public void getin()
    {
        
    }
    public void render()
    {
        if(isDisplayed()) {
            spr.render(new Vector3f(pos),0,new Vector2f(sx,sy),1,1,1,1);
        }
    }
    
    protected void init(float x,float y,float sx,float sy,int type,boolean displayed)
    {
        this.pos = new Vector2f(x,y);
        this.sx = sx;
        this.sy = sy;
        this.type = type;
        setDisplayed(displayed);
        this.spr = new Sprite(sx,sy);
    }
    protected void init(float x,float y,float sx,float sy,int type,boolean displayed, String texture)
    {
        this.pos = new Vector2f(x,y);
        this.sx = sx;
        this.sy = sy;
        this.type = type;
        setDisplayed(displayed);
        this.spr = new Sprite(sx,sy,texture);
    }
    
    public void setX(float x)
    {
        this.pos.setX(x);
    }
    public void setY(float y)
    {
        this.pos.setY(y);
    }
    public float getX()
    {
        return pos.getX();
    }
    public float getY()
    {
        return pos.getY();
    }
    public float getSX()
    {
        return spr.getsx();
    }
    public float getSY()
    {
        return spr.getsy();
    }
    public int gettype()
    {
        return type;
    }

    /**
     * @return the displayed
     */
    public boolean isDisplayed() {
        return displayed;
    }

    /**
     * @param displayed the displayed to set
     */
    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }
    public Vector2f getPos() {
        return pos;
    }
    public float getRotation() {
        return rotation;
    }
}
