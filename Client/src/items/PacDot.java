package items;

import graphics.Skins;

import java.awt.*;

/**
 * Pac dot item, is the pac man objective.
 * @author kevin Avevedo
 */
public class PacDot extends Item {

    /**
     * Constructor method
     * @param x PacDot X position
     * @param y PacDot Y position
     * @param tex Textures
     * @param id PacDot identifier
     * @param quadrant Number of quadrant
     */
    public PacDot(Integer x, Integer y, Skins tex, String id, Integer quadrant) {
        super(x, y, tex, id, quadrant);
    }

    /**
     * Draw the PacDot on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        g.drawImage(tex.pacDot, (int)x, (int)y, null);
    }
}
