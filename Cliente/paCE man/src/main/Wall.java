package main;

import main.Entidades.EntityD;

import java.awt.*;

public class Wall implements EntityD {

    private int x;
    private int y;
    private int width;
    private int height;
    public Wall(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
}
