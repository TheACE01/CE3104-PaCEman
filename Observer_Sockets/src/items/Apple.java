package items;


import graphics.Skins;

public class Apple extends Item {

    private Skins tex;
    private String id = "apple";


    public Apple(int x, int y, Skins tex, String id, int quadrant) {
        super(x, y, tex, id, quadrant);
    }
}
