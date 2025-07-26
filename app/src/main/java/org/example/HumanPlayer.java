package org.example;

package model;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private char symbol;
    private String name;
    private Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
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
        int move = -1;
        while (true) {
            System.out.print("\nWhat is your move? ");
            String input = scanner.nextLine();
            try {
                move = Integer.parseInt(input);
                if (move >= 1 && move <= 9 && board.getBoard()[move - 1] == ' ') break;
            } catch (Exception ignored) {}
            System.out.println("That is not a valid move! Try again.");
        }
        return move;
    }
}
