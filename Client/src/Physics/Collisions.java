package Physics;



import characters.PacMan;
import interfaces.Ghost;
import interfaces.Item;
import interfaces.Obstacle;
import main.Game;
import music.Sound;

import java.awt.*;
import java.util.LinkedList;

public class Collisions {

    public static void ItemCollision(PacMan pacman, LinkedList<Item> item, Game game){
        for(int i = 0; i < item.size(); i++){
            if(pacman.getBounds().intersects(item.get(i).getBounds())){

                if(item.get(i).getItemName().equals("pacDot")){
                    //System.out.println("Pac dot Quadrant: " + Structures.getPacDotQuadrant(item.get(i)));
                    //delete the pac dot
                    item.remove(i);
                    game.getInfoCreator().addScore();
                    //play the sound
                    Sound.play("Resources/eat_pacDot.wav");
                    continue;
                }
                if(item.get(i).getItemName().equals("energizer")){
                    //System.out.println("energizer");
                    //delete the energizer
                    item.remove(i);

                    Sound.play("Resources/ssj2.wav");

                    //turn on the energizer flag
                    game.setEnergizerOn(true);
                    game.setRefreshTarget(0);
                    continue;
                }
                if(item.get(i).getItemName().equals("apple") || item.get(i).getItemName().equals("banana") || item.get(i).getItemName().equals("cherry")){
                    //delete the energizer
                    item.remove(i);
                    Sound.play("Resources/pacman_eatfruit.wav");
                }


            }
        }
    }
    public static boolean wallCollison(LinkedList<Obstacle> obstacle, Rectangle rect) {
        for (int i = 0; i < obstacle.size(); i++) {

            if (rect.intersects(obstacle.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }
    public static void ghostCollison(PacMan pacman, LinkedList<Ghost> ghosts, Game game) {
        for (int i = 0; i < ghosts.size(); i++) {
            if (pacman.getBounds().intersects(ghosts.get(i).getPosBounds())) {

                if(game.isEnergizerOn()){
                    //System.out.println("ghost Killed");

                    Sound.play("Resources/pacman_eatghost.wav");

                    if(ghosts.get(i).getGhostID().equals("shadow")){
                        ghosts.remove(i);
                        game.getC().createShadow();
                        break;
                    }
                    if(ghosts.get(i).getGhostID().equals("bashful")){
                        ghosts.remove(i);
                        game.getC().createBashful();
                        break;
                    }
                    if(ghosts.get(i).getGhostID().equals("pokey")){
                        ghosts.remove(i);
                        game.getC().createPokey();
                        break;
                    }
                    if(ghosts.get(i).getGhostID().equals("speedy")){
                        ghosts.remove(i);
                        game.getC().createSpeedy();
                        break;
                    }

                }
                else{
                    //Sound.play("Resources/pacman_death.wav");
                    //game.getInfoCreator().removeLives();
                    //game.eb.clear();
                    //pacman.setDyingFlag(true);
                    //game.setResetCountFlag(true);


                }

            }
        }
    }

}
