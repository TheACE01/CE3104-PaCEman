package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

/**
 * Shadow is the most dangerous ghost of the game. When this enemy appears chase pacman in every moment.
 * @author Kevin Acevedo Rodríguez
 */
public class ShadowGhost extends Ghost {

    /**
     * Shadow constructor
     * @param x Shadow X position
     * @param y Shadow Y position
     * @param tex The textures of the game
     * @param game The main class
     */
    public ShadowGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        //init the normal animation sprites
        animR = new Animations(5, tex.shadow[0], tex.shadow[1]);
        animL = new Animations(5, tex.shadow[2], tex.shadow[3]);
        animU = new Animations(5, tex.shadow[4], tex.shadow[5]);
        animD = new Animations(5, tex.shadow[6], tex.shadow[7]);

        //init the scared animation sprites
        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        //init the ghost id
        ghostID = "shadow";
    }

    /**
     * Movement logic of shadow ghost
     */
    public void tick() {

        //when an energizer is activated
        if(game.isEnergizerOn()){
            hide();
        }
        else{
            ghostMovement();
        }
        //movement
        x += velX;
        y += velY;

        //encoding the shadow pos to notify the observer
        game.getEncoder().setShadowX(x);
        game.getEncoder().setShadowY(y);

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
     * Dangerous behavior of shadow, looking for pacman position in every moment.
     * This method change the ghost path when pacman visit a new ghost node of the graph.
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
                createDirectionsFromGraph(s, game.getShadowTarget());

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
     * Scared behavior of shadow. This movements are triggered when pacman eat an energizer.
     * The ghost escapes to the  ghostHouse
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
                createDirectionsFromGraph(s, 29); //we create a path with ghostHouse as finalNode
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
