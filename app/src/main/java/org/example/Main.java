package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        Scanner scanner = new Scanner(System.in);
        game.run(scanner);
        scanner.close();
    }
}
