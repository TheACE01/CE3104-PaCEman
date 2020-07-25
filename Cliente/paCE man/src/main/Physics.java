package main;

import main.Entidades.EntityA;
import main.Entidades.EntityC;
import main.Entidades.EntityD;

import java.awt.*;
import java.util.LinkedList;

public class Physics {

    public static boolean Collision(Player entA, LinkedList<EntityC> entc){
        for(int i = 0; i < entc.size(); i++){
            if(entA.getBounds().intersects(entc.get(i).getBounds())){
                return true;
            }
        }
        return false;
    }
    public static boolean wallCollison(LinkedList<EntityD> entd, Rectangle rect) {
        for (int i = 0; i < entd.size(); i++) {
            if (rect.intersects(entd.get(i).getBounds())) {
                return true;
            }
        }
        return false;
    }

}
