package items;

import interfaces.Obstacle;

import java.awt.*;

/**
 * Wall obstacle, block pac man movement when collides with him.
 * @author kevin Avevedo
 */
public class Wall implements Obstacle {

    //Wall position
    private Integer x;
    private Integer y;

    //Wall dimensions
    private Integer width;
    private Integer height;

    /**
     * Constructor method
     * @param x Wall X position
     * @param y Wall Y position
     * @param width Wall width
     * @param height Wall height
     */
    public Wall(Integer x, Integer y, Integer width, Integer height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates an invisible rectangle of the wall
     * @return The rectangle object of the wall
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
