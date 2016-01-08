package Graphics;

import Game.DeathMatch;
import Main.Matrix4f;
import Main.Time;
import Main.Vector2f;
import Main.Vector3f;
import Main.Vector4f;

public class Sprite 
{
    private static float[] sqrVertices = new float[]{
        -0.5f,-0.5f,0,
        +0.5f,-0.5f,0,
        -0.5f,+0.5f,0,
        +0.5f,+0.5f,0
    };
    private static float[] sqrTextVertices = new float[] {
        0,0,
        1,0,
        0,1,
        1,1
    };
    private static byte[] sqrIndices = new byte[]{
        0,2,1,
        1,2,3
    };
    private float sx;
    private float sy;
    private Shader shader;
    private VertexArray VAO;
    private Texture text;
    
    /**
     * Initializes the class with the chosen texture.
     * @param sx horizontal size
     * @param sy vertical size
     * @param texture loads the texture from 'res/textures/texture.png'
     */
    public Sprite(float sx, float sy,String texture)
    {
        Matrix4f scale = Matrix4f.scale(new Vector3f(sx,sy,1));
        text = new Texture(texture);
        shader = Shader.defShader;
        VAO = new VertexArray(scale.apply(sqrVertices), sqrIndices, sqrTextVertices);
        this.sx = sx;
        this.sy = sy;
    }
    /**
     * Initializes the class with the chosen texture.
     * @param sx horizontal size
     * @param sy vertical size
     * @param texture loads the texture from 'res/textures/texture.png'
     * @param shader loads the shader from 'res/shaders/shader.*'
     */
    public Sprite(float sx, float sy,String texture,String shader)
    {
        Matrix4f scale = Matrix4f.scale(new Vector3f(sx,sy,1));
        text = new Texture(texture);
        this.shader = new Shader(shader, shader);
        VAO = new VertexArray(scale.apply(sqrVertices), sqrIndices, sqrTextVertices);
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
        this(sx,sy,"lol");
    }
    /**
     * Renders the sprite
     * @param pos position to render the texture
     */
    public void render(Vector3f pos)
    {
        render(pos,0,1,1,1,1);
    }
    /**
     * Renders the sprite
     * @param pos position to render the texture
     */
    public void render(Vector2f pos)
    {
        render(new Vector3f(pos));
    }
    /**
     * Renders the sprite with specified color
     * @param pos position to render the texture
     * @param rot amount to rotate the texture
     * @param r red value of the color
     * @param g green value of the color
     * @param b blue value of the color
     * @param a alpha value of the color
     */
    public void render(Vector3f pos,float rot, float r, float g, float b, float a)
    {
        render(pos, rot, new Vector2f(sx, sy), r, g, b, a);
    }
//    public static Vector4f curColor = new Vector4f(.5f,.5f,.5f,1);
    /**
     * Render the texture with forced size parameters
     * @param pos position to render the texture
     * @param rot amount to rotate the texture
     * @param size size for the texture
     * @param r red value of the color
     * @param g green value of the color
     * @param b blue value of the color
     * @param a alpha value of the color
     */
    public void render(Vector3f pos,float rot, Vector2f size, float r, float g, float b, float a)
    {
        text.bind();
        shader.enable();
        shader.setUniformMat4f("ml_matrix", Matrix4f.translate(pos).multiply(Matrix4f.rotateZ(rot)).multiply(Matrix4f.scale(new Vector3f(size.getX()/this.sx,size.getY()/this.sy,1))));
        shader.setUniform4f("inColor", new Vector4f(1, 1, 1, 1));
//        shader.setUniform1f("time", DeathMatch.time);
//        shader.setUniform4f("seed", curColor);
        //shader.setUniform4f("seed", seed);
        VAO.render();
        shader.disable();
        text.unbind();
    }
    public float getsx()
    {
        return sx;
    }
    public float getsy()
    {
        return sy;
    }
}
