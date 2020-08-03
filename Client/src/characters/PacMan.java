package characters;

import Physics.Collisions;
import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PacMan {

    private double x, y;

    private double velX = 0;
    private double velY = 0;

    private double lastX;
    private double lastY;

    private String lastGhostNode = "Node_32";

    private boolean R;
    private boolean L;
    private boolean U;
    private boolean D;

    private Boolean dyingFlag = false;

    private Skins tex;
    private Game game;

    private Animations animR, animL, animU, animD, animRs, animLs, animUs, animDs;

    public PacMan(double x, double y, Skins tex, Game game){
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;

        animR = new Animations(10, tex.player[0], tex.player[1]);
        animL = new Animations(10, tex.player[2], tex.player[3]);
        animU = new Animations(10, tex.player[4], tex.player[5]);
        animD = new Animations(10, tex.player[6], tex.player[7]);

        animRs = new Animations(10, tex.playerEnergized[0], tex.playerEnergized[1]);
        animLs = new Animations(10, tex.playerEnergized[2], tex.playerEnergized[3]);
        animUs = new Animations(10, tex.playerEnergized[4], tex.playerEnergized[5]);
        animDs = new Animations(10, tex.playerEnergized[6], tex.playerEnergized[7]);

        R = true;
        L = false;
        U = false;
        D = false;

        lastX = 94;
        lastY = 100;
        x = lastX;
        y = lastY;
    }

    public void tick(){
        if(!game.getResetCountFlag()){
            x += velX;
            y += velY;
        }


        //verifying wall limits
        //we ask if the player moved
        whatGhostNode();

        //checking wall limits
        wallLimits();

        //verifying collisions with pac dots
        Collisions.ItemCollision(this, game.ec, game);

        //verifying collisions with ghosts
        Collisions.ghostCollison(this, game.eb, game);

        //verifying if a direction is on fire
        if(game.isEnergizerOn()){
            if(R) animRs.runAnimation();
            if(L) animLs.runAnimation();
            if(U) animUs.runAnimation();
            if(D) animDs.runAnimation();
        }
        else{
            if(R) animR.runAnimation();
            if(L) animL.runAnimation();
            if(U) animU.runAnimation();
            if(D) animD.runAnimation();
        }

        //set up the new X and Y
        lastX = x;
        lastY = y;

    }
    public void render(Graphics g){
        //verifying if a direction is on fire
        if(dyingFlag){
            g.drawImage(tex.playerDying, (int)x, (int)y, null);
        }

        if(game.isEnergizerOn() && !dyingFlag){
            if(R) animRs.drawAnimation(g,x,y,0);
            if(L) animLs.drawAnimation(g,x,y,0);
            if(U) animUs.drawAnimation(g,x,y,0);
            if(D) animDs.drawAnimation(g,x,y,0);
        }
        if(!game.isEnergizerOn() && !dyingFlag){
            if(R) animR.drawAnimation(g,x,y,0);
            if(L) animL.drawAnimation(g,x,y,0);
            if(U) animU.drawAnimation(g,x,y,0);
            if(D) animD.drawAnimation(g,x,y,0);
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

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y,47,50);
    }

    public Rectangle getUR() {
        return new Rectangle(((int)x+4), (int)y, 39, 4);
    }

    public Rectangle getRR() {
        return new Rectangle(((int)x+43), ((int)y+4), 4, 42);
    }

    public Rectangle getDR() {
        return new Rectangle(((int)x+4), ((int)y+46), 39, 4);
    }

    public Rectangle getLR() {
        return new Rectangle((int)x, ((int)y+4), 4, 42);
    }

    public void setR(boolean r) {
        R = r;
    }

    public void setL(boolean l) {
        L = l;
    }

    public void setU(boolean u) {
        U = u;
    }

    public void setD(boolean d) {
        D = d;
    }

    public void directionSwap(){
        if(R) R = false;
        if(L) L = false;
        if(U) U = false;
        if(D) D = false;
    }
    public void whatGhostNode(){
        for(int i = 0; i < Structures.ghostGraph.length; i++){
            if((getBounds().intersects(Structures.ghostGraph[i].getBounds()))){
                lastGhostNode = Structures.ghostGraph[i].getID();
                game.setShadowTarget(nameToInt(lastGhostNode));


            }
        }
    }

    public int nameToInt(String nodeName){
        String[] parts = nodeName.split("_");
        int node = Integer.parseInt(parts[1]);
        return node;
    }
    public void wallLimits(){
        if(x != lastX || y != lastY){
            if(R){
                if(Collisions.wallCollison(game.ed,this.getRR())){
                    x = lastX;
                };
            }
            if(L){
                if(Collisions.wallCollison(game.ed,this.getLR())){
                    x = lastX;
                };
            }
            if(U){
                if(Collisions.wallCollison(game.ed,this.getUR())){
                    y = lastY;
                };
            }
            if(D){
                if(Collisions.wallCollison(game.ed,this.getDR())){
                    y = lastY;
                };
            }

        }
    }

    public Rectangle getPosBounds(){
        return new Rectangle((int)x + 22, (int)y + 23,10, 10 );
    }


    public Boolean getDyingFlag() {
        return dyingFlag;
    }

    public void setDyingFlag(Boolean dyingFlag) {
        this.dyingFlag = dyingFlag;
    }
}
