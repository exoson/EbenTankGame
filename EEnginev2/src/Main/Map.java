
package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map
{
    public static final int MAPSIZE = 100;
    
    private Square[][] squares;
    private boolean[][][] visiblesqrs;
    private int width, height;
    
    public Map(String mapname)
    {
        initMap(mapname);
    }
    public void update()
    {
        checkVisibilitya();
    }
    
    private void checkVisibilitya()
    {
        for(int x = 0; x < width;x++){
            for(int y = 0; y < height;y++){
                visiblesqrs[0][x][y] = false;
                visiblesqrs[1][x][y] = false;
            }
        }
        for(int x = 0; x < width;x++){
            for(int y = 0; y < height;y++){
                visiblesqrs[0][x][y] = checkVisibility(0,x,y);
                visiblesqrs[1][x][y] = checkVisibility(1,x,y);
            }
        }
    }
    private boolean checkVisibility(int team,int x,int y)
    {
        if(getVisiblesqrs()[team][x][y]) return true;
        for(Gameobject go : Game.getObjects()){
            if(go.getTeam() != team) continue;
            if(Util.dist(x * Game.SQUARESIZE, y * Game.SQUARESIZE, go.getX(), go.getY()) > 600) continue;
            if(Util.los(go,x * Game.SQUARESIZE,y * Game.SQUARESIZE)) return true;
        }
        return false;
    }
    public boolean getVisibility(int x, int y, int team) {
        if(x >= width || x < 0 || y >= height || y < 0 || team >= visiblesqrs.length) 
        {
            return false;
        }
        return visiblesqrs[team][x][y];
    }
    public void render()
    {
        for(int x = 0; x < width; x++) 
        {
            for(int y = 0;y < height; y++)
            {
                //if(Math.abs(x * Game.SQUARESIZE + Game.game.getShiftX()) < Display.getWidth() && Math.abs(y * Game.SQUARESIZE + Game.game.getShiftY()) < Display.getHeight()){
                float modf = Game.getFow() ? (getVisiblesqrs()[Game.getTeam()][x][y] ? 1 : 10) : 1;
                getSquares()[x][y].render(new Vector2f(x*Game.SQUARESIZE,y*Game.SQUARESIZE),1 / modf,1 / modf,1 / modf);
                //}
            }
        }
    }
    public Square getsquare(int x,int y)
    {
        if(x >= width || x < 0 || y >= height || y < 0) 
        {
            return null;
        }
        return getSquares()[x][y];
    }
    public void initRandomMap() {
        width = 26;
        height = 20;
            squares = new Square[width][height];
            visiblesqrs = new boolean[2][width][height];
            Random rng = new Random();
            for(int y = 0; y < height; y++) { 
                for(int x = 0; x < width; x++) {
                    int temp = rng.nextFloat() > 0.9 ? 1 : 0;
                    for(Gameobject go : Game.game.getObjects()) {
                        if(Physics.rectRectCollision(go, new Vector2f[]{
                            new Vector2f((x-1f/2) * Game.SQUARESIZE,(y-1f/2) * Game.SQUARESIZE),
                            new Vector2f((x+1f/2) * Game.SQUARESIZE,(y-1f/2) * Game.SQUARESIZE),
                            new Vector2f((x-1f/2) * Game.SQUARESIZE,(y+1f/2) * Game.SQUARESIZE),
                            new Vector2f((x+1f/2) * Game.SQUARESIZE,(y+1f/2) * Game.SQUARESIZE)
                        })) {
                            temp = 0;
                        }
                        if(x == 0 || x == width-1 || y == 0 || y == height-1) {
                            temp = 1;
                        }
                    }
                    squares[x][y] = Square.values()[temp];
                }
            }
    }
    public void initMap(String mapname)
    {
        try {
            
            try (BufferedReader r = new BufferedReader(new FileReader("res/maps/" + mapname + ".txt"))) 
            {
                width = Integer.parseInt(r.readLine());
                height = Integer.parseInt(r.readLine());
                squares = new Square[width][height];
                visiblesqrs = new boolean[2][width][height];
                for(int y = 0; y < height; y++) { 
                    for(int x = 0; x < width; x++) {
                        char tempchar = (char)r.read();
                        squares[x][y] = Square.values()[Integer.parseInt("" + tempchar)];
                    }
                    r.readLine();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Square[][] getSquares() {
        return squares;
    }

    /**
     * @return the visiblesqrs
     */
    public boolean[][][] getVisiblesqrs() {
        return visiblesqrs;
    }
}
