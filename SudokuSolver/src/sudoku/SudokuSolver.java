package sudoku;

import java.util.Scanner;

public class SudokuSolver {
    private final int[][] sudokuBoard = new int[9][9];
    private final int[][] solvedBoard = new int[9][9];


    public void gameInit() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard[i][j] = 0;
            }
        }

        String k;
        Scanner in = new Scanner(System.in);
        System.out.println("Введите таблицу ссудоку построчно, " +
                "в каждой строке по 9 чисел (пустые клетки обозначаются нулями) Если в ответах выдаст нули, значит судоку нерешаемо");
        for (int i = 0; i < 9; i++) {
            if (in.hasNextLine()) {
                k = in.next();
                if(k.length() != 9){
                    throw new IllegalArgumentException("Invalid input format");
                }
                for (int j = 0; j < 9; j++) {
                    if (k.charAt(j) >= '0' && k.charAt(j) <= '9' && isValid(i, j, k.charAt(j) - '0', sudokuBoard)) {
                        this.sudokuBoard[i][j] = k.charAt(j) - '0';
                    }
                }
            } else {
                throw new IllegalArgumentException("Invalid number of raws, please try again");
            }
        }
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(sudokuBoard[i][j]<0||sudokuBoard[i][j]>9){
                    throw new IllegalArgumentException("Invalid input format");
                }
            }
        }
    }


    public boolean isValid(int row, int col, int value, int[][] board) {
        if (board[row][col] != 0) return false;
        for (int k = 0; k < 9; k++) {
            if (board[row][k] == value) return false;
            if (board[k][col] == value) return false;
        }
        int rawOfSquare = (row / 3) * 3;
        int colOfSquare = (col / 3) * 3;
        for (int squareRaw = rawOfSquare; squareRaw < rawOfSquare + 3; squareRaw++) {
            for (int squareColl = colOfSquare; squareColl < colOfSquare + 3; squareColl++) {
                if (board[squareRaw][squareColl] == value) return false;
            }
        }


        return true;
    }

    public Pair setWeights(int[][] board) {
        int cur = 0;
        int max = 0;
        int row = -1;
        int col = -1;
        Pair answer = new Pair(0, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0) continue;
                for (int k = 0; k < 9; k++) {
                    if (board[i][k] != 0) cur++;
                    if (board[k][j] != 0) cur++;
                }
                int rawOfSquare = (i / 3) * 3;
                int colOfSquare = (j / 3) * 3;
                for (int raww = rawOfSquare; raww < rawOfSquare + 3; raww++) {
                    for (int coll = colOfSquare; coll < colOfSquare + 3; coll++) {
                        if (board[raww][coll] != 0) cur++;
                    }
                }
                if (cur > max) {
                    max = cur;
                    row = i;
                    col = j;
                }
                cur = 0;
            }
        }
        answer.setCol(col);
        answer.setRow(row);
        return answer;
    }

    public boolean solve(int[][] board, boolean flag) {
        Pair cage = setWeights(board);

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    sum++;
                }
            }
        }
        if (sum == 0) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    this.solvedBoard[i][j] = board[i][j];
                }
            }
            return true;
        }
        if (sum == 81) {
            cage.row = 0;
            cage.col = 0;
        } else {
            if (cage.row == -1 || cage.col == -1) {
                return false;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (isValid(cage.row, cage.col, i, board)) {
                board[cage.row][cage.col] = i;
                if(flag) System.out.println(toOutput(board));
                if (solve(board, flag)) {
                    return true;
                }
                board[cage.row][cage.col] = 0;
            }
        }
        return false;

    }

    public String toOutput(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
                sb.append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String answerToString(boolean seeStages) {
        this.gameInit();
        solve(sudokuBoard, seeStages);
        return this.toOutput(solvedBoard);
    }

}
