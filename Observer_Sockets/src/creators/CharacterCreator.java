package creators;

import characters.*;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.LinkedList;

/**
 * Create and call the render methods of the ghosts
 * @author kevin Avevedo
 */
public class CharacterCreator {

    //Observed Ghosts Linked List
    private LinkedList<Ghost> ghosts = new LinkedList<Ghost>();

    //Ghost instance
    private Ghost entB;

    //Ghosts objects
    private ShadowGhost shadow;
    private BashfulGhost bashful;
    private PokeyGhost pokey;
    private SpeedyGhost speedy;

    //Textures access
    private Skins tex;

    //Game access
    private Game game;

    /**
     * Constructor method
     * @param tex Textures access
     * @param game Game access
     */
    public CharacterCreator(Skins tex, Game game){
        this.tex = tex;
        this.game = game;

    }

    /**
     * Call the render method of the observed ghosts
     * @param g The painter object
     */
    public void render(Graphics g){
        //B class
        for(int i = 0; i < ghosts.size(); i++){
            entB = ghosts.get(i);
            entB.render(g);
        }
    }

    /**
     * Receive a ghost and add it to the ghosts Linked List
     * @param entity A ghost
     */
    public void addEntity(Ghost entity){
        ghosts.add(entity);
    }

    /**
     * Create a new Shadow Ghost and add ii to the List
     */
    public void createShadow(){
        shadow = new ShadowGhost(0,0,tex, game);
        addEntity(shadow);
    }

    /**
     * Create a new Bashful Ghost and add ii to the List
     */
    public void createBashful(){
        bashful = new BashfulGhost(0,0,tex, game);
        addEntity(bashful);
    }

    /**
     * Create a new Pokey Ghost and add ii to the List
     */
    public void createPokey(){
        pokey = new PokeyGhost(0,0,tex, game);
        addEntity(pokey);
    }

    /**
     * Create a new Speedy Ghost and add ii to the List
     */
    public void createSpeedy(){
        speedy = new SpeedyGhost(0,0,tex, game);
        addEntity(speedy);
    }

    public LinkedList<Ghost> getEb() {
        return ghosts;
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
