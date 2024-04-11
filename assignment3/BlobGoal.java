package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal{

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		Color[][] unitCells = board.flatten();
		boolean[][] visited = new boolean[unitCells.length][unitCells.length];
		int output;
		int biggest = 0;

		for(int x=0;x<unitCells.length;x++){
			for(int y=0;y<unitCells.length;y++){
				output = this.undiscoveredBlobSize(x, y, unitCells, visited);
				if(output>biggest){
					biggest = output;
				}
			}
		}
		return biggest;
	}

	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal) 
		+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		int count = 0;
		if(unitCells[i][j] == targetGoal && !visited[i][j]){
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
		else{
			return 0;
		}
		return count;

	}

}
