package assignment3;

import java.awt.Color;
import java.util.ArrayList;

public class PerimeterGoal extends Goal{

	public PerimeterGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		Color[][] flat = board.flatten();
		int count = 0;
		int sz = flat.length - 1;

		for(int y=0; y<flat.length; y++){
			if(flat[0][y] == targetGoal){
				count++;
			}
			if(flat[sz][y] == targetGoal && sz != 0){
				count++;
			}
		}
		for(int x=0; x<flat.length; x++){
			if(flat[x][0] == targetGoal){
				count++;
			}
			if(flat[x][sz] == targetGoal && sz != 0){
				count++;
			}
		}
		return count;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal) 
		+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}
