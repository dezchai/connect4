package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.MainMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// SOURCES:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
// https://www.codejava.net/java-se/swing/show-save-file-dialog-using-jfilechooser

// Represents the main window in which the connect 4
// game is played. This is the desktop GUI version.
public class WindowedGame extends JFrame implements ActionListener {
    private final MainMenu mainMenu;

    public WindowedGame() {
        super("Connect 4!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        mainMenu = new MainMenu();
        add(mainMenu);
        pack();
        centreOnScreen();
        setVisible(true);
        setResizable(false);
        loadMenuBar();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scr.width - getWidth()) / 2, (scr.height - getHeight()) / 2);
    }

    private void loadMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_ESCAPE);
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Main Menu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        menu.add(open);

        JMenuItem saveAs = new JMenuItem("Save As");
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
        saveAs.addActionListener(this);
        menu.add(saveAs);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(this);
        menu.add(exitItem);

        setJMenuBar(menuBar);
    }

    private void saveDialogue() {
        JFileChooser fileChooser = new JFileChooser("./data");
        fileChooser.setDialogTitle("Specify a file to save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files","json"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            saveGamesToFile(fileToSave.getAbsolutePath());
        }
    }

    private void saveGamesToFile(String path) {
        JsonWriter jsonWriter = new JsonWriter(path);
        try {
            jsonWriter.open();
            jsonWriter.write(mainMenu.getSavedGames());
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void openDialogue() {
        JFileChooser fileChooser = new JFileChooser("./data");
        fileChooser.setDialogTitle("Open");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files","json"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            openGamesFromFile(fileToSave.getAbsolutePath());
        }
    }

    private void openGamesFromFile(String path) {
        JsonReader jsonReader = new JsonReader(path);
        try {
            mainMenu.updateSavedGames(jsonReader.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Main Menu")) {
            mainMenu.loadMainMenu();
        } else if (e.getActionCommand().equals("Open")) {
            openDialogue();
        } else if (e.getActionCommand().equals("Save As")) {
            saveDialogue();
        } else if (e.getActionCommand().equals("Exit")) {
            dispose();
        }
    }
}

