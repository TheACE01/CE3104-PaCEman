package main;

import characters.Ghost;
import characters.PacMan;
import creators.CharacterCreator;
import creators.InfoCreator;
import graphics.ImageLoader;
import graphics.Skins;
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

    public static final Integer WIDTH = 1269;
    public static final Integer HEIGHT = 700;
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
    
    public Server s;
    public Thread serverThread;
    private Boolean skaredFlag = false;


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
        addMouseListener(new MouseInput());
        menu = new Menu();
    }

    public void playInit(){


        tex = new Skins(this);
        p = new PacMan(0, 0, tex,this);
        c = new CharacterCreator(tex, this);

        c.createShadow();
        c.createBashful();
        c.createPokey();
        c.createSpeedy();


        infoCreator = new InfoCreator(tex, this);

        //initialize linked lists
        eb = c.getEb();
       
        //initialize the server
        s = new Server(6000);
        s.addObserver(this);
        serverThread = new Thread(s);
        serverThread.start();


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
        final Double amountOfTicks = 60.0;
        Double ns = 1000000000 / amountOfTicks;
        Double delta = 0.0;
        Integer updates = 0;
        Integer frames = 0;
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

    @SuppressWarnings("deprecation")
	@Override
    public void update(Observable o, Object arg) {

        String[] parts = ((String) arg).split(",");
        Double pacmanX = Double.parseDouble(parts[0]);
        Double pacmanY = Double.parseDouble(parts[1]);
        p.setX(pacmanX);
        p.setY(pacmanY);

        Double shadowX = Double.parseDouble(parts[2]);
        Double shadowY = Double.parseDouble(parts[3]);
        c.getShadow().setX(shadowX);
        c.getShadow().setY(shadowY);

        Double bashfulX = Double.parseDouble(parts[4]);
        Double bashfulY = Double.parseDouble(parts[5]);
        c.getBashful().setX(bashfulX);
        c.getBashful().setY(bashfulY);

        Double pokeyX = Double.parseDouble(parts[6]);
        Double pokeyY = Double.parseDouble(parts[7]);
        c.getPokey().setX(pokeyX);
        c.getPokey().setY(pokeyY);

        Double speedyX = Double.parseDouble(parts[8]);
        Double speedyY = Double.parseDouble(parts[9]);
        c.getSpeedy().setX(speedyX);
        c.getSpeedy().setY(speedyY);

        Integer score = Integer.parseInt(parts[10]);
        getInfoCreator().addScore(score);

        Integer level = Integer.parseInt(parts[11]);
        getInfoCreator().addLevel(level);

        Integer pacLives = Integer.parseInt(parts[12]);
        getInfoCreator().addLives(pacLives);
        
        
        Integer gameStatus = Integer.parseInt(parts[13]);
        //validate if there is a game over
        if(gameStatus == -1) {
        	state = STATE.MENU;//back to menu
        	startFlag = false;//init the start flag

        }
        
        Integer energizerStatus = Integer.parseInt(parts[14]);
        if(energizerStatus == 1) {
        	skaredFlag = true;
        }
        if(energizerStatus == 0) {
        	skaredFlag = false;
        }
        

    }
    public Boolean getSkaredFlag() {
    	return skaredFlag;
    }



}
