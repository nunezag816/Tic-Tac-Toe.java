package org.example;

package model;

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

    public String getFormattedLog() {
        return String.format("\nPlayer X Wins   %d\nPlayer O Wins   %d\nTies            %d\n",
                xWins, oWins, ties);
    }

    public void writeToFile() {
        try (FileWriter writer = new FileWriter("game.txt")) {
            writer.write(getFormattedLog());
            System.out.println("Writing the game log to disk. Please see game.txt for the final statistics!");
        } catch (IOException e) {
            System.out.println("Failed to write game log.");
        }
    }
}
