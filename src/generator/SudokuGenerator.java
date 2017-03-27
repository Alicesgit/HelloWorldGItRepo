package generator;

import java.util.*;

import javax.swing.JOptionPane;

/**
 * The SudokuGenerator class creates a random standard (9x9) Sudoku board
 * through the use of backtracking techniques.
 */
public class SudokuGenerator {
	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 9;

	/**
	 * Constructor. Resets board to zeros
	 */
	public SudokuGenerator() {
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
	}

	public int[][] nextBoard(int difficulty) {
		System.out.println("go into nextBoard method");
		board = new int[BOARD_WIDTH][BOARD_HEIGHT];
		nextCell(0, 0);
		makeHoles(difficulty);
		return board;
	}

	/**
	 * Recursive method that attempts to place every number in a cell.
	 */
	public boolean nextCell(int x, int y) {
		System.out.println("x: " + x);
		System.out.println("y: " + y);

		int nextX = x;
		int nextY = y;
		System.out.println("nextX: " + nextX);
		System.out.println("nextY: " + nextY);

		int[] toCheck = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

		for (int i = top - 1; i > 0; i--) {
			current = r.nextInt(i);
			tmp = toCheck[current];
			toCheck[current] = toCheck[i];
			toCheck[i] = tmp;
		}

		for (int i = 0; i < toCheck.length; i++) {
			System.out.println("to check: " + toCheck[i]);
			if (legalMove(x, y, toCheck[i])) {
				board[x][y] = toCheck[i];

				if (x == 8) {
					if (y == 8)
						return true;
					else {
						nextX = 0;
						nextY = y + 1;
					}
				} else {
					nextX = x + 1;
				}
				if (nextCell(nextX, nextY))
					 System.out.println("CCnext Y: "+nextY);
					System.out.println("CCnext x: "+nextX);
					 System.out.println("CC Y: "+y);
						System.out.println("CC x: "+x);
					return true;
			}
		}
		board[x][y] = 0;
		System.out.println("here it is backtrack");
		System.out.println("here it is backtrack, x:" + x);
		System.out.println("here it is backtrack, y:" + y);
		System.out.println("next x: "+nextX);
		System.out.println("next Y: "+nextY);
		return false;
	}

	/**
	 * Given a cell's coordinates and a possible number for that cell, determine
	 * if that number can be inserted into said cell legally
	 */
	private boolean legalMove(int x, int y, int current) {
		System.out.println("x: " + x);
		System.out.println("y: " + y);
		System.out.println("currentValue FOR CELL: " + current);
		boolean isLegal = false;
		for (int i = 0; i < 9; i++) {
			if (current == board[x][i])
				return isLegal = false;
		}
		for (int i = 0; i < 9; i++) {
			if (current == board[i][y])
				return isLegal = false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if (x > 2)
			if (x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if (y > 2)
			if (y > 5)
				cornerY = 6;
			else
				cornerY = 3;

		for (int i = cornerX; i < 10 && i < cornerX + 3; i++)
			for (int j = cornerY; j < 10 && j < cornerY + 3; j++)
				if (current == board[i][j])
					return isLegal = false;
		isLegal = true;
		return isLegal;

	}

	/**
	 * Given a completed board, replace a given amount of cells with 0s (to
	 * represent blanks)
	 */
	public void makeHoles(int holesToMake) {
		/*
		 * We define difficulty as follows: Easy: 32+ clues (49 or fewer holes)
		 * Medium: 27-31 clues (50-54 holes) Hard: 26 or fewer clues (54+ holes)
		 * This is human difficulty, not algorighmically (though there is some
		 * correlation)
		 */
		double remainingSquares = 81;
		double remainingHoles = (double) holesToMake;

		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++) {
				double holeChance = remainingHoles / remainingSquares;
				if (Math.random() <= holeChance) {
					board[i][j] = 0;
					remainingHoles--;
				}
				remainingSquares--;
			}
		printArray(board);
	}

	public static void printArray(int[][] num1) {
		String output = "";
		for (int x = 0; x < num1.length; x++) {
			output += Arrays.toString(num1[x]) + "\n";
		}
		JOptionPane.showMessageDialog(null, output, "Generated Sudoku", JOptionPane.INFORMATION_MESSAGE);
	}

	public void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				System.out.print(board[i][j] + "  ");
		}
	}

	int[][] board;
}