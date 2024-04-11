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
		for(ArrayList<Integer> i: {1, 2}){
			for(int)
		}
		return 0;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal) 
		+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}
