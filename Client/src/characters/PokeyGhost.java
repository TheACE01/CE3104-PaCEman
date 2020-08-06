package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.util.Random;

/**
 * Pokey look for the energizer points of the map. When he slashes a new energizer node, decides the next node to visit
 * @author kevin Avevedo
 */
public class PokeyGhost extends Ghost {

    //The number of the last corner visited
    private Integer corner = 59;

    /**
     *
     * @param x Pokey X position
     * @param y Pokey Y position
     * @param tex Textures of the characters
     * @param game The main class of the game
     */
    public PokeyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        //init the normal animations
        animR = new Animations(5, tex.pokey[0], tex.pokey[1]);
        animL = new Animations(5, tex.pokey[2], tex.pokey[3]);
        animU = new Animations(5, tex.pokey[4], tex.pokey[5]);
        animD = new Animations(5, tex.pokey[6], tex.pokey[7]);

        //init the scared animations
        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "pokey";
    }

    /**
     * Movement logic of the ghost
     */
    public void tick() {

        //when pacman eats an energizer
        if(game.isEnergizerOn()){
            hide();
        }
        else{
            ghostMovement();
        }

        //movement
        x += velX;
        y += velY;

        //encoding the pokey position
        game.getEncoder().setPokeyX(x);
        game.getEncoder().setPokeyY(y);

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
     * Pokey behavior when the energizer is not activated. Pokey moves on the map corners
     */
    public void ghostMovement(){
        //update the ghostPath every time shadow is on a new ghostNode
        for(Integer gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                Integer s = nameToInt(nowGhostNode); //The starting node
                createDirectionsFromGraph(s, corner);

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
                    Random random = new Random();
                    Boolean selected = false;

                    while(!selected){
                        Integer newCorner = random.nextInt(6);
                        if(newCorner == 0 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 0;
                            whatGhostNode(); //update nowGhostNode
                            Integer s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 1 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 9;
                            whatGhostNode(); //update nowGhostNode
                            Integer s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 2 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 47;
                            whatGhostNode(); //update nowGhostNode
                            Integer s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 3 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 59;
                            whatGhostNode(); //update nowGhostNode
                            Integer s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 4 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 27;
                            whatGhostNode(); //update nowGhostNode
                            Integer s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 5 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 36;
                            whatGhostNode(); //update nowGhostNode
                            Integer s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                    }

                }
            }
        }
    }

    /**
     * Pokey behavior when the energizer is triggered. He moves to the ghostHouse
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
                createDirectionsFromGraph(s, 30); //we create a path with ghostHouse as finalNode
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
