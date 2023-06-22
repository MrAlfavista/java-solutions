package gameRev;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Reversi {
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.W, "W",
            Cell.B, "B"
    );

    protected final Cell[][] field;
    protected Cell turn;
    protected int rows, columns, koef;
    protected int drawCount;

    public Reversi() {
        field = new Cell[8][8];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.B;
        this.rows = 8;
        this.columns = 8;
        this.drawCount = 64;
    }

    /*public Reversi GameInit(Scanner in) {
        System.out.println("Please enter game parameters in format: m, n, k");
        if (!in.hasNextInt()) {
            System.out.println("Invalid format, please try again");
            in.nextLine();
            return GameInit(in);
        }
        int m, n, k;
        m = in.nextInt();
        if (m <= 0) {
            System.out.println("m must be a positive number! Please Try again");
            in.nextLine();
            return GameInit(in);
        }
        if (!in.hasNextInt()) {
            System.out.println("Invalid format, please try again");
            in.nextLine();
            return GameInit(in);
        }
        n = in.nextInt();
        if (n <= 0) {
            System.out.println("n must be a positive number! Please Try again");
            in.nextLine();
            return GameInit(in);
        }
        if (!in.hasNextInt()) {
            System.out.println("Invalid format, please try again");
            in.nextLine();
            return GameInit(in);
        }
        k = in.nextInt();
        if (k <= 1 || (k > m && k > n)) {
            System.out.println("k must be greater than 1, and not greater than m or k. Please try again");
            in.nextLine();
            return GameInit(in);
        }
        return new TicTacToeBoard(m, n, k);
    }*/

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getColumns() {
        return this.columns;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    /*@Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            System.out.println("That's not the legal move. You lost.");
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        drawCount--;
        if (checkWin(move.getRow(), move.getCol(), turn)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }*/

    private boolean checkEnd() {
        int blackcounter = 0;
        int whitecounter = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(field[i][j]==Cell.W) {
                    whitecounter++;
                } else if(field[i][j] == Cell.B){
                    blackcounter++;
                }
            }
        }
        if(blackcounter == 0 || whitecounter == 0 || blackcounter+whitecounter == 64){
            return true;
        }
        return false;
    }

    public int min(int a, int b) {
        return Math.min(a, b);
    }


    public GameResult checkWin() {
        if(this.checkEnd()){
            int blackcounter = 0;
            int whitecounter = 0;
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(field[i][j]==Cell.W) {
                        whitecounter++;
                    } else if(field[i][j] == Cell.B){
                        blackcounter++;
                    }
                }
            }
            if(blackcounter>whitecounter) return GameResult.BLACKWIN;
            if(whitecounter>blackcounter) return GameResult.WHITEWIN;
            return GameResult.DRAW;
        }
        return GameResult.NOTHING;
    }

    public boolean validPos(int i, int j){
        return i >=0 && i < 8 && j >= 0 && j < 8;
    }

    public GameResult MakeMove(final Move move) {
        if(this.checkEnd()){
            return this.checkWin();
        }
        if(validPos(move.getCol() - 1, move.getRow() - 1)
                && field[move.getRow() - 1][move.getCol() - 1] == Cell.E) {
            int col = move.getCol() - 1;
            int row = move.getRow() - 1;
            if(validPos(row-1, col) && field[row-1][col] == move.getOppValue()){
                int i = 1;
                while(validPos(row-i, col) && field[row-i][col] == move.getOppValue()){
                    i++;
                }
                if(validPos(row-i, col)){
                    if(field[row-i][col] == move.getValue()){
                        int t = 1;
                        while(validPos(row-t, col) && field[row-t][col] == move.getOppValue()){
                            field[row-t][col] = move.getValue();
                            t++;
                        }
                    }
                }
            }

            if(validPos(row + 1, col) && field[row+1][col] == move.getOppValue()){
                int i = 1;
                while(validPos(row+i, col) && field[row+i][col] == move.getOppValue()){
                    i++;
                }
                if(validPos(row+i, col)){
                    if(field[row+i][col] == move.getValue()){
                        int t = 1;
                        while(validPos(row+t, col) && field[row+t][col] == move.getOppValue()){
                            field[row+t][col] = move.getValue();
                            t++;
                        }
                    }
                }
            }

            if(validPos(row, col-1) && field[row][col-1] == move.getOppValue()){
                int i = 1;
                while(validPos(row, col-i) && field[row][col-i] == move.getOppValue()){
                    i++;
                }
                if(validPos(row, col-i)){
                    if(field[row][col-i] == move.getValue()){
                        int t = 1;
                        while(validPos(row, col-t) && field[row][col-t] == move.getOppValue()){
                            field[row][col-t] = move.getValue();
                            t++;
                        }
                    }
                }
            }

            if(validPos(row, col+1) && field[row][col+1] == move.getOppValue()){
                int i = 1;
                while(validPos(row, col+i) && field[row][col+i] == move.getOppValue()){
                    i++;
                }
                if(validPos(row, col+i)){
                    if(field[row][col+i] == move.getValue()){
                        return true;
                    }
                }
            }

            if(validPos(row-1, col-1) && field[row-1][col-1] == move.getOppValue()){
                int i = 1;
                while(validPos(row-i, col-i) && field[row-i][col-i] == move.getOppValue()){
                    i++;
                }
                if(validPos(row-i, col-i)){
                    if(field[row-i][col-i] == move.getValue()){
                        return true;
                    }
                }
            }

            if(validPos(row-1, col+1) && field[row-1][col+1] == move.getOppValue()){
                int i = 1;
                while(validPos(row-i, col+i) && field[row-i][col+i] == move.getOppValue()){
                    i++;
                }
                if(validPos(row-i, col+i)){
                    if(field[row-i][col+i] == move.getValue()){
                        return true;
                    }
                }
            }

            if(validPos(row+1, col+1) && field[row+1][col+1] == move.getOppValue()){
                int i = 1;
                while(validPos(row+i, col+i) && field[row+i][col+i] == move.getOppValue()){
                    i++;
                }
                if(validPos(row+i, col+i)){
                    if(field[row+i][col+i] == move.getValue()){
                        return true;
                    }
                }
            }

            if(validPos(row+1, col-1) && field[row+1][col-1] == move.getOppValue()){
                int i = 1;
                while(validPos(row+i, col-i) && field[row+i][col-i] == move.getOppValue()){
                    i++;
                }
                if(validPos(row+i, col-i)){
                    if(field[row+i][col-i] == move.getValue()){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void empty(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                field[i][j]= Cell.E;
            }
        }
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("").append(System.lineSeparator());
        for (int r = 0; r < 8; r++) {
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell)).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
