
package Main;

public class Vector2f 
{
    private float x,y;
    
    public Vector2f()
    {
        this(0,0);
    }
    public Vector2f(float x,float y)
    {
        this.x = x;
        this.y = y;
    }
    
    public float length()
    {
        return (float)Math.sqrt(x*x+y*y);
    }
    
    public Vector2f add(Vector2f vect)
    {
        return new Vector2f(x + vect.getX(),y + vect.getY());
    }
    
    public Vector2f minus(Vector2f vect)
    {
        return new Vector2f(x - vect.getX(),y - vect.getY());
    }
    public Vector2f mult(float mul)
    {
        return new Vector2f(x * mul,y * mul);
    }
    public Vector2f div(float div) {
        return new Vector2f(x / div,y / div);
    }
    public float dot(Vector2f vect)
    {
        return x * vect.getX() + y * vect.getY();
    }
    public Vector2f dot(Vector2f row1, Vector2f row2)
    {
        Vector2f temp;
        float tempX = this.dot(row1);
        float tempY = this.dot(row2);
        
        temp = new Vector2f(tempX, tempY);
        return temp;
    }
    public Vector2f normalize()
    {
        float length = length();
        if(length != 0) {
            return div(length);
        }
        else {
            return this;
        }
    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }
    public void setX(float var) {
        x = var;
    }
    public void setY(float var) {
        y = var;
    }
    @Override
    public String toString() {
        return getX() + " " + getY();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Float.floatToIntBits(this.x);
        hash = 19 * hash + Float.floatToIntBits(this.y);
        return hash;
    }

    @Override
    public boolean equals(Object v) {
        if(!Vector2f.class.isInstance(v)) return false;
        return ((Vector2f)v).getX() == x && ((Vector2f)v).getY() == y;
    }
}
