public class TTTBoard
{
    public static final int SIZE = 3;

    private TTTTileButton[][] tiles;

    public TTTBoard()
    {
        tiles = new TTTTileButton[SIZE][SIZE];

        for (int row = 0; row < SIZE; row++)
        {
            for (int col = 0; col < SIZE; col++)
            {
                tiles[row][col] = new TTTTileButton(row, col);
                tiles[row][col].setText(" ");
            }
        }
    }

    public TTTTileButton getTile(int row, int col)
    {
        return tiles[row][col];
    }

    public String getMark(int row, int col)
    {
        return tiles[row][col].getText();
    }

    public boolean isAvailable(int row, int col)
    {
        return tiles[row][col].getText().equals(" ");
    }

    public void placeMark(int row, int col, String mark)
    {
        tiles[row][col].setText(mark);
    }

    public boolean isWin(String mark)
    {
        return isRowWin(mark) || isColWin(mark) || isDiagonalWin(mark);
    }

    private boolean isRowWin(String mark)
    {
        for (int row = 0; row < SIZE; row++)
        {
            if (tiles[row][0].getText().equals(mark)
                    && tiles[row][1].getText().equals(mark)
                    && tiles[row][2].getText().equals(mark))
            {
                return true;
            }
        }

        return false;
    }

    private boolean isColWin(String mark)
    {
        for (int col = 0; col < SIZE; col++)
        {
            if (tiles[0][col].getText().equals(mark)
                    && tiles[1][col].getText().equals(mark)
                    && tiles[2][col].getText().equals(mark))
            {
                return true;
            }
        }

        return false;
    }

    private boolean isDiagonalWin(String mark)
    {
        if (tiles[0][0].getText().equals(mark)
                && tiles[1][1].getText().equals(mark)
                && tiles[2][2].getText().equals(mark))
        {
            return true;
        }

        if (tiles[0][2].getText().equals(mark)
                && tiles[1][1].getText().equals(mark)
                && tiles[2][0].getText().equals(mark))
        {
            return true;
        }

        return false;
    }

    public boolean isFull()
    {
        for (int row = 0; row < SIZE; row++)
        {
            for (int col = 0; col < SIZE; col++)
            {
                if (tiles[row][col].getText().equals(" "))
                {
                    return false;
                }
            }
        }

        return true;
    }

    public void clear()
    {
        for (int row = 0; row < SIZE; row++)
        {
            for (int col = 0; col < SIZE; col++)
            {
                tiles[row][col].setText(" ");
            }
        }
    }
}
