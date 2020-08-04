package items;

import graphics.Skins;

import java.awt.*;

public class PacDot extends Item {


    public PacDot(int x, int y, Skins tex, String id, int quadrant) {
        super(x, y, tex, id, quadrant);
    }
    public void render(Graphics g){
        g.drawImage(tex.pacDot, (int)x, (int)y, null);
    }
}
