
package Graphics;

import Main.Vector4f;
import Main.Game;
import Main.Matrix4f;
import Main.Vector2f;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 *
 * @author emil
 */
public class Renderer 
{
    private static Texture defTexture = new Texture("lol");
    public static void setColor(Vector4f c) {
        glColor4f(c.getR(), c.getG(), c.getB(),c.getA());
    }
    public static void setPointSize(float size) {
        glPointSize(size);
    }
    public static void setLineSize(float size) {
        glLineWidth(size);
    }
    public static void renderPoints(ArrayList<Vector2f> points, float size, Vector4f c) {
        glPushMatrix();
        {
            glDisable(GL_TEXTURE_2D);
            
            setPointSize(size);
            
            glBegin(GL_POINTS);
            for(Vector2f v : points) {
                glVertex2f(v.getX() * Game.SQUARESIZE, v.getY() * Game.SQUARESIZE);
            }
            glEnd();
            
            glEnable(GL_TEXTURE_2D);
        }
        glPopMatrix();
    }
    public static void renderLines(ArrayList<Vector2f> points, float size, Vector4f c) {
        FloatBuffer fb = BufferUtils.createFloatBuffer(points.size()*2);
        for (Vector2f point : points) {
            fb.put(point.getX());
            fb.put(point.getY());
        }
        fb.flip();
        int VAO = glGenVertexArrays();
        glBindVertexArray(VAO);
        
        int VBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);
        
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glBindVertexArray(0);
        
        glBindVertexArray(VAO);
        
        glEnableVertexAttribArray(0);
        
        setLineSize(size);
        Shader.defShader.enable();
        Shader.defShader.setUniformMat4f("ml_matrix", Matrix4f.identity());
        Shader.defShader.setUniform4f("inColor", c);
        defTexture.bind();
        glDrawArrays(GL_LINE_STRIP, 0, points.size());

        defTexture.unbind();
        Shader.defShader.disable();
        
        
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
                
        glDisableClientState(GL_VERTEX_ARRAY);
    }
    public static void renderSquares(ArrayList<Vector2f> corners, Vector4f c) {
        FloatBuffer fb = BufferUtils.createFloatBuffer(corners.size()*2);
        for (Vector2f point : corners) {
            fb.put(point.getX());
            fb.put(point.getY());
        }
        fb.flip();
        int VAO = glGenVertexArrays();
        glBindVertexArray(VAO);
        
        int VBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, VBO);
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);
        
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        glBindVertexArray(0);
        
        glBindVertexArray(VAO);
        
        glEnableVertexAttribArray(0);
        
        Shader.defShader.enable();
        Shader.defShader.setUniformMat4f("ml_matrix", Matrix4f.identity());
        Shader.defShader.setUniform4f("inColor", c);
        
        glDrawArrays(GL_QUADS, 0, corners.size());

        Shader.defShader.disable();
        
        
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
                
        glDisableClientState(GL_VERTEX_ARRAY);
    }
}
