package Main;

import Game.DeathMatch;
import Graphics.Shader;
import Graphics.Textrenderer;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE1;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.system.MemoryUtil.NULL;

public class EEngine 
{
    private static long window;
    private static int width = 800, height = 600;
    static String gamename = "2dMobo";
    public static final int GAME = 0,MENU = 1;
    private static final State[] states = new State[2];
    public static int curstate = GAME;
    public static boolean running;
    public static void main(String[] args)
    {
        initDisplay();
        initgame();
        gameLoop();
        cleanup();
    }

    private static void gameLoop()
    {
        Time.init();
        running = true;
        while(running)
        {
            Input.update();
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
    private static void initgame()
    {
        Game.initGame(new DeathMatch());
        Game.getGameMode().start();
        Textrenderer.init();
        states[GAME] = Game.game;
        states[MENU] = Game.menu;
    }

    public static void cleanup()
    {
        Input.cleanUp();
        glfwDestroyWindow(getWindow());
        glfwTerminate();
    }

    private static void initDisplay()
    {
        
        if(glfwInit() != GL_TRUE) {
            System.err.println("GLFW initialization failed!");
        }
//        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);

        window = glfwCreateWindow(width, height, gamename, NULL, NULL);

        if(getWindow() == NULL) {
            System.err.println("Could not create our Window!");
        }

        // creates a bytebuffer object 'vidmode' which then queries 
        // to see what the primary monitor is. 
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        
        // Sets the initial position of our game window. 
        glfwSetWindowPos(getWindow(), 100, 100);
        
        
        Input.init();

        glfwMakeContextCurrent(getWindow());
        
        glfwShowWindow(getWindow());
        
        GL.createCapabilities();

        glClearColor(0,0,0,1);


        glActiveTexture(GL_TEXTURE1);

        glDisable(GL_DEPTH_TEST);

        Shader.loadAll();
    }

    private static void update() 
    {
        glfwPollEvents();
        states[curstate].update();
    }

    private static void render() 
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        
        states[curstate].render();
        
        int i = glGetError();
        if(i != GL_NO_ERROR)
            System.err.println(i);

        glfwSwapBuffers(getWindow());
    }

    /**
     * @return the window
     */
    public static long getWindow() {
        return window;
    }
}
