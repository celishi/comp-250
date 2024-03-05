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
        Tile tile = this.getPosition();
        if (tile.isOnThePath()) {
            Tile towardNest = tile.towardTheNest();
            for(int i = 0; i<range; i++) {
                if (towardNest.getNumOfHornets() != 0 && !towardNest.isOnFire() && !towardNest.isHive()) {
                    towardNest.setOnFire();
                    return true;
                }
                else {
                    if (towardNest.towardTheNest() != null) {
                        towardNest = towardNest.towardTheNest();
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