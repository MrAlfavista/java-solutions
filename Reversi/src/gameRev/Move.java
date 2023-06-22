package gameRev;

public class Move {
    private final int row;
    private final int col;
    private final Cell value;

    public Move(int row, int col, Cell value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getValue() {
        return value;
    }

    public Cell getOppValue(){
        if(this.getValue() == Cell.W) {
            return Cell.B;
        } else {
            if(this.getValue() == Cell.B){
                return Cell.W;
            }
        }
        return Cell.E;
    }

    @Override
    public String toString() {
        return String.format("Move(%s, %d, %d)", value, row + 1, col + 1);
    }
}
