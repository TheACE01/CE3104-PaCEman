package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Creates the sprites animations of the game.
 * @author kevin Avevedo
 */
public class Animations {

    //animation speed
    private Integer speed;

    //number of franes
    private Integer frames;

    //counters
    private Integer index = 0;
    private Integer count = 0;

    //images to load and animate
    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private BufferedImage img5;
    private BufferedImage img6;
    private BufferedImage img7;
    private BufferedImage img8;
    private BufferedImage img9;
    private BufferedImage img10;
    private BufferedImage img11;
    private BufferedImage img12;
    private BufferedImage img13;
    private BufferedImage img14;

    //the actual image
    private BufferedImage currentImg;

    /**
     * Constructor method,this is made for two images animations
     * @param speed Animation speed
     * @param img1 The first image
     * @param img2 The second image
     */
    public Animations(Integer speed, BufferedImage img1, BufferedImage img2){
        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        frames = 2;
    }

    /**
     * Image swap when the character moves
     */
    public void runAnimation(){
        index++;
        if(index > speed){
            index = 0;
            nextFrame();
        }
    }

    /**
     * Alternates the frames and animate
     */
    public void nextFrame(){

        //switch statement
        switch(frames){
            case 2:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 3:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 4:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 5:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 6:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 7:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 8:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 9:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;
                if(count == 8)
                    currentImg = img9;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 10:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;
                if(count == 8)
                    currentImg = img9;
                if(count == 9)
                    currentImg = img10;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 11:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;
                if(count == 8)
                    currentImg = img9;
                if(count == 9)
                    currentImg = img10;
                if(count == 10)
                    currentImg = img11;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 12:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;
                if(count == 8)
                    currentImg = img9;
                if(count == 9)
                    currentImg = img10;
                if(count == 10)
                    currentImg = img11;
                if(count == 11)
                    currentImg = img12;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 13:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;
                if(count == 8)
                    currentImg = img9;
                if(count == 9)
                    currentImg = img10;
                if(count == 10)
                    currentImg = img11;
                if(count == 11)
                    currentImg = img12;
                if(count == 12)
                    currentImg = img13;

                count++;

                if(count > frames)
                    count = 0;

                break;
            case 14:
                if(count == 0)
                    currentImg = img1;
                if(count == 1)
                    currentImg = img2;
                if(count == 2)
                    currentImg = img3;
                if(count == 3)
                    currentImg = img4;
                if(count == 4)
                    currentImg = img5;
                if(count == 5)
                    currentImg = img6;
                if(count == 6)
                    currentImg = img7;
                if(count == 7)
                    currentImg = img8;
                if(count == 8)
                    currentImg = img9;
                if(count == 9)
                    currentImg = img10;
                if(count == 10)
                    currentImg = img11;
                if(count == 11)
                    currentImg = img12;
                if(count == 12)
                    currentImg = img13;
                if(count == 13)
                    currentImg = img14;

                count++;

                if(count > frames)
                    count = 0;

                break;
        }
    }

    /**
     * Shows the visual animation
     * @param g The painter object
     * @param x X pos for drawing
     * @param y Y pos for drawing
     * @param offset Move number
     */
    public void drawAnimation(Graphics g, double x, double y, Integer offset){
        g.drawImage(currentImg, (int)x - offset, (int)y, null);
    }

}
