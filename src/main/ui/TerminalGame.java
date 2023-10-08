package ui;

import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import model.Game;
import model.Games;

import java.util.*;

public class TerminalGame {
    private Screen screen;
    private TerminalSize terminalSize;
    private Game currentGame;
    private Games savedGames;
    private int selectedColumn; // in Game
    private int selectedGame; // in loadMenu
    private String currentView;

    public void start() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        screen = new TerminalScreen(terminal);
        savedGames = new Games();
        screen.startScreen();
        terminalSize = screen.getTerminalSize();
        mainMenu();
        screen.refresh();
        while (true) {
            KeyStroke stroke = screen.readInput();
            handleKey(stroke.getKeyType());
            screen.refresh();
        }
    }

    private void mainMenu() {
        screen.clear();
        currentView = "main-menu";
        selectedColumn = 0;
        selectedGame = 0;

        String[] messages = {"Connect4!","Press F1 to Start","Press F2 to load game"};

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);

        for (int i = 0; i < messages.length; i++) {
            String message = messages[i];
            TerminalPosition pos = new TerminalPosition(
                    terminalSize.getColumns() / 2 - (message.length() / 2),
                    terminalSize.getRows() / 2 + i - 2);
            textGraphics.putString(pos, message);
        }

    }

    private void handleKey(KeyType type) {
        if (Objects.requireNonNull(type) == KeyType.Escape) {
            mainMenu();
        } else if (currentView.equals("main-menu")) {
            handleKeyMainMenu(type);
        } else if (currentView.equals("game")) {
            handleKeyGame(type);
        } else if (currentView.equals("load")) {
            handleKeyLoad(type);
        }
    }

    private void handleKeyMainMenu(KeyType type) {
        if (Objects.requireNonNull(type) == KeyType.F1) {
            this.currentGame = new Game();
            this.savedGames.addGame(currentGame);
            gameUi();
        } else if (Objects.requireNonNull(type) == KeyType.F2) {
            loadUi();
        }
    }

    private void handleKeyGame(KeyType type) {
        if (Objects.requireNonNull(type) == KeyType.ArrowLeft) {
            selectLeft();
        } else if (type == KeyType.ArrowRight) {
            selectRight();
        } else if (type == KeyType.ArrowDown || type == KeyType.Enter) {
            this.currentGame.move(selectedColumn);
        }
        gameUi();
    }

    private void handleKeyLoad(KeyType type) {
        if (Objects.requireNonNull(type) == KeyType.ArrowDown) {
            loadDown();
        } else if (Objects.requireNonNull(type) == KeyType.ArrowUp) {
            loadUp();
        } else if (Objects.requireNonNull(type) == KeyType.Backspace && !savedGames.getGames().isEmpty()) {
            savedGames.removeGame(selectedGame);
            selectedGame = 0;
        } else if (Objects.requireNonNull(type) == KeyType.Enter && !savedGames.getGames().isEmpty()) {
            loadGame();
            gameUi();
            return;
        }
        loadUi();
    }

    private void gameUi() {
        screen.clear();
        currentView = "game";

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);

        char[][] board = currentGame.getBoard();

        for (int i = 0; i < board.length; i++) {
            String message = new String(board[i]);
            TerminalPosition pos = new TerminalPosition(
                    terminalSize.getColumns() / 2 - (message.length() / 2),
                    terminalSize.getRows() / 2 + i - 2);
            textGraphics.putString(pos, message);
        }
        gameDetails();
        columnPointer();
    }

    private void columnPointer() {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        char[] bar = {' ',' ',' ',' ', ' ', ' ', ' '};
        bar[selectedColumn] = '^';
        textGraphics.putString(new TerminalPosition(terminalSize.getColumns() / 2 - 3, 16),
                new String(bar));
    }

    private void gameDetails() {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);

        if (currentGame.checkGameOver()) {
            String winnerMessage = currentGame.getWinner();
            textGraphics.putString(new TerminalPosition(
                            terminalSize.getColumns() / 2 - (winnerMessage.length() / 2),
                            terminalSize.getRows() - 2),
                    winnerMessage);
        } else {
            String whosTurnMessage = currentGame.getTurn();
            textGraphics.putString(new TerminalPosition(
                            terminalSize.getColumns() / 2 - (whosTurnMessage.length() / 2),
                            terminalSize.getRows() - 2),
                    whosTurnMessage);
        }
    }

    private void loadUi() {
        screen.clear();
        currentView = "load";

        List<Game> games = savedGames.getGames();

        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);

        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            String name = "Game #" + i + " " + game.getName();
            TerminalPosition pos = new TerminalPosition(
                    terminalSize.getColumns() / 2 - (name.length() / 2),
                    terminalSize.getRows() / 2 + i - 2);
            textGraphics.putString(pos, name);
        }

        String message = games.isEmpty() ? "There are no saved games currently." : "Selected Game# " + selectedGame;

        textGraphics.putString(new TerminalPosition(
                        terminalSize.getColumns() / 2 - (message.length() / 2),
                        terminalSize.getRows() - 2),
                message);
    }

    private void selectRight() {
        if (selectedColumn + 1 <= 6) {
            selectedColumn++;
        }
    }

    private void selectLeft() {
        if (selectedColumn - 1 >= 0) {
            selectedColumn--;
        }
    }

    private void loadDown() {
        if (selectedGame + 1 < savedGames.getGames().size()) {
            selectedGame++;
        }
    }

    private void loadUp() {
        if (selectedGame - 1 >= 0) {
            selectedGame--;
        }
    }

    private void loadGame() {
        currentGame = savedGames.getGames().get(selectedGame);
    }

}
