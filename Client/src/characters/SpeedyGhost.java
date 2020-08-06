package characters;

import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.Random;

/**
 * The smartest ghost, Speedy change his behavior every 10 seconds. His modes are chase and scatter.
 * @author kevin Avevedo
 */
public class SpeedyGhost extends Ghost {

    //The chase animations of Speedy
    private Animations animRc, animLc, animUc, animDc;

    //The las target of shadow
    private Integer lastShadowTarget;

    //The last corner visited
    private Integer corner;

    //A random generator
    private Random rand = new Random();

    /**
     * Speedy constructor method
     * @param x Speedy X position
     * @param y Speedy Y position
     * @param tex Textures of the game
     * @param game The main class of the game
     */
    public SpeedyGhost(double x, double y, Skins tex, Game game) {
        super(x, y, tex, game);

        //init the normal animations
        animR = new Animations(5, tex.speedy[0], tex.speedy[1]);
        animL = new Animations(5, tex.speedy[2], tex.speedy[3]);
        animU = new Animations(5, tex.speedy[4], tex.speedy[5]);
        animD = new Animations(5, tex.speedy[6], tex.speedy[7]);

        //init the chase animations
        animRc = new Animations(5, tex.chaseSpeedy[0], tex.chaseSpeedy[1]);
        animLc = new Animations(5, tex.chaseSpeedy[2], tex.chaseSpeedy[3]);
        animUc = new Animations(5, tex.chaseSpeedy[4], tex.chaseSpeedy[5]);
        animDc = new Animations(5, tex.chaseSpeedy[6], tex.chaseSpeedy[7]);

        //init scared animations
        animRs = new Animations(10, tex.scaredGhost[0], tex.scaredGhost[1]);
        animLs = new Animations(10, tex.scaredGhost[2], tex.scaredGhost[3]);
        animUs = new Animations(10, tex.scaredGhost[4], tex.scaredGhost[5]);
        animDs = new Animations(10, tex.scaredGhost[6], tex.scaredGhost[7]);

        ghostID = "speedy";

        //init the last shadow target
        lastShadowTarget = game.getShadowTarget();

        //When this ghost appears moves to a random corner of the map
        Integer newCorner = rand.nextInt(4);
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

    /**
     * Movement of Speedy
     */
    public void tick() {

        //when pacman eats an energizer
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

        //encoding the speedy pos
        game.getEncoder().setSpeedyX(x);
        game.getEncoder().setSpeedyY(y);

        //directions change
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

    /**
     * Visual animations of Speedy, draw the ghost on the screen
     * @param g The painter object created in the Game class
     */
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

    /**
     * Chase behavior of Speedy. When this mode is triggered Speedy moves to the pacman ghost node
     */
    public void shadowMode(){
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
     * Scatter behavior of Speedy. When this mode is triggered Speedy moves to the energizer nodes
     */
    public void pokeyMode(){
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
                        Integer newCorner = random.nextInt(4);
                        if(newCorner == 0 && newCorner != corner){
                            ghostPath.clear();
                            velX = 0.0;
                            velY = 0.0;
                            ghostNodeCont = 0;
                            corner = 32;
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
                            corner = 34;
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
                            corner = 21;
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
                            corner = 40;
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

}
