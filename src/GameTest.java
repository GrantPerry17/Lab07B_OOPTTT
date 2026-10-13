import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameTest
{
    private TTTBoard board;
    private Game game;

    @Before
    public void setUp()
    {
        board = new TTTBoard();
        game = new Game(board);
    }

    @Test
    public void testStartsWithPlayerX()
    {
        assertEquals("X", game.getCurrentPlayer());
        assertFalse(game.isGameOver());
        assertEquals(0, game.getMoveCount());
    }

    @Test
    public void testPlayersAlternate()
    {
        game.processMove(0, 0);
        assertEquals("O", game.getCurrentPlayer());
        game.processMove(1, 1);
        assertEquals("X", game.getCurrentPlayer());
    }

    @Test
    public void testInvalidMoveOnOccupiedTile()
    {
        game.processMove(0, 0);
        assertEquals(Game.GameResult.INVALID_MOVE, game.processMove(0, 0));
        assertEquals("O", game.getCurrentPlayer());
        assertEquals(1, game.getMoveCount());
    }

    @Test
    public void testRowWin()
    {
        assertEquals(Game.GameResult.VALID_MOVE, game.processMove(0, 0));
        assertEquals(Game.GameResult.VALID_MOVE, game.processMove(1, 0));
        assertEquals(Game.GameResult.VALID_MOVE, game.processMove(0, 1));
        assertEquals(Game.GameResult.VALID_MOVE, game.processMove(1, 1));
        assertEquals(Game.GameResult.WIN, game.processMove(0, 2));
        assertTrue(game.isGameOver());
    }

    @Test
    public void testColumnWin()
    {
        game.processMove(0, 0);
        game.processMove(0, 1);
        game.processMove(1, 0);
        game.processMove(1, 1);
        assertEquals(Game.GameResult.WIN, game.processMove(2, 0));
        assertTrue(game.isGameOver());
    }

    @Test
    public void testMainDiagonalWin()
    {
        game.processMove(0, 0);
        game.processMove(0, 1);
        game.processMove(1, 1);
        game.processMove(0, 2);
        assertEquals(Game.GameResult.WIN, game.processMove(2, 2));
        assertTrue(game.isGameOver());
    }

    @Test
    public void testOtherDiagonalWin()
    {
        game.processMove(0, 2);
        game.processMove(0, 0);
        game.processMove(1, 1);
        game.processMove(0, 1);
        assertEquals(Game.GameResult.WIN, game.processMove(2, 0));
        assertTrue(game.isGameOver());
    }

    @Test
    public void testDraw()
    {
        game.processMove(0, 0);
        game.processMove(0, 1);
        game.processMove(0, 2);
        game.processMove(1, 1);
        game.processMove(1, 0);
        game.processMove(1, 2);
        game.processMove(2, 1);
        game.processMove(2, 0);
        assertEquals(Game.GameResult.DRAW, game.processMove(2, 2));
        assertTrue(game.isGameOver());
    }

    @Test
    public void testMovesInvalidAfterGameOver()
    {
        game.processMove(0, 0);
        game.processMove(1, 0);
        game.processMove(0, 1);
        game.processMove(1, 1);
        game.processMove(0, 2);
        assertEquals(Game.GameResult.INVALID_MOVE, game.processMove(2, 2));
        assertEquals(5, game.getMoveCount());
    }

    @Test
    public void testReset()
    {
        game.processMove(0, 0);
        game.processMove(1, 0);
        game.processMove(0, 1);
        game.processMove(1, 1);
        game.processMove(0, 2);

        game.reset();

        assertFalse(game.isGameOver());
        assertEquals("X", game.getCurrentPlayer());
        assertEquals(0, game.getMoveCount());
        assertTrue(board.isAvailable(0, 0));
        assertEquals(Game.GameResult.VALID_MOVE, game.processMove(0, 0));
    }
}
