package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {
    private GameBoard board;

    @BeforeEach
    public void setUp() {
        board = new GameBoard();
    }

    @Test
    public void testBoardInitialization() {
        assertTrue(board.isEmpty());
    }

    @Test
    public void testValidMove() {
        assertTrue(board.isMoveValid(1));
        board.makeMove(1, 'X');
        assertFalse(board.isMoveValid(1));
    }

    @Test
    public void testInvalidMoveOutOfBounds() {
        assertFalse(board.isMoveValid(0));
        assertFalse(board.isMoveValid(10));
    }

    @Test
    public void testWinDetectionRows() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    public void testWinDetectionColumns() {
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        assertEquals('O', board.checkWinner());
    }

    @Test
    public void testWinDetectionDiagonals() {
        board.makeMove(1, 'X');
        board.makeMove(5, 'X');
        board.makeMove(9, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    public void testTieGame() {
        char[] moves = {
            'X', 'O', 'X',
            'X', 'O', 'O',
            'O', 'X', 'X'
        };

        for (int i = 0; i < 9; i++) {
            board.makeMove(i + 1, moves[i]);
        }

        assertEquals('-', board.checkWinner());
    }

    @Test
    public void testUndoTemporaryMove() {
        board.makeTemporaryMove(5, 'X');
        assertEquals('X', board.getBoardCopy()[4]);
        board.undoTemporaryMove(5);
        assertEquals(' ', board.getBoardCopy()[4]);
    }
}
