/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;

/**
 *
 * @author Lime
 */
public class Match {
    private final ArrayList<ClientServer> clients;
    private final Game game;
    
    public Match(GameMode gm, ArrayList<ClientServer> clients) {
        this.clients = clients;
        game = new Game(gm);
    }
    public void update() {
        Game.setGame(game);
        game.update(clients);
    }
    
}
