// package assignment1;
// import java.util.Arrays;
// import java.util.LinkedList;
// import java.util.Random;

// /**
//  * Copy, drop-in, and use test cases for the {@code SwarmOfHornets} class.
//  * This test suite includes a set of simple cases followed by a heavy test.
//  * The heavy test compares the behavior of the {@code SwarmOfHornets} class against a ground truth,
//  * represented by the {@code LinkedList} class from the Java standard library.
//  *
//  * @author yun
//  */
// public class SwarmTester {
//     private static final Tile tile = new Tile(0, true, true, true, null, null, null, new SwarmOfHornets());

//     public static void main(String[] args) {
//         SwarmOfHornets swarm = new SwarmOfHornets();
//         a(swarm.sizeOfSwarm() == 0, "Initial size should be 0");
//         a(swarm.getHornets().length == 0, "Initial array should be empty");

//         // Test Case 2: Add hornets and check the size and content
//         Hornet hornet1 = new Hornet(tile, 1, 0);
//         Hornet hornet2 = new Hornet(tile, 2, 0);
//         swarm.addHornet(hornet1);
//         swarm.addHornet(hornet2);

//         a(swarm.sizeOfSwarm() == 2, "Size should be 2 after adding hornets");
//         a(swarm.getFirstHornet() == hornet1, "First hornet should be hornet1");
//         a(swarm.getHornets()[1] == hornet2, "Second hornet should be hornet2");

//         // Test Case 3: Iterate over the swarm
//         int count = swarm.getHornets().length;
//         a(count == swarm.sizeOfSwarm(), "getHornets length should match the size");

//         // Test Case 4: Remove hornet by object
//         boolean removed = swarm.removeHornet(hornet1);
//         a(removed, "Should remove hornet1");
//         a(swarm.sizeOfSwarm() == 1, "Size should be 1 after removal");
//         a(swarm.getHornets()[0] == hornet2, "Remaining hornet should be hornet2");

//         // Test Case 6: Try to remove non-existing hornet
//         Hornet nonExistingHornet = new Hornet(tile, 3, 0);
//         boolean notRemoved = swarm.removeHornet(nonExistingHornet);
//         a(!notRemoved, "Should not remove non-existing hornet");

//         // Test Case 7: Check size changes
//         swarm.addHornet(new Hornet(tile, 4, 0));
//         a(swarm.sizeOfSwarm() == 1, "Size should be 1 after adding hornet4");

//         // Test Case 8: Check for removing items not in swarm
//         boolean notInSwarmRemoved = swarm.removeHornet(new Hornet(tile, 5, 0));
//         a(!notInSwarmRemoved, "Should not remove hornet not in swarm");

//         // Test Case 9: Add more hornets and check the size
//         swarm.addHornet(new Hornet(tile, 6, 0));
//         swarm.addHornet(new Hornet(tile, 7, 0));
//         swarm.addHornet(new Hornet(tile, 8, 0));
//         a(swarm.sizeOfSwarm() == 4, "Size should be 4 after adding more hornets");

//         // Test Case 11: Heavy randomized trials
//         randomizedTest(100, 1000);
//         System.out.println("All test cases passed!");
//     }

//     /**
//      * Run a heavy test on your SwarmOfHornets class!
//      * The test works by comparing your SwarmOfHornets class to the ground truth, a LinkedList.
//      *
//      * @param numTrials  total number of trials
//      * @param numActions total number of actions each trial
//      */
//     private static void randomizedTest(int numTrials, int numActions) {
//         // Here you can change the weight for each action (weighted random)

//         // The weights are in order:
//         // - add
//         // - remove by object
//         // - remove by index
//         // - get first
//         // - check size
//         int[] weights = {10, 1, 1, 1, 1};
//         int totalWeight = Arrays.stream(weights).sum();
//         while (numTrials-- > 0) {
//             Random random = new Random();
//             SwarmOfHornets swarm = new SwarmOfHornets();
//             LinkedList<Hornet> linkedList = new LinkedList<>();
//             try {
//                 for (int action = 0; action < numActions; action++)
//                     switch (linkedList.isEmpty() ? 0 : weighted(weights, totalWeight, random)) {
//                         case 0 -> {
//                             Hornet newHornet = new Hornet(tile, random.nextInt(100), 0);
//                             swarm.addHornet(newHornet);
//                             linkedList.add(newHornet);
//                         }
//                         case 1 -> {
//                             Hornet hornet = linkedList.get(random.nextInt(linkedList.size()));
//                             a(swarm.removeHornet(hornet) == (null != linkedList.remove(indexByReference(linkedList, hornet))), "Mismatch in removal");
//                         }
//                         case 2 -> {
                            
//                         }
//                         case 3 -> a(swarm.getFirstHornet() == linkedList.getFirst(), "Mismatch in first hornet");
//                         case 4 -> a(swarm.sizeOfSwarm() == linkedList.size(), "Mismatch in size");
//                     }
//             } catch (AssertionError exception) {
//                 System.out.println(exception.getMessage());
//                 System.out.println(Arrays.toString(linkedList.stream().mapToInt(Insect::getHealth).toArray()));
//                 System.out.println(Arrays.toString(Arrays.stream(swarm.getHornets()).mapToInt(Hornet::getHealth).toArray()));
//                 System.exit(0);
//             }
//             // Lastly, perform one sanity identity check
//             Hornet[] hornets = swarm.getHornets();
//             a(swarm.sizeOfSwarm() == linkedList.size(), "Mismatch in size");
//             a(swarm.sizeOfSwarm() == hornets.length, "Mismatch in reported size");
//             for (int i = 0; i < hornets.length; i++)
//                 a(hornets[i] == linkedList.get(i), "Identity mismatch at index " + i);
//         }
//         System.out.println("Heavy test completed!");
//     }

//     private static int weighted(int[] weights, int total, Random random) {
//         int r = random.nextInt(total), c = 0;
//         for (int i = 0; i < weights.length; c += weights[i++]) if (r < c) return i - 1;
//         return weights.length - 1;
//     }

//     private static void a(boolean condition, String message) {
//         if (!condition) throw new AssertionError("Assertion failed: " + message);
//     }

//     private static <T> int indexByReference(LinkedList<T> list, T target) {
//         int index = 0;
//         for (T currentObject : list) {
//             if (currentObject == target) return index;
//             index++;
//         }
//         return -1;
//     }
// }
