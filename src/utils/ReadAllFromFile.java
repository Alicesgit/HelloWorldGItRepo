package utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import model.SudokuProblem;
import model.SudokuProblemList;

public class ReadAllFromFile {

	public static SudokuProblemList getAllProblemsFromFile(File file2)
			throws URISyntaxException, UnsupportedEncodingException {

		// read from flie and store in list of Strings, one String per line
		List<String> lines;
		List<String> trimmedLines = new ArrayList<String>();
		try {
			lines = FileUtils.readLines(file2, "UTF-8");

			for (String line : lines) {
				if (!line.contains(":") && !line.equals(null) && !line.isEmpty()) {
					String replacedTrimmedLine = line.replace('.', '0');
					trimmedLines.add(replacedTrimmedLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		SudokuProblemList sudokuList = new SudokuProblemList();
		List<SudokuProblem> list = new ArrayList<SudokuProblem>();
		int countOfSudoku = trimmedLines.size() / 9;

		for (int n = 0; n < countOfSudoku; n++) {

			int[][] currentSudoku = new int[9][9];
			for (int i = 0; i < 9; i++) {
				String currentString = trimmedLines.get(i + 9 * n);
				for (int j = 0; j < 9; j++) {
					char currentChar = currentString.toCharArray()[j];
					int currentInt = Character.getNumericValue(currentChar);
					currentSudoku[i][j] = currentInt;
				}
			}
			System.out.println("matrix: " + n);
			printGrid(currentSudoku);
			SudokuProblem sdkProblem = new SudokuProblem(currentSudoku);

			list.add(sdkProblem);
		}
		sudokuList.setProblemLists(list);
		return sudokuList;
	}

	static void printGrid(int grid[][]) {
		int N = 9;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++)
				System.out.print(grid[row][col]);
			System.out.println();
		}
	}
}
