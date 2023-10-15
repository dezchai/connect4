package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of the Game class
// Methods include: adding a Game, removing a Game,
// getting all Games, and getting stats on those games
// i.e., how many are in progress / how many are over
public class Games {
    private final List<Game> games;

    // EFFECTS: constructs list of games
    public Games() {
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds game to list of games
    public void addGame(Game game) {
        games.add(game);
    }

    // REQUIRES: !games.isEmpty() && i >= 0 && i < games.size()
    // MODIFIES: this
    // EFFECTS: removes game from given index from list of games
    public void removeGame(int i) {
        games.remove(i);
    }

    // EFFECTS: returns list of games
    public List<Game> getGames() {
        return this.games;
    }

    // EFFECTS: returns string containing stats of how many games are done or in progress
    public String getStats() {
        int complete = 0;
        int inProgress = 0;
        for (Game game: games) {
            if (game.isGameOver()) {
                complete++;
            } else {
                inProgress++;
            }
        }
        return "Complete: " + complete + " | In Progress: " + inProgress;
    }

}
