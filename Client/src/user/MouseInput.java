package user;

import main.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Check the mouse actions when the MENU state is turned on
 * @author kevin Avevedo
 */
public class MouseInput implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * When the mouse is pressed verifies the X and Y position of the click event, and check
     * if the user slashed a button of the menu
     * @param e The mouse event
     */
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
