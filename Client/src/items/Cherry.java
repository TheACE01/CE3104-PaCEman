package items;

import graphics.Skins;


import java.awt.*;

/**
 * Cherry fruit item.
 * @author kevin Avevedo
 */
public class Cherry extends Item {

    /**
     * Constructor method
     * @param x Cherry X position
     * @param y Cherry Y position
     * @param tex Textures
     * @param id Cherry identifier
     * @param quadrant Number of quadrant
     */
    public Cherry(Integer x, Integer y, Skins tex, String id, Integer quadrant, Integer value) {
        super(x, y, tex, id, quadrant, value);
    }

    /**
     * Draw the Cherry on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        g.drawImage(tex.cherry, (int)x, (int)y, null);
    }
}
