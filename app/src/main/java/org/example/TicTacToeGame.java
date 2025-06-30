package org.example;

import java.util.Scanner;

public class TicTacToeGame {
    private GameBoard board;
    private GameLog log;
    private char currentPlayer;

    public TicTacToeGame() {
        board = new GameBoard();
        log = new GameLog();
        currentPlayer = 'X';
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        char starter = 'X';
        boolean playAgain = true;

        while (playAgain) {
            board.resetBoard();
            currentPlayer = starter;
            playRound(scanner);

            char winner = board.checkWinner();
            if (winner == 'X' || winner == 'O') {
                log.recordWin(winner);
                System.out.println("\nPlayer " + winner + " wins!");
                starter = (winner == 'X') ? 'O' : 'X';
            } else {
                log.recordWin('-');
                System.out.println("\nIt's a tie!");
            }

            log.printStats();

            System.out.print("\nWould you like to play again (yes/no)? ");
            playAgain = scanner.next().trim().equalsIgnoreCase("yes");
        }

        log.saveToFile("game.txt");
        System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
    }

    private void playRound(Scanner scanner) {
        int moves = 0;
        while (moves < 9 && board.checkWinner() == '-') {
            board.display();
            System.out.print("\n" + currentPlayer + ", what is your move (1-9)? ");

            try {
                int move = Integer.parseInt(scanner.next().trim());
                if (!board.isMoveValid(move)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }
                board.makeMove(move, currentPlayer);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                moves++;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 9.");
            }
        }
        board.display();
    }
}
