package items;

import graphics.Skins;


import java.awt.*;

/**
 * Banana fruit item.
 * @author kevin Avevedo
 */
public class Banana extends Item {

    /**
     * Constructor method
     * @param x Banana X position
     * @param y Banana Y position
     * @param tex Textures
     * @param id Banana identifier
     * @param quadrant Number of quadrant
     */
    public Banana(Integer x, Integer y, Skins tex, String id, Integer quadrant) {
        super(x, y, tex, id, quadrant);
    }

    /**
     * Draw the Banana on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        g.drawImage(tex.banana, (int)x, (int)y, null);
    }
}
