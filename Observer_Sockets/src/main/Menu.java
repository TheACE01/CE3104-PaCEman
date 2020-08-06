package main;

import java.awt.*;

/**
 * Menu System. Shows the initial elements of the game
 * @author kevin Avevedo
 */
public class Menu {

    //Buttons rectangles
    public Rectangle playButton = new Rectangle(559, 250, 150, 70);
    public Rectangle observerButton = new Rectangle(559, 400, 150, 70);

    /**
     * Draw the buttons and texts
     * @param g The painter object
     */
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font1 = new Font("arial", Font.BOLD, 50);
        g.setFont(font1);
        g.setColor(Color.YELLOW);
        g.drawString("PaCE man", 510, 100);

        Font font2 = new Font("arial", Font.BOLD, 30);
        g.setFont(font2);
        g.drawString("play", playButton.x + 40, playButton.y + 45);
        g.drawString("quit", observerButton.x + 40, observerButton.y + 45);

        g2d.draw(playButton);
        g2d.draw(observerButton);
    }
}
