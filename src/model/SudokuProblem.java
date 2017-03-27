package model;

public class SudokuProblem {
	String problemId;
	
	int [][] problem;
	
	public SudokuProblem(int[][] currentSudoku) {
		this.problem = currentSudoku;
	}
	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public int[][] getProblem() {
		return problem;
	}
	public void setProblem(int[][] problem) {
		this.problem = problem;
	}
	
}
