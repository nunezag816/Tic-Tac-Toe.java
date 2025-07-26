package org.example;

package model;

import java.util.*;

public class ComputerPlayer implements Player {
    private char symbol;
    private String name;
    private Random random = new Random();

    public ComputerPlayer(char symbol) {
        this.symbol = symbol;
        this.name = "Computer";
    }

    @Override
    public char getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMove(Board board) {
        char[] b = board.getBoard();
        char opponent = (symbol == 'X') ? 'O' : 'X';

        if (board.isEmpty()) {
            int[] corners = {0, 2, 6, 8};
            return corners[random.nextInt(4)] + 1;
        }

        if (b[4] == ' ') return 5;

        for (int i = 1; i <= 9; i++) {
            if (b[i - 1] == ' ') {
                b[i - 1] = symbol;
                if (checkWin(b, symbol)) return i;
                b[i - 1] = ' ';
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (b[i - 1] == ' ') {
                b[i - 1] = opponent;
                if (checkWin(b, opponent)) return i;
                b[i - 1] = ' ';
            }
        }

        List<Integer> moves = new ArrayList<>();
        for (int i = 0; i < 9; i++) if (b[i] == ' ') moves.add(i + 1);
        return moves.get(random.nextInt(moves.size()));
    }

    private boolean checkWin(char[] b, char symbol) {
        int[][] winPatterns = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };
        for (int[] line : winPatterns) {
            if (b[line[0]] == symbol && b[line[1]] == symbol && b[line[2]] == symbol)
                return true;
        }
        return false;
    }
}
