package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Block {
	private int xCoord;
	private int yCoord;
	private int size; // height/width of the square
	private int level; // the root (outer most block) is at level 0
	private int maxDepth;
	private Color color;

	private Block[] children; // {UR, UL, LL, LR}

	public static Random gen = new Random(2);

	/*
	 * These two constructors are here for testing purposes.
	 */
	public Block() {
	}

	public Block(int x, int y, int size, int lvl, int maxD, Color c, Block[] subBlocks) {
		this.xCoord = x;
		this.yCoord = y;
		this.size = size;
		this.level = lvl;
		this.maxDepth = maxD;
		this.color = c;
		this.children = subBlocks;
	}

	/*
	 * Creates a random block given its level and a max depth.
	 * 
	 * xCoord, yCoord, size, and highlighted should not be initialized
	 * (i.e. they will all be initialized by default)
	 */
	public Block(int lvl, int maxDepth) {
		this.level = lvl;
		this.maxDepth = maxDepth;

		if (lvl < maxDepth) {
			double x = gen.nextDouble();
			if (x < Math.exp(-0.25 * lvl)) {
				Block[] subBlocks = {
					new Block(lvl + 1, maxDepth), new Block(lvl + 1, maxDepth), new Block(lvl + 1, maxDepth), new Block(lvl + 1, maxDepth)
				};
				this.color = null;
				this.children = subBlocks;
			} else {
				int y = gen.nextInt(4);
				this.color = GameColors.BLOCK_COLORS[y];
				this.children = new Block[0];
			}
		}
		else {
			int y = gen.nextInt(4);
			this.color = GameColors.BLOCK_COLORS[y];
			this.children = new Block[0];
		}
	}

	/*
	 * Updates size and position for the block and all of its sub-blocks, while
	 * ensuring consistency between the attributes and the relationship of the
	 * blocks.
	 * 
	 * The size is the height and width of the block. (xCoord, yCoord) are the
	 * coordinates of the top left corner of the block.
	 */
	public void updateSizeAndPosition(int size, int xCoord, int yCoord) {
		if(size <=0){
			throw new IllegalArgumentException("negative size");
		}

		this.size = size;
		this.xCoord = xCoord;
		this.yCoord = yCoord;

		if(this.color == null){
			if(size % 2 != 0){
				throw new IllegalArgumentException("cant divide size by 2");
			}
			this.children[0].updateSizeAndPosition(size/2, xCoord + size/2, yCoord);
			this.children[1].updateSizeAndPosition(size/2, xCoord, yCoord);
			this.children[2].updateSizeAndPosition(size/2, xCoord, yCoord + size/2);
			this.children[3].updateSizeAndPosition(size/2, xCoord + size/2, yCoord + size/2);
		}
	}

	/*
	 * Returns a List of blocks to be drawn to get a graphical representation of
	 * this block.
	 * 
	 * This includes, for each undivided Block:
	 * - one BlockToDraw in the color of the block
	 * - another one in the FRAME_COLOR and stroke thickness 3
	 * 
	 * Note that a stroke thickness equal to 0 indicates that the block should be
	 * filled with its color.
	 * 
	 * The order in which the blocks to draw appear in the list does NOT matter.
	 */
	public ArrayList<BlockToDraw> getBlocksToDraw() {
		ArrayList<BlockToDraw> list = new ArrayList<BlockToDraw>();
		if(this.color == null){
			for(int i=0; i<4; i++){
				list.addAll(this.children[i].getBlocksToDraw());
			}
		}
		else{
			list.add(new BlockToDraw(this.color, this.xCoord, this.yCoord, this.size, 0));
			list.add(new BlockToDraw(GameColors.FRAME_COLOR, this.xCoord, this.yCoord, this.size, 3));
		}
		return list;
	}

	/*
	 * This method is provided and you should NOT modify it.
	 */
	public BlockToDraw getHighlightedFrame() {
		return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
	}

	/*
	 * Return the Block within this Block that includes the given location
	 * and is at the given level. If the level specified is lower than
	 * the lowest block at the specified location, then return the block
	 * at the location with the closest level value.
	 * 
	 * The location is specified by its (x, y) coordinates. The lvl indicates
	 * the level of the desired Block. Note that if a Block includes the location
	 * (x, y), and that Block is subdivided, then one of its sub-Blocks will
	 * contain the location (x, y) too. This is why we need lvl to identify
	 * which Block should be returned.
	 * 
	 * Input validation:
	 * - this.level <= lvl <= maxDepth (if not throw exception)
	 * - if (x,y) is not within this Block, return null.
	 */
	public Block getSelectedBlock(int x, int y, int lvl) {
		boolean inRange = 
			x>= this.xCoord && x<= this.xCoord + this.size &&
			y>= this.yCoord && y<= this.yCoord + this.size;
		
		if(lvl > maxDepth || this.level > lvl){
			throw new IllegalArgumentException("level bigger than height");
		}

		if(inRange && this.color == null && lvl > this.level){
			int index = 0;
			int midX=this.xCoord+this.size/2;
			int midY=this.yCoord+this.size/2;

			if(x>=midX && y<midY){
				index = 0;
			}
			else if(x<midX && y<midY){
				index = 1;
			}
			else if(x<midX && y>=midY){
				index = 2;
			}
			else if(x>=midX && y>=midY){
				index = 3;
			}

			Block block = this.children[index].getSelectedBlock(x, y, lvl);
			return block;
		}
		else if(inRange && this.level <= lvl){
			return this;
		}
		return null;
	}

	/*
	 * Swaps the child Blocks of this Block.
	 * If input is 1, swap vertically. If 0, swap horizontally.
	 * If this Block has no children, do nothing. The swap
	 * should be propagate, effectively implementing a reflection
	 * over the x-axis or over the y-axis.
	 * 
	 */
	public void reflect(int direction) {
		if(direction != 0 && direction != 1){
			throw new IllegalArgumentException("invalid direction");
		}

		if(direction == 1 && this.color == null){
			swap(0, 1);
			swap(2,3);
		}
		else if(direction == 0 && this.color == null){
			swap(0, 3);
			swap(1, 2);
		}
		this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);

		if(this.color == null){
			for(int i=0; i<4; i++){
				if(this.children[i].color == null){
					this.children[i].reflect(direction);
				}
			}
		}
	}

	private void swap(int a, int b){
		Block temp = this.children[a];
		this.children[a] = this.children[b];
		this.children[b] = temp;
	}

	/*
	 * Rotate this Block and all its descendants.
	 * If the input is 1, rotate clockwise. If 0, rotate
	 * counterclockwise. If this Block has no children, do nothing.
	 */
	public void rotate(int direction) {
		if(direction != 0 && direction != 1){
			throw new IllegalArgumentException("invalid direction");
		}

		if(direction == 0 && this.color == null){
			swap(0, 1);
			swap(2,3);
			swap(2, 0);
		}
		else if(direction == 1 && this.color == null){
			swap(0, 3);
			swap(1, 0);
			swap(1, 2);
		}
		this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);

		if(this.color == null){
			for(int i=0; i<4; i++){
				if(this.children[i].color == null){
					this.children[i].rotate(direction);
				}
			}
		}
	}

	/*
	 * Smash this Block.
	 * 
	 * If this Block can be smashed,
	 * randomly generate four new children Blocks for it.
	 * (If it already had children Blocks, discard them.)
	 * Ensure that the invariants of the Blocks remain satisfied.
	 * 
	 * A Block can be smashed iff it is not the top-level Block
	 * and it is not already at the level of the maximum depth.
	 * 
	 * Return True if this Block was smashed and False otherwise.
	 * 
	 */
	public boolean smash() {
		if(this.level != 0 && this.level<maxDepth){
			this.children = new Block[]{
				new Block (this.level + 1, this.maxDepth), new Block (this.level + 1, this.maxDepth), new Block (this.level + 1, this.maxDepth), new Block (this.level + 1, this.maxDepth)
			};
			this.color = null;
			this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
			return true;
		}
		return false;
	}

	/*
	 * Return a two-dimensional array representing this Block as rows and columns of
	 * unit cells.
	 * 
	 * Return and array arr where, arr[i] represents the unit cells in row i,
	 * arr[i][j] is the color of unit cell in row i and column j.
	 * 
	 * arr[0][0] is the color of the unit cell in the upper left corner of this
	 * Block.
	 */
	public Color[][] flatten() {
		int sz = (int)Math.pow(2, this.maxDepth - this.level);
		Color[][] arr = new Color[sz][sz];

		if(this.color == null){
			Color[][][] child = new Color[4][][];
			for(int i=0; i<4; i++){
				child[i] = this.children[i].flatten();
			}
			for(int x=0; x<sz; x++) {
                for (int y=0; y<sz; y++) {
					if (x < sz/2 && y >= sz/2){
						arr[x][y] = child[0][x][y-sz/2];
					}
                    else if (x < sz/2 && y < sz/2){
						arr[x][y] = child[1][x][y];
					}
                    else if (x >= sz/2 && y < sz/2){
						arr[x][y] = child[2][x-sz/2][y];
					}
                    else if (x >= sz/2 && y >= sz/2){
						arr[x][y] = child[3][x-sz/2][y-sz/2];
					}
                }
            }
			return arr;
		}
		else{
			for(int x=0;x<sz;x++){
                for(int y=0;y<sz;y++){
                    arr[x][y]=this.color;
                }
			}
			return arr;
		}
	}

	// These two get methods have been provided. Do NOT modify them.
	public int getMaxDepth() {
		return this.maxDepth;
	}

	public int getLevel() {
		return this.level;
	}

	/*
	 * The next 5 methods are needed to get a text representation of a block.
	 * You can use them for debugging. You can modify these methods if you wish.
	 */
	public String toString() {
		return String.format("pos=(%d,%d), size=%d, level=%d", this.xCoord, this.yCoord, this.size, this.level);
	}

	public void printBlock() {
		this.printBlockIndented(0);
	}

	private void printBlockIndented(int indentation) {
		String indent = "";
		for (int i = 0; i < indentation; i++) {
			indent += "\t";
		}

		if (this.children.length == 0) {
			// it's a leaf. Print the color!
			String colorInfo = GameColors.colorToString(this.color) + ", ";
			System.out.println(indent + colorInfo + this);
		} else {
			System.out.println(indent + this);
			for (Block b : this.children)
				b.printBlockIndented(indentation + 1);
		}
	}

	private static void coloredPrint(String message, Color color) {
		System.out.print(GameColors.colorToANSIColor(color));
		System.out.print(message);
		System.out.print(GameColors.colorToANSIColor(Color.WHITE));
	}

	public void printColoredBlock() {
		Color[][] colorArray = this.flatten();
		for (Color[] colors : colorArray) {
			for (Color value : colors) {
				String colorName = GameColors.colorToString(value).toUpperCase();
				if (colorName.length() == 0) {
					colorName = "\u2588";
				} else {
					colorName = colorName.substring(0, 1);
				}
				coloredPrint(colorName, value);
			}
			System.out.println();
		}
	}

}
