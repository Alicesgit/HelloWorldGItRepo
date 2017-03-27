package manager;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import generator.SudokuGenerator;
import model.Cell;
import model.SudokuProblem;
import model.SudokuProblemList;
import solver.Sudoku;
import utils.ReadAllFromFile;

public class SudokuTaskManager {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public SudokuTaskManager() {
		prepareGUI();
	}

	public static void main(String[] args) {
		SudokuTaskManager swingControlDemo = new SudokuTaskManager();
		swingControlDemo.showButtonDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Sudoku Manager");
		mainFrame.setSize(300, 400);
		mainFrame.setLayout(new GridLayout(3, 8));

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(300, 400);

		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showButtonDemo() {
		headerLabel.setText("Sudoku Task Manager");
		JButton problemsButton = new JButton("Read problems from chosed file");
		JButton resultsButton = new JButton("Solve problems from chosed file");
		JButton generatorButton = new JButton("Generate Problems");
		generatorButton.setHorizontalTextPosition(SwingConstants.LEFT);

		generatorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Display generated sudoku.");
				int chosedLevel = userChooseLevel();
				SudokuGenerator sg = new SudokuGenerator();
				int[][] generatedBoard = sg.nextBoard(chosedLevel);
				printArray(generatedBoard);
				statusLabel.setText("");
			}
		});
		problemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Display sudoku problems");
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						printOutProblems(file);
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		resultsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Display results");
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						printOutResults(file);
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		generatorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("Display generated sudoku.");
				int chosedLevel = userChooseLevel();
				SudokuGenerator sg = new SudokuGenerator();
				int[][] generatedBoard = sg.nextBoard(chosedLevel);
				printArray(generatedBoard);
				statusLabel.setText("");
			}
		});
		controlPanel.add(problemsButton);
		controlPanel.add(resultsButton);
		controlPanel.add(generatorButton);
		mainFrame.setVisible(true);
	}

	public int userChooseLevel() {
		final String[] levels = { "Easy", "Medium", "Hard", "Samurai" };
		String favoriteLevel = (String) JOptionPane.showInputDialog(null, "What is your level?", "Favorite level",
				JOptionPane.QUESTION_MESSAGE, null, levels, levels[0]);

		int currentLevel = 0;
		if ((favoriteLevel != null) && (favoriteLevel.length() > 0)) {
			switch (favoriteLevel) {
			case "Easy":
				currentLevel = 40;
				break;
			case "Medium":
				currentLevel = 49;
				break;
			case "Hard":
				currentLevel = 54;
				break;
			case "Samurai":
				currentLevel = 57;
				break;
			default:
				currentLevel = -1;
				break;
			}

		}
		return currentLevel;
	}

	public void printOutResults(File file) throws URISyntaxException, UnsupportedEncodingException {

		SudokuProblemList sudokuProblemList = ReadAllFromFile.getAllProblemsFromFile(file);
		List<SudokuProblem> problemList = sudokuProblemList.getProblemLists();
		int problemCount = problemList.size();
		for (int i = 0; i < problemCount; i++) {
			int grid[][] = problemList.get(i).getProblem();
			Sudoku soduku = new Sudoku();
			soduku.setGrid(grid);
			boolean solved = soduku.solve(new Cell(0, 0));
			if (!solved) {
				System.out.println("SUDOKU cannot be solved.");
			}
			printArray(grid);
		}
	}
	
	public void printOutProblems(File file) throws URISyntaxException, UnsupportedEncodingException {

		SudokuProblemList sudokuProblemList = ReadAllFromFile.getAllProblemsFromFile(file);
		List<SudokuProblem> problemList = sudokuProblemList.getProblemLists();
		int problemCount = problemList.size();
		for (int i = 0; i < problemCount; i++) {
			int grid[][] = problemList.get(i).getProblem();
			printArray(grid);
		}
	}

	public void printArray(int[][] num1) {
		String output = "";
		for (int x = 0; x < num1.length; x++) {
			output += Arrays.toString(num1[x]) + "\n";
		}
		JOptionPane.showMessageDialog(null, output, "Sudoku", JOptionPane.INFORMATION_MESSAGE);
	}
}
