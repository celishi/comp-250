package assignment1;

public class SniperBee extends HoneyBee {
    private int atk;
    private int power;
    public static int BASE_HEALTH;
    public static int BASE_COST;
    private boolean isAiming = true;

    public SniperBee (Tile tile, int atk, int power) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.atk = atk;
        this.power = power;
    }

    public boolean takeAction() {
        Tile tile = this.getPosition().towardTheNest();
        if (this.getPosition().isOnThePath() && !isAiming) {

            while (tile.getHornets() == null) {
                tile = tile.towardTheNest();
            }

            if (!tile.isNest()) {
                int n = tile.getNumOfHornets();

                if (power < tile.getNumOfHornets()) {
                    n = power;
                }

                for (int i = 0; i>n ; i++) {
                    tile.getHornets()[i].takeDamage(atk);
                }
                isAiming = true;
                return true;
            }
            else {
                return false;
            }
        }
        else {
            isAiming = false;
            return false;
        }
    }
}