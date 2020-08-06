package characters;

import graphics.Skins;
import main.Game;

import java.awt.*;

/**
 * Shadow ghost obvserved in the main Game
 * @author kevin Avevedo
 */
public class ShadowGhost extends Ghost {

    /**
     * Constructor method
     * @param x Shadow X position
     * @param y Shadow Y position
     * @param tex Textures access
     * @param game Game access
     */
    public ShadowGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);
    }

    /**
     * Draw the Shadow image on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        if(x != 0 && y != 0){
            if(!game.getSkaredFlag()) {
                g.drawImage(tex.shadow[0], (int)x, (int)y, null);
            }
            else {
                g.drawImage(tex.scaredGhost[0], (int)x, (int)y, null);
            }
        }
    }
}
