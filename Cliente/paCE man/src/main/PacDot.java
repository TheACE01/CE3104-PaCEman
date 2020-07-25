package main;

import main.Entidades.EntityC;

import java.awt.*;

public class PacDot extends GameObject implements EntityC {

    private Textures tex;


    public PacDot(double x, double y,Textures tex){
        super(x, y);
        this.tex = tex;




    }
    public void tick(){
        // pac dots are static elements
    }
    public void render(Graphics g){
        g.drawImage(tex.pacDot[0], (int)x, (int)y, null);
    }

    public double getX() {
        return 0;
    }

    public double getY() {
        return 0;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y,47,54);
    }
}
