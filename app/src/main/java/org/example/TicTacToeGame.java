package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    private GameBoard board;
    private GameLog log;
    private char currentPlayer;
    private boolean isComputerOpponent;
    private Random random;

    public TicTacToeGame() {
        board = new GameBoard();
        log = new GameLog();
        random = new Random();
    }

    public void run(Scanner scanner) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Select game mode:");
        System.out.println("1: Human vs Human");
        System.out.println("2: Human vs Computer");

        while (true) {
            System.out.print("Enter choice (1 or 2): ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                isComputerOpponent = false;
                break;
            } else if (choice.equals("2")) {
                isComputerOpponent = true;
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        boolean playAgain = true;
        char starter = 'X';

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
            String response = scanner.nextLine().trim();
            playAgain = response.equalsIgnoreCase("yes");
        }

        log.saveToFile("game.txt");
        System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
    }

    private void playRound(Scanner scanner) {
        int moves = 0;
        while (moves < 9 && board.checkWinner() == '-') {
            board.display();

            if (isComputerOpponent && currentPlayer == 'O') {
                // Computer turn
                System.out.println("\nComputer (" + currentPlayer + ") is making a move...");
                int move = getComputerMove();
                board.makeMove(move, currentPlayer);
                currentPlayer = 'X';
                moves++;
            } else {
                // Human turn
                System.out.print("\n" + currentPlayer + ", what is your move (1-9) or type 'exit' to quit? ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }

                try {
                    int move = Integer.parseInt(input);
                    if (!board.isMoveValid(move)) {
                        System.out.println("Invalid move. Cell is either taken or out of range. Try again.");
                        continue;
                    }
                    board.makeMove(move, currentPlayer);
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    moves++;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number between 1 and 9.");
                }
            }
        }
        board.display();
    }

    private int getComputerMove() {
        int move;
        do {
            move = random.nextInt(9) + 1;
        } while (!board.isMoveValid(move));
        return move;
    }
}
