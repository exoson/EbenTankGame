package Graphics;

import Main.EEngine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sprite 
{
    private static final Texture defText = loadtexture("lol");
    private float sx;
    private float sy;
    private final Texture text;
    
    /**
     * Initializes the class with the chosen texture.
     * @param sx horizontal size
     * @param sy vertical size
     * @param texture loads the texture from 'res/textures/texture.png'
     */
    public Sprite(float sx, float sy,String texture)
    {
        if(texture == null)
        {
            text = loadtexture("lol");
        }else{
            text = loadtexture(texture);
        }
        this.sx = sx;
        this.sy = sy;
    }
    /**
     * Initializes the class with test texture.
     * @param sx horizontal size
     * @param sy vertical size
     */
    public Sprite(float sx, float sy)
    {
        this.sx = sx;
        this.sy = sy;
        text = loadtexture("lol");
    }
    /**
     * Renders the sprite
     */
    public void render()
    {
        text.bind();
        glColor3f(1,1,1);
        
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0,0); glVertex2f(-sx/2,-sy/2);
            glTexCoord2f(1,0); glVertex2f(sx/2,-sy/2);
            glTexCoord2f(1,1); glVertex2f(sx/2,sy/2);
            glTexCoord2f(0,1); glVertex2f(-sx/2,sy/2);
        }
        glEnd();
        
    }
    /**
     * Renders the sprite with specified color
     * @param r red value of the color
     * @param g green value of the color
     * @param b blue value of the color
     * @param a alpha value of the color
     */
    public void render(float r, float g, float b, float a)
    {
        text.bind();
        glColor4f(r,g,b,a);
        
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0,0); glVertex2f(-sx/2,-sy/2);
            glTexCoord2f(1,0); glVertex2f(sx/2,-sy/2);
            glTexCoord2f(1,1); glVertex2f(sx/2,sy/2);
            glTexCoord2f(0,1); glVertex2f(-sx/2,sy/2);
        }
        glEnd();
        
    }
    /**
     * Render the texture with forced size parameters
     * @param sx Horizontal size for texture
     * @param sy Vertical size for texture
     */
    public static void render(float sx,float sy)
    {
        defText.bind();
        glColor3f(1,1,1);
        
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0,0); glVertex2f(-sx/2,-sy/2);
            glTexCoord2f(1,0); glVertex2f(sx/2,-sy/2);
            glTexCoord2f(1,1); glVertex2f(sx/2,sy/2);
            glTexCoord2f(0,1); glVertex2f(-sx/2,sy/2);
        }
        glEnd();
        
    }
    public float getsx()
    {
        return sx;
    }
    public float getsy()
    {
        return sy;
    }
    public void setsx(float sx)
    {
        this.sx = sx;
    }
    public void setsy(float sy)
    {
        this.sy = sy;
    }
    /**
     * 
     * @param key Specifies which texture to load.
     * @return Returns loaded texture.
     */
    public static Texture loadtexture(String key)
    {
        try
        {
            return TextureLoader.getTexture("PNG", ResourceLoader.getResource("res/textures/" + key + ".png").openStream());
        }
        catch (IOException ex)
        {
            System.out.println(key);
            Logger.getLogger(EEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
