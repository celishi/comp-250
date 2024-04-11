package assignment3;

public class testing {
    public static void main(String[] args) {
        // Block blockDepth3 = new Block(0,2);
        // blockDepth3.printColoredBlock();     
        Block b = new Block(0, 2);
        b.updateSizeAndPosition(16, 0, 0);

        BlobGoal g = new BlobGoal(GameColors.YELLOW);
        int x = g.undiscoveredBlobSize(1, 1, b.flatten(), new boolean[4][4]);
        System.out.println(x);
    }
}
