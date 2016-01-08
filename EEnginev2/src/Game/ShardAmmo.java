/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Main.Gameobject;
import Main.Vector2f;

/**
 *
 * @author Lime
 */
public class ShardAmmo extends AmmoBase
{
    private boolean hit;
    @Override
    public void move(Gameobject go) {
        Vector2f v = new Vector2f((float)Math.cos(go.getRotation()+Math.PI/2),(float)Math.sin(go.getRotation()+Math.PI/2)).mult(-speed);
        if(!hit && !go.move(v)){
            hit = true;
        }
    }
}
