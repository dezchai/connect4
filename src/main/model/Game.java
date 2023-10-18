package model;

import org.json.JSONObject;

import java.util.Date;

// Represents a Connect 4 board.
// Board is a 2D char array with each subarray
// containing a row from top to bottom. A play
// is represented as either an 'O' or 'X', or a space
// for neither. Methods include: play move, updateTurn,
// checking if game over / draw, getting turn, board,
// winner and name (datetime)
public class Game {
    private char[][] board = new char[6][7];
    private String turn;
    private char winner;
    private final String name;

    // EFFECTS: constructs a game with an empty board, turn being 'O', and current date as the name
    public Game() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        this.turn = "O";
        this.name = new Date().toString();
    }

    // EFFECTS: constructs a game with custom fields and checks if game is over
    public Game(String name, String turn, char[][] board) {
        this.name = name;
        this.turn = turn;
        this.board = board;
        isGameOver();
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

        board[row][col] = turn.toCharArray()[0];
        updateTurn();
        return true;
    }

    // MODIFIES: this
    // EFFECTS: switches turn from 'O' -> 'X' and vice versa
    private void updateTurn() {
        if (turn.equals("O")) {
            turn = "X";
        } else {
            turn = "O";
        }
    }

    // MODIFIES: this
    // EFFECTS: returns true if there is a win on the board
    public boolean isGameOver() {
        return overUp() || overRight() || overDiagonalLeft() || overDiagonalRight() || overDraw();
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

    // MODIFIES: this
    // EFFECTS: returns true if the game is drawn (full board with no win)
    public boolean overDraw() {
        int openSpaces = 0;
        for (char[] row : board) {
            for (char space : row) {
                if (space == ' ') {
                    openSpaces++;
                }
            }
        }
        return openSpaces == 0;
    }

    // EFFECTS: get current turn message
    public String getTurnMessage() {
        return "Player " + turn + "'s" + " turn";
    }

    // EFFECTS: returns the game board
    public char[][] getBoard() {
        return board;
    }

    // REQUIRES: isGameOver() must be called first and must have returned true
    // EFFECTS: get winner message, or if board is full and no winner, return "Draw!"
    public String getWinner() {
        return winner != 0 ? "Player " + winner + " has won!" : "Draw!";
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

    // EFFECTS: returns json object with key name and board
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("turn", turn);
        json.put("board", board);
        return json;
    }
}
