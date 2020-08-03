package items;

import graphics.Skins;


import java.awt.*;

public class Banana extends Item {


    public Banana(int x, int y, Skins tex, String id) {
        super(x, y, tex, id);
    }

    public void render(Graphics g){
        g.drawImage(tex.banana, (int)x, (int)y, null);
    }
}
