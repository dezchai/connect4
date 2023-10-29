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
// https://stackoverflow.com/questions/7867834/get-button-name-from-actionlistener
public class GameUi extends JPanel implements ActionListener {
    private List<List<JButton>> uiBoard;
    private Game currentGame;

    public GameUi() {
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setBackground(Color.blue);
        uiBoard = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            setLayout(new GridLayout(0,7));
            List<JButton> row = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                JButton b = new JButton();
                b.setActionCommand("" + i + j);
                b.addActionListener(this);
                b.setBackground(Color.white);
                add(b);

                row.add(b);
            }
            uiBoard.add(row);
        }
        currentGame = new Game();
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
        updateUI();
    }
}
