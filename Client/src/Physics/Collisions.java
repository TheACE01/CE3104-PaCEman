package Physics;



import characters.Ghost;
import items.Item;
import characters.PacMan;
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
                    //update the encoder
                    game.getEncoder().setRemoveItem(item.get(i).quadrant);


                    //delete the pac dot
                    item.remove(i);
                    game.getInfoCreator().addScore();


                    //play the sound
                    Sound.play("Resources/eat_pacDot.wav");
                    continue;
                }
                if(item.get(i).getItemName().equals("energizer")){
                    //System.out.println("energizer");
                    //update the encoder
                    game.getEncoder().setRemoveItem(item.get(i).quadrant);

                    //delete the energizer
                    item.remove(i);


                    Sound.play("Resources/shenron.wav");

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
    public static void ghostCollison(PacMan pacman, LinkedList<Ghost> ghostInterfaces, Game game) {
        for (int i = 0; i < ghostInterfaces.size(); i++) {
            if (pacman.getBounds().intersects(ghostInterfaces.get(i).getPosBounds())) {

                if(game.isEnergizerOn()){
                    //System.out.println("ghost Killed");

                    Sound.play("Resources/pacman_eatghost.wav");

                    if(ghostInterfaces.get(i).getGhostID().equals("shadow")){
                        ghostInterfaces.remove(i);

                        //reset encoder for shadow
                        game.getEncoder().setShadowX(0);
                        game.getEncoder().setShadowY(0);

                        //game.getC().createShadow();
                        break;
                    }
                    if(ghostInterfaces.get(i).getGhostID().equals("bashful")){
                        ghostInterfaces.remove(i);

                        //reset encoder for bashful
                        game.getEncoder().setBashfulX(0);
                        game.getEncoder().setBashfulY(0);

                        //game.getC().createBashful();
                        break;
                    }
                    if(ghostInterfaces.get(i).getGhostID().equals("pokey")){
                        ghostInterfaces.remove(i);

                        //reset encoder for pokey
                        game.getEncoder().setPokeyX(0);
                        game.getEncoder().setPokeyY(0);

                        //game.getC().createPokey();
                        break;
                    }
                    if(ghostInterfaces.get(i).getGhostID().equals("speedy")){
                        ghostInterfaces.remove(i);

                        //reset encoder for speedy
                        game.getEncoder().setSpeedyX(0);
                        game.getEncoder().setSpeedyY(0);

                        //game.getC().createSpeedy();
                        break;
                    }

                }
                else{
                    Sound.play("Resources/pacman_death.wav");
                    game.getInfoCreator().removeLives();

                    game.getEncoder().setShadowX(0);
                    game.getEncoder().setShadowY(0);

                    game.getEncoder().setBashfulX(0);
                    game.getEncoder().setBashfulY(0);

                    game.getEncoder().setPokeyX(0);
                    game.getEncoder().setPokeyY(0);

                    game.getEncoder().setSpeedyX(0);
                    game.getEncoder().setSpeedyY(0);

                    game.eb.clear();
                    pacman.setDyingFlag(true);
                    game.setResetCountFlag(true);


                }

            }
        }
    }

}
