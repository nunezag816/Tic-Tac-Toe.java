package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    private int xWins = 0;
    private int oWins = 0;
    private int ties = 0;

    public void recordWin(char winner) {
        if (winner == 'X') xWins++;
        else if (winner == 'O') oWins++;
        else ties++;
    }

    public void printStats() {
        System.out.println("\nThe current log is:");
        System.out.println("Player X Wins   " + xWins);
        System.out.println("Player O Wins   " + oWins);
        System.out.println("Ties            " + ties);
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Final Game Log:\n");
            writer.write("Player X Wins   " + xWins + "\n");
            writer.write("Player O Wins   " + oWins + "\n");
            writer.write("Ties            " + ties + "\n");
        } catch (IOException e) {
            System.out.println("Failed to write log to disk.");
        }
    }

    public int getXWins() { return xWins; }
    public int getOWins() { return oWins; }
    public int getTies() { return ties; }
}
