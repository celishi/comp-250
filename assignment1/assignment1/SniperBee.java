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

            while (tile.getNumOfHornets() == 0) {
                tile = tile.towardTheNest();
            }

            if (!tile.isNest()) {
                int x = tile.getNumOfHornets(); //original number of hornets
                int n = Math.min(power, tile.getNumOfHornets());
                for (int i = 0; i<n ; i++) {
                    tile.getHornets()[i].takeDamage(atk);

                    //check if the hornets have been killed, if so, all remaining hornets gets moved up by 1, so have to dmg hornet with same index
                    if (x > tile.getNumOfHornets() && tile.getNumOfHornets() != 0) {
                        i--;
                        n--;
                        x = tile.getNumOfHornets();
                    }
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