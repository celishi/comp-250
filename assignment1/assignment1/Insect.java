package assignment1;

public abstract class Insect {
    private Tile tile;
    private int hp;

    public Insect(Tile tile, int hp) {
        if (tile != null && tile.addInsect(this)) {
            this.tile = tile;
            tile.addInsect(this);
        }
        else {
            throw new IllegalArgumentException("unable to add insect to tile");
        }
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

    public void takeDamage(int dmg) {
        this.hp -= dmg;
        if (hp <= 0) {
            this.tile.removeInsect(this);
        }
    }
    
    public abstract boolean takeAction();

    public boolean equals(Object object) {
        if (object instanceof Insect && ((Insect) object).tile == this.tile && ((Insect) object).hp == this.hp) {
            return true;
        }
        else {
            return false;
        }
    }

    public void regenerateHealth(double healthRegen) {
        this.hp += Math.round(hp * healthRegen/100);
    }
}