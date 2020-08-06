package creators;

import graphics.Skins;
import main.Game;


import java.awt.*;

/**
 * Show all the statistics of the game like pac man lives, score and level
 * @author kevin Avevedo
 */
public class InfoCreator {

    //Text font
    private Font font1;

    //textures of the lives
    private Skins tex;

    //Access to the main class
    private Game game;

    //Lives counter
    private Integer lives = 3;

    //Score counter
    private Integer score = 0;

    //Level counter
    private Integer level = 1;

    /**
     * Constructor method
     * @param tex Textures of the lives
     * @param game The main class of the game
     */
    public InfoCreator(Skins tex, Game game) {
        this.tex = tex;
        this.game = game;

        //initialize the text font
        font1 = new Font("arial", Font.BOLD, 30);
    }

    /**
     * Visual control of the statistics of the game
     * @param g
     */
    public void render(Graphics g) {

        //Lives text with yellow color
        renderLives(g);
        g.setFont(font1);
        g.setColor(Color.YELLOW);

        //Score text
        g.drawString("Score:", 950, 30);
        g.drawString(Integer.toString(score), 1100, 30);

        //Level text
        g.drawString("Level:", 100, 30);
        g.drawString(Integer.toString(level), 200, 30);
    }

    /**
     * Validates the lives to draw on screen
     * @param g The printer object
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

    public Integer getLives() {
        return lives;
    }

    public void removeLives() {
        if(lives > 0) lives--;
        game.getEncoder().setLives(lives);
    }

    public void addLives(){
        if(lives <3) lives++;
        game.getEncoder().setLives(lives);
    }

    public void addScore(Integer points){
        score += points;
        game.getEncoder().setScore(score);
    }
    public void addLevel(){
        if(level <3) level++;
        game.getEncoder().setLevel(level);
    }

    public Integer getLevel(){
        return level;
    }

}