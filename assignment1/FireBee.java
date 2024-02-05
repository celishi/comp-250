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
        if (this.getPosition().isOnThePath()) { //to come back when we have an answer
            for(int i = 0; i < range; i++) {
                if (tile.getHornets() != null && !tile.isOnFire() && !tile.isHive()) {
                    tile.setOnFire();
                    return true;
                }
                else {
                    tile = tile.towardTheNest();
                }
            }
            return false;
        }
        else {
            return false;
        }
    }
}