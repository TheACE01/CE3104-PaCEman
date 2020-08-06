package graphics;

import java.awt.image.BufferedImage;

/**
 * Extract the exact image from a sprite sheet
 * @author kevin Avevedo
 */
public class SpriteSheet {

    //Image object
    private BufferedImage image;

    /**
     * Constructor method
     * @param image The loaded image
     */
    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    /**
     * Extract the character images using the row and column number, with and height
     * @param row Row number
     * @param col Column number
     * @param width Image width
     * @param height Image height
     * @return A new image from the sprite sheet
     */
    public BufferedImage grabImage(Integer row, Integer col, Integer width, Integer height){
        BufferedImage img = image.getSubimage((col * 47) - 47, (row * 50) - 50, width, height);
        return img;
    }
}
