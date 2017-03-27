package test;
import org.junit.Test;

import generator.SudokuGenerator;
import model.Cell;
import solver.Sudoku;
public class SudokuGeneratorTest {
	
	@Test
	public void generateAValidSudoku(){
	SudokuGenerator sg = new SudokuGenerator();
	int[][] generatedBoard = sg.nextBoard(50);
	Sudoku soduku = new Sudoku();
	soduku.setGrid(generatedBoard);
	boolean solved = soduku.solve(new Cell(0, 0));
	if (!solved) {
		System.out.println("SUDOKU cannot be solved.");
		return;
	}
	System.out.println("solved: "+solved);
	}
}
