package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeGameTest {

    @Test
    public void testCheckWinnerXRow() {
        GameBoard board = new GameBoard();
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertEquals('X', board.checkWinner());
    }

    @Test
    public void testNoWinnerInitially() {
        GameBoard board = new GameBoard();
        assertEquals('-', board.checkWinner());
    }

    @Test
    public void testFullBoardTie() {
        GameBoard board = new GameBoard();
        char[] moves = {
            'X', 'O', 'X',
            'X', 'O', 'O',
            'O', 'X', 'X'
        };
        for (int i = 0; i < 9; i++) {
            board.makeMove(i + 1, moves[i]);
        }
        assertTrue(board.isFull());
        assertEquals('-', board.checkWinner());
    }
}
