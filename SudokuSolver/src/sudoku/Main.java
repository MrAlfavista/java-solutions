package sudoku;

public class Main {
    public static void main(String[] args){
        SudokuSolver solv = new SudokuSolver();
        System.out.println(solv.answerToString(true));
        /* Пример ввода:
        403002000
        500060120
        900000004
        008070000
        000203008
        036000700
        070920000
        000005096
        000804500
         */
    }
}
