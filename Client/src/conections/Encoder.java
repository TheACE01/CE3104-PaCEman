package conections;

/**
 * Encode the information that the observer needs like character positions, energizer status and game over status
 * @author kevin Avevedo
 */
public class Encoder {

    //pac man position
    private Double pacmanX;
    private Double pacmanY;

    //shadow position
    private double shadowX;
    private double shadowY;

    //bashful position
    private double bashfulX;
    private double bashfulY;

    //pokey position
    private double pokeyX;
    private double pokeyY;

    //speedy position
    private double speedyX;
    private double SpeedyY;

    //Score information
    private Integer score = 0;

    //Level information
    private Integer level = 1;

    //pac man lives information
    private Integer lives = 3;

    //game state information
    private Integer state = 1;

    //energizer triggered state
    private Integer energizer = 0;

    public Double getPacmanX() {
        return pacmanX;
    }

    public void setPacmanX(Double pacmanX) {
        this.pacmanX = pacmanX;
    }

    public Double getPacmanY() {
        return pacmanY;
    }

    public void setPacmanY(Double pacmanY) {
        this.pacmanY = pacmanY;
    }

    public Double getShadowX() {
        return shadowX;
    }

    public void setShadowX(Double shadowX) {
        this.shadowX = shadowX;
    }

    public Double getShadowY() {
        return shadowY;
    }

    public void setShadowY(Double shadowY) {
        this.shadowY = shadowY;
    }

    public Double getBashfulX() {
        return bashfulX;
    }

    public void setBashfulX(Double bashfulX) {
        this.bashfulX = bashfulX;
    }

    public Double getBashfulY() {
        return bashfulY;
    }

    public void setBashfulY(Double bashfulY) {
        this.bashfulY = bashfulY;
    }

    public Double getPokeyX() {
        return pokeyX;
    }

    public void setPokeyX(Double pokeyX) {
        this.pokeyX = pokeyX;
    }

    public Double getPokeyY() {
        return pokeyY;
    }

    public void setPokeyY(Double pokeyY) {
        this.pokeyY = pokeyY;
    }

    public Double getSpeedyX() {
        return speedyX;
    }

    public void setSpeedyX(Double speedyX) {
        this.speedyX = speedyX;
    }

    public Double getSpeedyY() {
        return SpeedyY;
    }

    public void setSpeedyY(Double speedyY) {
        SpeedyY = speedyY;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getEnergizer() {
        return energizer;
    }

    public void setEnergizer(Integer energizer) {
        this.energizer = energizer;
    }
}
