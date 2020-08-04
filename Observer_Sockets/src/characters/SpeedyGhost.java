package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.Random;

public class SpeedyGhost extends Ghost {

    private Animations animRc, animLc, animUc, animDc;
    private int lastShadowTarget;
    private int corner;
    private Random rand = new Random();


    public SpeedyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        animR = new Animations(5, tex.speedy[0], tex.speedy[1]);
        animL = new Animations(5, tex.speedy[2], tex.speedy[3]);
        animU = new Animations(5, tex.speedy[4], tex.speedy[5]);
        animD = new Animations(5, tex.speedy[6], tex.speedy[7]);

        animRc = new Animations(5, tex.chaseSpeedy[0], tex.chaseSpeedy[1]);
        animLc = new Animations(5, tex.chaseSpeedy[2], tex.chaseSpeedy[3]);
        animUc = new Animations(5, tex.chaseSpeedy[4], tex.chaseSpeedy[5]);
        animDc = new Animations(5, tex.chaseSpeedy[6], tex.chaseSpeedy[7]);

        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "speedy";

    }
    public void render(Graphics g){
        if(x != 0 && y != 0) {
            g.drawImage(tex.speedy[0], (int) x, (int) y, null);
        }
    }


}
