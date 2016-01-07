
package Main;

import Graphics.Sprite;

public enum Square 
{
    grass(new Sprite(Game.SQUARESIZE,Game.SQUARESIZE,"grass") ,0,0f,1f,0f),
    stone(new Sprite(Game.SQUARESIZE,Game.SQUARESIZE,"stone"),2,0.7f,0.7f,0.7f),
    road(new Sprite(Game.SQUARESIZE,Game.SQUARESIZE,"road"),0,0.7f,0.3f,0.3f);
    
    public static final int WALKABLE = 0,FLYABLE = 1,NONFLYABLE = 2;
    public static final int 
            GRASS = 0,
            STONE = 1,
            ROAD = 2;
    
    private final Sprite spr;
    private final int type;
    private final float r,g,b;
    
    public boolean getTransparent() {
        return type != NONFLYABLE;
    }
    public boolean getWalkable() {
        return type == WALKABLE;
    }
    Square(Sprite spr,int type,float r,float g,float b)
    {
        this.spr = spr;
        this.type = type;
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public void render(Vector2f pos)
    {
        spr.render(new Vector3f(pos));
    }
    public void render(Vector2f pos, float r, float g, float b)
    {
        spr.render(new Vector3f(pos),0,r,g,b,1);
    }
    public int gettype()
    {
        return type;
    }
    public float getR() {
        return r;
    }
    public float getG() {
        return g;
    }
    public float getB() {
        return b;
    }
}
