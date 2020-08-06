package dataStructures;

import java.awt.*;

/**
 * Represent the nodes of the ghost graph structure
 */
public class GhostNode {

    //Positions of the ghost node
    private Integer x;
    private Integer y;

    //GhostNode name
    private String ID;

    //node access possibilities
    private String Up;
    private String Down;
    private String Left;
    private String Right;

    /**
     * Constructor method
     * @param x GhostNode X position
     * @param y GhostNode Y position
     * @param ID GhostNode name
     * @param Up Up node access
     * @param Down Down node access
     * @param Right Right node access
     * @param Left Left node access
     */
    public GhostNode(Integer x, Integer y, String ID, String Up, String Down, String Right, String Left){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.Up = Up;
        this.Down = Down;
        this.Right = Right;
        this.Left = Left;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x + 20, y + 10, 10, 20);
    }

    public String getID() {
        return ID;
    }

    public String getUp() {
        return Up;
    }

    public String getDown() {
        return Down;
    }

    public String getLeft() {
        return Left;
    }

    public String getRight() {
        return Right;
    }

    public Rectangle getPosBounds(){
        return new Rectangle((int)x + 22, (int)y + 23,2, 2 );
    }
}
