package creators;

import characters.Shadow;
import dataStructures.Structures;
import graphics.Skins;
import interfaces.Ghost;
import interfaces.Item;
import interfaces.Obstacle;
import items.PacDot;
import items.Wall;


import java.awt.*;
import java.util.LinkedList;

public class CharacterCreator {
    private LinkedList<Ghost> ghosts = new LinkedList<Ghost>();
    private LinkedList<Item> ec = new LinkedList<Item>();
    private LinkedList<Obstacle> ed = new LinkedList<Obstacle>();


    Ghost entB; //ghosts
    Item entC; //pac dots
    Obstacle entD; //walls

    private Skins tex;

    public CharacterCreator(Skins tex){
        this.tex = tex;

    }


    public void tick(){
        //B class
        for(int i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);

            entB.tick();
        }
        //C class
        for(int i = 0; i < ec.size(); i++){
            entC = ec.get(i);

            entC.tick();
        }

    }
    public void render(Graphics g){
        //B class
        for(int i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);

            entB.render(g);
        }
        //C class
        for(int i = 0; i < ec.size(); i++){
            entC = ec.get(i);

            entC.render(g);
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

    public void createGhost(){
        //here we add the specific ghost
        //now i put some ghost for test
        addEntity(new Shadow(200,0,tex));
        addEntity(new Shadow(500,0,tex));
    }
    public void createPacDots(){
        //put pac dots in the map
        for(int i = 0; i < Structures.director.length; i++){
            addEntity(new PacDot(Structures.director[i].getX(),Structures.director[i].getY(),tex));

        }

    }


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

    }
}
