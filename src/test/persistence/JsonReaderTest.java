package persistence;

// SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Game;
import model.GameList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests the parsing of JSON files
public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGameList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGameList.json");
        try {
            GameList gameList = reader.read();
            assertEquals(0, gameList.getGames().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGameList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGameList.json");
        try {
            GameList gameList = reader.read();
            List<Game> games = gameList.getGames();
            assertEquals(2, games.size());
            assertArrayEquals(new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'O', ' ', ' ', ' ', ' ', ' ', ' '}
            }, gameList.getGames().get(0).getBoard());
            assertArrayEquals(new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'X', ' ', ' ', ' ', ' ', ' '},
                    {' ', 'O', ' ', ' ', ' ', ' ', ' '}
            }, gameList.getGames().get(1).getBoard());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
