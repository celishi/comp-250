public class Tile {
    private int food;
    private boolean hiveBuilt;
    private boolean nestBuilt;
    private boolean partOfPath;
    private Tile nestToHive;
    private Tile hiveToNest;
    private HoneyBee bee;
    private SwarmOfHornets hornets;

    public Tile() {
        this.hiveBuilt = false;
        this.nestBuilt = false;
        this.partOfPath = false;
        this.food = 0;
        this.bee = null;
        this.hornets = null;
    }

    public Tile(boolean hiveBuilt, boolean nestBuilt, boolean partOfPath, int food, HoneyBee bee, SwarmOfHornets hornets) {
        this.hiveBuilt = hiveBuilt;
        this.nestBuilt = nestBuilt;
        this.partOfPath = partOfPath;
        this.food = food;
        this.bee = bee;
        this.hornets = hornets;
    }

    public boolean isHive() {
        return hiveBuilt;
    }
    
    public boolean isNest() {
        return nestBuilt;
    }

    public void buildHive() {
        this.hiveBuilt = true;
    }

    public void buildNest() {
        this.nestBuilt = true;
    }

    public boolean isOnThePath() {
        return this.partOfPath;
    }

    public Tile towardTheHive() {
        if(!this.nestToHive.partOfPath || this.nestToHive.hiveBuilt) {
            return null;
        }
        else {
            return this.nestToHive;
        }
    }

    public Tile towardTheNest() {
        if(!this.hiveToNest.partOfPath || this.hiveToNest.nestBuilt) {
            return null;
        }
        else {
            return this.hiveToNest;
        }
    }

    public void createPath(Tile tile1, Tile tile2) {
        if ((tile1 == null && !tile1.isHive()) || (!tile2.isNest() && tile2 == null)) { //come back to this
            throw new IllegalArgumentException("invalid tile");
        }
        else {
            this.nestToHive = tile1;
            this.hiveToNest = tile2;
            this.partOfPath = true;
        }
    }

    public int collectFood() {
        int food = this.food;
        this.food = 0;
        return food;
    }

    public void storeFood(int addedFood) {
        this.food += addedFood;
    }

    public int getNumOfHornets() {
        return this.hornets.sizeOfSwarm();
    }

    public HoneyBee getBee() {
        return bee;
    }

    public Hornet getHornet() {
        return this.hornets.getFirstHornet();
    }

    public Hornet[] getHornets() {
        return this.hornets.getHornets();
    }

    public boolean addInsect(Insect insect) {
        if (insect instanceof HoneyBee && this.bee == null && !this.isNest()) {
            this.bee = (HoneyBee) insect;
            insect.setPosition(this);
            return true;
        }
        else if(this.partOfPath == true && insect instanceof Hornet) {
            hornets.addHornet((Hornet) insect);
            insect.setPosition(this);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean removeInsect(Insect insect) { //error?
        if (insect instanceof Hornet) {
            hornets.removeHornet((Hornet) insect);
            insect.setPosition(null);
            return true;
        }
        else {
            this.bee = null;
            insect.setPosition(null);
            return true;
        }
    }
}