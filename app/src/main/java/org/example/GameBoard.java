package org.example;

package model;

import java.util.Arrays;

public class GameBoard {
    private char[] board = new char[9];

    public Board() {
        Arrays.fill(board, ' ');
    }

    public boolean isFull() {
        for (char c : board) if (c == ' ') return false;
        return true;
    }

    public boolean makeMove(int position, char symbol) {
        if (position < 1 || position > 9 || board[position - 1] != ' ') return false;
        board[position - 1] = symbol;
        return true;
    }

    public boolean isEmpty() {
        for (char c : board) if (c != ' ') return false;
        return true;
    }

    public void display() {
        for (int i = 0; i < 9; i++) {
            System.out.printf("  %c  ", board[i] == ' ' ? (char) ('1' + i) : board[i]);
            if ((i + 1) % 3 == 0 && i != 8) System.out.println("\n-----+-----+-----");
            else if ((i + 1) % 3 != 0) System.out.print("|");
        }
        System.out.println();
    }

    public char checkWinner() {
        int[][] lines = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] line : lines) {
            if (board[line[0]] != ' ' &&
                board[line[0]] == board[line[1]] &&
                board[line[1]] == board[line[2]]) {
                return board[line[0]];
            }
        }
        return ' ';
    }

    public char[] getBoard() {
        return board.clone();
    }
}
