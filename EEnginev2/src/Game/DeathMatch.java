
package Game;

import Graphics.Sprite;
import Main.Delay;
import Main.DynamicPanel;
import Main.DynamicText;
import Main.Game;
import Main.GameMode;
import Main.Gameobject;
import static Main.Gameobject.AFKANIM;
import Main.Input;
import Main.Sound;
import Main.Vector4f;
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
        Game.adduiobj(new DynamicText(20, 10, "Player 1: 0", true) {
            @Override
            public void update() {
                str = "Player1: " + points1;
            }
        });
        Game.adduiobj(new DynamicText(700, 10, "Player 2: 0", true) {
            @Override
            public void update() {
                str = "Player 2: " + points2;
            }
        });
        Game.adduiobj(new DynamicPanel(200, 25, 50, 50, true, "powerupempty") {
            @Override
            public void update() {
                Powerup p = ((CannonBehavior)player1.getBehavior("CannonBehavior")).getpUp();
                if(p != null){
                    this.spr = p.getAnims()[AFKANIM].getFrames().get(0).getSpr();
                } else {
                    this.spr = empty;
                }
            }
        });
        Game.adduiobj(new DynamicPanel(650, 25, 50, 50, true, "powerupempty") {
            @Override
            public void update() {
                Powerup p = ((CannonBehavior)player2.getBehavior("CannonBehavior")).getpUp();
                if(p != null){
                    this.spr = p.getAnims()[AFKANIM].getFrames().get(0).getSpr();
                } else {
                    this.spr = empty;
                }
            }
        });
    }
    private Random rng = new Random();
    public static float time = 0;
    @Override
    public boolean update() {
//        time += 1f/100;
//        float r = Sprite.curColor.getR()+(rng.nextFloat()-0.5f)/10;
//        float g = Sprite.curColor.getG()+(rng.nextFloat()-0.5f)/10;
//        float b = Sprite.curColor.getB()+(rng.nextFloat()-0.5f)/10;
//        r = Math.max(Math.min(r, 1),0);
//        g = Math.max(Math.min(g, 1),0);
//        b = Math.max(Math.min(b, 1),0);
//        Sprite.curColor = new Vector4f(r,g,b,Sprite.curColor.getA());
        if(!endDelay.active()) {
            if(player1.getIsDead()) {
                endDelay.start();
            }
            if(player2.getIsDead()) {
                endDelay.start();
            }
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
        Game.getLevel().getMap().initRandomMap();
        Sound start = new Sound("start");
        start.playClip();
    }

    
}
