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
import java.util.Random;

/**
 * Is used to create and render ghosts. Create pac dots, energizers, fruits, walls and may control the
 * ghosts velocity.
 * @author kevin Avevedo
 */
public class CharacterCreator {

    //List of all the ghosts of the game
    private LinkedList<Ghost> ghosts = new LinkedList<Ghost>();

    //List of all the items of the game
    private LinkedList<Item> items = new LinkedList<Item>();

    //List of all the walls of the map
    private LinkedList<Obstacle> walls = new LinkedList<Obstacle>();

    Ghost entB; //ghosts

    Item entC; //items

    Obstacle entD; //walls

    //Textures of the characters, this is passed by parameter when the characters are created
    private Skins tex;

    //The main access to the game
    private Game game;

    //Ghosts access
    private ShadowGhost shadow;
    private BashfulGhost bashful;
    private PokeyGhost pokey;
    private SpeedyGhost speedy;

    /**
     * Constructor method
     * @param tex Textures of all the characters
     * @param game The main class of the game
     */
    public CharacterCreator(Skins tex, Game game){
        this.tex = tex;
        this.game = game;
    }


    /**
     * Call the tick methods of all the ghosts
     */
    public void tick(){
        //ghosts tick
        for(Integer i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);
            entB.tick();
        }
    }

    /**
     * Call the render methods of all the Listed elements like ghosts and items
     * @param g
     */
    public void render(Graphics g){
        //Items
        for(Integer i = 0; i < items.size(); i++){
            entC = items.get(i);

            entC.render(g);
        }
        //ghosts
        for(Integer i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);

            entB.render(g);
        }
    }

    /**
     * Append a new Ghost to the ghosts List
     * @param entity A new ghost object
     */
    public void addEntity(Ghost entity){
        ghosts.add(entity);
    }

    /**
     * Append a new item to the items List
     * @param entity A new item object
     */
    public void addEntity(Item entity){
        items.add(entity);
    }

    /**
     * Create a new Shadow Ghost and append the new object to the List
     */
    public void createShadow(){
        shadow = new ShadowGhost(Structures.ghostGraph[29].getX(),Structures.ghostGraph[29].getY(),tex, game);
        addEntity(shadow);
    }

    /**
     * Create a new Bashful Ghost and append the new object to the List
     */
    public void createBashful(){
        bashful = new BashfulGhost(Structures.ghostGraph[31].getX(),Structures.ghostGraph[31].getY(),tex, game);
        addEntity(bashful);
    }

    /**
     * Create a new Pokey Ghost and append the new object to the List
     */
    public void createPokey(){
        pokey = new PokeyGhost(Structures.ghostGraph[30].getX(),Structures.ghostGraph[30].getY(),tex, game);
        addEntity(pokey);
    }

    /**
     * Create a new Speedy Ghost and append the new object to the List
     */
    public void createSpeedy(){
        speedy = new SpeedyGhost(Structures.ghostGraph[29].getX(),Structures.ghostGraph[29].getY(),tex, game);
        addEntity(speedy);
    }

    /**
     * Can create the initial pac dots and energizers on screen and add them to the item List
     */
    public void createPacDots(){


        for(Integer i = 0; i < Structures.director.length; i++){
            //Quadrant object
            Quadrant q = Structures.director[i];

            //Creating the energizers
            if(q.getQuadrant() == 0){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant(), 0));
                continue;
            }
            if(q.getQuadrant() == 140){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant(), 0));
                continue;
            }
            if(q.getQuadrant() == 165){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant(), 0));
                continue;
            }
            if(q.getQuadrant() == 20){
                addEntity(new Energizer(q.getX(), q.getY(), tex, "energizer", q.getQuadrant(), 0));
                continue;
            }
            else{
                //creating the pac dots
                addEntity(new PacDot(q.getX(),q.getY(),tex, "pacDot", q.getQuadrant(), 10));
            }
        }
    }

    public void createEnergizer(Integer quadrant){
        //check if the quadrant is not available
        for(Integer i = 0; i < items.size(); i++){
            Item item = items.get(i);
            //There is a pac dot in this position
            if(item.getX() == 564 && item.getY() == 250){
                //Then we remove the item to add a new fruit
                items.remove(item);
                break;
            }
        }
        //Search the X and Y pos using the quadrant number and the director structure
        for(Integer i = 0; i < Structures.director.length; i++){
            Quadrant q = Structures.director[i];
            //Quadrant match
            if(quadrant == q.getQuadrant()){
                Integer energizerX = q.getX();
                Integer energizerY = q.getY();
                //Add the pill to the Linked List to render with the other items
                addEntity(new Energizer(energizerX, energizerY, tex, "energizer", quadrant, 0));
            }
        }
    }

    public void createFruit(Integer fruitValue){
        //check if the quadrant is not available
        for(Integer i = 0; i < items.size(); i++){
            Item item = items.get(i);
            //There is a pac dot in this position
            if(item.getX() == 564 && item.getY() == 250){
                //Then we remove the item to add a new fruit
                items.remove(item);
                break;
            }
        }
        Random random = new Random();
        Integer randomFruit = random.nextInt(3);
        if(randomFruit == 0){
            addEntity(new Apple(564, 250, tex,"apple", 1, fruitValue));
        }
        if(randomFruit == 1){
            addEntity(new Banana(564, 250, tex,"banana", 1, fruitValue));
        }
        if(randomFruit == 2){
            addEntity(new Cherry(564, 250, tex, "cherry", 1, fruitValue));
        }
    }

    public LinkedList<Ghost> getEb() {
        return ghosts;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public LinkedList<Obstacle> getWalls() {
        return walls;
    }

    /**
     * Create all the walls representing the obstacles on the map
     */
    public void createWallRectangles(){

        walls.add(new Wall(47,50,(47*26),50));

        walls.add(new Wall(47,50,47,(50*13)));

        walls.add(new Wall((47*26),50,47,(50*13)));

        walls.add(new Wall(47,(50*13),(47*26),50));

        walls.add(new Wall(47*3,(50*3),47,(50*4)));

        walls.add(new Wall(47*4,(50*3),47,50));

        walls.add(new Wall(47*6,(50*3),(47*3),50));

        walls.add(new Wall(47*5,(50*5),47,(50*3)));

        walls.add(new Wall(47*3,(50*8),47,(50*2)));

        walls.add(new Wall(47*7,(50*5),(47*2),50));

        walls.add(new Wall(47*7,(50*7),(47*2),50));

        walls.add(new Wall(47*3,(50*11),(47*3),50));

        walls.add(new Wall(47*5,(50*9),47,(50*3)));

        walls.add(new Wall(47*6,(50*9),(47*2),50));

        walls.add(new Wall(47*7,(50*11),(47*3),50));

        walls.add(new Wall(47*10,(50*3),47,50*2));

        walls.add(new Wall(47*11,(50*4),47*4,50));

        walls.add(new Wall(47*12,(50*2),47*3,50));

        walls.add(new Wall(47*16,(50*3),47*4,50));

        walls.add(new Wall(47*21,(50*3),47,50));

        walls.add(new Wall(47*23,(50*3),47*2,50));

        walls.add(new Wall(47*10,(50*6),47,50*4));

        walls.add(new Wall(47*9,(50*9),47,50));

        walls.add(new Wall(47*11,(50*8),47*4,50));

        walls.add(new Wall(47*14,(50*6),47,50*2));

        walls.add(new Wall(47*11,(50*11),47*2,50));

        walls.add(new Wall(47*12,(50*10),47*4,50));

        walls.add(new Wall(47*14,(50*12),47*2,50));

        walls.add(new Wall(47*16,(50*5),47,50*4));

        walls.add(new Wall(47*17,(50*8),47,50*4));

        walls.add(new Wall(47*18,(50*5),47*5,50));

        walls.add(new Wall(47*18,(50*6),47,50));

        walls.add(new Wall(47*19,(50*8),47*2,50));

        walls.add(new Wall(47*19,(50*8),47*2,50));

        walls.add(new Wall(47*20,(50*7),47*2,50));

        walls.add(new Wall(47*23,(50*7),47,50));

        walls.add(new Wall(47*24,(50*5),47,50*3));

        walls.add(new Wall(47*22,(50*9),47*3,50));

        walls.add(new Wall(47*19,(50*10),47*2,50*2));

        walls.add(new Wall(47*19,(50*10),47*2,50*2));

        walls.add(new Wall(47*21,(50*11),47*4,50));

        walls.add(new Wall(47*10,(50*6),47*3,50));
    }

    /**
     * Change the velocity of all the ghosts of the ghost List
     * @param boost
     */
    public void GhostBoost(double boost){
        for(Integer i = 0; i < ghosts.size(); i++){
            ghosts.get(i).setBoost(boost);
        }
    }

}
