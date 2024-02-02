public class AngryBee extends HoneyBee {
    private int atk;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile tile, int atk) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.atk = atk;
    }
    
}