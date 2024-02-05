public abstract class HoneyBee extends Insect {
    private int cost;
    public static double HIVE_DMG_REDUCTION;

    public HoneyBee(Tile tile, int hp, int cost) {
        super(tile, hp);
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public void takeDamage(int dmg) {
        Tile tile = this.getPosition();
        int hp = this.getHealth();
        if (tile.isHive()) {
            dmg -= Math.round(dmg * HIVE_DMG_REDUCTION/100);
            hp -= dmg;
        }
        else {
            hp -= dmg;
        }

        if (hp <= 0) {
            tile.removeInsect(this);
        }
    }
    
}