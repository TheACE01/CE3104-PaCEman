package characters;

import graphics.Skins;
import main.Game;

import java.awt.*;

/**
 * Bashful ghost observed in the main Game
 * @author kevin Avevedo
 */
public class BashfulGhost extends Ghost {

    /**
     * Constructor method
     * @param x Bashful X position
     * @param y Bashful Y position
     * @param tex Textures access
     * @param game Game access
     */
    public BashfulGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);
    }

    /**
     * Draw Bashful ghost on screen using the X and Y positions
     * @param g The painter object
     */
    public void render(Graphics g){
        if(x != 0 && y != 0){
            if(!game.getSkaredFlag()) {
                g.drawImage(tex.bashful[0], (int)x, (int)y, null);
            }
            else {
                g.drawImage(tex.scaredGhost[0], (int)x, (int)y, null);
            }
        }
    }
}
