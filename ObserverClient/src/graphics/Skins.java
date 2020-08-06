package graphics;

import main.Game;

import java.awt.image.BufferedImage;

/**
 * Specifies the exact image for the ghost and pac man.
 * @author kevin Avevedo
 */
public class Skins {

    //Player images states
    public BufferedImage[]player = new BufferedImage[1];
    public BufferedImage playerDying;
    public BufferedImage[]playerEnergized = new BufferedImage[1];

    //Ghosts images
    public BufferedImage[]shadow = new BufferedImage[1];
    public BufferedImage[]bashful = new BufferedImage[1];
    public BufferedImage[]pokey = new BufferedImage[1];
    public BufferedImage[]speedy = new BufferedImage[1];

    //Skared ghost image
    public BufferedImage[]scaredGhost = new BufferedImage[1];

    //items images
    public BufferedImage pacDot;
    public BufferedImage energizer;
    private SpriteSheet ss;
    private SpriteSheet ss2;

    public BufferedImage apple;
    public BufferedImage banana;
    public BufferedImage cherry;

    //lives image
    public BufferedImage live;

    /**
     * Constructor method
     * @param game Game access
     */
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

    /**
     * init the characters textures on the BufferedImages
     */
    private void getTextures(){
        //Right Player
        player[0] = ss.grabImage(2, 19,47,50);

        //Right Player
        playerEnergized[0] = ss2.grabImage(1, 20,47,50);

        //Player Dying
        playerDying =  ss.grabImage(1, 19,47,50);

        //Right Ghost
        shadow[0] = ss.grabImage(1,1,47,50);

        //Right Ghost
        bashful[0] = ss.grabImage(1,3,47,50);

        //Right Ghost
        pokey[0] = ss.grabImage(1,4,47,50);

        //Right Ghost
        speedy[0] = ss.grabImage(1,2,47,50);

        //Right Ghost
        scaredGhost[0] = ss.grabImage(13,1,47,50);
    }
}
