package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;

public class BashfulGhost extends Ghost {

    public BashfulGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        animR = new Animations(5, tex.bashful[0], tex.bashful[1]);
        animL = new Animations(5, tex.bashful[2], tex.bashful[3]);
        animU = new Animations(5, tex.bashful[4], tex.bashful[5]);
        animD = new Animations(5, tex.bashful[6], tex.bashful[7]);

        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "bashful";

    }
    public void render(Graphics g){
        if(x != 0 && y != 0) {
            g.drawImage(tex.bashful[0], (int) x, (int) y, null);
        }
    }


}
