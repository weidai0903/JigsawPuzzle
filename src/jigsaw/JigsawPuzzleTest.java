package jigsaw;
import java.util.Random;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * TestCases for Jigsaw Puzzle Project
 * @author Wei Dai,Song Gao
 * 
 */
public class JigsawPuzzleTest {
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testLastThreeDigits() {
		assertEquals(PuzzlePiece.lastThreeDigits(12345678),678);
		assertEquals(PuzzlePiece.lastThreeDigits(0),0);
		assertEquals(PuzzlePiece.lastThreeDigits(-12345),345);
		assertEquals(PuzzlePiece.lastThreeDigits(-25),25);
		
	}
	@Test
	public void testToString() {
		PuzzlePiece pp=new PuzzlePiece(123456,2,33,111111);
		assertEquals(pp.toString(),"[456 002 033 111]");
		pp=new PuzzlePiece(0000,22222,333333,111111);
		assertEquals(pp.toString(),"[000 222 333 111]");
		pp=new PuzzlePiece(0000,-22222,333333,-111111);
		assertEquals(pp.toString(),"[000 222 333 111]");
	}
	
	@Test
	public void testShuffle() {
		Integer number[]=new Integer[10];
		for(int i=0;i<10;i++) {
			number[i]=i;
		}
		PuzzleCreator.shuffle(number);
		int i;
		for(i=0;i<10;i++) {
			if(number[i]!=i) {
				break;
			}
		}
		if(i==10) {
			fail("All the objects are equal to the old ones!");
		}
		int count[]=new int[10];
		for(int j=0;j<10;j++) {
			for(int k=0;k<10;k++) {
				if(number[j]==number[k]) {
					count[j]++;
				}
			}
		}
		int expected[]= {1,1,1,1,1,1,1,1,1,1};
		assertArrayEquals(expected,count);
		
		PuzzlePiece pp[]=new PuzzlePiece[10];
		for(int j=0;j<pp.length;j++) {
			pp[j]=new PuzzlePiece(j,j,j,j);
		}
		PuzzleCreator.shuffle(pp);
		boolean same=true;
		for(int j=0;j<pp.length;j++) {
			if(pp[j].toString()!="[j,j,j,j]") {
				same=false;
				break;
			}
		}
		if(same==true) {
			fail("All the objects are equal to the old ones!");
		}
		int countPiece[]= new int[pp.length];
		for(int j=0;j<pp.length;j++) {
			for(int k=0;k<pp.length;k++) {
				if(pp[j].toString().equals(pp[k].toString())){
					countPiece[j]++;
				}
			}
		}
		int expectedPiece[]=new int[pp.length];
		for(int j=0;j<pp.length;j++) {
			expectedPiece[j]=1;
		}
		assertArrayEquals(expectedPiece,countPiece);
	}
	
	@Test 
	public void testFindPiece() {
		PuzzlePiece pp[]=new PuzzlePiece[10];
		for (int i=0;i<10;i++) {
			pp[i]=new PuzzlePiece(i,i,i,i);
		}
		PuzzleSolver ps=new PuzzleSolver();
		assertEquals(ps.findPiece(1,1,pp).toString(),"[001 001 001 001]");	
		assertEquals(ps.findPiece(2,2,pp).toString(),"[002 002 002 002]");
		assertEquals(ps.findPiece(7,7,pp).toString(),"[007 007 007 007]");	
	}
	
	@Test
	public void testIsCorrectlyAssembled() {
		
		Random rnd=new Random();
		for(int checkTimes=0;checkTimes<1000;checkTimes++) {
			int rows=1+rnd.nextInt(20);
			int columns=1+rnd.nextInt(20);
			PuzzlePiece pp2d[][]=new PuzzlePiece[rows][columns];
			PuzzlePiece pp[]=new PuzzlePiece[rows*columns];
			PuzzleCreator pc=new PuzzleCreator();
			pp=pc.create(rows, columns);
			for(int i=0;i<rows*columns;i++) {
				pp2d[i/columns][i%columns]=pp[i];
			}
			//jp.print(pp2d);
			
			SolutionChecker sc=new SolutionChecker();
			if(rows==1 && columns==1) {
				assertEquals(true,sc.isCorrectlyAssembled(pp2d));
			}
			else {
				assertEquals(false,sc.isCorrectlyAssembled(pp2d));
			}
			PuzzleSolver ps=new PuzzleSolver();
			pp2d=ps.solve(rows, columns, pp);
			assertEquals(true,sc.isCorrectlyAssembled(pp2d));
			//jp.print(pp2d);
		}
	}

}
