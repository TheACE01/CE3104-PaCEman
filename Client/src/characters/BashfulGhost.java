package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

/**
 * Bashful is the craziest ghost in the game. His movements are unpredictable and completely random
 * @author kevin Avevedo
 */
public class BashfulGhost extends Ghost {

    /**
     * Bashful constructor method
     * @param x Bashful X position
     * @param y Bashful Y position
     * @param tex Textures of the characters
     * @param game The main class of the game
     */
    public BashfulGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        //init the normal animations
        animR = new Animations(5, tex.bashful[0], tex.bashful[1]);
        animL = new Animations(5, tex.bashful[2], tex.bashful[3]);
        animU = new Animations(5, tex.bashful[4], tex.bashful[5]);
        animD = new Animations(5, tex.bashful[6], tex.bashful[7]);

        //init the scared animations
        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "bashful";

    }

    /**
     * Movement of Bashful when he appears
     */
    public void tick() {

        //when pacman eat an energizer
        if(game.isEnergizerOn()){
            hide();
        }
        else{
            ghostMovement();
        }

        //movement
        x += velX;
        y += velY;


        //encoding the bashful position
        game.getEncoder().setBashfulX(x);
        game.getEncoder().setBashfulY(y);

        //directions change
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

    /**
     * Bashful behavior when the the energizer is not triggered. Bashful moves random in the ghost graph
     */
    public void ghostMovement(){
        for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {


                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                Integer s = nameToInt(nowGhostNode); //The starting node
                createScaredDirections(s);
                break;
            }
        }
        // we verify if Shadow is on the same pos than some GhostNode and there are commands to execute
        if(ghostNodeCont < ghostPath.size()){

            for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++){
                // GhostNode match
                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                    //Right
                    if(ghostPath.get(ghostNodeCont).equals("R")){
                        directionSwap();
                        velX = boost;
                        velY = 0.0;
                        ghostNodeCont++;
                        R = true;
                        break;
                    }
                    //Left
                    if(ghostPath.get(ghostNodeCont).equals("L")){
                        directionSwap();
                        velX = -boost;
                        velY = 0.0;
                        ghostNodeCont++;
                        L = true;
                        break;
                    }
                    //Up
                    if(ghostPath.get(ghostNodeCont).equals("U")){
                        directionSwap();
                        velX = 0.0;
                        velY = -boost;
                        ghostNodeCont++;
                        U = true;
                        break;
                    }
                    //Down
                    if(ghostPath.get(ghostNodeCont).equals("D")){
                        directionSwap();
                        velX = 0.0;
                        velY = boost;
                        ghostNodeCont++;
                        D = true;
                        break;
                    }
                }
            }
        }
        //When there are not paths in the list, shadow stops
        else{
            for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++) {
                // GhostNode match in the final node

                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {


                    ghostPath.clear();
                    velX = 0.0;
                    velY = 0.0;
                    ghostNodeCont = 0;
                    break;
                }
            }
        }
    }

    /**
     * Bashful behavior when the energizer is triggered. The ghost escapes to the ghostHouse
     */
    public void hide(){
        //update the ghostPath every time shadow is on a new ghostNode
        for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                Integer s = nameToInt(nowGhostNode); //The starting node
                createDirectionsFromGraph(s, 31); //we create a path with ghostHouse as finalNode
                break;
            }
        }
        // we verify if Shadow is on the same pos than some GhostNode and there are commands to execute
        if(ghostNodeCont < ghostPath.size()){

            for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++){

                // GhostNode match
                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                    //Right
                    if(ghostPath.get(ghostNodeCont).equals("R")){
                        directionSwap();
                        velX = boost;
                        velY = 0.0;
                        ghostNodeCont++;

                        R = true;
                        break;
                    }
                    //Left
                    if(ghostPath.get(ghostNodeCont).equals("L")){
                        directionSwap();
                        velX = -boost;
                        velY = 0.0;
                        ghostNodeCont++;

                        L = true;
                        break;
                    }
                    //Up
                    if(ghostPath.get(ghostNodeCont).equals("U")){
                        directionSwap();
                        velX = 0.0;
                        velY = -boost;
                        ghostNodeCont++;

                        U = true;
                        break;
                    }
                    //Down
                    if(ghostPath.get(ghostNodeCont).equals("D")){
                        directionSwap();
                        velX = 0.0;
                        velY = boost;
                        ghostNodeCont++;

                        D = true;
                        break;

                    }

                }

            }
        }
        //When there are not paths in the list, shadow stops
        else{
            for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++) {
                // GhostNode match in the final node

                if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {
                    ghostPath.clear();
                    velX = 0.0;
                    velY = 0.0;
                    ghostNodeCont = 0;
                    break;
                }
            }
        }
    }
}
