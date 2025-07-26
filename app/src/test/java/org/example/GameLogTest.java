package org.example;

package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {
    private GameLog log;

    @BeforeEach
    public void setup() {
        log = new GameLog();
    }

    @Test
    public void testRecordWin() {
        log.recordWin('X');
        log.recordWin('O');
        log.recordWin('-');

        assertEquals(1, log.getXWins());
        assertEquals(1, log.getOWins());
        assertEquals(1, log.getTies());
    }

    @Test
    public void testSaveToFile() throws IOException {
        log.recordWin('X');
        log.recordWin('X');
        log.recordWin('O');
        log.recordWin('-');

        String filePath = "test_log_output.txt";
        log.saveToFile(filePath);

        File file = new File(filePath);
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertTrue(lines.stream().anyMatch(line -> line.contains("Player X Wins: 2")));

        file.delete();
    }
}
