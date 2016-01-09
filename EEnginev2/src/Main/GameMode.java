
package Main;

/**
 *
 * @author emil
 */
public abstract class GameMode 
{
    public abstract void start();
    
    public abstract boolean update();
    
    public abstract void reset();
    
    public abstract int getMaxPlayers();
}
