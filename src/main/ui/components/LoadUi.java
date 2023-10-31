package ui.components;

import model.Game;
import model.GameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the LoadUi JPanel
public class LoadUi extends JPanel implements ActionListener {
    private final GameList savedGames;

    // EFFECTS: constructs the load menu window
    public LoadUi(GameList savedGames) {
        this.savedGames = savedGames;
//        setLayout(new FlowLayout());
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(Color.black);
        renderSavedGames();
    }

    // MODIFIES: this
    // EFFECTS: renders saved games as buttons with date as their name
    private void renderSavedGames() {
        removeAll();
        if (savedGames.getGames().isEmpty()) {
            JLabel noSavedGames = new JLabel("There are no saved games currently");
            noSavedGames.setForeground(Color.WHITE);
            noSavedGames.setFont(new Font("Dialog", Font.PLAIN, 28));
            add(noSavedGames);
        } else {
            renderStats();
            JPanel buttonPanel = new JPanel(new GridLayout(0, 6));
            buttonPanel.setBackground(Color.black);
            buttonPanel.setBorder(BorderFactory.createEmptyBorder());
            for (int i = 0; i < savedGames.getGames().size(); i++) {
                Game g = savedGames.getGames().get(i);
                JButton j = new JButton(g.getName());
                j.setActionCommand("" + i);
                j.addActionListener(this);
                j.setBorder(BorderFactory.createEmptyBorder(5,-30,5,10));
                buttonPanel.add(j);
            }
            add(buttonPanel);
        }
        updateUI();
    }

    // MODIFIES: this
    // EFFECTS: renders the stats text onto the screen (in progress / complete)
    private void renderStats() {
        JLabel stats = new JLabel(savedGames.getStats());
        stats.setForeground(Color.WHITE);
        stats.setFont(new Font("Dialog", Font.PLAIN, 28));
        stats.setHorizontalAlignment(JLabel.CENTER); // Center the text
        add(stats, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: event listener for the load window
    public void actionPerformed(ActionEvent e) {
        int index = Integer.parseInt(e.getActionCommand());
        if ((ActionEvent.CTRL_MASK & e.getModifiers()) != 0) {
            savedGames.removeGame(index);
            renderSavedGames();
        } else {
            removeAll();
            updateUI();
            setBorder(BorderFactory.createEmptyBorder());
            setLayout(new GridLayout());
            GameUi gameUi = new GameUi(savedGames.getGames().get(index));
            add(gameUi);
        }
    }

    // EFFECTS: gets saved games from load menu
    public GameList getSavedGames() {
        return savedGames;
    }
}
