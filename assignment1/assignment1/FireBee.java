package assignment1;

public class FireBee extends HoneyBee {
    private int range;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public FireBee(Tile tile, int range) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.range = range;
    }

    public boolean takeAction() {
        Tile tile = this.getPosition().towardTheNest();
        if (this.getPosition().isOnThePath()) {
            for(int i = 0; i<range; i++) {
                if (tile.getNumOfHornets() != 0 && !tile.isOnFire() && !tile.isHive()) {
                    tile.setOnFire();
                    return true;
                }
                else {
                    if (tile.towardTheNest() != null) {
                        tile = tile.towardTheNest();
                    }
                    else {
                        return false;
                    }
                }
            }
            return false;
        }
        else {
            return false;
        }
    }
}