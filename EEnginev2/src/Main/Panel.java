
package Main;

public class Panel extends UIobject
{
    public Panel(float x,float y,float sx,float sy,boolean dis)
    {
        init(x,y,sx,sy,PANEL,dis);
    }
    public Panel(float x,float y,float sx,float sy,boolean dis,String texture)
    {
        init(x,y,sx,sy,PANEL,dis,texture);
    }
}
