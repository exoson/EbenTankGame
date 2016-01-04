
package Main;

import org.newdawn.slick.Color;

/**
 *
 * @author emil
 */
public abstract class DynamicText extends Text{
    
    public DynamicText(int x, int y, String str, Color color, boolean displayed) {
        super(x, y, str, color, displayed);
    }
    
    @Override
    public abstract void update();
    
}
