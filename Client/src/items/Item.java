package items;

import graphics.Skins;

import java.awt.*;

/**
 * The item parent class. Specifies the characteristics of the fruits, pac dots and energizers that
 * appears on the game.
 * @author kevin Avevedo
 */
public class Item {

    //Item X and Y position
    public Integer x, y;

    //item identifier
    public String id;

    //Textures of the game
    public Skins tex;

    //Quadrant number
    public Integer quadrant;

    public Integer value;

    /**
     * Constructor method
     * @param x Item X position
     * @param y Item Y position
     * @param tex Textures of the characters
     * @param id Item identifier
     * @param quadrant Item quadrant number
     */
    public Item(Integer x, Integer y, Skins tex, String id, Integer quadrant, Integer value) {
        this.x = x;
        this.y = y;
        this.tex = tex;
        this.id = id;
        this.quadrant = quadrant;
        this.value = value;
    }

    /**
     * Draw the items images
     * @param g The painter object
     */
    public void render(Graphics g){

    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getItemName() {
        return id;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x + 19, (int)y + 21,8,8);
    }

    public Integer getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(Integer quadrant) {
        this.quadrant = quadrant;
    }

    public Integer getValue() {
        return value;
    }

}

