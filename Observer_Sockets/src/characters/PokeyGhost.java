package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.Random;

public class PokeyGhost extends Ghost {

    private int corner = 59;

    public PokeyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        animR = new Animations(5, tex.pokey[0], tex.pokey[1]);
        animL = new Animations(5, tex.pokey[2], tex.pokey[3]);
        animU = new Animations(5, tex.pokey[4], tex.pokey[5]);
        animD = new Animations(5, tex.pokey[6], tex.pokey[7]);

        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "pokey";
    }
    public void render(Graphics g){
        if(x != 0 && y != 0) {
            g.drawImage(tex.pokey[0], (int) x, (int) y, null);
        }
    }
}
