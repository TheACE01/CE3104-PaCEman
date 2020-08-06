package dataStructures;

/**
 * Represents the square spaces in the map. This is used in the director structure
 */
public class Quadrant {

    //Quadrant number
    private Integer quadrant;

    //Quadrant position
    private Integer x;
    private Integer y;

    /**
     * Constructor method
     * @param quadrant Quadrant number
     * @param x Quadrant X position
     * @param y Quadrant Y position
     */
    public Quadrant(Integer quadrant, Integer x, Integer y){
        this.quadrant = quadrant;
        this.x = x;
        this.y = y;
    }

    public Integer getQuadrant() {
        return quadrant;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
