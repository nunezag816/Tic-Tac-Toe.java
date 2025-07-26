package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameTest {

    @Test
    void testRunHumanVsComputerEarlyExit() {
        // Simulated inputs:
        // 2 => Human vs Computer
        // 1 => Human move (cell 1)
        // exit => User exits mid-round
        String input = "2\n1\nexit\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        TicTacToeGame game = new TicTacToeGame();
        boolean exitedEarly = game.run(scanner);

        assertTrue(exitedEarly, "Expected game to exit early when user types 'exit'");
    }

    @Test
    void testRunHumanVsComputerNormalExit() {
        // Simulated inputs:
        // 2 => Human vs Computer
        // 1 => Human move (cell 1)
        // 5 => Human move
        // 9 => Human move
        // no => Do not play again
        String input = "2\n1\n5\n9\nno\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        TicTacToeGame game = new TicTacToeGame();
        boolean exitedEarly = game.run(scanner);

        assertFalse(exitedEarly, "Expected game to complete normally, not exit early");
    }

    @Test
    void testInvalidMenuInputThenValid() {
        // Simulates invalid input followed by valid choice
        // x => invalid
        // 2 => valid (human vs computer)
        // 1, 2, 3 => some moves
        // no => exit
        String input = "x\n2\n1\n2\n3\nno\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        TicTacToeGame game = new TicTacToeGame();
        boolean exitedEarly = game.run(scanner);

        assertFalse(exitedEarly);
    }

    @Test
    void testHumanVsHumanShortGame() {
        // Simulates Human vs Human with one move then no replay
        String input = "1\n1\n2\n3\nno\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        TicTacToeGame game = new TicTacToeGame();
        boolean exitedEarly = game.run(scanner);

        assertFalse(exitedEarly);
    }
}
