package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal{

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		/*
		 * ADD YOUR CODE HERE
		 */
		return 0;
	}

	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal) 
		+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		int count = 0;
		if(unitCells[i][j] == targetGoal){
			if(!visited[i][j]){
				count++;
				visited[i][j] = true;
			}

			if(i+1<unitCells.length){
				count += undiscoveredBlobSize(i+1, j, unitCells, visited);
			}
			if(i-1>=0){
				count += undiscoveredBlobSize(i-1, j, unitCells, visited);
			}
			if(j+1<unitCells.length){//its a square so dont need to do unitCells[0].length
				count += undiscoveredBlobSize(i, j+1, unitCells, visited);
			}
			if(j-1>=0){
				count += undiscoveredBlobSize(i, j-1, unitCells, visited);
			}
		}
		else return 0;
		return count;

	}

}
