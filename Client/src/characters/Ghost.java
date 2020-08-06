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

/**
 * This is the parent ghost class, determines the characteristics and the main behavior of the enemies.
 * @author Kevin Acevedo Rodríguez
 */
public class Ghost {
    //ghost positions
    public double x, y;

    //needed to move on the screen
    public Double velX, velY;

    //velocity change factor
    public Double boost = 2.0;

    //ghost move direction
    public Boolean R, L, U, D;

    //This List saves the route of the ghost(the ghost nodes that the ghost have to visit)
    public List<String> ghostPath = new ArrayList<>();

    //access to the main class
    public Game game;

    //access to the sprites of the ghost
    public Skins tex;

    //the actual ghost node visited
    public String nowGhostNode = "Node_28";

    //the visited ghost nodes of the ghostPath
    public Integer ghostNodeCont = 0;

    //access to the animations of the characters
    public Animations animR, animL, animU, animD, animRs, animLs, animUs, animDs;

    //ghost identifier
    public String ghostID;


    /**
     * Constructor method of the Ghost class
     * @param x X position of the ghost
     * @param y Y position of the ghost
     * @param tex Textures to the sprites
     * @param game The main pacman class
     */
    public Ghost(Double x, Double y, Skins tex, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;

        //the ghost begin static
        R = false;
        L = false;
        U = false;
        D = false;
    }

    /**
     * Control the movement of the ghost
     */
    public void tick() {

        //if pacman ate an energizer
        if(game.isEnergizerOn()){
            hide();
        }
        else{
            ghostMovement();
        }

        //movement
        x += velX;
        y += velY;

        //change animation of the ghost when pacman ate an energizer
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
     * Controls the visual animations of the ghost
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
            if(R) animR.drawAnimation(g,x,y,0);
            if(L) animL.drawAnimation(g,x,y,0);
            if(U) animU.drawAnimation(g,x,y,0);
            if(D) animD.drawAnimation(g,x,y,0);
        }

    }

    /**
     * This method can create a rectangle to validate collisions with other objects in the game
     * @return An invisible rectangle of the ghost
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 47, 50);
    }

    /**
     * This method determines the actual ghost node visited by the ghost
     */
    public void whatGhostNode() {
        for (int i = 0; i < Structures.ghostGraph.length; i++) {
            if ((getBounds().intersects(Structures.ghostGraph[i].getBounds()))) {
                if (!(nowGhostNode.equals(Structures.ghostGraph[i].getID()))) {
                    nowGhostNode = Structures.ghostGraph[i].getID();
                }
            }
        }
    }


    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    /**
     * Use the dijkstra algorithm to modify the ghostPath and create a route
     * @param s The number of the starting ghost node
     * @param f The number of the finale ghost node
     */
    public void createDirectionsFromGraph(Integer s, Integer f) {
        //we create a new route when shadow arrives to the final node of the ghostPath
        if (s != f) {

            //The path of the ghost
            List<String> stringPath = game.getRouter().getPath(s, f, game.getRouter().getD());

            //we create the directions list
            for (int i = 0; i < stringPath.size() - 1; i++) {
                Integer posActualNode = nameToInt(stringPath.get(i)); //pos of the actual node
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

    /**
     * Conversion of a "Node_1" String to a number
     * @param nodeName The name of the node
     * @return An integer representing the number of the node
     */
    public Integer nameToInt(String nodeName) {
        String[] parts = nodeName.split("_");
        Integer node = Integer.parseInt(parts[1]);
        return node;
    }

    /**
     * denial of the ghost directions
     */
    public void directionSwap() {
        if (R) R = false;
        if (L) L = false;
        if (U) U = false;
        if (D) D = false;
    }

    /**
     * Create a little rectangle to validate when the ghost slashes in a ghost node
     * @return A rectangle object
     */
    public Rectangle getPosBounds() {
        return new Rectangle((int)x + 22, (int) y + 23, 2, 2);
    }

    /**
     * Behavior of the ghost when the energizer is not activated
     */
    public void ghostMovement() {

    }

    /**
     * Behavior of the ghost when the energizer is activated
     */
    public void hide(){

    }


    public String getGhostID() {
        return ghostID;
    }

    /**
     * A random directions creator
     * @param s The starting ghost node number
     */
    public void createScaredDirections(int s){
        GhostNode gn = Structures.ghostGraph[s]; //actual GhostNode
        Boolean selected = false;
        Random random = new Random();

        while(!selected){
            Integer randomDirection = random.nextInt(4);

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


    public Double getBoost() {
        return boost;
    }

    public void setBoost(Double boost) {
        this.boost = boost;
    }
}
