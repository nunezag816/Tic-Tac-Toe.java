package org.example;

package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    private GameBoard board;
    private GameLog log;
    private char currentPlayer;
    private boolean isComputerOpponent;
    private boolean computerGoesFirst;
    private boolean humanIsX;
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
        System.out.println("2: Human vs Computer (you go first)");
        System.out.println("3: Computer vs Human (computer goes first)");

        while (true) {
            System.out.print("Enter choice (1, 2, or 3): ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    isComputerOpponent = false;
                    humanIsX = true;
                    break;
                case "2":
                    isComputerOpponent = true;
                    humanIsX = true;
                    break;
                case "3":
                    isComputerOpponent = true;
                    humanIsX = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    continue;
            }
            break;
        }

        boolean playAgain = true;
        char loser = 'O';

        while (playAgain) {
            board.resetBoard();
            currentPlayer = loser;
            System.out.println("\n" + currentPlayer + " will go first this round!");

            playRound(scanner);

            char winner = board.checkWinner();
            if (winner == 'X' || winner == 'O') {
                log.recordWin(winner);
                System.out.println("\nPlayer " + winner + " wins!");
                loser = (winner == 'X') ? 'O' : 'X';
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

            if (isComputerOpponent && isComputerTurn()) {
                System.out.println("\nComputer (" + currentPlayer + ") is making a move...");
                int move = getOpportunisticComputerMove();
                board.makeMove(move, currentPlayer);
            } else {
                System.out.print("\n" + currentPlayer + ", what is your move (1-9) or type 'exit' to quit? ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }

                try {
                    int move = Integer.parseInt(input);
                    if (!board.isMoveValid(move)) {
                        System.out.println("Invalid move. Try again.");
                        continue;
                    }
                    board.makeMove(move, currentPlayer);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number between 1 and 9.");
                    continue;
                }
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            moves++;
        }

        board.display();
    }

    private boolean isComputerTurn() {
        return (humanIsX && currentPlayer == 'O') || (!humanIsX && currentPlayer == 'X');
    }

    private int getOpportunisticComputerMove() {
        char[] b = board.getBoardCopy();
        char opponent = (currentPlayer == 'X') ? 'O' : 'X';

        if (board.isEmpty()) {
            int[] corners = {1, 3, 7, 9};
            return corners[random.nextInt(corners.length)];
        }

        if (board.getMoveCount() == 1 && board.isMoveValid(5)) {
            return 5;
        }

        for (int i = 1; i <= 9; i++) {
            if (board.isMoveValid(i)) {
                board.makeTemporaryMove(i, currentPlayer);
                if (board.checkWinner() == currentPlayer) {
                    board.undoTemporaryMove(i);
                    return i;
                }
                board.undoTemporaryMove(i);
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (board.isMoveValid(i)) {
                board.makeTemporaryMove(i, opponent);
                if (board.checkWinner() == opponent) {
                    board.undoTemporaryMove(i);
                    return i;
                }
                board.undoTemporaryMove(i);
            }
        }

        int move;
        do {
            move = random.nextInt(9) + 1;
        } while (!board.isMoveValid(move));
        return move;
    }
}
