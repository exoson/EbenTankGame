
package Main;

import static Main.Game.game;
import org.lwjgl.opengl.Display;

public class Menu implements State
{
    private final Ui ui;
    
    public Menu()
    {
        ui = new Ui();
        inituiobs();
    }
    
    @Override
    public void render()
    {
        ui.render();
    }
    @Override
    public void update()
    {
        ui.update();
    }
    
    private void inituiobs()
    {
        ui.addobject(new Button(Display.getWidth()/2, Display.getHeight()/2, 100, true, "map0") {
            @Override
            public void click0() {
                game.getGameMode().start();
                Game.setState(EEngine.GAME);
            }
            
            @Override
            public void click1() {
                
            }
        });
    }
}
