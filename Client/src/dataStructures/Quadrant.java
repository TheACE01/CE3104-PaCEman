package dataStructures;

public class Quadrant {

    private int quadrant;
    private int x;
    private int y;
    private int item;

    public Quadrant(int quadrant, int x, int y){
        this.quadrant = quadrant;
        this.x = x;
        this.y = y;
    }

    public int getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(int quadrant) {
        this.quadrant = quadrant;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
