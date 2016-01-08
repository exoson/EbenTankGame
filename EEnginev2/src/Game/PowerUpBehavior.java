/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Main.Behavior;
import Main.Delay;
import Main.Gameobject;

/**
 *
 * @author Lime
 */
public abstract class PowerUpBehavior extends Behavior{

    protected Delay del;
    
    @Override
    public abstract void start(Gameobject go);

    @Override
    public void update(Gameobject go) {
        if(del.over()) {
            remove(go);
        }
    }

    protected void remove(Gameobject go) {
        go.removeBehavior(this);
    }
    
    @Override
    public abstract void render(Gameobject go);
    
}
