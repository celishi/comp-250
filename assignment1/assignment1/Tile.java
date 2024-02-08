package assignment1;

import java.util.Objects;

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
        this.hornets = new SwarmOfHornets();
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
        this.hornets = Objects.requireNonNullElseGet(hornets, SwarmOfHornets::new);
    }

    public String toString() {
        return "food cost is: " + this.food;
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
        if ((tile1 == null && !this.hiveBuilt) || (tile2 == null && !this.nestBuilt )) {
            throw new IllegalArgumentException("unable to create path, check if tiles are valid");
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
        else if(this.partOfPath && insect instanceof Hornet) {
            this.hornets.addHornet((Hornet) insect);
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
        else if (insect instanceof HoneyBee) {
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