
package Main;

import java.util.ArrayList;

public class Ui 
{
    private final ArrayList<UIobject> uiobs;
    
    public Ui()
    {
        uiobs = new ArrayList<>();
    }
    
    public void update()
    {
        for(UIobject u : uiobs) {
            u.update();
        }
    }
    public void render()
    {
        for(UIobject uo : uiobs)
        {
            uo.render();
        }
    }
    
    public void addobject(UIobject uo)
    {
        uiobs.add(uo);
    }
}
