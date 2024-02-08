package assignment1;

public class Hornet extends Insect{
    private int atk;
    public static int BASE_FIRE_DMG;
    private boolean isQueen;
    private static int queenCount = 0;

    public Hornet(Tile tile, int hp, int atk) {
        super(tile, hp);
        this.atk = atk;
        this.isQueen = false;
    }

    public String toString() {
        return "attack is: " + this.atk;
    }

    public boolean takeAction() {
        if (isQueen) {
            action();
        }
        return action();
    }

    private boolean action() {
        Tile tile = this.getPosition();
        HoneyBee bee = tile.getBee();

        if(this.getPosition().isOnFire()) {
            this.takeDamage(BASE_FIRE_DMG);
        }

        if (bee != null) {
            bee.takeDamage(atk);
            return true;
        }
        else if (!tile.isHive()) {
            this.setPosition(tile.towardTheHive());
            tile.removeInsect(this);
            tile.towardTheHive().addInsect(this);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean equals(Object object) {
        Tile tile = this.getPosition();
        int hp = this.getHealth();
        Hornet hornetObject = (Hornet) object;
        if (object instanceof Hornet && hornetObject.getPosition() == tile && hornetObject.getHealth() == hp && hornetObject.atk == this.atk) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isTheQueen() {
        return this.isQueen;
    }

    public void promote() {
        if (queenCount == 0) {
            this.isQueen = true;
        }
    }
}