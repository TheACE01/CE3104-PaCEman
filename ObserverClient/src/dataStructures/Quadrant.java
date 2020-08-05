package dataStructures;

public class Quadrant {

    private Integer quadrant;
    private Integer x;
    private Integer y;

    public Quadrant(Integer quadrant, Integer x, Integer y){
        this.quadrant = quadrant;
        this.x = x;
        this.y = y;
    }

    public Integer getQuadrant() {
        return quadrant;
    }

    public void setQuadrant(Integer quadrant) {
        this.quadrant = quadrant;
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
