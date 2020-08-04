package items;

import graphics.Skins;

import java.awt.*;

public class Item {

    public int x, y;
    public String id;
    public Skins tex;
    public int quadrant;

    public Item(int x, int y, Skins tex, String id, int quadrant) {
        this.x = x;
        this.y = y;
        this.tex = tex;
        this.id = id;
        this.quadrant = quadrant;

    }
    public void render(Graphics g){
        g.drawImage(tex.apple, (int)x, (int)y, null);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public String getItemName() {
        return id;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x + 19, (int)y + 21,8,8);
    }
}

