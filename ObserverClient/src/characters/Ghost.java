package characters;

import graphics.Skins;
import main.Game;

import java.awt.*;

/**
 * Parent ghost class, defines the structure and basic behavior of observed ghosts objects
 * @author kevin Avevedo
 */
public class Ghost {

    //Ghost positions
    public double x, y;

    //Game access
    public Game game;

    //Textures access
    public Skins tex;

    /**
     * Constructor method
     * @param x Ghost X position
     * @param y Ghost Y position
     * @param tex Textures of characters
     * @param game The main class
     */
    public Ghost(double x, double y, Skins tex, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;
    }

    /**
     * Draw the ghost image on screen
     * @param g The painter object
     */

    public void render(Graphics g){

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}
