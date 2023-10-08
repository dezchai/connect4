package model;

import java.util.ArrayList;
import java.util.List;

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
}
