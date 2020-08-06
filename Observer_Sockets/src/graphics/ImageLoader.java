package graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Load image using their file path
 * @author kevin Avevedo
 */
public class ImageLoader {

    //Image object
    private BufferedImage image;

    /**
     * Loads an image from file path
     * @param path Image path
     * @return A loaded image
     * @throws IOException
     */
    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}
