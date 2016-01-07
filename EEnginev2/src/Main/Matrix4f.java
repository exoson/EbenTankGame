/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Graphics.Camera;
import java.nio.FloatBuffer;


public class Matrix4f {

    public static final int SIZE = 4 * 4;
    public float[] elements = new float[SIZE];

    public Matrix4f(){ }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < SIZE; i++) {
            if(i % 4 == 0 && i != 0) {
                sb.append("\n");
            }
            sb.append(elements[i]);
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public static Matrix4f identity(){
        // Initialize a new matrix
        Matrix4f matrix = new Matrix4f();

        // The Identity Matrix:
        // [ 1 , 0 , 0 , 0 ]
        // [ 0 , 1 , 0 , 0 ]
        // [ 0 , 0 , 1 , 0 ]
        // [ 0 , 0 , 0 , 1 ]
        matrix.elements[0 + 0 * 4] = 1.0f;
        matrix.elements[1 + 1 * 4] = 1.0f;
        matrix.elements[2 + 2 * 4] = 1.0f;
        matrix.elements[3 + 3 * 4] = 1.0f;

        // Return our created Identity Matrix
        return matrix;
    }

    public static Matrix4f perspective(float left, float right, float bottom, float top, float near, float far, float FoV, float aspect){
        float y2 = near * (float)Math.tan(Math.toRadians(FoV));
        float y1 = -y2;
        float x1 = y1 * aspect;
        float x2 = y2 * aspect;
        return frustrum(x1, x2, y1, y2, near, far);
    }

    public static Matrix4f frustrum(float left, float right, float bottom, float top, float near, float far){
        Matrix4f cameraMat = new Matrix4f();

        cameraMat.elements[0 + 0 * 4] =  (2 * near) / (right - left);
        cameraMat.elements[1 + 1 * 4] =  (2 * near) / (top - bottom);
        cameraMat.elements[3 + 2 * 4] =  (2 * near * far) / (near - far);
        cameraMat.elements[2 + 2 * 4] =  (near + far) / ( near - far);
        cameraMat.elements[3 + 3 * 4] = 0;
        cameraMat.elements[2 + 3 * 4] = -1.0f;

        cameraMat.elements[0 + 2 * 4] = (right + left) / (right - left);
        cameraMat.elements[1 + 2 * 4] = (top + bottom) / (top - bottom);
        cameraMat.elements[2 + 2 * 4] = (near + far) / (near - far);

        return cameraMat;
    }


    public static Matrix4f setupViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix = Matrix4f.identity();

        return viewMatrix;
    }

    // Gives us our orthographic matrix
    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far){
        Matrix4f matrix = new Matrix4f();

        matrix.elements[0 + 0 * 4] = 2.0f / (right - left);
        matrix.elements[1 + 1 * 4] = 2.0f / (top - bottom);
        matrix.elements[2 + 2 * 4] = 2.0f / (near - far);

        matrix.elements[0 + 3 * 4] = (left + right) / (left - right);
        matrix.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
        matrix.elements[2 + 3 * 4] = (far + near) / (far - near);

        matrix.elements[3 + 3 * 4] = 1.0f;

        return matrix;
    }

    // this translation function basically gives us the ability to
    // move any of our objects by simply inputing a vector3f to it.
    // Say you wanted to move an object 5 places to the right, you'd
    // simple add a vector3f which equalled (0.0f, 5.0f, 0.0f) to your
    // matrix and it would translate it 5 places to the right.
    public static Matrix4f translate(Vector3f vector){
        Matrix4f matrix = identity();
        matrix.elements[0 + 3 * 4] = vector.getX();
        matrix.elements[1 + 3 * 4] = vector.getY();
        matrix.elements[2 + 3 * 4] = vector.getZ();
        return matrix;
    }
    public static Matrix4f scale(Vector3f v) {
        Matrix4f matrix = identity();
        matrix.elements[0        ] = v.getX();
        matrix.elements[1 + 1 * 4] = v.getY();
        matrix.elements[2 + 2 * 4] = v.getZ();
        return matrix;
    }

    // Our rotation function does exactly what it says on 
    // the tin. We give it the angle with which we want to
    // rotate our object and it converts it to radians and 
    // performs a matrix rotation given the cos and sin
    // values computed from this radian.
    public static Matrix4f rotateZ(float angle){
        Matrix4f matrix = identity();
        float r = angle;
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        matrix.elements[0 + 0 * 4] = cos;
        matrix.elements[1 + 0 * 4] = sin;
        matrix.elements[0 + 1 * 4] = -sin;
        matrix.elements[1 + 1 * 4] = cos;		
        return matrix;
    }

    public static Matrix4f rotateY(float angle){
        Matrix4f matrix = identity();
        float r = angle;
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        matrix.elements[0 + 0 * 4] = cos;
        matrix.elements[2 + 0 * 4] = -sin;
        matrix.elements[0 + 2 * 4] = sin;
        matrix.elements[2 + 2 * 4] = cos;

        return matrix;

    }

    public static Matrix4f rotateX(float angle){
        Matrix4f matrix = identity();
        float r = angle;
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        matrix.elements[1 + 1 * 4] = cos;
        matrix.elements[1 + 2 * 4] = -sin;
        matrix.elements[2 + 1 * 4] = sin;
        matrix.elements[2 + 2 * 4] = cos;

        return matrix;

    }

    // Our Matrix multiplication function which
    // gives us the ability to multiply any given
    // matrix using the mathematical rules for matrix
    // manipulation. 
    //
    // Matrix multiplication is quite complex so I
    // recommend you watch a few videos explaining
    // how it works first.
    public Matrix4f multiply(Matrix4f matrix){
        Matrix4f result = new Matrix4f();
        for(int x = 0; x < 4; x++){
                for(int y = 0; y < 4; y++){
                        float sum = 0.0f;
                        for(int e = 0; e < 4; e++){
                                sum += this.elements[x + e * 4] * matrix.elements[e + y * 4];
                        }
                        result.elements[x + y * 4] = sum;
                }
        }
        return result;
    }
    
    public float[] apply(float[] verts) {
        float[] temp = new float[verts.length];
        float q = elements[3 + 0 * 4] + elements[3 + 1 * 4] + elements[3 + 2 * 4] + elements[3 + 3 * 4];
        for(int i = 0; i < verts.length/3; i++) {
            Vector3f v = new Vector3f(verts[i*3 + 0],verts[i*3 + 1],verts[i*3 + 2]);
            for(int j = 0; j < 3; j++) {
                temp[i*3+j] = (v.dot(new Vector3f(elements[j + 0*4], elements[j + 1*4], elements[j + 2*4])) + elements[j + 3*4]) / q;
            }
        }
        return temp;
    }
    public FloatBuffer toFloatBuffer(){
        return Utilities.createFloatBuffer(elements);
    }
}