package persistence;

// SOURCE: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Game;
import model.GameList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGameList() {
        try {
            GameList gameList = new GameList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(gameList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            gameList = reader.read();
            assertEquals(0, gameList.getGames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("ExtractMethodRecommender")
    @Test
    void testWriterGeneralGameList() {
        try {
            GameList gameList = new GameList();
            Game g1 = new Game("11:32 PM", "O", new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'O', ' ', ' ', ' ', ' ', ' ', ' '}
            });
            Game g2 = new Game("11:45 PM", "O", new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'O'}
            });
            gameList.addGame(g1);
            gameList.addGame(g2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(gameList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            gameList = reader.read();
            List<Game> games = gameList.getGames();
            assertEquals(2, games.size());

            assertArrayEquals(new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'X', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'O', ' ', ' ', ' ', ' ', ' ', ' '}
            }, games.get(0).getBoard());
            assertArrayEquals(new char[][]{
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                    {' ', ' ', ' ', ' ', ' ', ' ', 'O'}
            }, games.get(1).getBoard());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

