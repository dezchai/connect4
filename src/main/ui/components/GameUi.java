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
public class GameUi extends JPanel implements ActionListener {
    private final List<List<JButton>> uiBoard;
    private final Game currentGame;
    private final JLabel heading;
    private final GridBagConstraints gridBagConstraints;

    public GameUi() {
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
        currentGame = new Game();
        heading.setText(currentGame.getTurnGui());
    }

    public void actionPerformed(ActionEvent e) {
        int col = Integer.parseInt(e.getActionCommand().substring(1,2));
        currentGame.move(col);
        renderBoard();
    }

    private void renderBoard()  {
        char[][] board = currentGame.getBoard();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 'O') {
                    uiBoard.get(i).get(j).setBackground(Color.yellow);
                } else if (board[i][j] == 'X') {
                    uiBoard.get(i).get(j).setBackground(Color.red);
                }
            }
        }
        if (currentGame.isGameOver()) {
            heading.setText(currentGame.getWinnerGui());
        } else {
            heading.setText(currentGame.getTurnGui());
        }
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        updateUI();
    }

    private void headingConstraints() {
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.weightx = 7;
    }

    private void boardConstraints() {
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.ipady = 100;
    }

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
                add(b, gridBagConstraints);

                row.add(b);
            }
            uiBoard.add(row);
        }
    }
}
