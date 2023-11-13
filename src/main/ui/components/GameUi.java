package ui.components;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// SOURCES:
// https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
// https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
// https://stackoverflow.com/questions/7867834/get-button-name-from-actionlistener
// https://stackoverflow.com/questions/17836692/center-components-in-jpanel-with-gridbaglayout
// https://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
// https://stackoverflow.com/questions/4801386/how-do-i-add-an-image-to-a-jbutton
public class GameUi extends JPanel implements ActionListener {
    private final List<List<JButton>> uiBoard;
    private final Game currentGame;
    private final JLabel heading;
    private final GridBagConstraints gridBagConstraints;

    // EFFECTS: constructs the game UI
    public GameUi(Game game) {
        currentGame = game;

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.blue);
        uiBoard = new ArrayList<>();
        gridBagConstraints = new GridBagConstraints();
        headingConstraints();
        setLayout(new GridBagLayout());
        heading = new JLabel("");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Dialog", Font.PLAIN, 28));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading, gridBagConstraints);
        boardConstraints();
        initBoard();
        renderBoard();
    }

    // MODIFIES: this
    // EFFECTS: event listener for GameUi
    public void actionPerformed(ActionEvent e) {
        int col = Integer.parseInt(e.getActionCommand().substring(1,2));
        currentGame.move(col);
        renderBoard();
    }

    // MODIFIES: this
    // EFFECTS: updates the color of each individual cell in the board and the heading
    private void renderBoard() {
        char[][] board = currentGame.getBoard();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 'O') {
                    uiBoard.get(i).get(j).setIcon(new ImageIcon("data/yellow.png"));
                } else if (board[i][j] == 'X') {
                    uiBoard.get(i).get(j).setIcon(new ImageIcon("data/red.png"));
                }
            }
        }
        heading.setText(currentGame.isGameOver() ? currentGame.getWinnerGui() : currentGame.getTurnGui());
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        updateUI();
    }

    // MODIFIES: this
    // EFFECTS: helper for heading grid bag constraints
    private void headingConstraints() {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.weightx = 7;
    }

    // MODIFIES: this
    // EFFECTS: helper for board grid bag constraints
    private void boardConstraints() {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
    }

    // MODIFIES: this
    // EFFECTS: initializes the board as a 2D array of JButtons with corresponding cells in the Game model
    // uiBoard acts in parallel with char[][] board from Game
    private void initBoard() {
        for (int i = 0; i < 6; i++) {
            gridBagConstraints.gridy = i + 1;
            List<JButton> row = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                gridBagConstraints.gridx = j;
                JButton b = new JButton();
                b.setActionCommand("" + i + j);
                b.addActionListener(this);
                b.setBackground(Color.white);
                b.setOpaque(true);
                b.setFocusPainted(false);
                b.setMargin(new Insets(0,0,0,0));
                Dimension d = new Dimension(25,25);
                b.setPreferredSize(d);
                b.setMinimumSize(d);
                b.setMaximumSize(d);
                add(b, gridBagConstraints);
                row.add(b);
            }
            uiBoard.add(row);
        }
    }
}
