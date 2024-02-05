public class test {
    public static void main(String[] args) {
        // SwarmOfHornets swarm = new SwarmOfHornets();
        // Hornet hornet = new Hornet(null, 0, 1);

        // for(int i=1; i<5; i++) {
        //     swarm.addHornet(hornet);
        // }
        // // System.out.println(swarm.toString());
        // System.out.println("size is: " + swarm.sizeOfSwarm());
        // Hornet specialHornet = new Hornet(null, 10, 0);
        // Hornet normalHornet = new Hornet(null, 0, 0);
        // swarm.addHornet(specialHornet);
        // for(int i=1; i<5; i++) {
        //     swarm.addHornet(normalHornet);
        // }
        // System.out.println(swarm.toString());
        // System.out.println("size is: " + swarm.sizeOfSwarm());

        // swarm.removeHornet(specialHornet);
        // System.out.println(swarm.toString());
        // System.out.println("size is: " + swarm.sizeOfSwarm());

        // boolean var = false;
        // System.out.println(var);

        // Hornet[] list = {normalHornet, normalHornet, specialHornet, normalHornet};
        // int size = list.length;
        // printText(size, list);

        // for(int i=0; i<size; i++) {
        //     System.out.println("checking hornet place");
        //     if (list[i] == specialHornet) {
        //         System.out.println("hornet found at index " + i);
        //         for(int index = i; i<size - 1; i++) {
        //             list[index] = list[index+1];
        //         }
        //         list[size-1] = null;
        //         size--;
        //     }
        // }
        // printText(size, list);
        
        // Tile path1 = new Tile(true, false, false, 0, null, swarm);
        // Tile path2 = new Tile(false, false, false, 1, null, null);
        // Tile path3 = new Tile(false, false, false, 2, null, null);
        
        // Hornet otherHornet = new Hornet(path3, 0, 0);
        // System.out.println(otherHornet.getPosition());
        // path1.createPath(path2, null);
        // path1.addInsect(otherHornet);
        // System.out.println(path1.getNumOfHornets());
        // System.out.println(otherHornet.getPosition());
        // System.out.println(path1.getHornets());
        // path1.removeInsect(hornet);
        // System.out.println(path1.getHornets());
        // System.out.println(path1.getNumOfHornets());

    }

    // private static void printText(int size, Hornet[] list) {
    //     String text = "";
    //     if (size == 0) {
    //         System.out.println("empty swarm");
    //     }
    //     else {
    //         for(int i=0; i<size; i++) {
    //             text += "hornet " + i + ": " + list[i].getHealth() + "\n";
    //         }
    //         System.out.println(text);
    //     }
    // }
}
