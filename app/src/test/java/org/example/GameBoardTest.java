package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    private GameBoard board;

    @BeforeEach
    public void setup() {
        board = new GameBoard();
    }

    @Test
    public void testResetBoard() {
        board.makeMove(1, 'X');
        board.resetBoard();
        for (int i = 1; i <= 9; i++) {
            assertTrue(board.isMoveValid(i), "Board should be empty after reset");
        }
    }

    @Test
    public void testIsMoveValid() {
        assertTrue(board.isMoveValid(1));
        assertTrue(board.isMoveValid(9));
        assertFalse(board.isMoveValid(0));
        assertFalse(board.isMoveValid(10));

        board.makeMove(1, 'X');
        assertFalse(board.isMoveValid(1));
    }

    @Test
    public void testMakeMove() {
        board.makeMove(5, 'O');
        assertFalse(board.isMoveValid(5));
    }

    @Test
    public void testCheckWinnerRow() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    public void testCheckWinnerColumn() {
        board.makeMove(1, 'O');
        board.makeMove(4, 'O');
        board.makeMove(7, 'O');
        assertEquals('O', board.checkWinner());
    }

    @Test
    public void testCheckWinnerDiagonal() {
        board.makeMove(1, 'X');
        board.makeMove(5, 'X');
        board.makeMove(9, 'X');
        assertEquals('X', board.checkWinner());

        board.resetBoard();

        board.makeMove(3, 'O');
        board.makeMove(5, 'O');
        board.makeMove(7, 'O');
        assertEquals('O', board.checkWinner());
    }

    @Test
    public void testCheckWinnerNone() {
        assertEquals('-', board.checkWinner());
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(3, 'X');
        assertEquals('-', board.checkWinner());
    }

    @Test
    public void testIsFull() {
        char player = 'X';
        for (int i = 1; i <= 9; i++) {
            board.makeMove(i, player);
            player = (player == 'X') ? 'O' : 'X';
        }
        assertTrue(board.isFull());
    }

    @Test
    public void testIsNotFull() {
        for (int i = 1; i <= 8; i++) {
            board.makeMove(i, 'X');
        }
        assertFalse(board.isFull());
    }
}
