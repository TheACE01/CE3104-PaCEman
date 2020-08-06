package characters;

import Physics.Collisions;
import dataStructures.Structures;
import graphics.Animations;
import graphics.Skins;
import main.Game;

import java.awt.*;

/**
 *The player class. The player controls pacman in the game using the keyboard.
 * @author kevin Avevedo
 */
public class PacMan {

    //position of the player
    private double x, y;

    //velocity of the player
    private Double velX = 0.0;
    private Double velY = 0.0;

    //Last positions of the player
    private Double lastX, lastY;

    //Last ghost node visited
    private String lastGhostNode = "Node_32";

    //Directions flags
    private Boolean R, L, U, D;

    //flag to verify when to use the dying animation
    private Boolean dyingFlag = false;

    //Textures of the character
    private Skins tex;

    //Access to the game
    private Game game;

    //Animations of the character
    private Animations animR, animL, animU, animD, animRs, animLs, animUs, animDs;

    /**
     * PacMan constructor method
     * @param x Player X position
     * @param y Player Y position
     * @param tex Textures of the character
     * @param game The main class of the game
     */
    public PacMan(double x, double y, Skins tex, Game game){
        this.x = x;
        this.y = y;
        this.game = game;
        this.tex = tex;

        //init the normal animations
        animR = new Animations(10, tex.player[0], tex.player[1]);
        animL = new Animations(10, tex.player[2], tex.player[3]);
        animU = new Animations(10, tex.player[4], tex.player[5]);
        animD = new Animations(10, tex.player[6], tex.player[7]);

        //init the scared animations
        animRs = new Animations(10, tex.playerEnergized[0], tex.playerEnergized[1]);
        animLs = new Animations(10, tex.playerEnergized[2], tex.playerEnergized[3]);
        animUs = new Animations(10, tex.playerEnergized[4], tex.playerEnergized[5]);
        animDs = new Animations(10, tex.playerEnergized[6], tex.playerEnergized[7]);

        //init the directions
        R = true;
        L = false;
        U = false;
        D = false;

        //init the initial positions
        lastX = 94.0;
        lastY = 100.0;
        x = lastX;
        y = lastY;
    }

    /**
     * PacMan movements control
     */
    public void tick(){
        //when pacman is not dead
        if(!game.getResetCountFlag()){
            x += velX;
            y += velY;

            //update the encoder
            game.getEncoder().setPacmanX(x);
            game.getEncoder().setPacmanY(y);

        }

        //we ask if the player moved
        whatGhostNode();

        //checking wall limits
        wallLimits();

        //verifying collisions with map items
        Collisions.ItemCollision(this, game.getItems(), game);

        //verifying collisions with ghosts
        Collisions.ghostCollison(this, game.ghosts, game);

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

    /**
     * Control of the visual animations of PacMan
     * @param g The graphic object to draw on screen
     */
    public void render(Graphics g){

        //when pacman is slashed
        if(dyingFlag){
            g.drawImage(tex.playerDying, (int)x, (int)y, null);
        }

        //when the energizer is triggered
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

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setVelX(Double velX) {
        this.velX = velX;
    }

    public void setVelY(Double velY) {
        this.velY = velY;
    }

    /**
     * Creates an invisible rectangle of the player
     * @return A rectangle object of pac man
     */
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

    public void setR(Boolean r) {
        R = r;
    }

    public void setL(Boolean l) {
        L = l;
    }

    public void setU(Boolean u) {
        U = u;
    }

    public void setD(Boolean d) {
        D = d;
    }

    /**
     * Denial of the direction flags
     */
    public void directionSwap(){
        if(R) R = false;
        if(L) L = false;
        if(U) U = false;
        if(D) D = false;
    }

    /**
     * Consult the actual visited ghost node in the graph
     */
    public void whatGhostNode(){
        for(Integer i = 0; i < Structures.ghostGraph.length; i++){
            if((getBounds().intersects(Structures.ghostGraph[i].getBounds()))){
                lastGhostNode = Structures.ghostGraph[i].getID();
                game.setShadowTarget(nameToInt(lastGhostNode));


            }
        }
    }

    /**
     * Converts a nodeName into a node number
     * @param nodeName The nodeName String
     * @return A node number representing the ghostNode
     */
    public Integer nameToInt(String nodeName){
        String[] parts = nodeName.split("_");
        Integer node = Integer.parseInt(parts[1]);
        return node;
    }

    /**
     * Controls the limits of the obstacles (walls) only for pac man
     */
    public void wallLimits(){
        if(x != lastX || y != lastY){
            if(R){
                if(Collisions.wallCollison(game.obstacles,this.getRR())){
                    x = lastX;
                };
            }
            if(L){
                if(Collisions.wallCollison(game.obstacles,this.getLR())){
                    x = lastX;
                };
            }
            if(U){
                if(Collisions.wallCollison(game.obstacles,this.getUR())){
                    y = lastY;
                };
            }
            if(D){
                if(Collisions.wallCollison(game.obstacles,this.getDR())){
                    y = lastY;
                };
            }
        }
    }

    /**
     * Control the death status of the player, this is used in the regeneration of the player in
     * the initial position
     * @param dyingFlag Boolean representing the live status of pac man
     */
    public void setDyingFlag(Boolean dyingFlag) {
        this.dyingFlag = dyingFlag;
    }
}
