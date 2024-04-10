package assignment3;

public class testing {
    public static void main(String[] args) {
        Block blockDepth3 = new Block(0,3);
        blockDepth3.updateSizeAndPosition(16, 0, 0);
        Block b1 = blockDepth3.getSelectedBlock(2, 15, 1);
        b1.printBlock();

    }
}
