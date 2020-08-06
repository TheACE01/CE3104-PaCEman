package creators;

import graphics.Skins;
import main.Game;

import java.awt.*;

/**
 * Draw the observed statistics of the main Game
 * @author kevin Avevedo
 */
public class InfoCreator {

    //Texts font
    private Font font1;

    //Textures access
    private Skins tex;

    //Game access
    private Game game;

    //Observed pac man lives
    private Integer lives = 3;

    //Observed score
    private Integer score = 0;

    //Observed level
    private Integer level = 1;

    /**
     * Constructor method
     * @param tex Textures access
     * @param game Game access
     */
    public InfoCreator(Skins tex, Game game) {
        this.tex = tex;
        this.game = game;

        //init the font
        font1 = new Font("arial", Font.BOLD, 30);
    }

    /**
     * Draw the observed statistics on screen
     * @param g The painter object
     */
    public void render(Graphics g) {

        renderLives(g);
        g.setFont(font1);
        g.setColor(Color.YELLOW);

        g.drawString("Score:", 1050, 30);
        g.drawString(Integer.toString(score), 1150, 30);

        g.drawString("Level:", 100, 30);
        g.drawString(Integer.toString(level), 200, 30);
    }

    /**
     * Draw the pac man lives image on screen
     * @param g The painter object
     */
    public void renderLives(Graphics g){
        if(lives == 3){
            g.drawImage(tex.live, 0, 100, null);
            g.drawImage(tex.live, 0, 150, null);
            g.drawImage(tex.live, 0, 200, null);
        }
        if(lives == 2){
            g.drawImage(tex.live, 0, 100, null);
            g.drawImage(tex.live, 0, 150, null);
        }
        if(lives == 1){
            g.drawImage(tex.live, 0, 100, null);
        }
    }

    /**
     * Update the lives
     * @param lives Observed pac man lives
     */
    public void addLives(int lives){
        this.lives = lives;
    }

    /**
     * Update the score
     * @param score Observed score
     */
    public void addScore(int score){
        this.score = score;
    }

    /**
     * Update the level
     * @param level Observed level
     */
    public void addLevel(int level){
        this.level = level;
    }

}