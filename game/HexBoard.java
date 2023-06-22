package game;

import java.util.Arrays;
import java.util.Map;

public class HexBoard extends TicTacToeBoard {

    public HexBoard(int k) {
        super(11, 11, k);
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

    public String toString() {
        final StringBuilder sb = new StringBuilder().append(System.lineSeparator());
        for (int r = 0; r < rows; r++) {
            for (int i = 0; i <= r; i++) {
                sb.append(" ");
            }
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell)).append("  ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
