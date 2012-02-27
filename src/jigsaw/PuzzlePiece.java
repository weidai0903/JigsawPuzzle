package jigsaw;
import java.util.Random;
import java.text.DecimalFormat;
import java.lang.Math;
/**
 *This class describes a single piece of the puzzle. It holds four private long instance variables to describe the four sides of the puzzle piece.
 *@author Wei Dai,Song Gao
 */
public class PuzzlePiece {
	private long top,left,right,bottom;
	final long RANDOM = -1;
	
	public PuzzlePiece(long top, long left, long right, long bottom) {
		
		this.top=randomNum(top);
		this.left=randomNum(left);
		this.right=randomNum(right);
		this.bottom=randomNum(bottom);
		
	}
	private long randomNum(long fourSides) {
		Random rnd=new Random();
		if(fourSides==RANDOM) {
			return rnd.nextLong();
		}
		else {
			return fourSides;
		}
	}

	public long getTop() {
		return top;
	}
	
	public long getLeft() {
		return left;
	}
	
	public long getRight() {
		return right;
	}
	
	public long getBottom() {
		return bottom;
	}
	/**
	 * Returns, as an integer, the last three digits of the provided long number
	 * @param Number
	 * @return int
	 */
	public static int lastThreeDigits(long Number) {	
		return (int)(Math.abs(Number)%1000);	
	}
	/**
	 * returns a description of this puzzle piece, in the form [top left right bottom].
	 */
	public String toString() {
		DecimalFormat threeDigits = new DecimalFormat("000");//format to three digits
		return "["+threeDigits.format(lastThreeDigits(top))+' '+threeDigits.format(lastThreeDigits(left))+' '+threeDigits.format(lastThreeDigits(right))+' '+threeDigits.format(lastThreeDigits(bottom))+"]";
	}

}
