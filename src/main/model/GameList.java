package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Represents a list of the Game class
// Methods include: adding a Game, removing a Game,
// getting all Games, and getting stats on those games
// i.e., how many are in progress / how many are over
public class GameList {
    private final List<Game> games;

    // EFFECTS: constructs list of games
    public GameList() {
        games = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds game to list of games
    public void addGame(Game game) {
        EventLog.getInstance().logEvent(new Event("Added Game '" + game.getName() + "'"));
        games.add(game);
    }

    // REQUIRES: !games.isEmpty() && i >= 0 && i < games.size()
    // MODIFIES: this
    // EFFECTS: removes game from given index from list of games
    public void removeGame(int i) {
        EventLog.getInstance().logEvent(new Event("Removed Game '" + games.get(i).getName() + "'"));
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

    // EFFECTS: returns games in this session as a json object in key "games"
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", savedBoardsToJson());
        return json;
    }

    // EFFECTS: returns Games as a JSON array
    private JSONArray savedBoardsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Game g: games) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }
}
