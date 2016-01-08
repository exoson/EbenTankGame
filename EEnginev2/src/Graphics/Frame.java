
package Graphics;

import Main.Vector2f;
import Main.Vector3f;

public class Frame 
{
    private final int length;
    private final Sprite spr;
    private int numDisplayed;
    
    public Frame(Sprite spr, int length)
    {
        this.spr = spr;
        this.length = length;
        numDisplayed = 0;
    }
    /**
     * Renders sprite and updates age of frame.
     * @param pos position to render the texture
     * @return True if frame has ended.
     */
    public boolean render(Vector3f pos)
    {
        return render(pos,0,new Vector2f(getSX(),getSY()),1,1,1,1);
    }
    /**
     * Renders the sprite with specified color and updates age of frame.
     * @param pos position to render the texture
     * @param rot amount to rotate the texture
     * @param size size to be rendered in
     * @param r the red value of the color
     * @param g the green value of the color
     * @param b the blue value of the color
     * @param a the alpha value of the color
     * @return True if frame has ended.
     */
    public boolean render(Vector3f pos,float rot,Vector2f size,float r, float g, float b, float a)
    {
        getSpr().render(pos,rot,size, r, g, b, a);
        numDisplayed++;
        
        if(numDisplayed >= length)
        {
            numDisplayed = 0;
            return true;
        }
        return false;
    }
    public float getSX() {
        return getSpr().getsx();
    }
    public float getSY() {
        return getSpr().getsy();
    }

    /**
     * @return the spr
     */
    public Sprite getSpr() {
        return spr;
    }
}
