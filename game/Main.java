package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int result = new TwoPlayerGame(
                new HexBoard(4),
                new HumanPlayer (in),
                new RandomPlayer()
        ).play(true);
        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
        /*
        Player players[] = new Player[4];
        for(int i = 0; i < 4; i++){
            players[i] = new RandomPlayer();
        }
        Tournament tournament = new Tournament(3, 3, 3, 4, players);
        tournament.play();
        */
    }
}
