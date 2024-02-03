public class SwarmOfHornets {
    private Hornet[] hornetList;
    private int size;

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
        return this.hornetList;
    }

    public Hornet getFirstHornet() {
        return this.hornetList[0];
    }

    public void addHornet(Hornet hornet) {
        if (this.hornetList.length == this.size) {
            resize();
        }

        this.hornetList[size] = hornet;
        this.size++;
    }

    public boolean removeHornet(Hornet hornet) {
        for(int i=0; i<this.size; i++) {
            System.out.println("checking hornet place");
            if (this.hornetList[i] == hornet) {
                System.out.println("hornet found at index " + i);
                for(int index = i; i<size-1; i++) {
                    this.hornetList[index] = this.hornetList[index+1];
                }
                this.hornetList[size-1] = null;
                size--;
                return true;
            }
        }
        System.out.println("hornet not found");
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