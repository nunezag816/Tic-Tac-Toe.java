package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {

    @Test
    public void testWinCounts() {
        GameLog log = new GameLog();
        log.recordWin('X');
        log.recordWin('O');
        log.recordWin('-');

        assertEquals(1, log.getXWins());
        assertEquals(1, log.getOWins());
        assertEquals(1, log.getTies());
    }
}
