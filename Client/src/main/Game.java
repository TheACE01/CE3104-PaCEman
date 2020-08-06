package main;

import characters.Ghost;
import conections.Client;
import conections.Encoder;
import conections.StreamingClient;
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
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This is the main class of the game, controls the player movement, the game configuration, the time counters
 * , the images, the observer messages and the window.
 * @author kevin Avevedo
 */
public class Game extends Canvas implements Runnable {

    //window dimensions
    public static final Integer WIDTH = 1269;
    public static final Integer HEIGHT = 700;

    //window title
    public final String TITTLE = "paCE man";

    //validates if the game started
    private Boolean running = false;

    //Main thread
    private Thread thread;

    //Images of the game
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

    //Pac Man object
    private PacMan p;

    //Character Creator object
    private CharacterCreator c;

    //Info Creator object
    private InfoCreator infoCreator;

    //Textures object
    private Skins tex;

    //Menu object
    private Menu menu;

    //Characters Lists
    public LinkedList<Ghost> ghosts;
    private LinkedList<Item> items;
    public LinkedList<Obstacle> obstacles;

    //Dijkstra Algorithm object
    private DijkstraExec router = new DijkstraExec();

    //Shadow Target
    private Integer shadowTarget = 32;

    //Energizer flag
    private Boolean energizerOn = false;

    //Refresh Target
    private Integer refreshTarget;

    //Determines when to trigger the Speedy chase mode
    private Boolean chaseSpeedy = false;

    //Controls the chase Speedy time
    private Integer chaseSpeedyCont;

    //Flag that determines when to animate the pac man dying
    private Boolean resetCountFlag = false;

    //Controls the reset pac man and ghosts time
    private Integer resetCount = 0;

    public LinkedList<Item> getItems() {
        return items;
    }

    public Client getClient() {
        return client;
    }


    //States of the game
    public static enum STATE{
        MENU,
        PLAY
    };

    //Actual game state
    public static STATE state = STATE.MENU;

    //Starting game flag
    public static Boolean startFlag = true;

    //Encoder object
    private Encoder encoder;

    //Client object
    private Client client;

    //Client Thread
    public Thread clientThread;

    /**
     * Initialize the game images and listeners.
     */
    public void init(){
        requestFocus();
        //create an image loader
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

        //initializing the listeners
        addKeyListener(new PacManControl(this));
        addMouseListener(new MouseInput());

        //creating the menu
        menu = new Menu();
    }

    /**
     * Initialize the characters and items objects
     */
    public void playInit() throws IOException {
        encoder = new Encoder();
        tex = new Skins(this);
        p = new PacMan(564, 450, tex,this);
        c = new CharacterCreator(tex, this);
        infoCreator = new InfoCreator(tex, this);

        //create the wall rectangles
        c.createWallRectangles();

        //adding pac dots for test
        c.createPacDots();

        //initialize the graph structure
        router.initGraph();

        //initialize linked lists
        ghosts = c.getEb();
        items = c.getItems();
        obstacles = c.getWalls();

        //initialize the client


        client = new Client("localhost", 8081, this);
        clientThread = new Thread(client);
        clientThread.start();
    }

    /**
     * Initialize the main thread
     */
    private synchronized void start(){
        if(running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stop the main thread
     */
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

    /**
     * Start the powerful game loop
     */
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

        refreshTarget = 0;
        chaseSpeedyCont = 0;
        while(running){

            //checking the start flag
            if(!startFlag){
                try {
                    playInit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

                    //update the encoder energizer flag
                    encoder.setEnergizer(0);
                }
                if(energizerOn && refreshTarget < 7 && state == STATE.PLAY){
                    refreshTarget++;
                    //System.out.println(refreshTarget);
                }
                //speedy flag
                if(chaseSpeedyCont == 10 && state == STATE.PLAY) {
                    chaseSpeedyCont = 0;
                    swapSpeedyMovement();
                }
                if(chaseSpeedyCont < 10 && state == STATE.PLAY){
                    chaseSpeedyCont++;
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

    /**
     * Call the CharacterCreator and Pac Man tick methods. Controls the movement
     */
    private void tick(){
        if(state == STATE.PLAY && startFlag){
            p.tick();
            c.tick();

            if(infoCreator.getLives() == 0 || infoCreator.getLevel() == 4){
                //notify the observer
                encoder.setState(-1);

                //stop the client socket
                /*
                try {
                    clientThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                 */

                state = STATE.MENU;
                startFlag = false;
            }

            //update the encoder object and notify the observer
            updateEncoder();
        }

    }

    /**
     * Call the CharacterCreator and Pac Man render methods
     */
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

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

    /**
     * Validates the pressed keys on the keyboard
     * @param e The key event
     */
    public void keyPressed(KeyEvent e){

        Integer key = e.getKeyCode();

        if(state == STATE.PLAY){

            if(key == KeyEvent.VK_D){
                p.directionSwap();

                p.setR(true);
                p.setVelX(3.0);
            }
            else if(key == KeyEvent.VK_A){
                p.directionSwap();

                p.setL(true);
                p.setVelX(-3.0);
            }
            else if(key == KeyEvent.VK_W){
                p.directionSwap();

                p.setU(true);
                p.setVelY(-3.0);
            }
            else if(key == KeyEvent.VK_S){
                p.directionSwap();
                p.setD(true);
                p.setVelY(3.0);
            }

            else if(key == KeyEvent.VK_1){
                c.createShadow();
            }
            else if(key == KeyEvent.VK_2){
                c.createBashful();
            }
            else if(key == KeyEvent.VK_3){
                c.createPokey();
            }
            else if(key == KeyEvent.VK_4){
                c.createSpeedy();
            }

            else if(key == KeyEvent.VK_5){
                c.GhostBoost(1.5);
            }
            else if(key == KeyEvent.VK_6){
                c.GhostBoost(2.5);
            }
            else if(key == KeyEvent.VK_7){
                c.createFruit(200);
            }

        }

    }

    /**
     * Validates the released key events
     * @param e The key event
     */
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_D){
           p.setVelX(0.0);
        }
        else if(key == KeyEvent.VK_A){
            p.setVelX(0.0);
        }
        else if(key == KeyEvent.VK_W){
            p.setVelY(0.0);
        }
        else if(key == KeyEvent.VK_S){
            p.setVelY(0.0);
        }
    }


    /**
     * The main method of the game
     * @param args
     */
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

    public Integer getShadowTarget() {
        return shadowTarget;
    }

    public void setShadowTarget(Integer shadowTarget) {
        this.shadowTarget = shadowTarget;
    }

    public BufferedImage getEnergizerSprite() {
        return energizerSprite;
    }

    public Boolean isEnergizerOn() {
        return energizerOn;
    }

    public void setEnergizerOn(Boolean energizerOn) {
        this.energizerOn = energizerOn;
    }


    public void setRefreshTarget(Integer refreshTarget) {
        this.refreshTarget = refreshTarget;
    }

    public Boolean isChaiseSpeedy() {
        return chaseSpeedy;
    }

    /**
     * Change the Speedy chase mode status
     */
    public void swapSpeedyMovement(){
        if(chaseSpeedy) chaseSpeedy = false;
        else{
            chaseSpeedy = true;
        }
    }

    /**
     * Initialize the player to the starting position
     */
    public void reset(){
        p.setX(47.0*12.0);
        p.setY(50.0*9.0);
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

    /**
     * Prepare the message to be sent to the observer, creates the socket and send the message
     */
    public void updateEncoder(){
        //sending messages to the observer next to the characters ticks


        String encodedCharacters =
                //pac man state
                Double.toString(encoder.getPacmanX()) +
                "," + Double.toString(encoder.getPacmanY()) +

                "," + Double.toString(encoder.getShadowX()) +
                "," + Double.toString(encoder.getShadowY()) +

                "," + Double.toString(encoder.getBashfulX()) +
                "," + Double.toString(encoder.getBashfulY()) +

                "," + Double.toString(encoder.getPokeyX()) +
                "," + Double.toString(encoder.getPokeyY()) +

                "," + Double.toString(encoder.getSpeedyX()) +
                "," + Double.toString(encoder.getSpeedyY()) +

                "," + Integer.toString(encoder.getScore()) +

                "," + Integer.toString(encoder.getLevel()) +

                "," + Integer.toString(encoder.getLives()) +

                "," + Integer.toString(encoder.getState()) +

                        "," + Integer.toString(encoder.getEnergizer());

        StreamingClient c = new StreamingClient(6000, encodedCharacters);
        Thread t = new Thread(c);
        t.start();
    }

    /**
     * Logic when the level up is activated
     */
    public void levelUp(){
        infoCreator.addLevel();
        items.clear();
        ghosts.clear();
        p.setX(564.0);
        p.setY(450.0);
        c.createPacDots();
    }

    public BufferedImage getLive() {
        return live;
    }

    public InfoCreator getInfoCreator() {
        return infoCreator;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public void redirectServerMessage(String message){
        //Split the message
        String[] serverRequest = (message).split(",");

        String requestName = serverRequest[0]; //The identifier
        Integer requestValue = Integer.parseInt(serverRequest[1]); //The value

        //1: Create ghost request only if there are not 4 ghosts in the game
        if(requestName.equals("createghost")  && ghosts.size() < 4){
            //Shadow?
            if(requestValue == 1){
                c.createShadow();
            }
            //Bashful?
            if(requestValue == 2){
                c.createBashful();
            }
            //Pokey?
            if(requestValue == 3){
                c.createPokey();
            }
            //Speedy?
            if(requestValue == 4){
                c.createSpeedy();
            }
        }

        //2: Create Energizer or Pill in a specific quadrant
        if(requestName.equals("pill")){
            c.createEnergizer(requestValue);
        }

        //3: Create fruit request
        if(requestName.equals("fruit")){
            c.createFruit(requestValue);
        }

        //4: Level up request
        if(requestName.equals("level")){
            levelUp();
        }

        //5: Pac man lifes request
        if(requestName.equals("lifes")){
            infoCreator.addLives();
        }

        //6: Velocity request
        if(requestName.equals("velocity")){
            c.GhostBoost(requestValue);
        }

    }
}
