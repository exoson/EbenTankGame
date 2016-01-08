/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Graphics.Renderer;
import Main.Delay;
import Main.Gameobject;
import Main.Vector2f;
import Main.Vector4f;
import java.util.ArrayList;

/**
 *
 * @author Lime
 */
public class SniperCrosshair extends PowerUpBehavior{

    private static final int LENGTH = 100, SPEED = 10;
    
    
    @Override
    public void start(Gameobject go) {
        del = new Delay(6000);
        del.start();
    }


    
    @Override
    public void render(Gameobject go) {
        ArrayList<Vector2f> points = new ArrayList<>();
        Gameobject g = new Gameobject() {
        };
        g.setPos(go.getPos());
        g.setIsSolid(true);
        g.setSX(10);
        g.setSY(10);
        g.setRotation(go.getRotation());
        Vector2f v = new Vector2f((float)Math.cos(g.getRotation()+Math.PI/2),(float)Math.sin(g.getRotation()+Math.PI/2)).mult(-SPEED);
        while(g.move(v)) {
            points.add(g.getPos());
        }
        Renderer.renderLines(points, 5, new Vector4f(0.7f,0,0.5f,1));
    }
    
}
