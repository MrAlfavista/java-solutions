package game;

import java.util.Scanner;

public class Tournament {
    Board board;
    int k, m, n, koef;
    Player players[];

    public Tournament(int m, int n,int koef, int k, Player[] players) {
        this.board = board;
        this.k = k;
        this.m = m;
        this.n = n;
        this.koef = koef;
        this.players = players;
    }

    public void play() {
        int score[] = new int[k];
        for(int i = 0; i<k;i++){
            score[i] = 0;
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if(i==j) continue;
                int result = new TwoPlayerGame(
                        new TicTacToeBoard(m,n,koef),
                        players[i],
                        players[j]
                ).play(true);
                switch (result) {
                    case 1:
                        System.out.println("Player number " + i +" won");
                        score[i]+=3;
                        break;
                    case 2:
                        System.out.println("Player number " + j +" won");
                        score[j]+=3;
                        break;
                    case 0:
                        System.out.println("Draw between player number " + j+ " and player number "+ i);
                        score[j]+=1;
                        score[i]+=1;
                        break;
                    default:
                        throw new AssertionError("Unknown result " + result);
                }
            }
        }
        for(int i = 0; i<k;i++){
            System.out.println("Player number "+ i +" scores: "+ score[i]);
        }
    }
}
