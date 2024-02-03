public abstract class Insect {
    private Tile tile;
    private int hp;

    public Insect(Tile tile, int hp) {
        this.tile = tile;
        this.hp = hp;
    }

    public final Tile getPosition() {
        return this.tile;
    }

    public final int getHealth() {
        return this.hp;
    }

    public void setPosition(Tile tile) {
        this.tile = tile;
    }
    
}