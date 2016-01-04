
package Main;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GameApplet extends Applet
{
    Canvas display_parent;
    public Thread gameThread;
    boolean running;
    
    @Override
    public void init()
    {
        setLayout(new BorderLayout());
        try 
        {
            display_parent = new Canvas()
            {
                @Override
            public final void addNotify() 
            {
                    super.addNotify();
                    startLWJGL();
            }
                @Override
            public final void removeNotify() 
            {
                    stopLWJGL();
                    super.removeNotify();
            }
            };
            display_parent.setSize(getWidth(),getHeight());
            add(display_parent);
            display_parent.setFocusable(true);
            display_parent.requestFocus();
            display_parent.setIgnoreRepaint(true);
            setVisible(true);
        }
        catch (Exception e)
        {
                System.err.println(e);
                throw new RuntimeException("Unable to create display");
        }
        
    }
    @Override
    public void start()
    {
        
    }
    @Override
    public void stop()
    {
        
    }
    @Override
    public void destroy()
    {
        remove(display_parent);
        super.destroy();
    }
    public void startLWJGL()
    {
        gameThread = new Thread()
        {
            @Override
            public void run()
            {
                running = true;
                try 
                {
                    Display.setParent(display_parent);
                    Display.setVSyncEnabled(true);
                    Display.setTitle(EEngine.gamename);
                    Display.create();
                    Keyboard.create();
                    Mouse.create();
                } 
                catch (LWJGLException e) 
                {
                    e.printStackTrace();
                    return;
                }
                EEngine.init();
                gameloop();
            }
        };
        gameThread.start();
    }
    public void gameloop()
    {
        while(running)
        {
            EEngine.gameloopapp();
        }
        EEngine.cleanup();
    }
    private void stopLWJGL() 
    {
        running = false;
        try
        {
            gameThread.join();
        } 
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
