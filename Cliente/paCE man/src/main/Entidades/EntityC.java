package main.Entidades;

import java.awt.*;

public interface EntityC {
    public void tick();
    public void render(Graphics g);
    public Rectangle getBounds();

    public double getX();
    public double getY();
}
