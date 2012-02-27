package jigsaw;
import java.util.Random;
import java.util.ArrayList;
/**
 * The create method in this class will need to generate unique random numbers, for use as edge "shapes." 
 * @author Wei Dai,Song Gao
 *
 */
public class PuzzleCreator {
	static Random rnd=new Random();
	/**
	 * This method creates the puzzle as a two-dimensional array, but then shuffles and returns it as a one-dimensional array
	 * @param rows
	 * @param columns
	 * @return PuzzlePiece[]
	 */
	public PuzzlePiece[] create(int rows, int columns) {
		PuzzlePiece puzzle[][]=new PuzzlePiece[rows][columns];
		puzzle=create2d(rows,columns);
		PuzzlePiece puzzleArray[]=new PuzzlePiece[rows*columns];
		ArrayList<PuzzlePiece> puzzleArrayList=new ArrayList<PuzzlePiece>();
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				puzzleArrayList.add(puzzle[i][j]);
			}
		}
		puzzleArray=puzzleArrayList.toArray(puzzleArray);
		PuzzleCreator.shuffle(puzzleArray);
		return puzzleArray;

	}
	/**
	 * returns 2-dimension PuzzlePiece
	 * @param rows
	 * @param columns	
	 * @return PuzzlePiece[][]
	 */
	public PuzzlePiece[][] create2d(int rows,int columns){
		PuzzlePiece puzzle[][]=new PuzzlePiece[rows][columns];
		if(rows==1) {
			for(int i=0;i<columns;i++) {
				if(columns!=1) {
					if(i==0) {
						puzzle[0][i]=new PuzzlePiece(0,0,-1,0);
					}
					else if(i==columns-1) {
						puzzle[0][i]=new PuzzlePiece(0,puzzle[0][i-1].getRight(),0,0);
					}
					else {
						puzzle[0][i]=new PuzzlePiece(0,puzzle[0][i-1].getRight(),-1,0);
					}
				}
				else {
					puzzle[0][i]=new PuzzlePiece(0,0,0,0);
				}
			}
		}
		else if(columns==1 && rows>1) {
			for(int i=0;i<rows;i++) {
				if(i==0) {
					puzzle[i][0]=new PuzzlePiece(0,0,0,-1);
				}
				else if(i==rows-1) {
					puzzle[i][0]=new PuzzlePiece(puzzle[i-1][0].getBottom(),0,0,0);
				}
				else {
					puzzle[i][0]=new PuzzlePiece(puzzle[i-1][0].getBottom(),0,0,-1);
				}
			}
		}
		else {
			for(int i=0;i<rows;i++) {
				for(int j=0;j<columns;j++) {
					if(i==0 && j==0) {
						puzzle[i][j]=new PuzzlePiece(0,0,-1,-1);
					}
					else if(i==0 && j==columns-1) {
						puzzle[i][j]=new PuzzlePiece(0,puzzle[i][j-1].getRight(),0,-1);
					}
					else if(i==0 && j!=columns-1 && j!=0) {
						puzzle[i][j]=new PuzzlePiece(0,puzzle[i][j-1].getRight(),-1,-1);
					}
					else if(i!=0 && i!=rows-1 && j==0) {
						puzzle[i][j]=new PuzzlePiece(puzzle[i-1][j].getBottom(),0,-1,-1);
					}
					else if(i!=0 && i!=rows-1 && j==columns-1) {
						puzzle[i][j]=new PuzzlePiece(puzzle[i-1][j].getBottom(),puzzle[i][j-1].getRight(),0,-1);
					}
					else if(i!=0 && i!=rows-1 && j!=columns-1 &&j!=0) {
						puzzle[i][j]=new PuzzlePiece(puzzle[i-1][j].getBottom(),puzzle[i][j-1].getRight(),-1,-1);
					}
					else if(i==rows-1 && j!=columns-1 && j!=0) {
						puzzle[i][j]=new PuzzlePiece(puzzle[i-1][j].getBottom(),puzzle[i][j-1].getRight(),-1,0);
					}
					else if(i==rows-1 && j==0) {
						puzzle[i][j]=new PuzzlePiece(puzzle[i-1][j].getBottom(),0,-1,0);
					}
					else if(i==rows-1 && j==columns-1) {
						puzzle[i][j]=new PuzzlePiece(puzzle[i-1][j].getBottom(),puzzle[i][j-1].getRight(),0,0);
					}
				}
			}
		}
		return puzzle;
	}
	/**
	 * Randomizes a one-dimensional array.
	 * @param objects
	 */
	public static void shuffle(Object[] objects) {
		for(int i=objects.length-1;i>=1;i--) {
			int r=rnd.nextInt(i);
			Object temp=objects[i];
			objects[i]=objects[r];
			objects[r]=temp;
		}
	}

	
	

}
