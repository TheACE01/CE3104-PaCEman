package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabImage(Integer row, Integer col, Integer width, Integer height){
        BufferedImage img = image.getSubimage((col * 47) - 47, (row * 50) - 50, width, height);
        return img;
    }

}