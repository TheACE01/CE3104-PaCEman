package Physics;

import characters.Ghost;
import items.Item;
import characters.PacMan;
import interfaces.Obstacle;
import main.Game;
import music.Sound;

import java.awt.*;
import java.util.LinkedList;

/**
 * Use the Characters Lists and check if pac man collides with the items, ghosts or walls in the game
 * @author kevin Avevedo
 */
public class Collisions {

    /**
     * Check if pac man collides with pac dots, energizers and fruits
     * @param pacman The pac man object
     * @param item The items Linked List
     * @param game The main class access
     */
    public static void ItemCollision(PacMan pacman, LinkedList<Item> item, Game game){
        for(int i = 0; i < item.size(); i++){
            if(pacman.getBounds().intersects(item.get(i).getBounds())){

                //verify the items list size
                Integer serverValue = 0;
                if(game.getItems().size() == 0){
                    serverValue = 163;
                }
                //pac dot collision
                if(item.get(i).getItemName().equals("pacDot")){

                    //notify the C server
                    String message = "dot" + "," + Integer.toString(serverValue);
                    game.getClient().SendMessage(message);


                    //Update the player score
                    game.getInfoCreator().addScore(item.get(i).value);

                    //delete the pac dot
                    item.remove(i);

                    //play the sound
                    Sound.play("Resources/eat_pacDot.wav");

                    continue;
                }
                //energizer collision
                if(item.get(i).getItemName().equals("energizer")){

                    //update the encoder energizer flag
                    game.getEncoder().setEnergizer(1);

                    //notify the C server
                    String message = "dot" + "," + Integer.toString(serverValue);
                    game.getClient().SendMessage(message);


                    //delete the energizer
                    item.remove(i);

                    //play the sound
                    Sound.play("Resources/shenron.wav");

                    //turn on the energizer flag
                    game.setEnergizerOn(true);
                    game.setRefreshTarget(0);
                    continue;
                }

                //fruit collision
                if(item.get(i).getItemName().equals("apple") || item.get(i).getItemName().equals("banana") || item.get(i).getItemName().equals("cherry")){

                    //notify the C server
                    String message = "fruit" + "," + Integer.toString(item.get(i).getValue());
                    game.getClient().SendMessage(message);

                    //Verify if there is just one item in the items linked list
                    if(item.size() == 1){
                        message = "dot" + "," + Integer.toString(163);
                        game.getClient().SendMessage(message);
                    }

                    //Update the player score
                    game.getInfoCreator().addScore(item.get(i).getValue());

                    //delete the fruit
                    item.remove(i);

                    Sound.play("Resources/pacman_eatfruit.wav");
                }
            }
        }
    }

    /**
     * Check if the pac man´s small rectangles collide with the walls created at the beginning
     * @param obstacle The walls Linked List
     * @param rect The small rectangle of pac man
     * @return True or False
     */
    public static Boolean wallCollison(LinkedList<Obstacle> obstacle, Rectangle rect) {
        for (int i = 0; i < obstacle.size(); i++) {

            if (rect.intersects(obstacle.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if pac man collides with the ghosts
     * @param pacman The pac man object
     * @param ghosts The ghosts Linked List
     * @param game The game access
     */
    public static void ghostCollison(PacMan pacman, LinkedList<Ghost> ghosts, Game game) {
        for (int i = 0; i < ghosts.size(); i++) {
            if (pacman.getBounds().intersects(ghosts.get(i).getPosBounds())) {

                //if the energizer is triggered pac man can easily eat ghosts
                if(game.isEnergizerOn()){
                    game.getInfoCreator().addScore(500);


                    //play the eat ghost sound
                    Sound.play("Resources/pacman_eatghost.wav");

                    //notify the C server
                    String message = "ghost" + "," + Integer.toString(5);
                    game.getClient().SendMessage(message);

                    //verify whose ghosts is ate
                    if(ghosts.get(i).getGhostID().equals("shadow")){
                        ghosts.remove(i);

                        //reset encoder for shadow
                        game.getEncoder().setShadowX(0.0);
                        game.getEncoder().setShadowY(0.0);

                        //game.getC().createShadow();
                        break;
                    }
                    if(ghosts.get(i).getGhostID().equals("bashful")){
                        ghosts.remove(i);

                        //reset encoder for bashful
                        game.getEncoder().setBashfulX(0.0);
                        game.getEncoder().setBashfulY(0.0);

                        //game.getC().createBashful();
                        break;
                    }
                    if(ghosts.get(i).getGhostID().equals("pokey")){
                        ghosts.remove(i);

                        //reset encoder for pokey
                        game.getEncoder().setPokeyX(0.0);
                        game.getEncoder().setPokeyY(0.0);

                        //game.getC().createPokey();
                        break;
                    }
                    if(ghosts.get(i).getGhostID().equals("speedy")){
                        ghosts.remove(i);

                        //reset encoder for speedy
                        game.getEncoder().setSpeedyX(0.0);
                        game.getEncoder().setSpeedyY(0.0);

                        //game.getC().createSpeedy();
                        break;
                    }

                }
                else{

                    //if the energizer isn´t activated, pac man dies
                    Sound.play("Resources/pacman_death.wav");
                    game.getInfoCreator().removeLives();

                    //reset the ghosts encoders
                    game.getEncoder().setShadowX(0.0);
                    game.getEncoder().setShadowY(0.0);

                    game.getEncoder().setBashfulX(0.0);
                    game.getEncoder().setBashfulY(0.0);

                    game.getEncoder().setPokeyX(0.0);
                    game.getEncoder().setPokeyY(0.0);

                    game.getEncoder().setSpeedyX(0.0);
                    game.getEncoder().setSpeedyY(0.0);

                    //remove the ghosts List
                    game.ghosts.clear();
                    pacman.setDyingFlag(true);
                    game.setResetCountFlag(true);
                }
            }
        }
    }

}