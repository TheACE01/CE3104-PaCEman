package graphics;

import main.Game;

import java.awt.image.BufferedImage;

public class Skins {

    public BufferedImage[]player = new BufferedImage[8];
    public BufferedImage[]ghost = new BufferedImage[8];
    public BufferedImage pacDot;
    private SpriteSheet ss;

    public Skins(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());
        pacDot = game.getPDSpriteSheet();
        getTextures();
    }

    private void getTextures(){
        //Right Player
        player[0] = ss.grabImage(2, 19,47,50);
        player[1] = ss.grabImage(3, 19,47,50);

        //Left Player
        player[2] = ss.grabImage(8, 19,47,50);
        player[3] = ss.grabImage(9, 19,47,50);

        //Up Player
        player[4] = ss.grabImage(11, 19,47,50);
        player[5] = ss.grabImage(12, 19,47,50);

        //Down Player
        player[6] = ss.grabImage(5, 19,47,50);
        player[7] = ss.grabImage(6, 19,47,50);


        //Right Ghost
        ghost[0] = ss.grabImage(1,1,47,50);
        ghost[1] = ss.grabImage(2,1,47,50);

        //Left Ghost
        ghost[2] = ss.grabImage(5,1,47,50);
        ghost[3] = ss.grabImage(6,1,47,50);

        //Up Ghost
        ghost[4] = ss.grabImage(7,1,47,50);
        ghost[5] = ss.grabImage(8,1,47,50);

        //Down Ghost
        ghost[6] = ss.grabImage(3,1,47,50);
        ghost[7] = ss.grabImage(4,1,47,50);
    }

}
