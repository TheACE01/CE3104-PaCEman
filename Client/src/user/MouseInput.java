package user;

import main.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Integer mx = e.getX();
        Integer my = e.getY();

        //play Button
        if(mx >= 559 && mx <= 709){
            if(my >= 250 && my <= 320){
                Game.startFlag = false;
                Game.state = Game.STATE.PLAY;
            }
        }

        //quit Button
        if(mx >= 559 && mx <= 709){
            if(my >= 400 && my <= 470){
                System.exit(1);
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
