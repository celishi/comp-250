package assignment1;

public class SwarmOfHornets {
    private Hornet[] hornetList;
    private int size;
    public static double QUEEN_BOOST;

    public SwarmOfHornets() {
        this.hornetList = new Hornet[10];
        this.size = 0;
    }

    public String toString() {
        String text = "";
        if (size == 0) {
            return "empty swarm";
        }
        else {
            for(int i=0; i<size; i++) {
                text += "hornet " + i + ": " + this.hornetList[i].getHealth() + "\n";
            }
            return text;
        }
    }

    public int sizeOfSwarm() {
        return this.size;
    }

    public Hornet[] getHornets() {
        Hornet[] list = new Hornet[size];
        int idx = 0;
        for(int i=0; i<this.size; i++) {
            if (hornetList[i] != null) {
                list[idx] = hornetList[i];
                idx++;
            }
        }
        return list;
    }

    public Hornet getFirstHornet() {
        return this.hornetList[0];
    }

    public void addHornet(Hornet hornet) {
        if (hornet.isTheQueen()) {
            for(int i=0; i<size; i++) {
                hornetList[i].regenerateHealth(QUEEN_BOOST);
            }
        }
        if (this.hornetList.length == this.size && size != 0) {
            resize();
        }
        this.hornetList[size] = hornet;
        this.size++;
    }

    public boolean removeHornet(Hornet hornet) {
        for(int i=0; i<this.size; i++) {
            if (this.hornetList[i].equals(hornet)) {
                for(int index = i; index<size-1; index++) {
                    this.hornetList[index] = this.hornetList[index+1];
                }
                this.hornetList[size-1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    private void resize() {
        Hornet[] bigger = new Hornet[this.hornetList.length*2];
        for(int i=0; i<this.size; i++) {
            bigger[i] = this.hornetList[i];
        }
        this.hornetList = bigger;
    }

}