package characters;

import dataStructures.Structures;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.Random;

public class PokeyGhost extends Ghost {


    public PokeyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);
    }
    
    public void render(Graphics g){
        if(x != 0 && y != 0){
        	if(!game.getSkaredFlag()) {
        		g.drawImage(tex.pokey[0], (int)x, (int)y, null);
        	}
        	else {
        		g.drawImage(tex.scaredGhost[0], (int)x, (int)y, null);
        	}
        }
    }
}
