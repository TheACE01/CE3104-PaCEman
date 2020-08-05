package creators;

import characters.*;
import dataStructures.Quadrant;
import dataStructures.Structures;
import graphics.Skins;
import main.Game;

import java.awt.*;
import java.util.LinkedList;

public class CharacterCreator {
    private LinkedList<Ghost> ghosts = new LinkedList<Ghost>();


    Ghost entB; //ghosts
    
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
