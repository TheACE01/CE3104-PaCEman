package graphics;

import java.awt.image.BufferedImage;

/**
 * Loads Sprite Sheets images into Buffered Images and grab images
 */
public class SpriteSheet {

    //The sprite sheet
    private BufferedImage image;

    /**
     * Constructor method
     * @param image The sprite sheet image
     */
    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    /**
     * Determines the size of the sprites
     * @param row Row number
     * @param col Column number
     * @param width Square width
     * @param height Square height
     * @return The specific image loaded
     */
    public BufferedImage grabImage(Integer row, Integer col, Integer width, Integer height){
        BufferedImage img = image.getSubimage((col * 47) - 47, (row * 50) - 50, width, height);
        return img;
    }

}
