package creators;

import characters.*;
import dataStructures.Quadrant;
import dataStructures.Structures;
import graphics.Skins;
import items.*;
import main.Game;

import java.awt.*;
import java.util.LinkedList;

public class CharacterCreator {
    private LinkedList<Ghost> ghosts = new LinkedList<Ghost>();
    private LinkedList<Item> ec = new LinkedList<Item>();



    Ghost entB; //ghosts
    Item entC; //items

    private ShadowGhost shadow;
    private BashfulGhost bashful;
    private PokeyGhost pokey;
    private SpeedyGhost speedy;

    private Skins tex;
    private Game game;

    public CharacterCreator(Skins tex, Game game){
        this.tex = tex;
        this.game = game;

    }


    public void tick(){

    }
    public void render(Graphics g){
        //C class
        for(int i = 0; i < ec.size(); i++){
            entC = ec.get(i);

            entC.render(g);
        }
        //B class
        for(int i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);

            entB.render(g);
        }
    }

    public void addEntity(Ghost entity){
        ghosts.add(entity);
    }
    public void removeEntity(Ghost entity){
        ghosts.remove(entity);
    }

    public void addEntity(Item entity){
        ec.add(entity);
    }
    public void removeEntity(Item entity){
        ec.remove(entity);
    }

    public void createShadow(){
        shadow = new ShadowGhost(0,0,tex, game);
        addEntity(shadow);
    }

    public void createBashful(){
        bashful = new BashfulGhost(0,0,tex, game);
        addEntity(bashful);
    }

    public void createPokey(){
        pokey = new PokeyGhost(0,0,tex, game);
        addEntity(pokey);
    }

    public void createSpeedy(){
        speedy = new SpeedyGhost(0,0,tex, game);
        addEntity(speedy);
    }
    public void createPacDots(){
        //put pac dots in the map
        for(int i = 0; i < Structures.director.length; i++){
            Quadrant q = Structures.director[i];
            if(q.getQuadrant() == 0){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant()));
                continue;
            }
            if(q.getQuadrant() == 143){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant()));
                continue;
            }
            if(q.getQuadrant() == 165){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant()));
                continue;
            }
            if(q.getQuadrant() == 20){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant()));
                continue;
            }
            else{
                addEntity(new PacDot(q.getX(),q.getY(),tex, "pacDot", q.getQuadrant()));
            }
        }
    }

    public void removeItem(int quadrant){
        for(int i = 0; i < ec.size(); i++){
            Item item = ec.get(i);
            if(item.quadrant == quadrant){
                removeEntity(item);
                break;
            }
        }
    }



    public LinkedList<Ghost> getEb() {
        return ghosts;
    }

    public LinkedList<Item> getEc() {
        return ec;
    }

    public ShadowGhost getShadow() {
        return shadow;
    }

    public BashfulGhost getBashful() {
        return bashful;
    }

    public PokeyGhost getPokey() {
        return pokey;
    }

    public SpeedyGhost getSpeedy() {
        return speedy;
    }

}
