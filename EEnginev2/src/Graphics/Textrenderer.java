
package Graphics;

import Main.Matrix4f;
import Main.Vector3f;
import Main.Vector4f;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import org.lwjgl.BufferUtils;
import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;
import org.lwjgl.stb.*;


public class Textrenderer
{
    private static TrueTypeFont font;
    
    public static void init()
    {
        font = new TrueTypeFont(new Font("Times New Roman",0,25), false);
//        font = new TrueTypeFont("chars","res/textures/charLocations.loc");
    }
    /**
     * Renders a string with prespecified font.
     * @param s String to be rendered
     * @param x x coordinate for where to render the text.
     * @param y y coordinate for where to render the text.
     * @param color
     */
    public static void drawString(String s,float x,float y,Vector4f color)
    {
        font.drawString(x, y, s, color);
    }
}