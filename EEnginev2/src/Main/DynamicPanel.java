/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Lime
 */
public abstract class DynamicPanel extends Panel{

    public DynamicPanel(float x, float y, float sx, float sy, boolean dis) {
        super(x, y, sx, sy, dis);
    }
    public DynamicPanel(float x, float y, float sx, float sy, boolean dis, String texture) {
        super(x, y, sx, sy, dis, texture);
    }
    
    @Override
    public abstract void update();
    
}
