
package Game;

import Graphics.Sprite;
import Main.Delay;
import Main.Game;
import Main.GameMode;
import Main.Gameobject;
import Main.Input;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author emil
 */
public class DeathMatch extends GameMode
{
    private Delay endDelay;
    
    private Tank player1, player2;
    private int points1,points2;
    private Sprite empty = new Sprite(50,50,"powerupempty");
    @Override
    public void start() {
        endDelay = new Delay(2000);
        Game.setFow(false);
        reset();
    }
    @Override
    public boolean update() {
        if(!endDelay.active()) {
            if(player1.getIsDead()) {
                endDelay.start();
            } else if(player2.getIsDead()) {
                endDelay.start();
            }
        } else if(!player1.getIsDead() && !player2.getIsDead()) {
            endDelay.terminate();
        }
        
        return endDelay.over();
    }

    @Override
    public void reset() {
        ArrayList<Gameobject> gos = Game.getObjects();
        if(player1 != null) {
            boolean player1Alive = !player1.getIsDead();
            boolean player2Alive = !player2.getIsDead();
            points1 += player1Alive ? player2Alive ? 0 : 1 : 0;
            points2 += player2Alive ? player1Alive ? 0 : 1 : 0;
        }
        gos.removeAll(gos);
        Random rng = new Random(); 
        endDelay.terminate();
        player1 = new Tank(100+rng.nextInt(300),
                100+rng.nextInt(400),
                0,
                new int[]{
                    Input.KEY_W,
                    Input.KEY_D,
                    Input.KEY_S,
                    Input.KEY_A,
                    Input.KEY_Q
                });
        player2 = new Tank(400+rng.nextInt(300),
                100+rng.nextInt(400),
                1,
                new int[]{
                    Input.KEY_UP,
                    Input.KEY_RIGHT,
                    Input.KEY_DOWN,
                    Input.KEY_LEFT,
                    Input.KEY_SPACE
                });
        Game.addObject(player1);
        Game.addObject(player2);
        Game.initObject(new PowerUpSpawner());
        Game.getMap().initRandomMap();
    }

    @Override
    public int getMaxPlayers() {
        return 2;
    }

    
}
