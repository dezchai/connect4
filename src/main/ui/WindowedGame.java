package ui;

import ui.components.MainMenu;

import javax.swing.JFrame;
import java.awt.*;

// SOURCE: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

// Represents the main window in which the connect 4
// game is played. This is the desktop GUI version.
public class WindowedGame extends JFrame {
    private MainMenu mainMenu;

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
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scr.width - getWidth()) / 2, (scr.height - getHeight()) / 2);
    }
}

