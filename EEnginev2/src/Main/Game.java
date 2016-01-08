
package Main;

import Graphics.Camera;
import java.util.ArrayList;

public class Game implements State
{
    public static final float SQUARESIZE = 32;
    
    public static Game game;
    public static Menu menu;
    private static Camera camera;
    private Level curlvl;
    private ArrayList<Gameobject> objects;
    private ArrayList<Gameobject> remove;
    private ArrayList<Gameobject> added;
    private Ui ui;
    private float shiftX,shiftY;
    private int team;
    private boolean[] gameFlags;
    
    private GameMode gMod;
    
    
    public static void initGame(GameMode mode)
    {
        menu = new Menu();
        game = new Game();
        game.objects = new ArrayList<>();
        game.remove = new ArrayList<>();
        game.added = new ArrayList<>();
        game.gameFlags = new boolean[] {
            false // Fog of war
        };
        game.curlvl = new Level("simplearena");
        game.ui = new Ui();
        game.gMod = mode;
    }
    /**
     * Updates the game.
     */
    @Override
    public void update()
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
        getLevel().update();
        ui.update();
        if(gMod.update()) {
            Sound.resetSounds();
            gMod.reset();
        }
    }
    /**
     * Renders the game.
     */
    @Override
    public void render()
    {
        getCamera().enable();
        getLevel().render();
        for(Gameobject go : objects) {
            go.render();
        }
        getCamera().disable();
        ui.render();
    }
    /**
     * Inits new Gameobject into the game.
     * @param go the Gameobject to be added.
     */
    public static void initObject(Gameobject go)
    {
        game.added.add(go);
    }
    /**
     * Adds new Gameobject directly to game. Be careful whe using this one.
     * @param go the Gameobject to be added.
     */
    public static void addObject(Gameobject go) {
        game.objects.add(go);
    }
    /**
     * Adds new object on the ui.
     * @param uo the UIobject to be added.
     */
    public static void adduiobj(UIobject uo)
    {
        game.ui.addobject(uo);
    }
    /**
     * @return the Menu
     */
    public static Menu getmenu()
    {
        return menu;
    }
    /**
     * @return the UI
     */
    public static Ui getui()
    {
        return game.ui;
    }
    /**
     * @return the Gameobjects in the game
     */
    public static ArrayList<Gameobject> getObjects()
    {
        return game.objects;
    }
    /**
     * @return the recently added Gameobjects in the game
     */
    public static ArrayList<Gameobject> getadded()
    {
        return game.added;
    }
    /**
     * @return the Current game state.
     */
    public static int getState()
    {
        return EEngine.curstate;
    }
    /**
     * Sets the current game state.
     * @param state the state to be set.
     */
    public static void setState(int state)
    {
        EEngine.curstate = state;
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
        return game.shiftX;
    }
    /**
     * @return the y-shift of the screen.
     */
    public static float getShiftY() {
        return game.shiftY;
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
     * @return the curlvl
     */
    public static Level getLevel() {
        return game.curlvl;
    }

    /**
     * @param curlvl the curlvl to set
     */
    public static void setLevel(Level curlvl) {
        game.curlvl = curlvl;
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
        return game.gameFlags[0];
    }

    /**
     * @return the team
     */
    public static int getTeam() {
        return game.team;
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
        return game.gMod;
    }

    /**
     * @return the camera
     */
    public static Camera getCamera() {
        return camera;
    }

    /**
     * @param camera the camera to set
     */
    public static void setCamera(Camera camera) {
        Game.camera = camera;
    }
}
