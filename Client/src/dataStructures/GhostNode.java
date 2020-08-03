package dataStructures;

import java.awt.*;

public class GhostNode {

    private int x;
    private int y;

    private String ID;
    private String Up;
    private String Down;
    private String Left;
    private String Right;


    public GhostNode(int x, int y, String ID, String Up, String Down, String Right, String Left){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.Up = Up;
        this.Down = Down;
        this.Right = Right;
        this.Left = Left;
    }

    public int getX() {
        return x;
    }

    public int getY() {
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
