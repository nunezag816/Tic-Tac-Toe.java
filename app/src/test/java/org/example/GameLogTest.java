package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameLogTest {

    private GameLog log;

    @BeforeEach
    public void setup() {
        log = new GameLog();
    }

    @Test
    public void testInitialCounts() {
        assertEquals(0, log.getXWins());
        assertEquals(0, log.getOWins());
        assertEquals(0, log.getTies());
    }

    @Test
    public void testRecordXWin() {
        log.recordWin('X');
        assertEquals(1, log.getXWins());
        assertEquals(0, log.getOWins());
        assertEquals(0, log.getTies());
    }

    @Test
    public void testRecordOWin() {
        log.recordWin('O');
        assertEquals(0, log.getXWins());
        assertEquals(1, log.getOWins());
        assertEquals(0, log.getTies());
    }

    @Test
    public void testRecordTie() {
        log.recordWin('-');
        assertEquals(0, log.getXWins());
        assertEquals(0, log.getOWins());
        assertEquals(1, log.getTies());
    }

    @Test
    public void testMultipleRecords() {
        log.recordWin('X');
        log.recordWin('O');
        log.recordWin('-');
        log.recordWin('X');
        log.recordWin('O');
        log.recordWin('-');
        assertEquals(2, log.getXWins());
        assertEquals(2, log.getOWins());
        assertEquals(2, log.getTies());
    }
}
