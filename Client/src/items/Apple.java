package items;


import graphics.Skins;

import java.awt.*;

/**
 * Apple fruit item
 * @author kevin Avevedo
 */
public class Apple extends Item {

    //The textures
    private Skins tex;

    //identifier
    private String id = "apple";

    /**
     * Constructor method
     * @param x Apple X position
     * @param y Apple Y position
     * @param tex Textures
     * @param id Apple identifier
     * @param quadrant Number of quadrant
     */
    public Apple(Integer x, Integer y, Skins tex, String id, Integer quadrant) {
        super(x, y, tex, id, quadrant);
    }

    /**
     * Draw the Apple on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        g.drawImage(tex.apple, (int)x, (int)y, null);
    }
}
