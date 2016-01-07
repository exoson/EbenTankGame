
package Graphics;

import Main.Vector2f;
import Main.Vector3f;
import java.util.ArrayList;

public class Animation
{
    private final ArrayList<Frame> frames;
    private int curframe;
    
    public Animation(ArrayList<Frame> frames)
    {
        this.frames = frames;
        curframe = 0;
    }
    /**
     * Renders current frame and updates animation
     * @param pos position to render the texture
     * @return True if animation has ended.
     */
    public boolean render(Vector3f pos)
    {
        return render(pos,0,1,1,1,1);
    }
    /**
     * Renders current frame and updates animation with the spcified color
     * @param pos position to render the texture
     * @param rot amount to rotate the texture
     * @param r red value of the color
     * @param g green value of the color
     * @param b blue value of the color
     * @param a alpha value of the color
     * @return True if animation has ended.
     */
    public boolean render(Vector2f pos,float rot, float r, float g, float b, float a)
    {
        return render(new Vector3f(pos),rot,r,g,b,a);
    }
    /**
     * Renders current frame and updates animation with the spcified color
     * @param pos position to render the texture
     * @param rot amount to rotate the texture
     * @param r red value of the color
     * @param g green value of the color
     * @param b blue value of the color
     * @param a alpha value of the color
     * @return True if animation has ended.
     */
    public boolean render(Vector3f pos, float rot, float r, float g, float b, float a)
    {
        Frame temp = frames.get(curframe);
        
        if(temp.render(pos, rot, r, g, b, a))
        {
            curframe++;
            curframe %= frames.size();
            if(curframe == 0) return true; else return false;
        }
        return false;
    }
}
