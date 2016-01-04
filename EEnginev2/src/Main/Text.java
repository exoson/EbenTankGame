
package Main;

import Graphics.Textrenderer;
import org.newdawn.slick.Color;

public class Text extends UIobject
{
    protected String str;
    private final Color color;
    public Text(int x,int y,String str,Color color,boolean displayed)
    {
        this.str = str;
        this.color = color;
        init(x,y,1,1,TEXT,displayed);
    }
    @Override
    public void render()
    {
        if(isDisplayed()) {
            Textrenderer.drawString(str, getX(), getY(), color);
        }
    }
}
