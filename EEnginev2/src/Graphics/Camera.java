
package Graphics;

import Main.Matrix4f;
import Main.Vector3f;

public class Camera {

	public Matrix4f cameraMat = new Matrix4f();
	public Vector3f position = new Vector3f();
	
	float rot = 0.0f;
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(Matrix4f cameraMat){
            this.cameraMat = cameraMat;
	}
	
	public Matrix4f getMatrix(){
            return cameraMat;
	}
	
	public Matrix4f getCameraMat() {
            return cameraMat;
	}

	public void setCameraMat(Matrix4f cameraMat) {
            this.cameraMat = cameraMat;
	}

	public void setPosition(Vector3f pos){
            this.position = pos;
	}
	
	public Vector3f getPosition() {
            return position;
	}

	public float getRot() {
            return rot;
	}

	public float getPitch() {
            return pitch;
	}

	public float getYaw() {
            return yaw;
	}

	public float getRoll() {
            return roll;
	}

	public void update() {
            
	}

	public Matrix4f setupViewMatrix(){
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix = Matrix4f.identity();
		
		Vector3f negativeCameraPos = new Vector3f(-position.getX(), -position.getY(), -position.getZ());
		
		viewMatrix.translate(negativeCameraPos);
		return viewMatrix;
		
	}
	

	public void render(){
		
		Shader.defShader.enable();
		
		// Uncomment different lines to see different rotation effects
//		Shader.shader1.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotateX(rot)));
//		Shader.shader1.setUniformMat4f("ml_matrix", Matrix4f.translate(position).multiply(Matrix4f.rotateY(rot)));
		Shader.defShader.setUniformMat4f("vw_matrix", Matrix4f.translate(
				new Vector3f(-position.getX(), -position.getY(), -position.getZ())).multiply(
				Matrix4f.rotateZ(roll).multiply(
				Matrix4f.rotateY(yaw)).multiply(
				Matrix4f.rotateX(pitch))));
		
		Shader.defShader.disable();
		
	}
	
}