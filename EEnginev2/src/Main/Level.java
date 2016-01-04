
package Main;

public class Level 
{
    private final Map map;
    
    public Level(String mapname)
    {
        map = new Map(mapname);
    }
    
    public void render()
    {
        getMap().render();
    }
    public void update()
    {
        getMap().update();
    }

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }
}
