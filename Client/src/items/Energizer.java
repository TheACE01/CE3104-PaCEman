package items;

import graphics.Skins;

import java.awt.*;

/**
 * Energizer item, when pac man eats this item can kill ghosts.
 * @author kevin Avevedo
 */
public class Energizer extends Item {

    /**
     * Constructor method
     * @param x Energizer X position
     * @param y Energizer Y position
     * @param tex Textures
     * @param id Energizer identifier
     * @param quadrant Number of quadrant
     */
    public Energizer(Integer x, Integer y, Skins tex, String id, Integer quadrant, Integer value) {
        super(x, y, tex, id, quadrant, value);
    }

    /**
     * Draw the Energizer on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        g.drawImage(tex.energizer, (int)x, (int)y, null);
    }
}
