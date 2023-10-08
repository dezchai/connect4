package model;

import java.util.Date;

public class Game {
    private char[][] board = new char[6][7];
    private char turn;
    private char winner;
    private boolean gameOver;
    private final String name;

    public Game() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        this.turn = 'O';
        this.name = new Date().toString();
    }

    public boolean move(int place) {
        if (gameOver) {
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
        checkGameOver();
        updateTurn();
        return true;
    }

    private void updateTurn() {
        if (turn == 'O') {
            turn = 'X';
        } else {
            turn = 'O';
        }
    }

    public boolean checkGameOver() {
        this.gameOver = overUp() || overRight() || overDiagonalLeft() || overDiagonalRight();
        return gameOver;
    }

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

    public String getTurn() {
        return "Player " + turn + "'s" + " turn";
    }

    public char[][] getBoard() {
        return board;
    }

    public String getWinner() {
        return "Player " + winner + " has won!";
    }

    public String getName() {
        return name;
    }

    public void setBoard(char[][] b) {
        board = b;
    }
}
