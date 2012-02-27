package jigsaw;
/**
 * Solve the puzzle
 * @author Wei Dai, Song Gao
 */
public class PuzzleSolver {
	/**
	 * Solve the puzzle
	 * @param rows
	 * @param columns
	 * @param pieces
	 * @return PuzzlePiece[][]
	 */
	
	public PuzzlePiece[][] solve(int rows, int columns, PuzzlePiece[] pieces){
		PuzzlePiece solvedPuzzle[][]=new PuzzlePiece[rows][columns];
		solvedPuzzle[0][0]=findPiece(0,0,pieces);
		long top,left;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				if(i==0 && j==0) {
					top=0;
					left=0;
				}
				else if(i==0 &&j!=0) {
					top=0;
					left=solvedPuzzle[i][j-1].getRight();
				}
				else if(i!=0 && j==0) {
					top=solvedPuzzle[i-1][j].getBottom();
					left=0;
				}
				else{
					top=solvedPuzzle[i-1][j].getBottom();
					left=solvedPuzzle[i][j-1].getRight();
				}
				solvedPuzzle[i][j]=findPiece(top,left,pieces);
			}
		}
		return solvedPuzzle;
		
	}
	/**
	 * Searches the one-dimensional array of pieces for a piece whose top edge is topWanted and whose left edge is leftWanted. 
	 * @param topWanted
	 * @param leftWanted
	 * @param pieces
	 * @return PuzzlePiece
	 */
	PuzzlePiece findPiece(long topWanted, long leftWanted, PuzzlePiece[] pieces) {
		for(int i=0;i<pieces.length;i++) {
			if(pieces[i].getTop()==topWanted && pieces[i].getLeft()==leftWanted) {
				return pieces[i];
			}
		}
		throw new RuntimeException("The puzzle being searched for is ["+topWanted+' '+leftWanted+"].Not found.");
	}

}
