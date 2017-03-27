package model;

import java.util.ArrayList;
import java.util.List;

public class SudokuProblemList {
	List<SudokuProblem> problemLists = new ArrayList<SudokuProblem>();

	public List<SudokuProblem> getProblemLists() {
		return problemLists;
	}

	public void setProblemLists(List<SudokuProblem> problemLists) {
		this.problemLists = problemLists;
	}
}
