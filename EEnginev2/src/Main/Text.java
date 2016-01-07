
package Main;

import Graphics.Textrenderer;

public class Text extends UIobject
{
    protected String str;
    protected Vector4f color;
    public Text(int x,int y,String str,boolean displayed)
    {
        this(x,y,str,new Vector4f(1,1,1,1),displayed);
    }
    public Text(int x,int y,String str,Vector4f color,boolean displayed)
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
