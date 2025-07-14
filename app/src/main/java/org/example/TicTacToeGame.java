package org.example;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
    private GameBoard board;
    private GameLog log;
    private char currentPlayer;
    private Scanner scanner;
    private boolean vsComputer;

    public TicTacToeGame() {
        board = new GameBoard();
        log = new GameLog();
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        chooseMode();

        char starter = 'X';
        boolean playAgain = true;

        while (playAgain) {
            board.resetBoard();
            currentPlayer = starter;
            playRound();

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
            playAgain = scanner.nextLine().trim().equalsIgnoreCase("yes");
        }

        log.saveToFile("game.txt");
        System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!");
    }

    private void chooseMode() {
        while (true) {
            System.out.print("Select mode (1 = Human vs Human, 2 = Human vs Computer): ");
            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                vsComputer = false;
                break;
            } else if (input.equals("2")) {
                vsComputer = true;
                break;
            } else {
                System.out.println("Invalid input. Please type 1 or 2.");
            }
        }
    }

    private void playRound() {
        int moves = 0;
        while (moves < 9 && board.checkWinner() == '-') {
            board.display();

            if (vsComputer && currentPlayer == 'O') {
                System.out.println("\nComputer (O) is making a move...");
                int move = getRandomMove();
                board.makeMove(move, 'O');
                currentPlayer = 'X';
                moves++;
            } else {
                System.out.print("\n" + currentPlayer + ", what is your move (1-9)? Or type 'exit': ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("\nExiting game.");
                    log.saveToFile("game.txt");
                    System.exit(0);
                }

                try {
                    int move = Integer.parseInt(input);
                    if (!board.isMoveValid(move)) {
                        System.out.println("Invalid move. Try again.");
                        continue;
                    }
                    board.makeMove(move, currentPlayer);
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    moves++;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number from 1 to 9.");
                }
            }
        }

        board.display();
    }

    private int getRandomMove() {
        Random random = new Random();
        int move;
        do {
            move = random.nextInt(9) + 1; // 1 to 9
        } while (!board.isMoveValid(move));
        return move;
    }
}
