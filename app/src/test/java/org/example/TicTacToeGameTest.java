package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TicTacToeGameTest {

    @Test
    public void testHumanVsHumanEarlyExit() {
        String input = "1\n1\n2\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);

        TicTacToeGame game = new TicTacToeGame();
        try {
            game.run(scanner);
        } catch (SecurityException | IllegalStateException e) {
        }
    }

    @Test
    public void testHumanVsComputerPlay() {
        String input = "2\n1\n2\nexit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream);

        TicTacToeGame game = new TicTacToeGame();
        try {
            game.run(scanner);
        } catch (SecurityException | IllegalStateException e) {
        }
    }
}
