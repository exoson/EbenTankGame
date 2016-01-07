
package Game;

import Main.Delay;
import Main.DynamicText;
import Main.Game;
import Main.GameMode;
import Main.Gameobject;
import Main.Input;
import Main.Sound;
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
    
    @Override
    public void start() {
        endDelay = new Delay(2000);
        Game.setFow(false);
        reset();
        Game.adduiobj(new DynamicText(20, 10, "Player 1: 0", true) {
            @Override
            public void update() {
                str = "Player1: " + points1;
            }
        });
        Game.adduiobj(new DynamicText(670, 10, "Player 2: 0", true) {
            @Override
            public void update() {
                str = "Player 2: " + points2;
            }
        });
    }
    
    @Override
    public boolean update() {
        
        if(!endDelay.active()) {
            if(!Game.getObjects().contains(player1)) {
                endDelay.start();
            }
            if(!Game.getObjects().contains(player2)) {
                endDelay.start();
            }
        }
        return endDelay.over();
    }

    @Override
    public void reset() {
        ArrayList<Gameobject> gos = Game.getObjects();
        boolean player1Alive = gos.contains(player1);
        boolean player2Alive = gos.contains(player2);
        points1 += player1Alive ? player2Alive ? 0 : 1 : 0;
        points2 += player2Alive ? player1Alive ? 0 : 1 : 0;
        
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
        Game.getLevel().getMap().initRandomMap();
        Sound start = new Sound("start");
        start.playClip();
    }

    
}
