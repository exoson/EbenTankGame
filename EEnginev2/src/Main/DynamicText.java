
package Main;


/**
 *
 * @author emil
 */
public abstract class DynamicText extends Text{
    
    public DynamicText(int x, int y, String str, boolean displayed) {
        super(x, y, str, displayed);
    }
    
    @Override
    public abstract void update();
    
}
