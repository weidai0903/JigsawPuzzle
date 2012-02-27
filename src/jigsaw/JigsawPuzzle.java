package jigsaw;

/**
 * CIT591 Assignment 10: Jigsaw Puzzle
 *@author Wei Dai,Song Gao
 *
 */
public class JigsawPuzzle {
	
	/**
	 * Call the program with command-line arguments: args[0] as the number of rows, and args[1] as the number of columns.
	 * create a puzzle£¬print it (as a list of puzzle pieces)£¬solve the puzzle.
	 * @param args
	 */
	public static void main(String[] args) {
		int rows=-1,columns=-1;
		boolean rightArgs=true;
		PuzzleCreator pc=new PuzzleCreator();
		if(args.length!=2) {
			rightArgs=false;
		}
		try {
			rows=Integer.parseInt(args[0]);
			columns=Integer.parseInt(args[1]);
		}
		catch(Exception e){
			rightArgs=false;
		}
		if(rows<=0 || columns<=0) {
			rightArgs=false;
		}
		if(rightArgs==false) {
			System.out.println("Invalid input!");
		}
		else {
			PuzzlePiece puzzle[]=new PuzzlePiece[rows*columns];
			PuzzlePiece solvedPuzzle[][]=new PuzzlePiece[rows][columns];
			//create a puzzle
			puzzle=pc.create(rows, columns);
			//print it as a list of puzzle pieces
			System.out.println("Jigsaw Puzzle:");
			for(int i=0;i<rows*columns;i++) {
				System.out.println(puzzle[i]);
			}
			System.out.println("Solution:");
			//use the PuzzleSolver to solve the puzzle
			PuzzleSolver ps=new PuzzleSolver();
			solvedPuzzle=ps.solve(rows, columns, puzzle);
			//ensure that the puzzle was solved correctly
			SolutionChecker sc=new SolutionChecker();
			if(sc.isCorrectlyAssembled(solvedPuzzle)==true) {
				JigsawPuzzle jp=new JigsawPuzzle();
				jp.print(solvedPuzzle);;
			}
			else {
				System.out.println("The puzzle is not solved correctly!");
			}	
		}
	}
	/**
	 * Prints the two-dimensional puzzle array
	 * @param puzzle
	 */
	public void print(PuzzlePiece[][] puzzle) {
		for(int i=0;i<puzzle.length*4+1;i++) {
			for(int j=0;j<puzzle[0].length*4+1;j++) {
				if(i%4==0 && j%4==0) {
					System.out.print("+");
				}
				else if(i%4==0 && j%4!=0) {
					System.out.print("---");
				}
				else if(i%2==1 && j%2==1) {
					System.out.print("   ");
				}
				else if(i%4==2 && j%4==2 ) {
					System.out.print("   ");
				}
				else if(i%4==1 && j%4==2){
					System.out.printf("%03d",PuzzlePiece.lastThreeDigits(puzzle[i/4][j/4].getTop()));
				}
				else if(i%4==2 && j%4==1) {
					System.out.printf("%03d",PuzzlePiece.lastThreeDigits(puzzle[i/4][j/4].getLeft()));
				}
				else if(i%4==2 && j%4==3) {
					System.out.printf("%03d",PuzzlePiece.lastThreeDigits(puzzle[i/4][j/4].getRight()));
				}
				else if(i%4==3 && j%4==2) {
					System.out.printf("%03d",PuzzlePiece.lastThreeDigits(puzzle[i/4][j/4].getBottom()));
				}
				else {
					System.out.print("|");
				}
			}
			System.out.println();
		}
		
	}


}
