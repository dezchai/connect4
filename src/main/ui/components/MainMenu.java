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

// Represents the main menu window JPanel
public class MainMenu extends JPanel implements ActionListener {
    private static final int WIDTH = 800; // 1200
    private static final int HEIGHT = 600; // 900
    private GameList savedGames;
    private LoadUi loadUi;

    // EFFECTS: constructs the main menu window
    public MainMenu() {
        savedGames = new GameList();
        loadMainMenu();
    }

    // MODIFIES: this, loadUi
    // EFFECTS: event listener for main menu
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

    // MODIFIES: this
    // EFFECTS: loads the main menu buttons (new/load) and grabs savedGames from loadUi
    public void loadMainMenu() {
        removeAll();
        setLayout(new FlowLayout());
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

    // MODIFIES: this, loadUi
    // EFFECTS: updates saved games when given a GameList, used by open dialogue
    public void updateSavedGames(GameList gameList) {
        removeAll();
        updateUI();
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout());
        loadUi = new LoadUi(gameList);
        add(loadUi);
    }

    // EFFECTS: returns games currently saved in memory in main menu
    public GameList getSavedGames() {
        return savedGames;
    }
}
