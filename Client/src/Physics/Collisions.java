package Physics;



import characters.PacMan;
import dataStructures.Structures;
import interfaces.Item;
import interfaces.Obstacle;

import java.awt.*;
import java.util.LinkedList;

public class Collisions {

    public static void ItemCollision(PacMan pacman, LinkedList<Item> item){
        for(int i = 0; i < item.size(); i++){
            if(pacman.getBounds().intersects(item.get(i).getBounds())){

                System.out.println("Pac dot Quadrant: " + Structures.getPacDotQuadrant(item.get(i)));
                //delete the pac dot
                item.remove(i);
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

}
