
package Main;

public abstract class Button extends UIobject
{
    
    public Button(int x,int y,int sx,int sy,boolean dis,String texture) {
        init(x,y,sx,sy,BUTTON,dis,texture);
    }
    public Button(int x,int y,int sx,int sy,boolean dis) {
        init(x,y,sx,sy,BUTTON,dis);
    }
    public Button(int x,int y,int size,boolean dis) {
        init(x,y,size,size,BUTTON,dis);
    }
    public Button(int x,int y,int size,boolean dis,String texture) {
        init(x,y,size,size,BUTTON,dis,texture);
    }
    @Override
    public void update() {
        if(Physics.pointInRectangle(this, Input.getMousePos())) {
            if(Input.getMousePressed(0)) {
                click0();
            }
            if(Input.getMousePressed(1)) {
                click1();
            }
        }
    }
    public abstract void click0();
    
    public abstract void click1();
    
}
