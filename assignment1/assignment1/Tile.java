package assignment1;

public class Tile {
    private int food;
    private boolean hiveBuilt;
    private boolean nestBuilt;
    private boolean partOfPath;
    private Tile nestToHive;
    private Tile hiveToNest;
    private HoneyBee bee;
    private SwarmOfHornets hornets;
    private boolean isOnFire = false; //set it in constructor or not?

    public Tile() {
        this.hiveBuilt = false;
        this.nestBuilt = false;
        this.partOfPath = false;
        this.food = 0;
        this.bee = null;
        this.hornets = null;
        this.nestToHive = null;
        this.hiveToNest = null;
    }

    public Tile(int food, boolean hiveBuilt, boolean nestBuilt, boolean partOfPath, Tile nestToHive, Tile hiveToNest, HoneyBee bee, SwarmOfHornets hornets) {
        this.food = food;
        this.hiveBuilt = hiveBuilt;
        this.nestBuilt = nestBuilt;
        this.partOfPath = partOfPath;
        this.nestToHive = nestToHive;
        this.hiveToNest = hiveToNest;
        this.bee = bee;
        this.hornets = hornets;
    }

    public String toString() {
        String text = "food cost is: " + this.food;
        return text;
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
        if ((tile1 == null || tile2 == null) && (!this.hiveBuilt && !this.nestBuilt)) {
            throw new IllegalArgumentException("unable to create path, check if tiles are valid");
        }
        else {
            System.out.println("made into a tile");
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
        if (insect instanceof HoneyBee && this.bee == null && !this.isHive()) {
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
        if (insect instanceof Hornet && insect != null) {
            hornets.removeHornet((Hornet) insect);
            insect.setPosition(null);
            return true;
        }
        else if (insect instanceof HoneyBee && insect != null) {
            this.bee = null;
            insect.setPosition(null);
            return true;
        }
        else {
            return false;
        }
    }

    public void setOnFire() {
        this.isOnFire = true;
    }

    public boolean isOnFire() {
        return this.isOnFire;
    }
}