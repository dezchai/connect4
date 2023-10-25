package persistence;

import model.Game;
import model.GameList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGames(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private GameList parseGames(JSONObject jsonObject) {
        GameList gameList = new GameList();
        addGames(gameList, jsonObject);
        return gameList;
    }

    // MODIFIES: games
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addGames(GameList gameList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(gameList, nextGame);
        }
    }

    // MODIFIES: games
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addGame(GameList gameList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String turn = jsonObject.getString("turn");
        JSONArray boardJsonArray = jsonObject.getJSONArray("board");
        char[][] board = jsonBoardToCharBoard(boardJsonArray);
        Game game = new Game(name, turn, board);
        gameList.addGame(game);
    }

    // EFFECTS: converts JSON board representation to 2D char array (intended representation)
    private char[][] jsonBoardToCharBoard(JSONArray boardJsonArray) {
        char[][] board = new char[6][7];
        for (int i = 0; i < 6; i++) {
            JSONArray row = (JSONArray) boardJsonArray.get(i);
            for (int j = 0; j < 7; j++) {
                String cell = (String) row.get(j);
                board[i][j] = cell.charAt(0);
            }
        }
        return board;
    }
}
