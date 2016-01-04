
package Main;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;

public class Minimap extends UIobject
{
    private final Map map;
    public Minimap(float x,float y,float sx,float sy,boolean displayed,Map map,String bg)
    {
        this.map = map;
        init(x,y,sx,sy,MINMAP,displayed,bg);
    }
    @Override
    public void render()
    {
        if(isDisplayed())
        {
            ArrayList<Gameobject> gos = Game.getObjects();
            glPushMatrix();
            {
                glTranslatef(getX(),getY(),0);
                glBegin(GL_QUADS);
                {
                    Square[][] sqrs = map.getSquares();
                    for(int i = 0;i < sqrs.length;i++)
                    {
                        for(int j = 0;j < sqrs[i].length;j++)
                        {
                            glColor3f(sqrs[i][j].getR(),sqrs[i][j].getG(),sqrs[i][j].getB());
                            glVertex2f(i * 2,j * 2);
                            glVertex2f(i * 2 + 2,j * 2);
                            glVertex2f(i * 2 + 2,j * 2 + 2);
                            glVertex2f(i * 2,j * 2 + 2);
                        }
                    }
                }
                for(Gameobject go : gos)
                {
                    if(go.getId() == Gameobject.HEROID)
                    {
                        glColor3f(1,0,0);
                        glVertex2f(go.getX() / Game.SQUARESIZE -3,go.getY() / Game.SQUARESIZE -3);
                        glVertex2f(go.getX() / Game.SQUARESIZE +3,go.getY() / Game.SQUARESIZE -3);
                        glVertex2f(go.getX() / Game.SQUARESIZE +3,go.getY() / Game.SQUARESIZE +3);
                        glVertex2f(go.getX() / Game.SQUARESIZE -3,go.getY() / Game.SQUARESIZE +3);
                    }
                    if(go.getId() == Gameobject.MINIONID)
                    {
                        
                        glColor3f(1,1,0);
                        glVertex2f(go.getX() / Game.SQUARESIZE -2,go.getY() / Game.SQUARESIZE -2);
                        glVertex2f(go.getX() / Game.SQUARESIZE +2,go.getY() / Game.SQUARESIZE -2);
                        glVertex2f(go.getX() / Game.SQUARESIZE +2,go.getY() / Game.SQUARESIZE +2);
                        glVertex2f(go.getX() / Game.SQUARESIZE -2,go.getY() / Game.SQUARESIZE +2);
                    }
                }
                glEnd();
            }
            glPopMatrix();
        }
    }
}
