package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.Random;

public class SpeedyGhost extends Ghost {

    private Animations animRc, animLc, animUc, animDc;
    private int lastShadowTarget;
    private int corner;
    private Random rand = new Random();


    public SpeedyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        animR = new Animations(5, tex.speedy[0], tex.speedy[1]);
        animL = new Animations(5, tex.speedy[2], tex.speedy[3]);
        animU = new Animations(5, tex.speedy[4], tex.speedy[5]);
        animD = new Animations(5, tex.speedy[6], tex.speedy[7]);

        animRc = new Animations(5, tex.chaseSpeedy[0], tex.chaseSpeedy[1]);
        animLc = new Animations(5, tex.chaseSpeedy[2], tex.chaseSpeedy[3]);
        animUc = new Animations(5, tex.chaseSpeedy[4], tex.chaseSpeedy[5]);
        animDc = new Animations(5, tex.chaseSpeedy[6], tex.chaseSpeedy[7]);

        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "speedy";
        lastShadowTarget = game.getShadowTarget();

        int newCorner = rand.nextInt(4);
        if(newCorner == 0){
            corner = 32;
        }
        if(newCorner == 1){
            corner = 34;
        }
        if(newCorner == 2){
            corner = 21;
        }
        if(newCorner == 3){
            corner = 40;
        }
    }
    public void tick() {

        if(game.isEnergizerOn()){
            hide();
        }
        else{
            if(game.isChaiseSpeedy()){
                shadowMode();
            }
            else{
                pokeyMode();
            }

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
            if(game.isChaiseSpeedy()){
                if(R) animRc.runAnimation();
                if(L) animLc.runAnimation();
                if(U) animUc.runAnimation();
                if(D) animDc.runAnimation();
            }
            else{
                if(R) animR.runAnimation();
                if(L) animL.runAnimation();
                if(U) animU.runAnimation();
                if(D) animD.runAnimation();
            }

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
            if(game.isChaiseSpeedy()){
                if(R) animRc.drawAnimation(g,x,y,0);
                if(L) animLc.drawAnimation(g,x,y,0);
                if(U) animUc.drawAnimation(g,x,y,0);
                if(D) animDc.drawAnimation(g,x,y,0);
            }
            else{
                if(R) animR.drawAnimation(g,x,y,0);
                if(L) animL.drawAnimation(g,x,y,0);
                if(U) animU.drawAnimation(g,x,y,0);
                if(D) animD.drawAnimation(g,x,y,0);
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
                createDirectionsFromGraph(s, 29); //we create a path with ghostHouse as finalNode
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
    public void shadowMode(){
        //update the ghostPath every time shadow is on a new ghostNode
        for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                int s = nameToInt(nowGhostNode); //The starting node
                createDirectionsFromGraph(s, game.getShadowTarget());
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
    public void pokeyMode(){
        //update the ghostPath every time shadow is on a new ghostNode
        for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                int s = nameToInt(nowGhostNode); //The starting node
                createDirectionsFromGraph(s, corner);

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
                    Random random = new Random();
                    boolean selected = false;

                    while(!selected){
                        int newCorner = random.nextInt(4);
                        if(newCorner == 0 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 32;
                            whatGhostNode(); //update nowGhostNode
                            int s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 1 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 34;
                            whatGhostNode(); //update nowGhostNode
                            int s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 2 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 21;
                            whatGhostNode(); //update nowGhostNode
                            int s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 3 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 40;
                            whatGhostNode(); //update nowGhostNode
                            int s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                    }

                }
            }
        }
    }

}
