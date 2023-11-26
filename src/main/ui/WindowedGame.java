package ui;

import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.components.MainMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// SOURCES:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html
// https://www.codejava.net/java-se/swing/show-save-file-dialog-using-jfilechooser
// https://stackoverflow.com/questions/1065691/how-to-set-the-background-color-of-a-jbutton-on-the-mac-os
// Represents the main window in which the connect 4
// game is played. This is the desktop GUI version.
public class WindowedGame extends JFrame implements ActionListener, WindowListener {
    private final MainMenu mainMenu;

    // EFFECTS: constructs the root window
    public WindowedGame() {
        super("Connect 4!");
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        mainMenu = new MainMenu();
        add(mainMenu);
        pack();
        centreOnScreen();
        setResizable(false);
        loadMenuBar();
        setVisible(true);
        addWindowListener(this);
    }

    // MODIFIES: this
    // EFFECTS:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scr.width - getWidth()) / 2, (scr.height - getHeight()) / 2);
    }

    // MODIFIES: this
    // EFFECTS: loads menu bar with menu, open, save as, and exit as options
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

    // EFFECTS: save dialogue for menu bar
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

    // EFFECTS: helper for saveDialogue, writes file to disk
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

    // EFFECTS: open dialogue for menu bar
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

    // MODIFIES: mainMenu
    // EFFECTS: helper for openDialogue, reads file from disk
    private void openGamesFromFile(String path) {
        JsonReader jsonReader = new JsonReader(path);
        try {
            mainMenu.updateSavedGames(jsonReader.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // MODIFIES: mainMenu
    // EFFECTS: event listener for menu bar
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Main Menu")) {
            mainMenu.loadMainMenu();
        } else if (e.getActionCommand().equals("Open")) {
            openDialogue();
        } else if (e.getActionCommand().equals("Save As")) {
            saveDialogue();
        } else if (e.getActionCommand().equals("Exit")) {
            eventLogsOnWindowClose();
            dispose();
        }
    }

    private void eventLogsOnWindowClose() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.getDescription());
        }
    }

    public void windowClosing(WindowEvent e) {
        eventLogsOnWindowClose();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowGainedFocus(WindowEvent e) {
    }

    public void windowLostFocus(WindowEvent e) {
    }


}

