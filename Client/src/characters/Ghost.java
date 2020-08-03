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

    public void tick() {

        if(game.isEnergizerOn()){
            hide();
        }
        else{
            ghostMovement();
        }
        //movement
        x += velX;
        y += velY;

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

    }

    public void render(Graphics g){
        if(game.isEnergizerOn()){
            if(R) animRs.drawAnimation(g,x,y,0);
            if(L) animLs.drawAnimation(g,x,y,0);
            if(U) animUs.drawAnimation(g,x,y,0);
            if(D) animDs.drawAnimation(g,x,y,0);
        }
        else{
            if(R) animR.drawAnimation(g,x,y,0);
            if(L) animL.drawAnimation(g,x,y,0);
            if(U) animU.drawAnimation(g,x,y,0);
            if(D) animD.drawAnimation(g,x,y,0);
        }

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 47, 50);
    }

    public void whatGhostNode() {
        for (int i = 0; i < Structures.ghostGraph.length; i++) {
            if ((getBounds().intersects(Structures.ghostGraph[i].getBounds()))) {
                if (!(nowGhostNode.equals(Structures.ghostGraph[i].getID()))) {
                    nowGhostNode = Structures.ghostGraph[i].getID();
                }
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void createDirectionsFromGraph(int s, int f) {
        //we create a new route when shadow arrives to the final node of the ghostPath
        if (s != f) {

            List<String> stringPath = game.getRouter().getPath(s, f, game.getRouter().getD());

            //we create the directions list
            for (int i = 0; i < stringPath.size() - 1; i++) {
                int posActualNode = nameToInt(stringPath.get(i)); //pos of the actual node
                GhostNode gn = Structures.ghostGraph[posActualNode]; //actual GhostNode
                String nextNode = stringPath.get(i + 1); //ID of the next node

                //now we lock for the direction for the next node

                //we ask if the next node is right
                if (nextNode.equals(gn.getRight())) {
                    ghostPath.add("R");
                }
                //we ask if the next node is left
                if (nextNode.equals(gn.getLeft())) {
                    ghostPath.add("L");
                }
                //we ask if the next node is Up
                if (nextNode.equals(gn.getUp())) {
                    ghostPath.add("U");
                }
                //we ask if the next node is Down
                if (nextNode.equals(gn.getDown())) {
                    ghostPath.add("D");
                }
            }
        }
    }

    public int nameToInt(String nodeName) {
        String[] parts = nodeName.split("_");
        int node = Integer.parseInt(parts[1]);
        return node;
    }

    public void directionSwap() {
        if (R) R = false;
        if (L) L = false;
        if (U) U = false;
        if (D) D = false;
    }

    public Rectangle getPosBounds() {
        return new Rectangle((int) x + 22, (int) y + 23, 2, 2);
    }

    public void ghostMovement() {

    }
    public void hide(){

    }


    public String getGhostID() {
        return ghostID;
    }
    public void createScaredDirections(int s){
        GhostNode gn = Structures.ghostGraph[s]; //actual GhostNode
        boolean selected = false;
        Random random = new Random();

        while(!selected){
            int randomDirection = random.nextInt(4);

            if(randomDirection == 0 && !(gn.getRight().equals("None"))){
                ghostPath.add("R");
                selected = true;

            }
            if(randomDirection == 1 && !(gn.getLeft().equals("None"))){
                ghostPath.add("L");
                selected = true;
            }
            if(randomDirection == 2 && !(gn.getUp().equals("None"))){
                ghostPath.add("U");
                selected = true;
            }
            if(randomDirection == 3 && !(gn.getDown().equals("None"))){
                ghostPath.add("D");
                selected = true;
            }
        }
    }


}
