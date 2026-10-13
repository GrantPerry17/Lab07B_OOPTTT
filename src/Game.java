public class Game
{
    public enum GameResult
    {
        VALID_MOVE, INVALID_MOVE, WIN, DRAW
    }

    public static final String PLAYER_X = "X";
    public static final String PLAYER_O = "O";

    private TTTBoard board;
    private String currentPlayer;
    private int moveCount;
    private boolean gameOver;

    public Game(TTTBoard board)
    {
        this.board = board;
        currentPlayer = PLAYER_X;
        moveCount = 0;
        gameOver = false;
    }

    public GameResult processMove(int row, int col)
    {
        if (gameOver || !board.isAvailable(row, col))
        {
            return GameResult.INVALID_MOVE;
        }

        board.placeMark(row, col, currentPlayer);
        moveCount++;

        if (board.isWin(currentPlayer))
        {
            gameOver = true;
            return GameResult.WIN;
        }

        if (board.isFull())
        {
            gameOver = true;
            return GameResult.DRAW;
        }

        if (currentPlayer.equals(PLAYER_X))
        {
            currentPlayer = PLAYER_O;
        }
        else
        {
            currentPlayer = PLAYER_X;
        }

        return GameResult.VALID_MOVE;
    }

    public String getCurrentPlayer()
    {
        return currentPlayer;
    }

    public int getMoveCount()
    {
        return moveCount;
    }

    public boolean isGameOver()
    {
        return gameOver;
    }

    public void reset()
    {
        board.clear();
        currentPlayer = PLAYER_X;
        moveCount = 0;
        gameOver = false;
    }
}
