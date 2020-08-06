package characters;

import dataStructures.GhostNode;
import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import interfaces.Ghost;
import main.Game;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bashful extends GameObject implements Ghost {

    private double velX = 0;
    private double velY = 0;

    private boolean R;
    private boolean L;
    private boolean U;
    private boolean D;

    private Game game;

    private List<String> ghostPath = new ArrayList<String>();
    private String nowGhostNode = "Node_28";
    private int ghostNodeCont = 0;

    private String ghostID = "bashful";

    private Skins tex;
    private Animations animR, animL, animU, animD, animRs, animLs, animUs, animDs;

    Random rand;

    public Bashful(double x, double y, Skins tex, Game game){
        super(x, y);
        this.tex = tex;
        this.game = game;

        animR = new Animations(5, tex.bashful[0], tex.bashful[1]);
        animL = new Animations(5, tex.bashful[2], tex.bashful[3]);
        animU = new Animations(5, tex.bashful[4], tex.bashful[5]);
        animD = new Animations(5, tex.bashful[6], tex.bashful[7]);

        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

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


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y,47,54);
    }

    public void whatGhostNode(){
        for(int i = 0; i < Structures.ghostGraph.length; i++){
            if((getBounds().intersects(Structures.ghostGraph[i].getBounds()))){
                if(!(nowGhostNode.equals(Structures.ghostGraph[i].getID()))){
                    nowGhostNode = Structures.ghostGraph[i].getID();
                }
            }
        }
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

    public int nameToInt(String nodeName){
        String[] parts = nodeName.split("_");
        int node = Integer.parseInt(parts[1]);
        return node;
    }

    public void directionSwap(){
        if(R) R = false;
        if(L) L = false;
        if(U) U = false;
        if(D) D = false;
    }



    public void ghostMovement(){
        for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {


                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                int s = nameToInt(nowGhostNode); //The starting node
                createScaredDirections(s);
                break;
            }
        }
        // we verify if Shadow is on the same pos than some GhostNode and there are commands to execute
        if(ghostNodeCont < ghostPath.size()){

            for(int gn = 0; gn < Structures.ghostGraph.length; gn++){
                // GhostNode match
                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                    //Right
                    if(ghostPath.get(ghostNodeCont).equals("R")){
                        directionSwap();
                        velX = 2;
                        velY = 0;
                        ghostNodeCont++;
                        R = true;
                        break;
                    }
                    //Left
                    if(ghostPath.get(ghostNodeCont).equals("L")){
                        directionSwap();
                        velX = -2;
                        velY = 0;
                        ghostNodeCont++;
                        L = true;
                        break;
                    }
                    //Up
                    if(ghostPath.get(ghostNodeCont).equals("U")){
                        directionSwap();
                        velX = 0;
                        velY = -2;
                        ghostNodeCont++;
                        U = true;
                        break;
                    }
                    //Down
                    if(ghostPath.get(ghostNodeCont).equals("D")){
                        directionSwap();
                        velX = 0;
                        velY = 1.5;
                        ghostNodeCont++;
                        D = true;
                        break;
                    }
                }
            }
        }
        //When there are not paths in the list, shadow stops
        else{
            for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
                // GhostNode match in the final node

                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {


                    ghostPath.clear();
                    velX = 0;
                    velY = 0;
                    ghostNodeCont = 0;
                    break;
                }
            }
        }
    }
    public void createDirectionsFromGraph(int s, int f){
        //we create a new route when shadow arrives to the final node of the ghostPath
        if(s != f){

            List<String> stringPath = game.getRouter().getPath(s, f, game.getRouter().getD());

            //we create the directions list
            for(int i = 0; i < stringPath.size() - 1; i++){
                int posActualNode = nameToInt(stringPath.get(i)); //pos of the actual node
                GhostNode gn = Structures.ghostGraph[posActualNode]; //actual GhostNode
                String nextNode = stringPath.get(i+1); //ID of the next node

                //now we lock for the direction for the next node

                //we ask if the next node is right
                if(nextNode.equals(gn.getRight())){
                    ghostPath.add("R");
                }
                //we ask if the next node is left
                if(nextNode.equals(gn.getLeft())){
                    ghostPath.add("L");
                }
                //we ask if the next node is Up
                if(nextNode.equals(gn.getUp())){
                    ghostPath.add("U");
                }
                //we ask if the next node is Down
                if(nextNode.equals(gn.getDown())){
                    ghostPath.add("D");
                }
            }
        }
    }

    public void hide(){
        //update the ghostPath every time shadow is on a new ghostNode
        for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                int s = nameToInt(nowGhostNode); //The starting node
                createDirectionsFromGraph(s, 31); //we create a path with ghostHouse as finalNode
                break;
            }
        }
        // we verify if Shadow is on the same pos than some GhostNode and there are commands to execute
        if(ghostNodeCont < ghostPath.size()){

            for(int gn = 0; gn < Structures.ghostGraph.length; gn++){

                // GhostNode match
                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                    //Right
                    if(ghostPath.get(ghostNodeCont).equals("R")){
                        directionSwap();
                        velX = 2;
                        velY = 0;
                        ghostNodeCont++;

                        R = true;
                        break;
                    }
                    //Left
                    if(ghostPath.get(ghostNodeCont).equals("L")){
                        directionSwap();
                        velX = -2;
                        velY = 0;
                        ghostNodeCont++;

                        L = true;
                        break;
                    }
                    //Up
                    if(ghostPath.get(ghostNodeCont).equals("U")){
                        directionSwap();
                        velX = 0;
                        velY = -1.5;
                        ghostNodeCont++;

                        U = true;
                        break;
                    }
                    //Down
                    if(ghostPath.get(ghostNodeCont).equals("D")){
                        directionSwap();
                        velX = 0;
                        velY = 2;
                        ghostNodeCont++;

                        D = true;
                        break;

                    }

                }

            }
        }
        //When there are not paths in the list, shadow stops
        else{
            for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
                // GhostNode match in the final node

                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {
                    ghostPath.clear();
                    velX = 0;
                    velY = 0;
                    ghostNodeCont = 0;
                    break;
                }
            }
        }
    }

    public String getGhostID() {
        return ghostID;
    }

    public Rectangle getPosBounds(){
        return new Rectangle((int)x + 22, (int)y + 23,2, 2 );
    }
}
