package characters;

import graphics.Animations;
import graphics.Skins;
import interfaces.Ghost;


import java.awt.*;

public class Shadow extends GameObject implements Ghost {

    private double velX = 0;
    private double velY = 0;

    private Skins tex;
    Animations anim;

    public Shadow(double x, double y, Skins tex){
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
