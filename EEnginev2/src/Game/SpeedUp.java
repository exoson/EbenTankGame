/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Main.Delay;
import Main.Gameobject;

/**
 *
 * @author Lime
 */
public class SpeedUp extends PowerUpBehavior{

    private final int multiplier = 2;
    @Override
    public void start(Gameobject go) {
        del = new Delay(300);
        TankMovement tm = ((TankMovement)go.getBehavior("TankMovement"));
        tm.setSpeed(tm.getSpeed() * multiplier);
    }
    
    @Override
    protected void remove(Gameobject go) {
        super.remove(go);
        TankMovement tm = ((TankMovement)go.getBehavior("TankMovement"));
        tm.setSpeed(tm.getSpeed() / multiplier);
    }

    @Override
    public void render(Gameobject go) {
        
    }
    
}
