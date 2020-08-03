package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.util.Random;

public class PokeyGhost extends Ghost {

    private int corner = 59;

    public PokeyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        animR = new Animations(5, tex.pokey[0], tex.pokey[1]);
        animL = new Animations(5, tex.pokey[2], tex.pokey[3]);
        animU = new Animations(5, tex.pokey[4], tex.pokey[5]);
        animD = new Animations(5, tex.pokey[6], tex.pokey[7]);

        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "pokey";
    }
    public void ghostMovement(){
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
                        int newCorner = random.nextInt(6);
                        if(newCorner == 0 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 0;
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
                            corner = 9;
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
                            corner = 47;
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
                            corner = 59;
                            whatGhostNode(); //update nowGhostNode
                            int s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 4 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 27;
                            whatGhostNode(); //update nowGhostNode
                            int s = nameToInt(nowGhostNode); //The starting node
                            createDirectionsFromGraph(s, corner);
                            selected = true;
                        }
                        if(newCorner == 5 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0;
                            velY = 0;
                            ghostNodeCont = 0;
                            corner = 36;
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
    public void hide(){
        //update the ghostPath every time shadow is on a new ghostNode
        for(int gn = 0; gn < Structures.ghostGraph.length; gn++) {
            // GhostNode match
            if ((getPosBounds().intersects(Structures.ghostGraph[gn].getPosBounds()))) {

                whatGhostNode(); //update nowGhostNode
                ghostPath.clear(); //reset the ghostPath
                ghostNodeCont = 0; //reset counter
                int s = nameToInt(nowGhostNode); //The starting node
                createDirectionsFromGraph(s, 30); //we create a path with ghostHouse as finalNode
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



}
