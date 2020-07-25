package main;

import main.Entidades.EntityB;

import java.awt.*;

public class Ghost extends GameObject implements EntityB {

    private double velX = 0;
    private double velY = 0;

    private Textures tex;
    Animations anim;

    public Ghost(double x, double y, Textures tex){
        super(x, y);
        this.tex = tex;

        anim = new Animations(5, tex.ghost[0], tex.ghost[1]);
    }

    public void tick() {
        //y += 1;
        anim.runAnimation();
    }
    public void render(Graphics g){
        anim.drawAnimation(g,x,y,0);
    }


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y,47,54);
    }
}
