package game;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeBoard implements Board, Position {
    protected static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    protected final Cell[][] field;
    protected Cell turn;
    protected int rows, columns, koef;
    protected int drawCount;

    public TicTacToeBoard(int m, int n, int k) {
        field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.rows = m;
        this.columns = n;
        this.koef = k;
        this.drawCount = m * n;
    }

    public TicTacToeBoard GameInit(Scanner in) {
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
    }

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

    @Override
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
    }

    private boolean checkDraw() {
        return drawCount == 0;
    }

    public int min(int a, int b) {
        return Math.min(a, b);
    }


    public boolean checkWin(int m, int n, Cell trn) {
        int left, right, up, down;
        int cnt = 0;
        left = Math.max(n - koef, 0);
        right = Math.min(n + koef, columns);
        up = Math.max(m - koef, 0);
        down = Math.min(m + koef, rows);
        for (int i = left; i < right; i++) {
            if (field[m][i] == trn) {
                cnt++;
                if (cnt == koef) return true;
            } else {
                cnt = 0;
            }
        }
        cnt = 0;
        for (int i = up; i < down; i++) {
            if (field[i][n] == trn) {
                cnt++;
                if (cnt == koef) return true;
            } else {
                cnt = 0;
            }
        }
        cnt = 0;
        for (int i = m - min(n - left, m - up); i <= m + min(right - n - 1, down - m - 1); i++) {
            int j = i - m + n;
            if (field[i][j] == trn) {
                cnt++;
                if (cnt == koef) return true;
            } else {
                cnt = 0;
            }
        }
        cnt = 0;
        for (int i = m - min(right - n - 1, m - up); i <= m + min(n - left, down - m - 1); i++) {
            int j = n + m - i;
            if (field[i][j] == trn) {
                cnt++;
                if (cnt == koef) return true;
            } else {
                cnt = 0;
            }
        }
        return false;
    }

    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < rows
                && 0 <= move.getCol() && move.getCol() < columns
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("").append(System.lineSeparator());
        for (int r = 0; r < rows; r++) {
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell)).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
