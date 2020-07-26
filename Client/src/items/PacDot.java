package items;

import characters.GameObject;
import graphics.Skins;
import interfaces.Item;


import java.awt.*;

public class PacDot extends GameObject implements Item {

    private Skins tex;


    public PacDot(double x, double y, Skins tex){
        super(x, y);
        this.tex = tex;




    }
    public void tick(){
        // pac dots are static elements

    }
    public void render(Graphics g){
        g.drawImage(tex.pacDot, (int)x, (int)y, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x + 19, (int)y + 21,8,8);
    }
}
