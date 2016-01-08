package Main;

import java.util.ArrayList;

public class EEngine 
{
    public static ArrayList<Match> matches;
    public static boolean running;
    public static void main(String[] args)
    {
        initgame();
        gameLoop();
        cleanup();
    }

    private static void gameLoop()
    {
        Time.init();
        running = true;
        while(running) {
            Time.update();
            update();
            render();
            sync();
        }
    }
    private static Delay frameDel = new Delay(1000/60);
    private static void sync() {
        while(!frameDel.over() && frameDel.active()) {
            
        }
        frameDel.start();
    }
    public static void gameloopapp()
    {
        update();
        render();
    }
    private static void initgame() {
        
    }

    public static void cleanup()
    {
    }

    private static void update()
    {
    }

    private static void render() 
    {
    }
}
