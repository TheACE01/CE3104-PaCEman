package main;

import characters.Ghost;
import items.Item;
import characters.PacMan;
import creators.CharacterCreator;
import creators.InfoCreator;
import dataStructures.DijkstraAlgorithm.DijkstraExec;
import graphics.ImageLoader;
import graphics.Skins;
import interfaces.Obstacle;
import user.MouseInput;
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
    public final String TITTLE = "paCE man";

    private boolean running = false;
    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage spriteSheet2 = null;
    private BufferedImage background = null;
    private BufferedImage menu_background = null;
    private BufferedImage loading = null;
    private BufferedImage pacDotSprite = null;
    private BufferedImage energizerSprite = null;
    private BufferedImage appleSprite = null;
    private BufferedImage bananaSprite = null;
    private BufferedImage cherrySprite = null;
    private BufferedImage live = null;

    private int dotsTakenCount = 0;

    private PacMan p;
    private CharacterCreator c;
    private InfoCreator infoCreator;
    private Skins tex;
    private Menu menu;

    public LinkedList<Ghost> eb;
    public LinkedList<Item> ec;
    public LinkedList<Obstacle> ed;

    private DijkstraExec router = new DijkstraExec();
    private Integer shadowTarget = 32;

    private boolean energizerOn = false;
    private Integer refreshTarget;

    private boolean chaiseSpeedy = false;
    private int chaiseSpeedyCont;

    private Boolean resetCountFlag = false;
    private Integer resetCount = 0;

    public BufferedImage getLive() {
        return live;
    }

    public InfoCreator getInfoCreator() {
        return infoCreator;
    }

    public static enum STATE{
        MENU,
        PLAY
    };
    public static STATE state = STATE.MENU;

    public static Boolean startFlag = true;



    public void init(){
        requestFocus();
        ImageLoader loader = new ImageLoader();
        try{
            spriteSheet = loader.loadImage("/spriteSheet.png");
            spriteSheet2 = loader.loadImage("/spriteSheet2.png");

            background = loader.loadImage("/bg.jpg");
            menu_background = loader.loadImage("/menu_background.jpg");
            loading = loader.loadImage("/loading.jpg");

            live = loader.loadImage("/live.png");

            pacDotSprite = loader.loadImage("/dot.png");
            energizerSprite = loader.loadImage("/energizer.png");
            appleSprite = loader.loadImage("/appleItem.png");
            bananaSprite = loader.loadImage("/bananaItem.png");
            cherrySprite = loader.loadImage("/cherryItem.png");



        }catch (IOException e){
            e.printStackTrace();
        }
        addKeyListener(new PacManControl(this));
        addMouseListener(new MouseInput());
        menu = new Menu();
    }

    public void playInit(){

        tex = new Skins(this);
        p = new PacMan(564, 450, tex,this);
        c = new CharacterCreator(tex, this);
        infoCreator = new InfoCreator(tex, this);

        //create the wall rectangles
        c.createWallRectangles();

        //adding pac dots for test
        c.createPacDots();

        //c.createAppleItem(94, 100);
        //c.createBananaItem(1175, 100);
        //c.createCherryItem(94, 600);

        //initialize the graph structure
        router.initGraph();

        //adding the ghost for test

        c.createShadow();
        c.createBashful();
        c.createPokey();
        c.createSpeedy();

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

        refreshTarget = 0;
        chaiseSpeedyCont = 0;
        while(running){

            //checking the start flag
            if(!startFlag){
                playInit();
                startFlag = true;
            }
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


                //check if pac man eats any energizer
                if(energizerOn && refreshTarget == 7 && state == STATE.PLAY){
                    //System.out.println(refreshTarget);
                    refreshTarget = 0;
                    energizerOn = false;
                }
                if(energizerOn && refreshTarget < 7 && state == STATE.PLAY){
                    refreshTarget++;
                    //System.out.println(refreshTarget);
                }

                //speedy flag
                if(chaiseSpeedyCont == 10 && state == STATE.PLAY) {
                    chaiseSpeedyCont = 0;
                    swapSpeedyMovement();

                }
                if(chaiseSpeedyCont < 10 && state == STATE.PLAY){
                    chaiseSpeedyCont++;
                }

                //reset level counter
                if(resetCountFlag && resetCount == 2 && state == STATE.PLAY){
                    reset();
                    resetCount = 0;
                    resetCountFlag = false;
                }
                if(resetCountFlag && resetCount < 2 && state == STATE.PLAY){
                    resetCount++;
                }
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){
        if(state == STATE.PLAY && startFlag){
            p.tick();
            c.tick();
        }

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

        if(state == STATE.PLAY && startFlag){
            g.drawImage(background,0,0,this);
            c.render(g);
            p.render(g);
            infoCreator.render(g);
        }
        else if(state == STATE.MENU){
            g.drawImage(menu_background,0,0,this);
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();

        if(state == STATE.PLAY){

            if(key == KeyEvent.VK_D){
                p.directionSwap();

                p.setR(true);
                p.setVelX(3);
            }
            else if(key == KeyEvent.VK_A){
                p.directionSwap();

                p.setL(true);
                p.setVelX(-3);
            }
            else if(key == KeyEvent.VK_W){
                p.directionSwap();

                p.setU(true);
                p.setVelY(-3);
            }
            else if(key == KeyEvent.VK_S){
                p.directionSwap();
                p.setD(true);
                p.setVelY(3);
            }
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

    public DijkstraExec getRouter() {
        return router;
    }

    public int getShadowTarget() {
        return shadowTarget;
    }

    public void setShadowTarget(int shadowTarget) {
        this.shadowTarget = shadowTarget;
    }

    public BufferedImage getEnergizerSprite() {
        return energizerSprite;
    }

    public boolean isEnergizerOn() {
        return energizerOn;
    }

    public void setEnergizerOn(boolean energizerOn) {
        this.energizerOn = energizerOn;
    }


    public void setRefreshTarget(int refreshTarget) {
        this.refreshTarget = refreshTarget;
    }

    public boolean isChaiseSpeedy() {
        return chaiseSpeedy;
    }
    public void swapSpeedyMovement(){
        if(chaiseSpeedy) chaiseSpeedy = false;
        else{
            chaiseSpeedy = true;
        }
    }
    public void reset(){
        p.setX(47*12);
        p.setY(50*9);

        c.createShadow();
        c.createBashful();
        //c.createPokey();
        //c.createSpeedy();

        p.setDyingFlag(false);
    }


    public BufferedImage getAppleSprite() {
        return appleSprite;
    }

    public BufferedImage getBananaSprite() {
        return bananaSprite;
    }

    public BufferedImage getCherrySprite() {
        return cherrySprite;
    }

    public BufferedImage getSpriteSheet2() {
        return spriteSheet2;
    }

    public CharacterCreator getC() {
        return c;
    }

    public Boolean getResetCountFlag() {
        return resetCountFlag;
    }

    public void setResetCountFlag(Boolean resetCountFlag) {
        this.resetCountFlag = resetCountFlag;
    }
}
