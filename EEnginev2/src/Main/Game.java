
package Main;

import java.util.ArrayList;

public class Game
{
    public static final float SQUARESIZE = 32;
    
    private static Game game;

    /**
     * @return the game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * @param aGame the game to set
     */
    public static void setGame(Game aGame) {
        game = aGame;
    }
    private Map map;
    private final ArrayList<Gameobject> objects;
    private final ArrayList<Gameobject> remove;
    private final ArrayList<Gameobject> added;
    private float shiftX,shiftY;
    private int team;
    private final boolean[] gameFlags;
    
    private GameMode gMod;
    
    
    public Game(GameMode mode) {
        objects = new ArrayList<>();
        remove = new ArrayList<>();
        added = new ArrayList<>();
        gameFlags = new boolean[] {
            false // Fog of war
        };
        map = new Map();
        gMod = mode;
    }
    /**
     * Updates the game.
     * @param clients the clients who are in the game
     */
    public void update(ArrayList<ClientServer> clients)
    {
        for(Gameobject go : objects) 
        {
            go.update();
            if(go.getRemove()) {
                remove.add(go);
            }
        }
        for(Gameobject go : added){
            objects.add(go);
        }
        added.removeAll(added);
        for(Gameobject go : remove) {
            objects.remove(go);
        }
        remove.removeAll(remove);
        getMap().update();
        if(gMod.update()) {
            Sound.resetSounds();
            gMod.reset();
        }
    }
    /**
     * Inits new Gameobject into the game.
     * @param go the Gameobject to be added.
     */
    public static void initObject(Gameobject go)
    {
        getGame().added.add(go);
    }
    /**
     * Adds new Gameobject directly to game. Be careful whe using this one.
     * @param go the Gameobject to be added.
     */
    public static void addObject(Gameobject go) {
        getGame().objects.add(go);
    }
    /**
     * @return the Gameobjects in the game
     */
    public static ArrayList<Gameobject> getObjects()
    {
        return getGame().objects;
    }
    /**
     * @return the recently added Gameobjects in the game
     */
    public static ArrayList<Gameobject> getadded()
    {
        return getGame().added;
    }
    /**
     * Calculates which Gameobjects collide with the specified sphere.
     * @param x x-coordinate of the sphere.
     * @param y y-coordinate of the sphere.
     * @param radius radius of the sphere.
     * @return ArrayList of Gameobjects collided with.
     */
    public static ArrayList<Gameobject> sphereCollide(float x,float y,float radius)
    {
        ArrayList<Gameobject> res = new ArrayList<>();
        
        for(Gameobject go : getObjects())
        {
            if(go.getTeam() == -1) continue;
            
            if(Physics.rectCircleCollision(go, new Vector2f(x,y), radius)) {
                res.add(go);
            }
        }
        
       return res;
    }

    /**
     * Calculates which Gameobjects collide with the specified Gameobject.
     * @param go the Gameobject to test which Gameobjects collide with
     * @return ArrayList of Gameobjects collided with.
     */
    public static ArrayList<Gameobject> gameObjectCollide(Gameobject go)
    {
        ArrayList<Gameobject> res = new ArrayList<>();
        
        for(Gameobject g : getObjects())
        {
            if(go == g) continue;
            if(g.getTeam() == -1) continue;
            
            if(Physics.rectRectCollision(go, g)) {
                res.add(g);
            }
        }
        
       return res;
    }
    /**
     * @return the x-shift of the screen.
     */
    public static float getShiftX() {
        return getGame().shiftX;
    }
    /**
     * @return the y-shift of the screen.
     */
    public static float getShiftY() {
        return getGame().shiftY;
    }
    /**
     * @param f the x-shift of the screen to be set.
     */
    public static void setShiftX(float f) {
        game.shiftX = f;
    }
    /**
     * @param f the y-shift of the screen to be set.
     */
    public static void setShiftY(float f) {
        game.shiftY = f;
    }
    /**
     * @param shiftX the amount to be moved in x-axis.
     * @param shiftY the amount to be moved in y-axis.
     */
    public static void Movescreen(float shiftX,float shiftY) {
        game.shiftY += shiftY;
        game.shiftX += shiftX;
        if(getShiftX() > 0) {
            setShiftX(0);
        }
        if(getShiftY() > 0) {
            setShiftY(0);
        }
        /*if(getShiftX() < -Map.MAPSIZE * Game.SQUARESIZE + Display.getWidth()) {
            setShiftX(-Map.MAPSIZE * Game.SQUARESIZE + Display.getWidth());
        }
        if(getShiftY() < -Map.MAPSIZE * Game.SQUARESIZE + Display.getHeight()) {
            setShiftY(-Map.MAPSIZE * Game.SQUARESIZE + Display.getHeight());
        }*/
    }

    /**
     * @param val Sets fog of war
     */
    public static void setFow(boolean val) {
        game.gameFlags[0] = val;
    }
    /**
     * @return true if fog of war is enabled
     */
    public static boolean getFow() {
        return getGame().gameFlags[0];
    }

    /**
     * @return the team
     */
    public static int getTeam() {
        return getGame().team;
    }

    /**
     * @param team the team to set
     */
    public static void setTeam(int team) {
        game.team = team;
    }
    /**
     * @param gm the GameMode to be set.
     */
    public static void setGameMode(GameMode gm) {
        game.gMod = gm;
    }

    public static GameMode getGameMode() {
        return getGame().gMod;
    }

    /**
     * @return the map
     */
    public static Map getMap() {
        return getGame().map;
    }

    /**
     * @param map the map to set
     */
    public static void setMap(Map map) {
        game.map = map;
    }
}
