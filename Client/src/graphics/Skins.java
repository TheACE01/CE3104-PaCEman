package graphics;

import main.Game;

import java.awt.image.BufferedImage;

/**
 * Loads the image animations of the ghosts, pac man and items of the games.
 * @author kevin Avevedo
 */
public class Skins {

    //Pac man images List
    public BufferedImage[]player = new BufferedImage[8];

    //Pac man dead image
    public BufferedImage playerDying;

    //Pac man energized images List
    public BufferedImage[]playerEnergized = new BufferedImage[8];

    //Shadow images List
    public BufferedImage[]shadow = new BufferedImage[8];

    //Bashful images List
    public BufferedImage[]bashful = new BufferedImage[8];

    //Pokey images List
    public BufferedImage[]pokey = new BufferedImage[8];

    //Speedy images List
    public BufferedImage[]speedy = new BufferedImage[8];

    //Speedy chase images List
    public BufferedImage[]chaseSpeedy = new BufferedImage[8];

    //Skared ghost images List
    public BufferedImage[]scaredGhost = new BufferedImage[8];

    //pac dot image
    public BufferedImage pacDot;

    //energizer image
    public BufferedImage energizer;

    //SpriteSheet images
    private SpriteSheet ss;
    private SpriteSheet ss2;

    //Apple image
    public BufferedImage apple;

    //Banana image
    public BufferedImage banana;

    //Cherry image
    public BufferedImage cherry;

    //Live (hearth) image
    public BufferedImage live;

    /**
     * Constructor method
     * @param game The main class access
     */
    public Skins(Game game){

        //initializing all the images used in the game
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

    /**
     * Create the Characters Images Lists
     */
    private void getTextures(){

        //Pac Man Animation Images
        player[0] = ss.grabImage(2, 19,47,50);
        player[1] = ss.grabImage(3, 19,47,50);

        player[2] = ss.grabImage(8, 19,47,50);
        player[3] = ss.grabImage(9, 19,47,50);

        player[4] = ss.grabImage(11, 19,47,50);
        player[5] = ss.grabImage(12, 19,47,50);

        player[6] = ss.grabImage(5, 19,47,50);
        player[7] = ss.grabImage(6, 19,47,50);

        //Pac Man Overpowered Animation Images
        playerEnergized[0] = ss2.grabImage(2, 20,47,50);
        playerEnergized[1] = ss2.grabImage(3, 20,47,50);

        playerEnergized[2] = ss2.grabImage(8, 20,47,50);
        playerEnergized[3] = ss2.grabImage(9, 20,47,50);

        playerEnergized[4] = ss2.grabImage(11, 20,47,50);
        playerEnergized[5] = ss2.grabImage(12, 20,47,50);

        playerEnergized[6] = ss2.grabImage(5, 20,47,50);
        playerEnergized[7] = ss2.grabImage(6, 20,47,50);

        //Pac man dead Image
        playerDying =  ss.grabImage(1, 19,47,50);

        //Shadow Animation Images
        shadow[0] = ss.grabImage(1,1,47,50);
        shadow[1] = ss.grabImage(2,1,47,50);

        shadow[2] = ss.grabImage(5,1,47,50);
        shadow[3] = ss.grabImage(6,1,47,50);

        shadow[4] = ss.grabImage(7,1,47,50);
        shadow[5] = ss.grabImage(8,1,47,50);

        shadow[6] = ss.grabImage(3,1,47,50);
        shadow[7] = ss.grabImage(4,1,47,50);


        //Bashful Animation Images
        bashful[0] = ss.grabImage(1,3,47,50);
        bashful[1] = ss.grabImage(2,3,47,50);

        bashful[2] = ss.grabImage(5,3,47,50);
        bashful[3] = ss.grabImage(6,3,47,50);

        bashful[4] = ss.grabImage(7,3,47,50);
        bashful[5] = ss.grabImage(8,3,47,50);

        bashful[6] = ss.grabImage(3,3,47,50);
        bashful[7] = ss.grabImage(4,3,47,50);

        //Pokey Animation Images
        pokey[0] = ss.grabImage(1,4,47,50);
        pokey[1] = ss.grabImage(2,4,47,50);

        pokey[2] = ss.grabImage(5,4,47,50);
        pokey[3] = ss.grabImage(6,4,47,50);

        pokey[4] = ss.grabImage(7,4,47,50);
        pokey[5] = ss.grabImage(8,4,47,50);

        pokey[6] = ss.grabImage(3,4,47,50);
        pokey[7] = ss.grabImage(4,4,47,50);

        //Speedy Animation Images
        speedy[0] = ss.grabImage(1,2,47,50);
        speedy[1] = ss.grabImage(2,2,47,50);

        speedy[2] = ss.grabImage(5,2,47,50);
        speedy[3] = ss.grabImage(6,2,47,50);

        speedy[4] = ss.grabImage(7,2,47,50);
        speedy[5] = ss.grabImage(8,2,47,50);

        speedy[6] = ss.grabImage(3,2,47,50);
        speedy[7] = ss.grabImage(4,2,47,50);

        //Speedy Chase Animation Images
        chaseSpeedy[0] = ss2.grabImage(1,2,47,50);
        chaseSpeedy[1] = ss2.grabImage(2,2,47,50);

        chaseSpeedy[2] = ss2.grabImage(5,2,47,50);
        chaseSpeedy[3] = ss2.grabImage(6,2,47,50);

        chaseSpeedy[4] = ss2.grabImage(7,2,47,50);
        chaseSpeedy[5] = ss2.grabImage(8,2,47,50);

        chaseSpeedy[6] = ss2.grabImage(3,2,47,50);
        chaseSpeedy[7] = ss2.grabImage(4,2,47,50);

        //Scared Ghost Animation Images
        scaredGhost[0] = ss.grabImage(13,1,47,50);
        scaredGhost[1] = ss.grabImage(13,2,47,50);

        scaredGhost[2] = ss.grabImage(13,1,47,50);
        scaredGhost[3] = ss.grabImage(14,2,47,50);

        scaredGhost[4] = ss.grabImage(13,1,47,50);
        scaredGhost[5] = ss.grabImage(14,2,47,50);

        scaredGhost[6] = ss.grabImage(13,1,47,50);
        scaredGhost[7] = ss.grabImage(14,2,47,50);
    }

}
