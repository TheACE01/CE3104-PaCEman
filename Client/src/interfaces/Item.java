package interfaces;

import java.awt.*;

public interface Item {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();
    public String getItemName();
}
