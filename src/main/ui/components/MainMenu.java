package ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// SOURCES:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// https://www.youtube.com/watch?v=5o3fMLPY7qY
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
// https://stackoverflow.com/questions/15412467/opening-a-new-jframe-from-a-button

public class MainMenu extends JPanel implements ActionListener {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;

    public MainMenu() {
        setBorder(BorderFactory.createEmptyBorder(400,500,400,500));
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        JButton newGameButton = new JButton("New Game");
        JButton loadButton = new JButton("Load Games");
        newGameButton.addActionListener(this);
        loadButton.addActionListener(this);
        add(newGameButton);
        add(loadButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New Game")) {
            removeAll();
            updateUI();
            setBorder(BorderFactory.createEmptyBorder());
            setLayout(new GridLayout());
            GameUi gameUi = new GameUi();
            add(gameUi);
        }
    }
}
