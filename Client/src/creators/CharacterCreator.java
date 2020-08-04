package creators;

import characters.*;
import dataStructures.Quadrant;
import dataStructures.Structures;
import graphics.Skins;
import interfaces.Obstacle;
import items.*;
import main.Game;


import java.awt.*;
import java.util.LinkedList;

public class CharacterCreator {
    private LinkedList<Ghost> ghosts = new LinkedList<Ghost>();
    private LinkedList<Item> ec = new LinkedList<Item>();
    private LinkedList<Obstacle> ed = new LinkedList<Obstacle>();


    Ghost entB; //ghosts
    Item entC; //items
    Obstacle entD; //walls

    private Skins tex;
    private Game game;

    private ShadowGhost shadow;
    private BashfulGhost bashful;
    private PokeyGhost pokey;
    private SpeedyGhost speedy;

    public CharacterCreator(Skins tex, Game game){
        this.tex = tex;
        this.game = game;

    }


    public void tick(){

        //B class
        for(int i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);

            entB.tick();
        }

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
        shadow = new ShadowGhost(Structures.ghostGraph[29].getX(),Structures.ghostGraph[29].getY(),tex, game);
        addEntity(shadow);
    }

    public void createBashful(){
        bashful = new BashfulGhost(Structures.ghostGraph[31].getX(),Structures.ghostGraph[31].getY(),tex, game);
        addEntity(bashful);
    }

    public void createPokey(){
        pokey = new PokeyGhost(Structures.ghostGraph[30].getX(),Structures.ghostGraph[30].getY(),tex, game);
        addEntity(pokey);
    }

    public void createSpeedy(){
        speedy = new SpeedyGhost(Structures.ghostGraph[29].getX(),Structures.ghostGraph[29].getY(),tex, game);
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
    /*
    public void createAppleItem(int x, int y){
        addEntity(new Apple(x, y, tex));
    }
    public void createBananaItem(int x, int y){
        addEntity(new Banana(x, y, tex));
    }
    public void createCherryItem(int x, int y){
        addEntity(new Cherry(x, y, tex));
    }

     */


    public LinkedList<Ghost> getEb() {
        return ghosts;
    }

    public LinkedList<Item> getEc() {
        return ec;
    }

    public LinkedList<Obstacle> getEd() {
        return ed;
    }

    public void createWallRectangles(){
        //superior corner
        ed.add(new Wall(47,50,(47*26),50));
        //left corner
        ed.add(new Wall(47,50,47,(50*13)));
        //right corner
        ed.add(new Wall((47*26),50,47,(50*13)));
        //down corner
        ed.add(new Wall(47,(50*13),(47*26),50));

        ed.add(new Wall(47*3,(50*3),47,(50*4)));

        ed.add(new Wall(47*4,(50*3),47,50));

        ed.add(new Wall(47*6,(50*3),(47*3),50));

        ed.add(new Wall(47*5,(50*5),47,(50*3)));

        ed.add(new Wall(47*3,(50*8),47,(50*2)));

        ed.add(new Wall(47*7,(50*5),(47*2),50));

        ed.add(new Wall(47*7,(50*7),(47*2),50));

        ed.add(new Wall(47*3,(50*11),(47*3),50));

        ed.add(new Wall(47*5,(50*9),47,(50*3)));

        ed.add(new Wall(47*6,(50*9),(47*2),50));

        ed.add(new Wall(47*7,(50*11),(47*3),50));

        ed.add(new Wall(47*10,(50*3),47,50*2));

        ed.add(new Wall(47*11,(50*4),47*4,50));

        ed.add(new Wall(47*12,(50*2),47*3,50));

        ed.add(new Wall(47*16,(50*3),47*4,50));

        ed.add(new Wall(47*21,(50*3),47,50));

        ed.add(new Wall(47*23,(50*3),47*2,50));

        ed.add(new Wall(47*10,(50*6),47,50*4));

        ed.add(new Wall(47*9,(50*9),47,50));

        ed.add(new Wall(47*11,(50*8),47*4,50));

        ed.add(new Wall(47*14,(50*6),47,50*2));

        ed.add(new Wall(47*11,(50*11),47*2,50));

        ed.add(new Wall(47*12,(50*10),47*4,50));

        ed.add(new Wall(47*14,(50*12),47*2,50));

        ed.add(new Wall(47*16,(50*5),47,50*4));

        ed.add(new Wall(47*17,(50*8),47,50*4));

        ed.add(new Wall(47*18,(50*5),47*5,50));

        ed.add(new Wall(47*18,(50*6),47,50));

        ed.add(new Wall(47*19,(50*8),47*2,50));

        ed.add(new Wall(47*19,(50*8),47*2,50));

        ed.add(new Wall(47*20,(50*7),47*2,50));

        ed.add(new Wall(47*23,(50*7),47,50));

        ed.add(new Wall(47*24,(50*5),47,50*3));

        ed.add(new Wall(47*22,(50*9),47*3,50));

        ed.add(new Wall(47*19,(50*10),47*2,50*2));

        ed.add(new Wall(47*19,(50*10),47*2,50*2));

        ed.add(new Wall(47*21,(50*11),47*4,50));

        ed.add(new Wall(47*10,(50*6),47*3,50));


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

    public void GhostBoost(double boost){
        for(int i = 0; i < ghosts.size(); i++){
            ghosts.get(i).setBoost(boost);
        }
    }

}
