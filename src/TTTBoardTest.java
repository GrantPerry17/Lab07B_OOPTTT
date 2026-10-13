import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TTTBoardTest
{
    private TTTBoard board;

    @Before
    public void setUp()
    {
        board = new TTTBoard();
    }

    @Test
    public void testTilesStartEmpty()
    {
        for (int row = 0; row < TTTBoard.SIZE; row++)
        {
            for (int col = 0; col < TTTBoard.SIZE; col++)
            {
                assertTrue(board.isAvailable(row, col));
                assertEquals(" ", board.getMark(row, col));
                assertEquals(row, board.getTile(row, col).getRow());
                assertEquals(col, board.getTile(row, col).getCol());
            }
        }
    }

    @Test
    public void testPlaceMark()
    {
        board.placeMark(1, 2, "X");
        assertEquals("X", board.getMark(1, 2));
        assertFalse(board.isAvailable(1, 2));
        assertTrue(board.isAvailable(0, 0));
    }

    @Test
    public void testRowWin()
    {
        board.placeMark(2, 0, "O");
        board.placeMark(2, 1, "O");
        assertFalse(board.isWin("O"));
        board.placeMark(2, 2, "O");
        assertTrue(board.isWin("O"));
        assertFalse(board.isWin("X"));
    }

    @Test
    public void testColumnWin()
    {
        board.placeMark(0, 1, "X");
        board.placeMark(1, 1, "X");
        board.placeMark(2, 1, "X");
        assertTrue(board.isWin("X"));
        assertFalse(board.isWin("O"));
    }

    @Test
    public void testMainDiagonalWin()
    {
        board.placeMark(0, 0, "X");
        board.placeMark(1, 1, "X");
        board.placeMark(2, 2, "X");
        assertTrue(board.isWin("X"));
    }

    @Test
    public void testOtherDiagonalWin()
    {
        board.placeMark(0, 2, "O");
        board.placeMark(1, 1, "O");
        board.placeMark(2, 0, "O");
        assertTrue(board.isWin("O"));
    }

    @Test
    public void testIsFull()
    {
        assertFalse(board.isFull());
        board.placeMark(0, 0, "X");
        board.placeMark(0, 1, "O");
        board.placeMark(0, 2, "X");
        board.placeMark(1, 0, "O");
        board.placeMark(1, 1, "X");
        board.placeMark(1, 2, "O");
        board.placeMark(2, 0, "O");
        board.placeMark(2, 1, "X");
        board.placeMark(2, 2, "O");
        assertTrue(board.isFull());
    }

    @Test
    public void testClear()
    {
        board.placeMark(0, 0, "X");
        board.placeMark(1, 1, "O");
        board.clear();
        assertTrue(board.isAvailable(0, 0));
        assertTrue(board.isAvailable(1, 1));
        assertFalse(board.isWin("X"));
        assertFalse(board.isFull());
    }
}
