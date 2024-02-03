public class test {
    public static void main(String[] args) {
        SwarmOfHornets swarm = new SwarmOfHornets();

        for(int i=1; i<5; i++) {
            swarm.addHornet(new Hornet(null, 0, 1));
        }
        // System.out.println(swarm.toString());
        // System.out.println("size is: " + swarm.sizeOfSwarm());
        Hornet specialHornet = new Hornet(null, 10, 0);
        Hornet normalHornet = new Hornet(null, 0, 0);
        swarm.addHornet(specialHornet);
        for(int i=1; i<5; i++) {
            swarm.addHornet(normalHornet);
        }
        System.out.println(swarm.toString());
        System.out.println("size is: " + swarm.sizeOfSwarm());

        swarm.removeHornet(specialHornet);
        System.out.println(swarm.toString());
        System.out.println("size is: " + swarm.sizeOfSwarm());

        boolean var = false;
        System.out.println(var);

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
