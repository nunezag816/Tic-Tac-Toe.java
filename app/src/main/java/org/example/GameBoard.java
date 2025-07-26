package org.example;

public class GameBoard {
    private final char[] board;

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
        if (isMoveValid(position)) {
            board[position - 1] = player;
        }
    }

    public void makeTemporaryMove(int position, char player) {
        board[position - 1] = player;
    }

    public void undoTemporaryMove(int position) {
        board[position - 1] = ' ';
    }

    public boolean isEmpty() {
        for (char c : board) {
            if (c != ' ') {
                return false;
            }
        }
        return true;
    }

    public int getMoveCount() {
        int count = 0;
        for (char c : board) {
            if (c != ' ') {
                count++;
            }
        }
        return count;
    }

    public char[] getBoardCopy() {
        return board.clone();
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            char cell = (board[i] == ' ') ? (char) ('1' + i) : board[i];
            System.out.print(" " + cell + " ");
            if (i % 3 != 2) {
                System.out.print("|");
            } else if (i != 8) {
                System.out.println("\n-----------");
            }
        }
        System.out.println();
    }

    public char checkWinner() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6}             
        };

        for (int[] pattern : winPatterns) {
            char a = board[pattern[0]];
            char b = board[pattern[1]];
            char c = board[pattern[2]];
            if (a != ' ' && a == b && b == c) {
                return a;
            }
        }

        return (getMoveCount() == 9) ? '-' : '-';
    }
}
