package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        ((Board) position).makeMove (new Move (2, 2, Cell.X));
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        if (!in.hasNextInt()) {
            System.out.println("Not valid format! Please try again");
            in.nextLine();
            return makeMove(position);
        }
        int m = in.nextInt() - 1;
        if (!in.hasNextInt()) {
            System.out.println("Not valid format! Please try again");
            in.nextLine();
            return makeMove(position);
        }
        int n = in.nextInt() - 1;
        Move move = new Move(m, n, position.getTurn());
        if (!position.isValid(move)) {
            System.out.println("Tried to cheat! Not today! GET BUSTED");
            return move;
        } else {
            return new Move(m, n, position.getTurn());
        }
    }
}
