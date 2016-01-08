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
public class Armor extends PowerUpBehavior {

    @Override
    public void start(Gameobject go) {
        del = new Delay(5000);
        del.start();
        go.setIsInvincible(true);
    }
    @Override
    public void update(Gameobject go) {
        super.update(go);
        if(go.getIsHit()) {
            remove(go);
        }
    }
    @Override
    public void remove(Gameobject go) {
        super.remove(go);
        go.setIsInvincible(false);
    }
    @Override
    public void render(Gameobject go) {
        
    }
    
}
