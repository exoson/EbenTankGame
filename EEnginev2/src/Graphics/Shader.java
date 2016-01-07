package Graphics;


import Main.Vector4f;
import Main.Matrix4f;
import Main.Vector2f;
import Main.Vector3f;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;


public class Shader {
	// We used these variables to set where we 
    // would be storing both our vertex and texture
    // coordinates in our Vertex Array Object!
	public static final int VERTEX_ATTRIB = 0;
	public static final int TEXTURE_COORDS_ATTRIB = 1;
	// our way to identify what shader this is.
    private final int ID;
    // A performance increasing tactic taught I learned from 
    // @TheCherno - You should definitely check out his
    // tutorials as I based the starting code on some of 
    // the stuff I learned from his videos!
    private Map<String, Integer> locationCache = new HashMap<>();
    
    private boolean enabled = false;
    
    public static Shader defShader;
	
	public Shader(String vertex, String fragment){
		ID = ShaderUtils.load(vertex, fragment);
	}
	
	public int getUniform(String name){
		if (locationCache.containsKey(name)){
			return locationCache.get(name);
		}
		int result = glGetUniformLocation(ID, name);
		if(result == -1)
			System.err.println("Could not find uniform variable'" + name + "'!");
		else
			locationCache.put(name, result);
		return glGetUniformLocation(ID, name);
	}
	
	public static void loadAll(){
		defShader = new Shader("res/shaders/bg.vert", "res/shaders/bg.frag");
	}
	
	public static void loadShader(String vertShader, String fragShader){
		new Shader(vertShader, fragShader);
	}
	
	public void setUniform1i(String name, int value) {
		if (!enabled) enable();
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, float value) {
		if (!enabled) enable();
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, Vector2f vector) {
		if (!enabled) enable();
		glUniform2f(getUniform(name), vector.getX(), vector.getY());
	}
	
	public void setUniform3f(String name, Vector3f vector) {
		if (!enabled) enable();
		glUniform3f(getUniform(name), vector.getX(), vector.getY(), vector.getZ());
	}
	public void setUniform4f(String name, Vector4f vector) {
		if (!enabled) enable();
		glUniform4f(getUniform(name), vector.getR(), vector.getG(), vector.getB(),vector.getA());
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix) {
		if (!enabled) enable();
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	public void enable() {
		glUseProgram(ID);
		enabled = true;
	}
	
	public void disable() {
		glUseProgram(0);
		enabled = false;
	}

}