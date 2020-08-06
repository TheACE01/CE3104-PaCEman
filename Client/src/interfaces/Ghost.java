package interfaces;

import java.awt.*;

public interface Ghost {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();
    public Rectangle getPosBounds();

    public double getX();
    public double getY();
    public String getGhostID();
}
