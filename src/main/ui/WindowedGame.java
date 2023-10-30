package ui;

import ui.components.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

// SOURCES:
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
// https://docs.oracle.com/javase/tutorial/uiswing/components/menu.html

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
//        setLayout(new GridBagLayout());
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

        JMenuItem menuItem = new JMenuItem("Main Menu",
                KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        JMenuItem exitItem = new JMenuItem("Exit",
                KeyEvent.VK_T);
        exitItem.addActionListener(this);
        menu.add(exitItem);
        setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Main Menu")) {
            mainMenu.loadMainMenu();
        } else if (e.getActionCommand().equals("Exit")) {
            dispose();
        }
    }
}

