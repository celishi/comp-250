// package assignment1;

// public class test {
//     public static void main(String[] args) {
        
        
//         //death by fire
//             // Hornet.BASE_FIRE_DMG = 500;
//             // // Path: nest -> tile1 -> tile2
//             // Tile hornetNest = new Tile(2, false, false, false, null, null, null, null);
//             // hornetNest.buildNest();
//             // Tile tile1 = new Tile(0, false, false, false, null, null, null, null);
//             // Tile tile2 = new Tile(1, false, false, false, null, null, null, null);
//             // hornetNest.createPath(tile1, null);
//             // tile1.createPath(tile2, hornetNest);
//             // // We check that if a hornet dies from fire on tile1 that it does not move to tile2
//             // Hornet dyingHornet = new Hornet(hornetNest, 1, 0);
//             // FireBee hornetKiller = new FireBee(tile2, 3);
//             // hornetKiller.takeAction();
//             // dyingHornet.takeAction();
//             // System.out.println("should be 0 but is actually " + tile2.getNumOfHornets());

//         //sniper bee death cleanup
// //            Tile tile1 = new Tile(0, false, false, true, null, null, null, null);
// //            Tile tile2 = new Tile(1, false, false, true, null, null, null, null);
// //            Tile tile3 = new Tile(2, false, false, true, null, null, null, null);
// //            tile1.buildNest();
// //            tile1.createPath(tile2, null);
// //            tile2.createPath(tile3, tile1);
// //            tile3.createPath(new Tile(3, false, false, true, null, null, null, null), tile2);
// //            SniperBee sniperBee = new SniperBee(tile3, 5,2);
// //            // Swarm: [hornet1, hornet2]
// //            Hornet hornet1 = new Hornet(tile2, 1, 0);
// //            Hornet hornet2 = new Hornet(tile2, 1, 1);
// //            System.out.println(tile2.getHornets()[0]);
// //            System.out.println(tile2.getHornets()[1]);
// //            sniperBee.takeAction();
// //            // sniperBee should kill hornet1 and hornet2
// //            sniperBee.takeAction();
// //            System.out.println("should be 0, is actually " + tile2.getNumOfHornets());

//         //queen regeneration
// //            Tile tile = new Tile();
// //            Tile placeholder = new Tile();
// //            tile.createPath(new Tile(), new Tile());
// //            placeholder.createPath(new Tile(), new Tile());
// //            Hornet hornet1 = new Hornet(tile, 10, 0);
// //            tile.addInsect(hornet1);
// //            Hornet hornet2 = new Hornet(tile, 10, 0);
// //            Hornet hornet3 = new Hornet(tile, 10, 0);
// //
// //            SwarmOfHornets.QUEEN_BOOST = 50.0/100;
// //            Hornet queen = new Hornet(new Tile(4, false, false, true, null, null, null, null),  10, 1);
// //            queen.promote();
// //            tile.addInsect(queen);
// //
// //            System.out.println("hornet1 health should be 15, actually is " + hornet1.getHealth());
// //            System.out.println("hornet2 health should be 15, actually is " + hornet2.getHealth());
// //            System.out.println("hornet3 health should be 15, actually is " + hornet3.getHealth());
// //            System.out.println("queen health should be 10, actually is " + queen.getHealth());

//         //queen double action
// //            Tile tile1 = new Tile(0, false, false, true, null, null, null, null);
// //            Tile tile2 = new Tile(1, false, false, true, null, null, null, null);
// //            Tile tile3 = new Tile(2, false, false, true, null, null, null, null);
// //            tile1.buildNest();
// //            tile1.createPath(tile2, null);
// //            tile2.createPath(tile3, tile1);
// //            tile3.createPath(new Tile(3, false, false, true, null, null, null, null), tile2);
// //            Hornet queen = new Hornet(new Tile(4, false, false, true, null, null, null, null),  5, 1);
// //            queen.promote();
// //            tile1.addInsect(queen);
// //            // queen should move two spots with this takeAction()
// //            queen.takeAction();
// //            System.out.println(queen.getPosition());

//         //add bee on top of each other
//     //        Tile tile = new Tile();
//     //        System.out.println(tile.getBee());
//     //        BusyBee bee1 = new BusyBee(tile);
//     //        System.out.println(bee1.equals(tile.getBee()));
//     //        BusyBee bee2;
//     //        System.out.println(bee1.equals(tile.getBee()));

//         //fire bee
// //            Tile previousTile1 = new Tile(0, false, false, false, null, null, null, null);
// //            Tile previousTile2 = new Tile(1, false, false, false, null, null, null, null);
// //            Tile previousTile3 = new Tile(2, false, false, false, null, null, null, null);
// //            Tile currentTile = new Tile(3, false, false, false, null, null, null, null);
// //            Tile nextTile = new Tile(4, false, false, false, null, null, null, null);
// //
// //            currentTile.createPath(nextTile, previousTile1);
// //            previousTile1.createPath(currentTile, previousTile2);
// //            previousTile2.createPath(previousTile1, previousTile3);
// //
// //            previousTile1.setOnFire();
// //            //previousTile2.setOnFire();
// //
// //            Hornet aHornet = new Hornet(previousTile3, 2, 2);
// //
// //            FireBee testFirebee = new FireBee(currentTile,2);
// //            boolean actionTaken = testFirebee.takeAction();

// //        Hornet hornet = null;
// //        System.out.println(hornet instanceof Hornet);

//         //queen health regen
//             // Tile tile = new Tile();
//             // Tile path1 = new Tile();
//             // Tile path2 = new Tile();
//             // Tile queenTile = new Tile();
//             // tile.createPath(path1, path2);
//             // SwarmOfHornets.QUEEN_BOOST = 5.0/100.0;
//             // Hornet hornet = new Hornet(tile, 43, 0);
//             // Hornet queen = new Hornet(queenTile, 0, 0);
//             // System.out.println(tile.getHornet().getHealth());
//             // queen.promote();
//             // tile.addInsect(queen);
//             // System.out.println(tile.getHornet().getHealth());

//         // SwarmOfHornets swarm = new SwarmOfHornets();
//         // System.out.println(swarm.getHornets());
//         // System.out.println(swarm.getFirstHornet());
//         // Tile tile = new Tile();
//         // Tile path1 = new Tile();
//         // Tile path2 = new Tile();
//         // tile.createPath(path1, path2);
//         // SwarmOfHornets swarm = tile.getHornets();
//         // Hornet hornet = new Hornet(tile, 0, 1);
//         // System.out.println(hornet);
//         // tile.addInsect(hornet);
//         // System.out.println(tile.getHornets());
//         // Hornet insect = new Hornet(new Tile(), 6, 5;)  

//         // for(int i=1; i<5; i++) {
//         //     swarm.addHornet(hornet);
//         // }
//         // // System.out.println(swarm.toString());
//         // System.out.println("size is: " + swarm.sizeOfSwarm());
//         // Hornet specialHornet = new Hornet(null, 10, 0);
//         // Hornet normalHornet = new Hornet(null, 0, 0);
//         // swarm.addHornet(specialHornet);
//         // for(int i=1; i<5; i++) {
//         //     swarm.addHornet(normalHornet);
//         // }
//         // System.out.println(swarm.toString());
//         // System.out.println("size is: " + swarm.sizeOfSwarm());

//         // swarm.removeHornet(specialHornet);
//         // System.out.println(swarm.toString());
//         // System.out.println("size is: " + swarm.sizeOfSwarm());

//         // boolean var = false;
//         // System.out.println(var);

//         // Hornet[] list = {normalHornet, normalHornet, specialHornet, normalHornet};
//         // int size = list.length;
//         // printText(size, list);

//         // for(int i=0; i<size; i++) {
//         //     System.out.println("checking hornet place");
//         //     if (list[i] == specialHornet) {
//         //         System.out.println("hornet found at index " + i);
//         //         for(int index = i; i<size - 1; i++) {
//         //             list[index] = list[index+1];
//         //         }
//         //         list[size-1] = null;
//         //         size--;
//         //     }
//         // }
//         // printText(size, list);
        
//         // Tile path1 = new Tile(true, false, false, 0, null, swarm);
//         // Tile path2 = new Tile(false, false, false, 1, null, null);
//         // Tile path3 = new Tile(false, false, false, 2, null, null);
        
//         // Hornet otherHornet = new Hornet(path3, 0, 0);
//         // System.out.println(otherHornet.getPosition());
//         // path1.createPath(path2, null);
//         // path1.addInsect(otherHornet);
//         // System.out.println(path1.getNumOfHornets());
//         // System.out.println(otherHornet.getPosition());
//         // System.out.println(path1.getHornets());
//         // path1.removeInsect(hornet);
//         // System.out.println(path1.getHornets());
//         // System.out.println(path1.getNumOfHornets());

//     }

//     // private static void printText(int size, Hornet[] list) {
//     //     String text = "";
//     //     if (size == 0) {
//     //         System.out.println("empty swarm");
//     //     }
//     //     else {
//     //         for(int i=0; i<size; i++) {
//     //             text += "hornet " + i + ": " + list[i].getHealth() + "\n";
//     //         }
//     //         System.out.println(text);
//     //     }
//     // }
// }
