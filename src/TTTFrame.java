import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TTTFrame extends JFrame
{
    private TTTBoard board;
    private Game game;

    public TTTFrame()
    {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        board = new TTTBoard();
        game = new Game(board);

        JPanel boardPanel = new JPanel(new GridLayout(TTTBoard.SIZE, TTTBoard.SIZE));

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (game.isGameOver())
                {
                    return;
                }

                TTTTileButton tile = (TTTTileButton)e.getSource();

                switch (game.processMove(tile.getRow(), tile.getCol()))
                {
                    case WIN:
                        JOptionPane.showMessageDialog(TTTFrame.this, "Player " + game.getCurrentPlayer() + " wins!");
                        playAgain();
                        break;

                    case DRAW:
                        JOptionPane.showMessageDialog(TTTFrame.this, "The game is a tie!");
                        playAgain();
                        break;

                    case INVALID_MOVE:
                        JOptionPane.showMessageDialog(TTTFrame.this, "That is not a valid move.");
                        break;

                    case VALID_MOVE:
                        break;
                }
            }
        };

        for (int row = 0; row < TTTBoard.SIZE; row++)
        {
            for (int col = 0; col < TTTBoard.SIZE; col++)
            {
                TTTTileButton tile = board.getTile(row, col);
                tile.addActionListener(listener);
                boardPanel.add(tile);
            }
        }

        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));

        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int choice = JOptionPane.showConfirmDialog(
                        TTTFrame.this,
                        "Are you sure you want to quit?",
                        "Quit Game",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });

        buttonPanel.add(quitButton);

        add(boardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void playAgain()
    {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play another game?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION)
        {
            game.reset();
        }
        else
        {
            System.exit(0);
        }
    }
}
