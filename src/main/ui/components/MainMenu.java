package ui.components;

import model.Game;
import model.GameList;

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
    private GameList savedGames;
    private LoadUi loadUi;

    public MainMenu() {
        savedGames = new GameList();
        loadMainMenu();
    }

    public void actionPerformed(ActionEvent e) {
        removeAll();
        updateUI();
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout());
        if (e.getActionCommand().equals("New Game")) {
            Game game = new Game();
            GameUi gameUi = new GameUi(game);
            savedGames.addGame(game);
            add(gameUi);
        } else if (e.getActionCommand().equals("Load Games")) {
            loadUi = new LoadUi(savedGames);
            add(loadUi);
        }
    }

    public void loadMainMenu() {
        removeAll();
        setBorder(BorderFactory.createEmptyBorder(375,450,375,450));
        setLayout(new GridLayout(3,1));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        JButton newGameButton = new JButton("New Game");
        JButton loadButton = new JButton("Load Games");
        newGameButton.addActionListener(this);
        loadButton.addActionListener(this);
        add(newGameButton);
        add(loadButton);
        if (loadUi != null) {
            savedGames = loadUi.getSavedGames();
        }
        updateUI();
    }

    public void updateSavedGames(GameList gameList) {
        removeAll();
        updateUI();
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout());
        loadUi = new LoadUi(gameList);
        add(loadUi);
    }

    // EFFECTS: returns games currently saved in memory
    public GameList getSavedGames() {
        return savedGames;
    }
}
