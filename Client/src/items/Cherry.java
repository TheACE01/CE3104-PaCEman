package items;

import graphics.Skins;


import java.awt.*;

public class Cherry extends Item {


    public Cherry(int x, int y, Skins tex, String id) {
        super(x, y, tex, id);
    }
    public void render(Graphics g){
        g.drawImage(tex.cherry, (int)x, (int)y, null);
    }
}
