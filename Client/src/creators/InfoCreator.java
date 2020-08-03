package creators;

import characters.Bashful;
import characters.Pokey;
import characters.Shadow;
import characters.Speedy;
import dataStructures.Quadrant;
import dataStructures.Structures;
import graphics.Skins;
import interfaces.Ghost;
import interfaces.Item;
import interfaces.Obstacle;
import items.*;
import main.Game;


import java.awt.*;
import java.util.LinkedList;

public class InfoCreator {

    private Font font1;
    private Skins tex;
    private Game game;

    private Integer lives = 3;
    private Integer score = 0;
    private Integer level = 1;

    public InfoCreator(Skins tex, Game game) {
        this.tex = tex;
        this.game = game;

        font1 = new Font("arial", Font.BOLD, 30);

    }


    public void tick() {


    }

    public void render(Graphics g) {

        renderLives(g);
        g.setFont(font1);
        g.setColor(Color.YELLOW);

        g.drawString("Score:", 1050, 30);
        g.drawString(Integer.toString(score), 1150, 30);

        g.drawString("Level:", 100, 30);
        g.drawString(Integer.toString(level), 200, 30);


    }
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
    }
    public void addLives(){
        if(lives <3) lives++;
    }

    public void addScore(){
        score++;
    }
    public void addLevel(){
        if(level <3) level++;
    }

}