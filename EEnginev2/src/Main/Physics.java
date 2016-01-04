
package Main;

public class Physics 
{
    public static boolean rectRectCollision(Gameobject go1, Gameobject go2)
    {
        Vector2f[] rotationMatrix1 = new Vector2f[]{
            new Vector2f((float)Math.cos(-go1.getRotation()),
                    (float)Math.sin(-go1.getRotation())),
            new Vector2f(-(float)Math.sin(-go1.getRotation()),
                    (float)Math.cos(-go1.getRotation()))
        };
        Vector2f[] rotationMatrix2 = new Vector2f[]{
            new Vector2f((float)Math.cos(-go2.getRotation()),
                    (float)Math.sin(-go2.getRotation())),
            new Vector2f(-(float)Math.sin(-go2.getRotation()),
                    (float)Math.cos(-go2.getRotation()))
        };
        Vector2f[] corners1 = new Vector2f[4];
        Vector2f[] corners2 = new Vector2f[4];
        corners1[0] = go1.getPos().add(new Vector2f(-go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[1] = go1.getPos().add(new Vector2f(go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[2] = go1.getPos().add(new Vector2f(-go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[3] = go1.getPos().add(new Vector2f(go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners2[0] = go2.getPos().add(new Vector2f(-go2.getSX()/2,-go2.getSY()/2).dot(rotationMatrix2[0],rotationMatrix2[1]));
        corners2[1] = go2.getPos().add(new Vector2f(go2.getSX()/2,-go2.getSY()/2).dot(rotationMatrix2[0],rotationMatrix2[1]));
        corners2[2] = go2.getPos().add(new Vector2f(-go2.getSX()/2,go2.getSY()/2).dot(rotationMatrix2[0],rotationMatrix2[1]));
        corners2[3] = go2.getPos().add(new Vector2f(go2.getSX()/2,go2.getSY()/2).dot(rotationMatrix2[0],rotationMatrix2[1]));
        
        return rectRectCollision(corners1, corners2);
    }
    public static boolean rectRectCollision(Gameobject go1, Vector2f[] corners2)
    {
        boolean tooFar = true;
        for (Vector2f corner : corners2) {
            if(Util.dist(go1.getPos(), corner) < (go1.getSX() + go1.getSY())) {
                tooFar = false;
                break;
            }
        }
        if(tooFar) return false;
        
        Vector2f[] rotationMatrix1 = new Vector2f[]{
            new Vector2f((float)Math.cos(-go1.getRotation()),
                    (float)Math.sin(-go1.getRotation())),
            new Vector2f(-(float)Math.sin(-go1.getRotation()),
                    (float)Math.cos(-go1.getRotation()))
        };
        Vector2f[] corners1 = new Vector2f[4];
        corners1[0] = go1.getPos().add(new Vector2f(-go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[1] = go1.getPos().add(new Vector2f(go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[2] = go1.getPos().add(new Vector2f(-go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[3] = go1.getPos().add(new Vector2f(go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        
        return rectRectCollision(corners1, corners2);
    }
    public static boolean rectRectCollision(Vector2f[] corners1,Vector2f[] corners2)
    {
        
        boolean collided = true;
        
        Vector2f[] axises = new Vector2f[8];
        axises[0] = corners1[1].minus(corners1[0]);
        axises[1] = corners1[1].minus(corners1[3]);
        axises[2] = corners2[1].minus(corners2[0]);
        axises[3] = corners2[1].minus(corners2[3]);
        axises[4] = axises[0].mult(-1);
        axises[5] = axises[1].mult(-1);
        axises[6] = axises[2].mult(-1);
        axises[7] = axises[3].mult(-1);
        for(Vector2f axis : axises) {
            float lSqr = axis.dot(axis);
            float min1 = Float.MAX_VALUE;
            float max1 = Float.MIN_VALUE;
            float min2 = Float.MAX_VALUE;
            float max2 = Float.MIN_VALUE;
            
            for(Vector2f corner : corners1) {
                Vector2f temp = axis.mult(axis.dot(corner)/lSqr);
                float location = temp.dot(axis);
                if(location < min1){
                    min1 = location;
                }
                if(location > max1){
                    max1 = location;
                }
            }
            for(Vector2f corner : corners2) {
                Vector2f temp = axis.mult(axis.dot(corner)/lSqr);
                float location = temp.dot(axis);
                if(location < min2) {
                    min2 = location;
                }
                if(location > max2) {
                    max2 = location;
                }
            }
            if(min2 > max1) {
                collided = false;
                break;
            }
            if(min1 > max2) {
                collided = false;
                break;
            }
        }
        return collided;
    }
    public static boolean rectCircleCollision(Gameobject go1, Vector2f v, float radius) {
        Vector2f[] rotationMatrix1 = new Vector2f[]{
            new Vector2f((float)Math.cos(-go1.getRotation()),
                    (float)Math.sin(-go1.getRotation())),
            new Vector2f(-(float)Math.sin(-go1.getRotation()),
                    (float)Math.cos(-go1.getRotation()))
        };
        Vector2f[] corners1 = new Vector2f[4];
        corners1[0] = go1.getPos().add(new Vector2f(-go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[1] = go1.getPos().add(new Vector2f(go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[2] = go1.getPos().add(new Vector2f(-go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        corners1[3] = go1.getPos().add(new Vector2f(go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        
        return circleIntersectLine(v, radius, corners1[0], corners1[1]) ||
               circleIntersectLine(v, radius, corners1[1], corners1[3]) ||
               circleIntersectLine(v, radius, corners1[2], corners1[3]) ||
               circleIntersectLine(v, radius, corners1[2], corners1[0]) ||
                pointInRectangle(go1, v);
    }
    public static boolean circleIntersectLine(Vector2f mid, float rad, Vector2f start, Vector2f end) {
        Vector2f startCenter = mid.minus(start);
        float scLength = startCenter.length();
        float ecLength = mid.minus(end).length();
        Vector2f startEnd = end.minus(start);
        float projection = startEnd.dot(startCenter) / startEnd.length();
        float dist = (float)Math.sqrt(projection*projection+scLength*scLength);
        return dist < rad && (scLength < rad || ecLength < rad);
    }
    public static boolean pointInRectangle(UIobject uo, Vector2f v)
    {
        Vector2f[] rotationMatrix1 = new Vector2f[]{
            new Vector2f((float)Math.cos(-uo.getRotation()),
                    (float)Math.sin(-uo.getRotation())),
            new Vector2f(-(float)Math.sin(-uo.getRotation()),
                    (float)Math.cos(-uo.getRotation()))
        };
        Vector2f a = uo.getPos().add(new Vector2f(-uo.getSX()/2,-uo.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        Vector2f b = uo.getPos().add(new Vector2f(uo.getSX()/2,-uo.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        Vector2f c = uo.getPos().add(new Vector2f(-uo.getSX()/2,uo.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        Vector2f d = uo.getPos().add(new Vector2f(uo.getSX()/2,uo.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        
        return 0 < b.minus(a).dot(v.minus(a)) && 
                b.minus(a).dot(v.minus(a)) < b.minus(a).dot(b.minus(a)) && 
                0 < d.minus(a).dot(v.minus(a)) &&  
                d.minus(a).dot(v.minus(a)) < d.minus(a).dot(d.minus(a));
    }
    public static boolean pointInRectangle(Gameobject go1, Vector2f v)
    {
        Vector2f[] rotationMatrix1 = new Vector2f[]{
            new Vector2f((float)Math.cos(-go1.getRotation()),
                    (float)Math.sin(-go1.getRotation())),
            new Vector2f(-(float)Math.sin(-go1.getRotation()),
                    (float)Math.cos(-go1.getRotation()))
        };
        Vector2f a = go1.getPos().add(new Vector2f(-go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        Vector2f b = go1.getPos().add(new Vector2f(go1.getSX()/2,-go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        Vector2f c = go1.getPos().add(new Vector2f(-go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        Vector2f d = go1.getPos().add(new Vector2f(go1.getSX()/2,go1.getSY()/2).dot(rotationMatrix1[0],rotationMatrix1[1]));
        
        return 0 < b.minus(a).dot(v.minus(a)) && 
                b.minus(a).dot(v.minus(a)) < b.minus(a).dot(b.minus(a)) && 
                0 < d.minus(a).dot(v.minus(a)) &&  
                d.minus(a).dot(v.minus(a)) < d.minus(a).dot(d.minus(a));
    }
}
