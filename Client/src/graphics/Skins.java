package graphics;

import main.Game;

import java.awt.image.BufferedImage;

public class Skins {

    public BufferedImage[]player = new BufferedImage[8];
    public BufferedImage playerDying;
    public BufferedImage[]playerEnergized = new BufferedImage[8];


    public BufferedImage[]shadow = new BufferedImage[8];
    public BufferedImage[]bashful = new BufferedImage[8];
    public BufferedImage[]pokey = new BufferedImage[8];

    public BufferedImage[]speedy = new BufferedImage[8];
    public BufferedImage[]chaseSpeedy = new BufferedImage[8];

    public BufferedImage[]scaredGhost = new BufferedImage[8];

    public BufferedImage pacDot;
    public BufferedImage energizer;
    private SpriteSheet ss;
    private SpriteSheet ss2;

    public BufferedImage apple;
    public BufferedImage banana;
    public BufferedImage cherry;

    public BufferedImage live;

    public Skins(Game game){
        ss = new SpriteSheet(game.getSpriteSheet());
        ss2 = new SpriteSheet(game.getSpriteSheet2());
        pacDot = game.getPDSpriteSheet();
        energizer = game.getEnergizerSprite();
        apple = game.getAppleSprite();
        banana = game.getBananaSprite();
        cherry = game.getCherrySprite();
        live = game.getLive();
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


        //Right Player
        playerEnergized[0] = ss2.grabImage(2, 20,47,50);
        playerEnergized[1] = ss2.grabImage(3, 20,47,50);

        //Left Player
        playerEnergized[2] = ss2.grabImage(8, 20,47,50);
        playerEnergized[3] = ss2.grabImage(9, 20,47,50);

        //Up Player
        playerEnergized[4] = ss2.grabImage(11, 20,47,50);
        playerEnergized[5] = ss2.grabImage(12, 20,47,50);

        //Down Player
        playerEnergized[6] = ss2.grabImage(5, 20,47,50);
        playerEnergized[7] = ss2.grabImage(6, 20,47,50);

        //Player Dying
        playerDying =  ss.grabImage(1, 19,47,50);



        //Right Ghost
        shadow[0] = ss.grabImage(1,1,47,50);
        shadow[1] = ss.grabImage(2,1,47,50);

        //Left Ghost
        shadow[2] = ss.grabImage(5,1,47,50);
        shadow[3] = ss.grabImage(6,1,47,50);

        //Up Ghost
        shadow[4] = ss.grabImage(7,1,47,50);
        shadow[5] = ss.grabImage(8,1,47,50);

        //Down Ghost
        shadow[6] = ss.grabImage(3,1,47,50);
        shadow[7] = ss.grabImage(4,1,47,50);


        //Right Ghost
        bashful[0] = ss.grabImage(1,3,47,50);
        bashful[1] = ss.grabImage(2,3,47,50);

        //Left Ghost
        bashful[2] = ss.grabImage(5,3,47,50);
        bashful[3] = ss.grabImage(6,3,47,50);

        //Up Ghost
        bashful[4] = ss.grabImage(7,3,47,50);
        bashful[5] = ss.grabImage(8,3,47,50);

        //Down Ghost
        bashful[6] = ss.grabImage(3,3,47,50);
        bashful[7] = ss.grabImage(4,3,47,50);

        //Right Ghost
        pokey[0] = ss.grabImage(1,4,47,50);
        pokey[1] = ss.grabImage(2,4,47,50);

        //Left Ghost
        pokey[2] = ss.grabImage(5,4,47,50);
        pokey[3] = ss.grabImage(6,4,47,50);

        //Up Ghost
        pokey[4] = ss.grabImage(7,4,47,50);
        pokey[5] = ss.grabImage(8,4,47,50);

        //Down Ghost
        pokey[6] = ss.grabImage(3,4,47,50);
        pokey[7] = ss.grabImage(4,4,47,50);


        //Right Ghost
        speedy[0] = ss.grabImage(1,2,47,50);
        speedy[1] = ss.grabImage(2,2,47,50);

        //Left Ghost
        speedy[2] = ss.grabImage(5,2,47,50);
        speedy[3] = ss.grabImage(6,2,47,50);

        //Up Ghost
        speedy[4] = ss.grabImage(7,2,47,50);
        speedy[5] = ss.grabImage(8,2,47,50);

        //Down Ghost
        speedy[6] = ss.grabImage(3,2,47,50);
        speedy[7] = ss.grabImage(4,2,47,50);

        //Right Ghost
        chaseSpeedy[0] = ss2.grabImage(1,2,47,50);
        chaseSpeedy[1] = ss2.grabImage(2,2,47,50);

        //Left Ghost
        chaseSpeedy[2] = ss2.grabImage(5,2,47,50);
        chaseSpeedy[3] = ss2.grabImage(6,2,47,50);

        //Up Ghost
        chaseSpeedy[4] = ss2.grabImage(7,2,47,50);
        chaseSpeedy[5] = ss2.grabImage(8,2,47,50);

        //Down Ghost
        chaseSpeedy[6] = ss2.grabImage(3,2,47,50);
        chaseSpeedy[7] = ss2.grabImage(4,2,47,50);



        //Right Ghost
        scaredGhost[0] = ss.grabImage(13,1,47,50);
        scaredGhost[1] = ss.grabImage(13,2,47,50);

        //Left Ghost
        scaredGhost[2] = ss.grabImage(13,1,47,50);
        scaredGhost[3] = ss.grabImage(14,2,47,50);

        //Up Ghost
        scaredGhost[4] = ss.grabImage(13,1,47,50);
        scaredGhost[5] = ss.grabImage(14,2,47,50);

        //Down Ghost
        scaredGhost[6] = ss.grabImage(13,1,47,50);
        scaredGhost[7] = ss.grabImage(14,2,47,50);

    }

}
