
package Graphics;

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
     * @return True if frame has ended.
     */
    public boolean render()
    {
        return render(1,1,1,1);
    }
    /**
     * Renders the sprite with specified color and updates age of frame.
     * @param r the red value of the color
     * @param g the green value of the color
     * @param b the blue value of the color
     * @param a the alpha value of the color
     * @return True if frame has ended.
     */
    public boolean render(float r, float g, float b, float a)
    {
        spr.render(r, g, b, a);
        numDisplayed++;
        
        if(numDisplayed >= length)
        {
            numDisplayed = 0;
            return true;
        }
        return false;
    }
}
