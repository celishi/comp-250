package assignment1;

public class AngryBee extends HoneyBee {
    private int atk;
    public static int BASE_HEALTH;
    public static int BASE_COST;

    public AngryBee(Tile tile, int atk) {
        super(tile, BASE_HEALTH, BASE_COST);
        this.atk = atk;
    }

    public boolean takeAction() {
        Tile tile = this.getPosition();
        if(tile.isOnThePath()) {
            if (tile.getHornet() != null) {
                tile.getHornet().takeDamage(atk);
                return true;
            }
            else if (tile.towardTheNest().getHornet() != null) {
                tile.towardTheNest().getHornet().takeDamage(atk);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    
}