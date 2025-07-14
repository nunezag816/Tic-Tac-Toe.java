package org.example;

public class GameBoard {
    private char[] board;

    public GameBoard() {
        board = new char[9];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public boolean isMoveValid(int position) {
        return position >= 1 && position <= 9 && board[position - 1] == ' ';
    }

    public void makeMove(int position, char player) {
        board[position - 1] = player;
    }

    public char checkWinner() {
        int[][] winConditions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combo : winConditions) {
            if (board[combo[0]] != ' ' &&
                board[combo[0]] == board[combo[1]] &&
                board[combo[1]] == board[combo[2]]) {
                return board[combo[0]];
            }
        }
        return '-';
    }

    public boolean isFull() {
        for (char c : board) {
            if (c == ' ') return false;
        }
        return true;
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            char displayChar = (board[i] == ' ') ? (char) ('1' + i) : board[i];
            System.out.print("  " + displayChar + "  ");
            if (i % 3 != 2) System.out.print("|");
            if (i % 3 == 2 && i < 8) System.out.println("\n-----+-----+-----");
        }
        System.out.println();
    }
}
