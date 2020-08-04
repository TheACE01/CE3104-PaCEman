package characters;

import dataStructures.GhostNode;
import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost {

    public double x, y;
    public double velX, velY;
    public double boost;
    public boolean R, L, U, D;
    public List<String> ghostPath = new ArrayList<>();
    public Game game;
    public Skins tex;
    public String nowGhostNode = "Node_28";
    public int ghostNodeCont = 0;
    public Animations animR, animL, animU, animD, animRs, animLs, animUs, animDs;
    public String ghostID;

    public Ghost(double x, double y, Skins tex, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;

        R = false;
        L = false;
        U = false;
        D = false;
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
