package model;

import java.util.ArrayList;
import java.util.List;

public class Games {
    private final List<Game> games;

    public Games() {
        games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(int i) {
        games.remove(i);
    }

    public List<Game> getGames() {
        return this.games;
    }
}
