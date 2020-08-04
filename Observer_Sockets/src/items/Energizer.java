package items;

import graphics.Skins;

import java.awt.*;

public class Energizer extends Item {


    public Energizer(int x, int y, Skins tex, String id, int quadrant) {
        super(x, y, tex, id, quadrant);
    }
    public void render(Graphics g){
        g.drawImage(tex.energizer, (int)x, (int)y, null);
    }
}
