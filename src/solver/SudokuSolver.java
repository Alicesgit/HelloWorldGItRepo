package solver;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import model.Cell;
import model.SudokuProblem;
import model.SudokuProblemList;
import utils.ReadAllFromFile;

public class SudokuSolver {
	public static void printOutResults(File file) throws URISyntaxException, UnsupportedEncodingException {
	
	    SudokuProblemList sudokuProblemList = ReadAllFromFile.getAllProblemsFromFile(file);
		List<SudokuProblem>problemList=sudokuProblemList.getProblemLists();
		int problemCount = problemList.size();
		
		for(int i=0;i<problemCount;i++){
			int grid [][]=problemList.get(i).getProblem();
			Sudoku soduku = new Sudoku();
			soduku.setGrid(grid);
			boolean solved = soduku.solve(new Cell(0, 0));
			if (!solved) {
				System.out.println("SUDOKU cannot be solved.");
				return;
			}
			System.out.println("SOLUTION\n");
			printArray(grid);
		}
	}
	
	public static void printOutProblems(File file) throws URISyntaxException, UnsupportedEncodingException {
		
	    SudokuProblemList sudokuProblemList = ReadAllFromFile.getAllProblemsFromFile(file);
		List<SudokuProblem>problemList=sudokuProblemList.getProblemLists();
		int problemCount = problemList.size();
		
		for(int i=0;i<problemCount;i++){
			int grid [][]=problemList.get(i).getProblem();
			printArray(grid);
		}
	}

	public static void printArray(int[][] num1) {
		String output = "";
		for (int x = 0; x < num1.length; x++) {
			output += Arrays.toString(num1[x]) + "\n";
		}
		JOptionPane.showMessageDialog(null, output, "Generated Sudoku", JOptionPane.INFORMATION_MESSAGE);
	}
}
