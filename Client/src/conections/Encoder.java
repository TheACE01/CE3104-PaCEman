package conections;

public class Encoder {

    private double pacmanX;
    private double pacmanY;


    private double shadowX;
    private double shadowY;


    private double bashfulX;
    private double bashfulY;


    private double pokeyX;
    private double pokeyY;


    private double speedyX;
    private double SpeedyY;


    private int score;
    private int level = 1;
    private int lives = 3;

    private int state = 1;

    private int energizer = 0;




    public double getPacmanX() {
        return pacmanX;
    }

    public void setPacmanX(double pacmanX) {
        this.pacmanX = pacmanX;
    }

    public double getPacmanY() {
        return pacmanY;
    }

    public void setPacmanY(double pacmanY) {
        this.pacmanY = pacmanY;
    }

    public double getShadowX() {
        return shadowX;
    }

    public void setShadowX(double shadowX) {
        this.shadowX = shadowX;
    }

    public double getShadowY() {
        return shadowY;
    }

    public void setShadowY(double shadowY) {
        this.shadowY = shadowY;
    }

    public double getBashfulX() {
        return bashfulX;
    }

    public void setBashfulX(double bashfulX) {
        this.bashfulX = bashfulX;
    }

    public double getBashfulY() {
        return bashfulY;
    }

    public void setBashfulY(double bashfulY) {
        this.bashfulY = bashfulY;
    }

    public double getPokeyX() {
        return pokeyX;
    }

    public void setPokeyX(double pokeyX) {
        this.pokeyX = pokeyX;
    }

    public double getPokeyY() {
        return pokeyY;
    }

    public void setPokeyY(double pokeyY) {
        this.pokeyY = pokeyY;
    }

    public double getSpeedyX() {
        return speedyX;
    }

    public void setSpeedyX(double speedyX) {
        this.speedyX = speedyX;
    }

    public double getSpeedyY() {
        return SpeedyY;
    }

    public void setSpeedyY(double speedyY) {
        SpeedyY = speedyY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getEnergizer() {
        return energizer;
    }

    public void setEnergizer(int energizer) {
        this.energizer = energizer;
    }
}
