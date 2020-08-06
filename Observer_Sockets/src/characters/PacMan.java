package characters;

import graphics.Skins;
import main.Game;

import java.awt.*;

public class PacMan {

    private double x, y;
    private Skins tex;
    private Game game;

    public PacMan(double x, double y, Skins tex, Game game){
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;

    }

    public void render(Graphics g){
        if(x != 0 && y != 0){
            if(!game.getSkaredFlag()) {
                g.drawImage(tex.playerDying, (int)x, (int)y, null);
            }
            else {
                g.drawImage(tex.playerEnergized[0], (int)x, (int)y, null);
            }
        }
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
