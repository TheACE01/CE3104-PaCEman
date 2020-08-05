package main;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(559, 250, 150, 70);
    public Rectangle observerButton = new Rectangle(559, 400, 150, 70);


    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Font font1 = new Font("arial", Font.BOLD, 50);
        g.setFont(font1);
        g.setColor(Color.YELLOW);
        g.drawString("PaCE man", 510, 100);

        Font font2 = new Font("arial", Font.BOLD, 30);
        g.setFont(font2);
        g.drawString("play", playButton.x + 45, playButton.y + 45);
        g.drawString("quit", observerButton.x + 45, observerButton.y + 45);

        g2d.draw(playButton);
        g2d.draw(observerButton);
    }
}
