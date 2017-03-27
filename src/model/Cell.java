package model;

/**
 * Class to abstract the representation of a cell. Cell => (x, y)
 */
 public class Cell {

 public int row;
 public int col;

 public Cell(int row, int col) {
  super();
  this.row = row;
  this.col = col;
 }

 @Override
 public String toString() {
  return "Cell [row=" + row + ", col=" + col + "]";
 }
};
