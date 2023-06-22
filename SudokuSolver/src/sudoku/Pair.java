package sudoku;

public class Pair {
    int row, col;
    public Pair(int i, int j){
        this.row = i;
        this.col = j;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
