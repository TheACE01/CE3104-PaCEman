package characters;

import dataStructures.Structures;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost {

    public double x, y;
    public Game game;
    public Skins tex;

    public Ghost(double x, double y, Skins tex, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;
    }

    public void render(Graphics g){

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}
