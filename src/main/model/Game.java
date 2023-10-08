package model;

import java.util.Date;

public class Game {
    private char[][] board = new char[6][7];
    private char turn;
    private char winner;
    private final String name;

    // EFFECTS: constructs a game with an empty board, turn being 'O', and current date as the name
    public Game() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        this.turn = 'O';
        this.name = new Date().toString();
    }

    // REQUIRES: 0 <= place <= 6
    // MODIFIES: this
    // EFFECTS: inserts move at place (column)
    public boolean move(int place) {
        if (isGameOver()) {
            return false;
        }
        int row = -1;
        int col = -1;
        for (int i = 0; i < 6; i++) {
            if ((board[i][place] == ' ')) {
                row = i;
                col = place;
            }
        }
        if (row == -1) {
            return false;
        }

        board[row][col] = turn;
        updateTurn();
        return true;
    }

    // MODIFIES: this
    // EFFECTS: switches turn from 'O' -> 'X' and vice versa
    private void updateTurn() {
        if (turn == 'O') {
            turn = 'X';
        } else {
            turn = 'O';
        }
    }

    // MODIFIES: this
    // EFFECTS: returns true if there is a win on the board
    public boolean isGameOver() {
        return overUp() || overRight() || overDiagonalLeft() || overDiagonalRight();
    }

    // MODIFIES: this
    // EFFECTS: returns true if there is a vertical connect 4
    public boolean overUp() {
        // start at 3 because otherwise would go out of bounds
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i - 1][j]
                        && board[i - 1][j] == board[i - 2][j]
                        && board[i - 2][j] == board[i - 3][j]) {
                    this.winner = board[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: returns true if there is a horizontal connect 4
    public boolean overRight() {
        // stop at 3 otherwise would go out of bounds horizontally
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i][j + 1]
                        && board[i][j + 1] == board[i][j + 2]
                        && board[i][j + 2] == board[i][j + 3]) {
                    this.winner = board[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: returns true if there is a diagonal (down, right) win
    public boolean overDiagonalRight() {
        // stop at 2 otherwise would go out of bounds vertically
        // stop at 3 otherwise would go out of bounds horizontally
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i + 1][j + 1]
                        && board[i + 1][j + 1] == board[i + 2][j + 2]
                        && board[i + 2][j + 2] == board[i + 3][j + 3]) {
                    this.winner = board[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: returns true if there is a diagonal (down, left) win
    public boolean overDiagonalLeft() {
        // stop at 2 otherwise would go out of bounds vertically
        // start at 3 otherwise would go out of bounds horizontally
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i][j] != ' '
                        && board[i][j] == board[i + 1][j - 1]
                        && board[i + 1][j - 1] == board[i + 2][j - 2]
                        && board[i + 2][j - 2] == board[i + 3][j - 3]) {
                    this.winner = board[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    // EFFECTS: get current turn message
    public String getTurn() {
        return "Player " + turn + "'s" + " turn";
    }

    // EFFECTS: returns the game board
    public char[][] getBoard() {
        return board;
    }

    // EFFECTS: get winner message
    public String getWinner() {
        return "Player " + winner + " has won!";
    }

    // EFFECTS: get name of game (creation date)
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: override current board (for testing)
    public void setBoard(char[][] b) {
        board = b;
    }
}
