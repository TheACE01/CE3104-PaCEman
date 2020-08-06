package characters;

import graphics.Skins;
import main.Game;

import java.awt.*;

/**
 * Speedy ghost observed in the main Game
 * @author kevin Avevedo
 */
public class SpeedyGhost extends Ghost {

    /**
     * Constructor method
     * @param x Speedy X position
     * @param y Speedy Y position
     * @param tex Textures access
     * @param game Game access
     */
    public SpeedyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);
    }

    /**
     * Draw Shadow image on screen
     * @param g The painter object
     */
    public void render(Graphics g){
        if(x != 0 && y != 0){
            if(!game.getSkaredFlag()) {
                g.drawImage(tex.speedy[0], (int)x, (int)y, null);
            }
            else {
                g.drawImage(tex.scaredGhost[0], (int)x, (int)y, null);
            }
        }
    }
}
