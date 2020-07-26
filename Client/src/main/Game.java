package main;

import characters.PacMan;
import creators.CharacterCreator;
import dataStructures.Structures;
import graphics.ImageLoader;
import graphics.Skins;
import interfaces.Ghost;
import interfaces.Item;
import interfaces.Obstacle;
import user.PacManControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1269;
    public static final int HEIGHT = 700;
    public final String TITTLE = "paCE man Game";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private BufferedImage pacDotSprite = null;

    private int dotsTakenCount = 0;

    private PacMan p;
    private CharacterCreator c;
    private Skins tex;

    public LinkedList<Ghost> eb;
    public LinkedList<Item> ec;
    public LinkedList<Obstacle> ed;

    public void init(){
        requestFocus();
        ImageLoader loader = new ImageLoader();
        try{
            spriteSheet = loader.loadImage("/spriteSheet.png");
            background = loader.loadImage("/bg.jpg");
            pacDotSprite = loader.loadImage("/dot.png");

        }catch (IOException e){
            e.printStackTrace();
        }
        addKeyListener(new PacManControl(this));

        tex = new Skins(this);

        p = new PacMan(564, 450, tex,this);
        c = new CharacterCreator(tex);

        //create the wall rectangles
        c.createWallRectangles();
        //adding the ghost for test
        //c.createGhost();

        //adding pac dots for test
        c.createPacDots();

        //initialize linked lists
        eb = c.getEb();
        ec = c.getEc();
        ed = c.getEd();
    }

    private synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }
    private synchronized void stop(){
        if(!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }

    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            //game loop, the hard of the games
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){
        p.tick();
        c.tick();
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        /////////////////////////////////////
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(background,0,0,this);
        c.render(g);
        p.render(g);


        g.dispose();
        bs.show();
    }
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(key == KeyEvent.VK_D){
            p.directionSwap();
            p.setR(true);
            p.setVelX(2);
        }
        else if(key == KeyEvent.VK_A){
            p.directionSwap();
            p.setL(true);
            p.setVelX(-2);
        }
        else if(key == KeyEvent.VK_W){
            p.directionSwap();
            p.setU(true);
            p.setVelY(-2);
        }
        else if(key == KeyEvent.VK_S){
            p.directionSwap();
            p.setD(true);
            p.setVelY(2);
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_D){
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_A){
            p.setVelX(0);
        }
        else if(key == KeyEvent.VK_W){
            p.setVelY(0);
        }
        else if(key == KeyEvent.VK_S){
            p.setVelY(0);
        }
    }



    public static void main(String args[]){
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH,HEIGHT ));
        game.setMaximumSize(new Dimension(WIDTH ,HEIGHT));
        game.setMinimumSize(new Dimension(WIDTH ,HEIGHT));

        JFrame frame = new JFrame(game.TITTLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        game.start();


    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }
    public BufferedImage getPDSpriteSheet(){
        return pacDotSprite;
    }


    public int getDotsTakenCount() {
        return dotsTakenCount;
    }

    public void setDotsTakenCount(int dotsTakenCount) {
        this.dotsTakenCount = dotsTakenCount;
    }
}
