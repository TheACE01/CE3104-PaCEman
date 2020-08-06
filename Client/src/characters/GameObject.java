package characters;

import dataStructures.Structures;

import java.awt.*;

public class GameObject {
    public double x, y;


    public GameObject(double x, double y){
        this.x = x;
        this.y = y;
    }
    public void tick(){

    }
    public void render(Graphics g){

    }

    public Rectangle getBounds(int width, int height){
        return new Rectangle((int) x, (int) y,width,height);
    }
}
