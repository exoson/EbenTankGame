
package Graphics;

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
     * @return True if animation has ended.
     */
    public boolean render()
    {
        return render(1,1,1,1);
    }
    /**
     * Renders current frame and updates animation with the spcified color
     * @param r red value of the color
     * @param g green value of the color
     * @param b blue value of the color
     * @param a alpha value of the color
     * @return True if animation has ended.
     */
    public boolean render(float r, float g, float b, float a)
    {
        Frame temp = frames.get(curframe);
        
        if(temp.render(r, g, b, a))
        {
            curframe++;
            curframe %= frames.size();
            if(curframe == 0) return true; else return false;
        }
        return false;
    }
}
