
package Game;

import Main.Behavior;
import Main.Game;
import Main.Gameobject;
import Main.Vector2f;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emil
 */
public class PowerUpSpawner extends Gameobject
{
    private final Random rng;
    private final ArrayList<Powerup> pUps;
    
    public PowerUpSpawner()
    {
        init(0, 0, 0, 0, -1, "Spawner", new ArrayList<Behavior>());
        rng = new Random();
        pUps = new ArrayList<>();
        pUps.add(new TNTPowerUp());
        pUps.add(new ShotgunPowerUp());
        pUps.add(new LaserPowerUp());
        pUps.add(new RocketPowerUp());
        pUps.add(new SniperPowerUp());
        pUps.add(new ArmorPowerUp());
    }
    @Override
    public void update() 
    {
        if(rng.nextFloat() > 0.99) {
            Powerup p = null;
            try {
                p = pUps.get(rng.nextInt(pUps.size())).getClass().newInstance();
                Vector2f v = new Vector2f(100+rng.nextInt(600),100+rng.nextInt(400));
                p.setPos(v);
                while(p.checkCollisions()) {
                    v = new Vector2f(100+rng.nextInt(600),100+rng.nextInt(400));
                    p.setPos(v);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(PowerUpSpawner.class.getName()).log(Level.SEVERE, null, ex);
            }
            Game.initObject(p);
        }
    }
}
