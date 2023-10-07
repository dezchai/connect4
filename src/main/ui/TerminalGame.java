package ui;

import java.io.IOException;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
//import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
//import com.googlecode.lanterna.screen.Screen;
//import com.googlecode.lanterna.terminal.Terminal;


public class TerminalGame {
    private Screen screen;
    Terminal terminal = null;
    DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

    public void start() throws IOException, InterruptedException {
        terminal = defaultTerminalFactory.createTerminal();
        terminal.putCharacter('H');
        terminal.putCharacter('e');
        terminal.putCharacter('l');
        terminal.putCharacter('l');
        terminal.putCharacter('o');
        terminal.putCharacter('\n');
        terminal.flush();
        KeyStroke stroke = screen.pollInput();
        terminal.putCharacter(handleKey(stroke.getKeyType()));
//        screen = new DefaultTerminalFactory().createScreen();
//        screen.startScreen();
    }

    private char handleKey(KeyType type) {
        switch (type) {
            case ArrowLeft:
                return 'l';
            case ArrowRight:
                return 'r';
            default:
                return 'x';
        }
    }
}
