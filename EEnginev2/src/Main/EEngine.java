package Main;

import Game.DeathMatch;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EEngine 
{
    public static Server s;
    public static ArrayList<Match> matches = new ArrayList<>();
    public static boolean running;
    public static void main(String[] args) {
        initServer();
        gameLoop();
        cleanup();
    }

    private static void gameLoop() {
        Time.init();
        running = true;
        while(running) {
            Time.update();
            update();
        }
    }
    private static void initServer() {
        s = new Server();
        Thread t = new Thread(s);
        t.start();
    }
    private static final GameMode curGM = new DeathMatch();
    private static void update() {
        for(Match m : matches) {
            m.update();
        }
        ArrayList<ClientServer> nextMatch = new ArrayList<>();
        for(ClientServer cs : s.getCs()) {
            if(!cs.isInMatch()) {
                nextMatch.add(cs);
            }
            if(nextMatch.size() == curGM.getMaxPlayers()) {
                try {
                    matches.add(new Match(curGM.getClass().newInstance(),nextMatch));
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(EEngine.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static void cleanup()
    {
        s.stop();
    }
}
