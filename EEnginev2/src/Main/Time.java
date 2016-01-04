
package Main;

public class Time 
{
    public static final long DAMPING = 10000000;
    private static long curtime;
    private static long lasttime;
    private static Delay secdel;
    private static int frames;
    
    public static long gettime()
    {
        return System.nanoTime();
    }
    public static float getdelta()
    {
        return (curtime - lasttime) / DAMPING;
    }
    public static void update()
    {
        lasttime = curtime;
        curtime = gettime();
//        System.out.println("Fps: " + 100 / getdelta());
//        if(secdel.over())
//        {
//            System.out.println("Fps: " + frames);
//            frames = 0;
//            secdel.start();
//        }
//        else 
//        {
//            frames++;
//        }
    }
    public static void init()
    {
        lasttime = gettime();
        curtime = gettime();
        secdel = new Delay(1000);
        secdel.end();
    }
}
