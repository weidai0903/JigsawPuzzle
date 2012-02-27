package jigsaw;
/**
 * Check the solution
 * @author Song Gao,Wei Dai
 * 
 */
public class SolutionChecker {
	/**
	 * Checks the completed puzzle (the two-dimensional array) to make sure that all the pieces in it have been placed correctly
	 * @param solution
	 * @return boolean 
	 */
	public boolean isCorrectlyAssembled(PuzzlePiece[][] solution) {
		for(int i = 0; i < solution.length; i ++) {
			for(int j = 0; j < solution[0].length; j++) {
				if ( i == 0 ) {
					if(solution[i][j].getTop() != 0)	return false;
				}
				if ( i == solution.length-1) {
					if(solution[i][j].getBottom() != 0)		return false;
				}
				if ( j == 0) {
					if(solution[i][j].getLeft() != 0)		return false;
				}
				if ( j == solution[0].length-1) {
					if(solution[i][j].getRight() != 0)		return false;
				}
				if( i == solution.length-1 && j != solution[0].length-1) {
					if(solution[i][j].getRight() != solution[i][j+1].getLeft())
						return false;
				}
				if( j == solution[0].length-1 && i != solution.length-1) {
					if(solution[i][j].getBottom() != solution[i+1][j].getTop())
						return false;
				}
				if( i < solution.length-1 && j < solution[0].length-1) {
					if(solution[i][j].getRight() != solution[i][j+1].getLeft())
						return false;
					if(solution[i][j].getBottom() != solution[i+1][j].getTop())
						return false;
				}
			}	
		}
		return true;
	}
	

}
