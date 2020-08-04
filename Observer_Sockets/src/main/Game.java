package main;

import characters.Ghost;
import characters.PacMan;

import creators.CharacterCreator;
import creators.InfoCreator;
import graphics.ImageLoader;
import graphics.Skins;
import items.Item;
import user.MouseInput;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;



public class Game extends Canvas implements Runnable, Observer {

    public static final int WIDTH = 1269;
    public static final int HEIGHT = 700;
    public final String TITTLE = "paCE man Observer";

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


    private PacMan p;
    private CharacterCreator c;
    private InfoCreator infoCreator;
    private Skins tex;
    private Menu menu;

    public LinkedList<Ghost> eb;
    public LinkedList<Item> ec;





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

    Server s;



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
        addMouseListener(new MouseInput());
        menu = new Menu();
    }

    public void playInit(){


        tex = new Skins(this);
        p = new PacMan(0, 0, tex,this);
        c = new CharacterCreator(tex, this);

        //c.createPacDots();

        c.createShadow();
        c.createBashful();
        c.createPokey();
        c.createSpeedy();


        infoCreator = new InfoCreator(tex, this);

        //initialize linked lists
        eb = c.getEb();
        ec = c.getEc();



        s = new Server(6000);
        s.addObserver(this);
        Thread t = new Thread(s);
        t.start();


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
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){
        if(state == STATE.PLAY && startFlag){
            //update pos

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


    public BufferedImage getEnergizerSprite() {
        return energizerSprite;
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

    @Override
    public void update(Observable o, Object arg) {

        String[] parts = ((String) arg).split(",");
        double pacmanX = Double.parseDouble(parts[0]);
        double pacmanY = Double.parseDouble(parts[1]);
        p.setX(pacmanX);
        p.setY(pacmanY);

        double shadowX = Double.parseDouble(parts[2]);
        double shadowY = Double.parseDouble(parts[3]);
        c.getShadow().setX(shadowX);
        c.getShadow().setY(shadowY);

        double bashfulX = Double.parseDouble(parts[4]);
        double bashfulY = Double.parseDouble(parts[5]);
        c.getBashful().setX(bashfulX);
        c.getBashful().setY(bashfulY);

        double pokeyX = Double.parseDouble(parts[6]);
        double pokeyY = Double.parseDouble(parts[7]);
        c.getPokey().setX(pokeyX);
        c.getPokey().setY(pokeyY);

        double speedyX = Double.parseDouble(parts[8]);
        double speedyY = Double.parseDouble(parts[9]);
        c.getSpeedy().setX(speedyX);
        c.getSpeedy().setY(speedyY);

        int score = Integer.parseInt(parts[10]);
        getInfoCreator().addScore(score);

        int level = Integer.parseInt(parts[11]);
        getInfoCreator().addLevel(level);

        int removeItem = Integer.parseInt(parts[12]);
        //c.removeItem(removeItem);


        int newItem = Integer.parseInt(parts[13]);

        int pacLives = Integer.parseInt(parts[14]);
        getInfoCreator().addLives(pacLives);


    }



}
